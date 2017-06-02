package logica;

import datos.Conexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import presentacion.Dialogo;

/**
 * Clase que contiene los métodos relacionados con los calendarios de actividades del Sistema. La clase implementa
 * los métodos de la interface CalendarioActividadesDAO e implementa otros utilizados para la funcionalidad
 * correcta del programa.
 */
public class CalendarioActividades {
    
    private int noCalendario;
    private Date fechaLimiteExamen;
    private String materialReportar;
    private String convCalendarioActividades;
    
    /**
     * Constructor vacío de la clase. Permite crear objetos tipo CalendarioActividades.
     */
    public CalendarioActividades() {}
    
    /**
     * Constructor completo de la clase. Permite crear objetos tipo CalendarioActividades.
     * @param noCalendario
     * @param fechaLimiteExamen
     * @param materialReportar
     * @param convCalendarioActividades 
     */
    public CalendarioActividades(int noCalendario, Date fechaLimiteExamen, String materialReportar,
        String convCalendarioActividades) {
        this.noCalendario = noCalendario;
        this.fechaLimiteExamen = fechaLimiteExamen;
        this.materialReportar = materialReportar;
        this.convCalendarioActividades = convCalendarioActividades;
        
    }
    
    /**
     * Método que permite recuperar la fecha límite para entregar el examen del curso.
     * @param nrcCurso NRC del curso del que se desea saber la fecha límite del examen.
     * @return Regresa la fecha límite del examen.
     */
    public LocalDate recuperarFechaLimiteExamen (int nrcCurso) {
        PortafolioEvidencias portafolio = new PortafolioEvidencias();
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet rs = null;
        int idPortafolio;
        idPortafolio = portafolio.recuperarIDPortafolio(nrcCurso);
        
        try {
            conexion = new Conexion().connection();
            String consulta = "select fechaLimiteExamen from "
                + "portafolioEvidencias, calendarioActividades where "
                + "portafolioEvidencias.CalendarioActividades_idCalendarioActividades "
                + "= calendarioActividades.noCalendarioActividades and "
                + "portafolioEvidencias.idportafolioEvidencias  = ?;";
            sentencia = conexion.prepareStatement(consulta);
            sentencia.setInt(1, idPortafolio);
            rs = sentencia.executeQuery();

            if (rs.next()) {
                return rs.getDate("fechaLimiteExamen").toLocalDate();
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
        return null;
    }
    
}
