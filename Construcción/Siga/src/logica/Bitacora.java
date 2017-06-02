package logica;

import datos.BitacoraDAO;
import datos.Conexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import presentacion.Dialogo;

/**
 * Clase que contiene los métodos relacionados con las bitácoras del Sistema. La clase implementa
 * los métodos de la interface BitacoraDAO e implementa otros utilizados para la funcionalidad
 * correcta del programa.
 */
public class Bitacora implements BitacoraDAO {

    /**
     * Atributos de la clase
     */
    private Integer tiempoEmpleado;
    private String comentario;
    private Date fechaBitacora;
    private int idPortafolioEvidencias;
    private int idAutoevaluacion;

    /**
     * Constructor vacío de la clase. Permite crear objetos tipo Bitacora.
     */
    public Bitacora() {}

    /**
     * Constructor completo de la clase. Permite crear objetos tipo bitácora.
     *
     * @param tiempoEmpleado Tiempo empleado en realizar la bitácora.
     * @param comentario Comentario sobre la bitácora.
     * @param fechaBitacora Fecha en que se realizó la bitácora.
     * @param idPortafolioEvidencias: ID del portafolio de evidencias donde se almacenará la
     * bitácora
     */
    public Bitacora(Integer tiempoEmpleado, String comentario,
        Date fechaBitacora, int idPortafolioEvidencias, int idAutoevaluacion) {
        this.tiempoEmpleado = tiempoEmpleado;
        this.comentario = comentario;
        this.fechaBitacora = fechaBitacora;
        this.idPortafolioEvidencias = idPortafolioEvidencias;
        this.idAutoevaluacion = idAutoevaluacion;
    }

    /**
     * Bloque de Getters y Setters de la clase. Su documentación no es necesaria.
     */
    public Integer getTiempoEmpleado() {
        return tiempoEmpleado;
    }

