/*
 * PacienteHistorialDAO.java
 *
 * Created on 22 de noviembre de 2006, 04:25 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package mx.com.cyberdent.dao;

import java.sql.*;
import mx.com.cyberdent.objects.Historial;
import mx.com.cyberdent.objects.Paciente;
/**
 *
 * @author Jorge Alberto
 */
public class PacienteHistorialDAO {
   
   public static final String IDHISTORIAL = "idHistorial";
   public static final String IDPACIENTE = "idPaciente";
   public static final String NOMBRE = "nombreCompleto";
   public static final String TEL = "talParticular";
    
   /*private static final String SELECT = "SELECT * FROM " +
            MySQLDAOFactory.HISTORIALFAMILIAR + ";";*/
    
   private static final String EXISTS = "SELECT * FROM " +
            MySQLDAOFactory.PAC_HIST + " WHERE " + IDPACIENTE + " = ?;";
   
    //atributo conexion
    private Connection conn;
    
    //constructor, solo asigna la referencia al objeto de la clase
    /** Creates a new instance of PacienteHistorialDAO */
    public PacienteHistorialDAO(Connection conn) {
        setConnection(conn);
    }
    public Connection getConnection() {
        return conn;
    }
    
    public void setConnection(Connection conn) {
        this.conn = conn;
    }
    
    public boolean exists(String id){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            getConnection().setAutoCommit(false);
            try {
                pstmt = getConnection().prepareStatement(EXISTS);
                pstmt.setString(1, id);
                rs = pstmt.executeQuery();
                getConnection().commit();
                if (rs.next()) {
                    return true;
                }
                return false;
            } catch (SQLException sqle) {
                getConnection().rollback();
                sqle.printStackTrace();
            } finally {
                getConnection().setAutoCommit(true);

                // Free resources
                if (pstmt != null) {
                    try {
                        pstmt.close();
                    } catch (SQLException sqle) {

                    } finally {
                        pstmt = null;
                    }
                }

            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return false;
    }
     
     private void loadObject(Historial historial, ResultSet rs) throws SQLException{
        //DUDA SOBRE EL ID
        /*historial.setIdHistorial(rs.getString(1));
        historial.setIdPaciente(rs.getString(2));
        historial.setIdUsuario(rs.getString(3));*/
    }
     
     public Historial find(String id){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Historial historial = null;
        try {
            getConnection().setAutoCommit(false);
            try {
                pstmt = getConnection().prepareStatement(EXISTS);
                pstmt.setString(1,id);
                rs = pstmt.executeQuery();
                getConnection().commit();
                if (rs.next()) {
                    historial = new Historial();
                    loadObject(historial, rs);
                    return historial;
                }
                return historial;
            } catch (SQLException sqle) {
                getConnection().rollback();
                sqle.printStackTrace();
            } finally {
                getConnection().setAutoCommit(true);

                // Free resources
                if (pstmt != null) {
                    try {
                        pstmt.close();
                    } catch (SQLException sqle) {

                    } finally {
                        pstmt = null;
                    }
                }

            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        //Should never be reached
        return null;
    }
}