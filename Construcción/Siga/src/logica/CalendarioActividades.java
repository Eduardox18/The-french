/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import datos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import presentacion.Dialogo;

/**
 *
 * @author José Andrés Domínguez González
 */
public class CalendarioActividades {
    
    public LocalDate recuperarFechaLimiteExamen (int nrcCurso) {
        PortafolioEvidencias portafolio = new PortafolioEvidencias();
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet rs = null;
        
        try {
            conexion = new Conexion().connection();
            String consulta = "select fechaLimiteExamen from "
                + "portafolioEvidencias, calendarioActividades where "
                + "portafolioEvidencias.CalendarioActividades_idCalendarioActividades "
                + "= calendarioActividades.noCalendarioActividades and "
                + "portafolioEvidencias.idportafolioEvidencias  = ?;";
            sentencia = conexion.prepareStatement(consulta);
            sentencia.setInt(1, nrcCurso);
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
