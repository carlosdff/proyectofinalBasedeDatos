/*
 * HistorialFamiliarDAO.java
 *
 * Created on 15 de noviembre de 2006, 09:30 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package mx.com.cyberdent.dao;

import java.sql.*;
import mx.com.cyberdent.objects.HistorialFamiliar;

/**
 *
 * @author Jorge Alberto
 */
public class HistorialFamiliarDAO {
    
   public static final String ANTECEDENTESFAMILIARES = "antecedentes";
   public static final String ID = "idHistorial";
    
   private static final String INSERT = "INSERT INTO " +
            MySQLDAOFactory.HISTORIALFAMILIAR + " VALUES ( ?, ?);";
    
    private static final String UPDATE = "UPDATE " + MySQLDAOFactory.HISTORIALFAMILIAR + " SET " + ANTECEDENTESFAMILIARES +
            "=? WHERE " + ID + " = ?;";
    
    private static final String EXISTS = "SELECT * FROM " +
            MySQLDAOFactory.HISTORIALFAMILIAR + " WHERE " + ID + " = ?;";
   
    private static final String SELECT_ANTECEDENTESFAMILIARES = "SELECT " + ANTECEDENTESFAMILIARES + " FROM " +
            MySQLDAOFactory.HISTORIALFAMILIAR + " WHERE " + ID + " = ?;";  
    
    //atributo conexion
    private Connection conn;
    
    //constructor, solo asigna la referencia al objeto de la clase
    
    /** Creates a new instance of DentistaDAO */
    public HistorialFamiliarDAO(Connection conn) {
        
        setConnection(conn);
    }
    
    public Connection getConnection() {
        return conn;
    }
    
    public void setConnection(Connection conn) {
        this.conn = conn;
    }
    
     public void create(HistorialFamiliar historialfamiliar){
        
        PreparedStatement pstmt = null;
        try {
            getConnection().setAutoCommit(false);
            try {
                pstmt = getConnection().prepareStatement(INSERT);
                pstmt.setString(1, historialfamiliar.getAntecedentesFamiliares());
                pstmt.setString(2, historialfamiliar.getIdHistorialFamiliar());
                pstmt.executeUpdate();
                getConnection().commit();
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
     
     private void loadObject(HistorialFamiliar historialfamiliar, ResultSet rs) throws SQLException{
        //DUDA SOBRE EL ID
        historialfamiliar.setAntecedentesFamiliares(rs.getString(1));
        historialfamiliar.setIdHistorialFamiliar(rs.getString(2)); 
        
    }
     
     public HistorialFamiliar find(String id){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        HistorialFamiliar historialfamiliar = null;
        try {
            getConnection().setAutoCommit(false);
            try {
                pstmt = getConnection().prepareStatement(EXISTS);
                pstmt.setString(1,id);
                rs = pstmt.executeQuery();
                getConnection().commit();
                if (rs.next()) {
                    historialfamiliar = new HistorialFamiliar();
                    loadObject(historialfamiliar, rs);
                    return historialfamiliar;
                }
                return historialfamiliar;
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
     
    public void update(HistorialFamiliar historialfamiliar){
        PreparedStatement pstmt = null;
        try {
            getConnection().setAutoCommit(false);
            try {
                pstmt = getConnection().prepareStatement(UPDATE);

                pstmt.setString(1, historialfamiliar.getAntecedentesFamiliares());
                pstmt.setString(2, historialfamiliar.getIdHistorialFamiliar());
                pstmt.executeUpdate();
                getConnection().commit();
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
    }
}
