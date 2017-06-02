package logica;

import datos.ActividadDAO;
import datos.Conexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import presentacion.Dialogo;

/**
 * Clase que contiene los métodos relacionados con las actividades del Sistema. La clase implementa
 * los métodos de la interface ActividadDAO e implementa otros utilizados para la funcionalidad
 * correcta del programa.
 */
public class Actividad implements ActividadDAO {

    /**
     * Atributos de la clase.
     */
    private String nombreActividad;
    private String tipoActividad;
    private Time horaActividad;
    private Date diaActividad;
    private String asesorActividad;

    /**
     *
     * Constructor vacio de la clase. Permite crear objetos tipo Actividad.
     */
    public Actividad() {}

    /**
     * Constructor completo de la clase. Permite crear objetos Actividad.
     *
     * @param nombre Nombre de la actividad.
     * @param tipoActividad Tipo de la actividad (Asesoría, Conversación o Taller de lectura).
     * @param horaActividad Hora de la actividad.
     * @param diaActividad Fecha en que se impartirá la actividad.
     * @param asesorActividad Asesor encargado de impartir la actividad (Asesor del curso al que
     * pertenece la actividad).
     */
    public Actividad(String nombre, String tipoActividad, Time horaActividad, Date diaActividad,
        String asesorActividad) {
        this.nombreActividad = nombre;
        this.tipoActividad = tipoActividad;
        this.horaActividad = horaActividad;
        this.diaActividad = diaActividad;
        this.asesorActividad = asesorActividad;
    }

    /**
     * Bloque de Getters y Setters de la clase. Su documentación no es necesaria.
     */
    public String getNombreActividad() {
        return nombreActividad;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }

    public String getTipoActividad() {
        return tipoActividad;
    }

    public void setTipoActividad(String tipoActividad) {
        this.tipoActividad = tipoActividad;
    }

    public Time getHoraActividad() {
        return horaActividad;
    }

    public void setHoraActividad(Time horaActividad) {
        this.horaActividad = horaActividad;
    }

    public Date getDiaActividad() {
        return diaActividad;
    }

    public void setDiaActividad(Date diaActividad) {
        this.diaActividad = diaActividad;
    }

    public String getAsesorActividad() {
        return asesorActividad;
    }

    public void setAsesorActividad(String asesorActividad) {
        this.asesorActividad = asesorActividad;
    }