    public void setTiempoEmpleado(Integer tiempoEmpleado) {
        this.tiempoEmpleado = tiempoEmpleado;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Date getFechaBitacora() {
        return fechaBitacora;
    }

    public void setFechaBitacora(Date fechaBitacora) {
        this.fechaBitacora = fechaBitacora;
    }

    public int getIdPortafolioEvidencias() {
        return idPortafolioEvidencias;
    }

    public void setIdPortafolioEvidencias(int idPortafolioEvidencias) {
        this.idPortafolioEvidencias = idPortafolioEvidencias;
    }

    public int getIdAutoevaluacion() {
        return idAutoevaluacion;
    }

    public void setIdAutoevaluacion(int idAutoevaluacion) {
        this.idAutoevaluacion = idAutoevaluacion;
    }

    /**
     *
     * Método sobreescrito encargado de agregar una nueva bitácora a la base de datos del Sistema.
     *
     * @param bitacora Objeto de tipo Bitacora de donde se obtienen todos los datos.
     * @return Regresa verdadero(true) si el registro de la bitácora fue exitosa o falso (false) si
     * ocurrió un error al registrar la bitácora.
     */
    @Override
    public boolean registrarBitacora(Bitacora bitacora) {
        Connection conexion = null;
        PreparedStatement sentencia = null;

        try {
            conexion = new Conexion().connection();
            String consultaBitacora = "INSERT INTO Bitacora (tiempoEmpleado, "
                + "comentario, fechaBitacora, "
                + "portafolioEvidencias_idportafolioEvidencias, "
                + "autoevaluacion_idAutoevaluacion) VALUES (?, ?, ?, ?, ?);";
            sentencia = conexion.prepareStatement(consultaBitacora);
            sentencia.setInt(1, bitacora.getTiempoEmpleado());
            sentencia.setString(2, bitacora.getComentario());
            sentencia.setDate(3, bitacora.getFechaBitacora());
            sentencia.setInt(4, bitacora.getIdPortafolioEvidencias());
            sentencia.setInt(5, bitacora.getIdAutoevaluacion());

            sentencia.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Dialogo dialogo = new Dialogo();
            dialogo.alertaError();
        } finally {
            Dialogo dialogo = new Dialogo();
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
        return false;
    }

    /**
     * Método encargador de recuperar el último número de bitácora agregado al Sistema y sumarle uno
     * por si se desea registrar una nueva bitácora y ver qué número le corresponde.
     *
     * @return Número de la nueva bitácora a registrar en el Sistema.
     */
    public int recuperarNoActualBitacora() {
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet rs = null;

        try {
            conexion = new Conexion().connection();
            String consulta = "SELECT IFNULL((SELECT MAX(noBitacora) FROM "
                + "bitacora), 0) AS noBitacora;";
            sentencia = conexion.prepareStatement(consulta);
            rs = sentencia.executeQuery();

            if (rs.next()) {
                return rs.getInt("noBitacora") + 1;
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
        return 0;
    }

    /**
     * Método encargado de recuperar el último número de bitácora agregado al Sistema para poder
     * guardarlo posteriormente en las tablas que lo utilizan.
     *
     * @return Último número de bitácora agregado al Sistema.
     */
    public int recuperarUltimoNoBitacora() {
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet rs = null;

        try {
            conexion = new Conexion().connection();
            String consulta = "SELECT MAX(noBitacora) FROM bitacora";
            sentencia = conexion.prepareStatement(consulta);
            rs = sentencia.executeQuery();

            if (rs.next()) {
                return rs.getInt("MAX(noBitacora)");
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
        return 0;
    }

    /**
     * Método encargado recuperar las actividades a las que asistió el alumno para posteriormente
     * hacer un registro con el número de bitácora que le corresponde.
     *
     * @param nrcCurso NRC del curso en el que se registró la bitácora.
     * @return Regresa verdadero(true) si el registro fue un éxito o falso(false) si ocurrió algún
     * error.
     */
    public boolean guardarActividadesAsistidas(int nrcCurso) {
        Usuario usuario = new Usuario();
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet rs = null;

        try {
            conexion = new Conexion().connection();
            String consulta = "SELECT idasistenciaActividad FROM actividad, "
                + "asistenciaActividad, reservacion WHERE "
                + "asistenciaActividad.reservacion_noReservacion = "
                + "reservacion.noReservacion AND actividad.idActividad = "
                + "reservacion.actividad_idActividad AND "
                + "actividad.curso_nrcCurso = ? AND "
                + "reservacion.alumno_matriculaAlumno = ? AND "
                + "asistenciaActividad.presencia = 1";
            sentencia = conexion.prepareStatement(consulta);
            sentencia.setInt(1, nrcCurso);
            sentencia.setString(2, usuario.getUsuarioActual());
            rs = sentencia.executeQuery();

            while (rs.next()) {
                updateActividadesAsistidas(rs.getInt("idasistenciaActividad"));
            }
            return true;
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
        return false;
    }

    /**
     * Método encargado de guardar la relación entre las actividades a las que asistió el alumno y
     * el número de bitácora que le corresponde. Se guarda para actualizaciones futuras del
     * programa.
     *
     * @param idAAsistidas ID de la actividad a la que asistió el alumno.
     */
    private void updateActividadesAsistidas(int idAAsistidas) {
        Connection conexion = null;
        PreparedStatement sentencia = null;

        try {
            conexion = new Conexion().connection();
            String update = "INSERT INTO actividadesPresencialesBitacora "
                + "VALUES (?, ?)";
            sentencia = conexion.prepareStatement(update);
            sentencia.setInt(1, recuperarUltimoNoBitacora());
            sentencia.setInt(2, idAAsistidas);
            sentencia.executeUpdate();
        } catch (SQLException ex) {
            Dialogo dialogo = new Dialogo();
            dialogo.alertaError();
        } finally {
            Dialogo dialogo = new Dialogo();
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
    }

    /**
     * Método encargado recuperar las actividades escritas que completó el alumno para
     * posteriormente hacer un registro con el número de bitácora que le corresponde.
     *
     * @param nrcCurso NRC del curso al que pertenecen las actividades escritas.
     * @return Regresa verdadero(true) si el registro fue un éxito o falso(false) si ocurrió algún
     * error.
     */
    public boolean guardarActividadesEscritas(int nrcCurso) {
        Usuario usuario = new Usuario();
        PortafolioEvidencias pEvidencias = new PortafolioEvidencias();
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet rs = null;

        try {
            conexion = new Conexion().connection();
            String update = "SELECT idARealizada FROM actividadRealizada "
                + "WHERE alumno_matriculaAlumno = ? AND "
                + "portafolioEvidencias_idportafolioEvidencias = ?";
            sentencia = conexion.prepareStatement(update);
            sentencia.setString(1, usuario.getUsuarioActual());
            sentencia.setInt(2, pEvidencias.recuperarIDPortafolio(nrcCurso));
            rs = sentencia.executeQuery();

            while (rs.next()) {
                updateActividadesEscritas(rs.getInt("idARealizada"));
            }
            return true;
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
        return false;
    }

    /**
     * Método encargado de guardar la relación entre las actividades escritas que realizó el alumno
     * y el número de bitácora que le corresponde. Se guarda para actualizaciones futuras del
     * programa.
     *
     * @param idAEscritas ID de las actividades escritas que realizó el alumno.
     */
    private void updateActividadesEscritas(int idAEscritas) {
        Connection conexion = null;
        PreparedStatement sentencia = null;

        try {
            conexion = new Conexion().connection();
            String update = "INSERT INTO actividadesEscritasBitacora "
                + "VALUES (?, ?)";
            sentencia = conexion.prepareStatement(update);
            sentencia.setInt(1, idAEscritas);
            sentencia.setInt(2, recuperarUltimoNoBitacora());
            sentencia.executeUpdate();
        } catch (SQLException ex) {
            Dialogo dialogo = new Dialogo();
            dialogo.alertaError();
        } finally {
            Dialogo dialogo = new Dialogo();
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
    }
    
    /**
     * 
     * Método que devuelve la fecha actual del sistema en formato YY-MM-DD
     * @return LocalDate con la fecha actual del sistema 
     */
    public LocalDate fechaActual () {
        java.util.Date fecha = new java.util.Date();
        Instant instante = Instant.ofEpochMilli(fecha.getTime());
        return LocalDateTime.ofInstant(instante, ZoneId.systemDefault())
        .toLocalDate();
    }

    /**
     * Método encargado de comprobar si la bitácora registrada del alumno en el curso ya existe.
     *
     * @param nrcCurso NRC del curso que se desea buscar.
     * @return Regresa verdadero(true) si ya existe la bitácora del alumno en ese curso o falso
     * (false) si es que no existe.
     */
    public boolean comprobarBitacoraExistente(int nrcCurso) {
        Usuario usuario = new Usuario();
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet rs = null;

        try {
            conexion = new Conexion().connection();
            String consulta = "SELECT DISTINCT noBitacora FROM bitacora, "
                + "portafolioEvidencias, inscripcion, grupoAlumno, curso "
                + "WHERE bitacora.portafolioEvidencias_idportafolioEvidencias "
                + "= portafolioEvidencias.idportafolioEvidencias AND "
                + "portafolioEvidencias.inscripcion_idinscripcion = "
                + "inscripcion.idinscripcion AND "
                + "inscripcion.alumno_matriculaAlumno = ? AND "
                + "inscripcion.grupoAlumno_idGrupoAlumno = "
                + "grupoAlumno.idGrupoAlumno AND grupoAlumno.curso_nrcCurso "
                + "= ?";
            sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, usuario.getUsuarioActual());
            sentencia.setInt(2, nrcCurso);
            rs = sentencia.executeQuery();

            return rs.next();

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
        return false;
    }

}
