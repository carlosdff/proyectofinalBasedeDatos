/*
 * HistorialDAO.java
 *
 * Created on 15 de noviembre de 2006, 09:41 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package mx.com.cyberdent.dao;

import java.sql.*;
import mx.com.cyberdent.objects.Historial;
/**
 *
 * @author Jorge Alberto
 */
public class HistorialDAO {
    
   public static final String ID = "idHistorial";
   public static final String IDPACIENTE = "idPaciente";
   public static final String IDUSUARIO = "idUsuario";
    
   private static final String INSERT = "INSERT INTO " +
            MySQLDAOFactory.HISTORIAL + " ( " + IDPACIENTE + ", " + IDUSUARIO + ")" + " VALUES "
            + "(?, ?);";
    
   private static final String SELECT = "SELECT * FROM " +
            MySQLDAOFactory.HISTORIAL + " WHERE " + ID + "=?;";
    
    private static final String EXISTS = "SELECT * FROM " +
            MySQLDAOFactory.HISTORIAL + " WHERE " + IDPACIENTE + " = ?;";
   
    //atributo conexion
    private Connection conn;
    
    //constructor, solo asigna la referencia al objeto de la clase
    
    /** Creates a new instance of DentistaDAO */
    public HistorialDAO(Connection conn) {
        
        setConnection(conn);
    }
    
    public Connection getConnection() {
        return conn;
    }
    
    public void setConnection(Connection conn) {
        this.conn = conn;
    }
    
     public void create(Historial historial){
        
        PreparedStatement pstmt = null;
        ResultSet rs=null;
        /*String idHistorial = null;
        final String SELECT_ID = "SELECT idHistorial FROM " + MySQLDAOFactory.HISTORIAL + 
                " WHERE " + IDPACIENTE +" = ?;";*/
        
        try {
            getConnection().setAutoCommit(false);
            try {
                pstmt = getConnection().prepareStatement(INSERT);
                pstmt.setString(1, historial.getIdPaciente());
                pstmt.setString(2, historial.getIdUsuario());
                pstmt.executeUpdate();
                getConnection().commit();
                /*pstmt = null;
                pstmt = getConnection().prepareStatement(SELECT_ID);
                pstmt.setString(1,historial.getIdPaciente());
                pstmt.executeQuery();
                idHistorial=rs.getString(1);
                getConnection().commit();
                return idHistorial;*/
                /*if(rs.next()){
                    idHistorial=rs.getString(1);
                    return idHistorial;
                }
                getConnection().commit();
                return idHistorial;*/
            } catch (SQLException sqle) {
                getConnection().rollback();
                sqle.printStackTrace();
                //return idHistorial;
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
        //return idHistorial;
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
        historial.setIdHistorial(rs.getString(1));
        historial.setIdPaciente(rs.getString(2));
        historial.setIdUsuario(rs.getString(3));
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

    public Historial findIDHist(String id){
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            Historial historial = null;
            try {
                getConnection().setAutoCommit(false);
                try {
                    pstmt = getConnection().prepareStatement(SELECT);
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