    /**
     *
     * Método sobreescrito que recupera un ObservableList de objetos Actividad, a partir del NRC del
     * curso del que se interesa recuperar actividades y la fecha interesada.
     *
     * @param nrcCurso NRC del curso que se desea buscar
     * @param diaActividad Fecha en la que se desean buscar las actividades
     * @return Regresa un ObservableList de objetos Actividad, que satisfacen todas las
     * coincidencias de la búsqueda.
     */
    @Override
    public ObservableList<Actividad> consultarActividades(int nrcCurso, Date diaActividad) {
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet rs = null;
        Actividad actividadResultado;
        ObservableList<Actividad> listaActividades = FXCollections.observableArrayList();

        try {
            conexion = new Conexion().connection();
            String consulta = "SELECT actividad.nombreActividad, CONCAT (asesor.nombreAsesor,' ', "
                + "asesor.apPaternoAsesor, ' ', asesor.apMaternoAsesor) AS asesorActividad, "
                + "actividad.tipoActividad, actividad.horaActividad FROM actividad, curso, "
                + "asesor WHERE curso.nrcCurso = ? AND curso.nrcCurso = "
                + "actividad.curso_nrcCurso AND curso.asesor_noPersonalAsesor = "
                + "asesor.noPersonalAsesor AND actividad.diaActividad = ?";
            sentencia = conexion.prepareStatement(consulta);
            sentencia.setInt(1, nrcCurso);
            sentencia.setDate(2, diaActividad);
            rs = sentencia.executeQuery();

            while (rs.next()) {
                actividadResultado = new Actividad();
                actividadResultado.setNombreActividad(rs.getString("nombreActividad"));
                actividadResultado.setAsesorActividad(rs.getString("asesorActividad"));
                actividadResultado.setTipoActividad(rs.getString("tipoActividad"));
                actividadResultado.setHoraActividad(rs.getTime("horaActividad"));
                listaActividades.add(actividadResultado);
            }
        } catch (SQLException ex) {
            Dialogo dialogo = new Dialogo();
            dialogo.alertaError();
        } finally {
            Dialogo dialogo = new Dialogo();
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    dialogo.alertaCerrarConexion();
                }
            }
            if (sentencia != null) {
                try {
                    sentencia.close();
                } catch (SQLException ex) {
                    dialogo.alertaCerrarConexion();
                }
            }
            if (conexion != null) {
                try {
                    conexion.close();
                } catch (SQLException ex) {
                    dialogo.alertaCerrarConexion();
                }
            }
        }
        return listaActividades;
    }

    /**
     * Método sobreescrito que permite consultar las actividades del curso a las que asistió un
     * alumno. Esto se recupera de las que ya haya reservado anteriormente y que el asesor haya
     * confirmado su asistencia.
     *
     * @param nrcCurso NRC del curso que se desea buscar.
     * @param matriculaAlumno Matrícula del alumno del que se desea recuperar las actividades a las
     * que asistió.
     * @return Regresa un ObservableList de objetos Actividad que satisfacen todas las coincidencias
     * de búsqueda.
     */
    @Override
    public ObservableList<Actividad> consultarActividadesAsistidas(int nrcCurso,
        String matriculaAlumno) {
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet rs = null;
        Actividad actividadResultado;
        ObservableList<Actividad> listaActividadesAsistidas = FXCollections.
            observableArrayList();
        try {
            conexion = new Conexion().connection();
            String consulta = "SELECT nombreActividad, diaActividad FROM "
                + "actividad, asistenciaActividad, reservacion WHERE "
                + "asistenciaActividad.reservacion_noReservacion = "
                + "reservacion.noReservacion and actividad.idActividad = "
                + "reservacion.actividad_idActividad and "
                + "actividad.curso_nrcCurso = ? and "
                + "reservacion.alumno_matriculaAlumno = ? AND "
                + "asistenciaActividad.presencia = 1";
            sentencia = conexion.prepareStatement(consulta);
            sentencia.setInt(1, nrcCurso);
            sentencia.setString(2, matriculaAlumno);
            rs = sentencia.executeQuery();

            while (rs.next()) {
                actividadResultado = new Actividad();
                actividadResultado.setNombreActividad(rs.getString(
                    "nombreActividad"));
                actividadResultado.setDiaActividad(rs.getDate("diaActividad"));
                listaActividadesAsistidas.add(actividadResultado);
            }
        } catch (SQLException ex) {
            Dialogo dialogo = new Dialogo();
            dialogo.alertaError();
        } finally {
            Dialogo dialogo = new Dialogo();
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    dialogo.alertaCerrarConexion();
                }
            }
            if (sentencia != null) {
                try {
                    sentencia.close();
                } catch (SQLException ex) {
                    dialogo.alertaCerrarConexion();
                }
            }
            if (conexion != null) {
                try {
                    conexion.close();
                } catch (SQLException ex) {
                    dialogo.alertaCerrarConexion();
                }
            }
        }
        return listaActividadesAsistidas;
    }

    /**
     * Método que devuelve el ID solicitado de una Actividad existente en la base de datos del
     * Sistema.
     *
     * @return Regresa el ID de la Actividad solicitada, según su nombre y la hora en que se
     * impartirá.
     */
    public int obtenerIDActividad() {
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet rs = null;

        try {
            conexion = new Conexion().connection();
            String consulta = "SELECT idActividad FROM actividad WHERE nombreActividad = ?"
                + "AND horaActividad = ?";
            sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, nombreActividad);
            sentencia.setTime(2, horaActividad);
            rs = sentencia.executeQuery();

            if (rs.next()) {
                return rs.getInt("idActividad");
            }

        } catch (SQLException e) {
            Dialogo dialogo = new Dialogo();
            dialogo.alertaError();
            return 0;
        } finally {
            Dialogo dialogo = new Dialogo();
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    dialogo.alertaCerrarConexion();
                }
            }
            if (sentencia != null) {
                try {
                    sentencia.close();
                } catch (SQLException ex) {
                    dialogo.alertaCerrarConexion();
                }
            }
            if (conexion != null) {
                try {
                    conexion.close();
                } catch (SQLException ex) {
                    dialogo.alertaCerrarConexion();
                }
            }
        }
        return 0;
    }
}
