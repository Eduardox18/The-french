package logica;

import datos.ActividadRealizadaDAO;
import datos.Conexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import presentacion.Dialogo;

/**
 * Clase que contiene los métodos relacionados con las actividades escritas realizadas del Sistema.
 * La clase implementa los métodos de la interface ActividadRealizadaDAO e implementa otros
 * utilizados para la funcionalidad correcta del programa.
 */
public class ActividadRealizada implements ActividadRealizadaDAO {

    /**
     * Atributos de la clase. 
     */
    private String tipoARealizada;
    private String nombreARealizada;
    private Date fechaARealizada;
    private Integer tiempoEmpleado;

    /**
     * Constructor vacío de la clase. Permite crear objetos tipo ActividadRealizada.
     */
    public ActividadRealizada() {
    }

    /**
     * Constructor completo de la clase. Permite crear objetos tipo ActividadRealizada.
     *
     * @param tipoARealizada Tipo de la actividad realizada.
     * @param nombreARealizada Nombre de la actividad realizada.
     * @param fechaARealizada Fecha en que se realizó la actividad.
     * @param tiempoEmpleado Tiempo empleado en la actividad que se realizó.
     */
    public ActividadRealizada(String tipoARealizada, String nombreARealizada, Date fechaARealizada,
        Integer tiempoEmpleado) {
        this.tipoARealizada = tipoARealizada;
        this.nombreARealizada = nombreARealizada;
        this.fechaARealizada = fechaARealizada;
        this.tiempoEmpleado = tiempoEmpleado;
    }

    /**
     * Bloque de Getters y Setters de la clase. Su documentación no es necesaria.
     *
     */
    public String getTipoARealizada() {
        return tipoARealizada;
    }

    public void setTipoARealizada(String tipoARealizada) {
        this.tipoARealizada = tipoARealizada;
    }

    public String getNombreARealizada() {
        return nombreARealizada;
    }

    public void setNombreARealizada(String nombreARealizada) {
        this.nombreARealizada = nombreARealizada;
    }

    public Date getFechaARealizada() {
        return fechaARealizada;
    }

    public void setFechaARealizada(Date fechaARealizada) {
        this.fechaARealizada = fechaARealizada;
    }

    public boolean guardarARealizada(ActividadRealizada aRealizada) {
        return false;
    }

    public Integer getTiempoEmpleado() {
        return tiempoEmpleado;
    }

    public void setTiempoEmpleado(Integer tiempoEmpleado) {
        this.tiempoEmpleado = tiempoEmpleado;
    }

    /**
     * Método sobreescrito que permite consultar la lista de actividades realizadas por el alumno en
     * un curso específico.
     *
     * @param nrcCurso NRC del curso que se desea buscar.
     * @param matriculaAlumno Matrícula del alumno del que se está interesado.
     * @return Regresa un ObservableList de objetos tipo ActividadRealizada que satisfacen todas las
     * coincidencias de búsqueda.
     */
    @Override
    public ObservableList<ActividadRealizada> obtenerActividadRealizada(int nrcCurso,
        String matriculaAlumno) {
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet rs = null;
        ActividadRealizada actividadResultado;
        ObservableList<ActividadRealizada> listaActividadesRealizadas
            = FXCollections.observableArrayList();

        try {
            conexion = new Conexion().connection();
            String consulta = "SELECT nombreAEscrita, tipoAEscrita, "
                + "fechaRealizada, tiempoDedicado FROM actividadEscrita, "
                + "actividadRealizada, portafolioEvidencias WHERE "
                + "actividadRealizada.actividadEscrita_codigoAEscrita = "
                + "actividadEscrita.codigoAEscrita and "
                + "portafolioEvidencias.idPortafolioEvidencias = "
                + "actividadRealizada.portafolioEvidencias_idportafolioEvidencias "
                + "and actividadRealizada.alumno_matriculaAlumno = ? "
                + "and actividadEscrita.curso_nrcCurso = ?";
            sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, matriculaAlumno);
            sentencia.setInt(2, nrcCurso);
            rs = sentencia.executeQuery();

            while (rs.next()) {
                actividadResultado = new ActividadRealizada();
                actividadResultado.setNombreARealizada(rs.getString("nombreAEscrita"));
                actividadResultado.setTipoARealizada(rs.getString("tipoAEscrita"));
                actividadResultado.setTiempoEmpleado(rs.getInt("tiempoDedicado"));
                actividadResultado.setFechaARealizada(rs.getDate("fechaRealizada"));
                listaActividadesRealizadas.add(actividadResultado);
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
        return listaActividadesRealizadas;
    }
}
