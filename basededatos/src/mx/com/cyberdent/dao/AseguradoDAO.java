/*
 * AseguradoDAO.java
 *
 * Created on 19 de octubre de 2006, 05:22 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package mx.com.cyberdent.dao;

import java.sql.*;
import mx.com.cyberdent.objects.Asegurado;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author Usuario
 */
public class AseguradoDAO {
    
    public static final String ID = "idPaciente";
    public static final String NOMBRE = "nombreSeguro";
    public static final String NUMC = "numCredencial";
    
    private static final String INSERT = "INSERT INTO " +
            MySQLDAOFactory.ASEGURADO + " values " + "( ?, ?, ?);";
    
    private static final String UPDATE = "UPDATE " + MySQLDAOFactory.ASEGURADO +
            " SET " + NOMBRE + " = ?, " + NUMC + "= ? WHERE " + ID + " = ?;";
     
    private static final String SELECT = "SELECT * FROM " +
            MySQLDAOFactory.ASEGURADO + ";";
    
    private static final String EXISTS = "SELECT * FROM " +
            MySQLDAOFactory.ASEGURADO +
            " WHERE " + ID + " = ?;";
     
     private Connection conn;
    
    //constructor, solo asigna la referencia al objeto de la clase
    public AseguradoDAO(Connection conn) {
        
        setConnection(conn);
    }
    
    public Connection getConnection() {
        return conn;
    }
    
    public void setConnection(Connection conn) {
        this.conn = conn;
    }
    
    public void create(Asegurado asegurado){
        
        PreparedStatement pstmt = null;
        try {
            getConnection().setAutoCommit(false);
            try {
                pstmt = getConnection().prepareStatement(INSERT);
                
                pstmt.setString(1, asegurado.getNombreSeguro());
                pstmt.setString(2, asegurado.getNumCredencial());
                pstmt.setInt(3, (Integer.parseInt(asegurado.getIdAsegurado())));
                
                
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
                pstmt.setInt(1, (Integer.parseInt(id)));
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
    
    private void loadObject(Asegurado asegurado, ResultSet rs) throws SQLException{
        //DUDA SOBRE EL ID
        asegurado.setNombreSeguro(rs.getString(1));
        asegurado.setNumCredencial(rs.getString(2)); 
        asegurado.setIdPaciente(rs.getString(3)); 
        
    }
    
    public Asegurado find(String id){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Asegurado asegurado = null;
        try {
            getConnection().setAutoCommit(false);
            try {
                pstmt = getConnection().prepareStatement(EXISTS);
                pstmt.setInt(1,(Integer.parseInt(id)));

                rs = pstmt.executeQuery();
                getConnection().commit();
                if (rs.next()) {
                    asegurado = new Asegurado();
                    loadObject(asegurado, rs);
                    return asegurado;
                }
                return asegurado;
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
    
    public ArrayList<Asegurado> getAsegurados(){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        LinkedList<Asegurado> linkedList = null;
        Asegurado asegurado = null;
        try {
            getConnection().setAutoCommit(false);
            try {
                pstmt = getConnection().prepareStatement(SELECT);

                rs = pstmt.executeQuery();
                getConnection().commit();
                if (rs.next()) {
                    linkedList = new LinkedList<Asegurado>();
                    asegurado = new Asegurado();
                    loadObject(asegurado, rs);
                    linkedList.add(asegurado);
                    while (rs.next()) {
                        asegurado = new Asegurado();
                        loadObject(asegurado, rs);
                        linkedList.add(asegurado);

                    }
                    return new ArrayList<Asegurado>(linkedList);
                }
                
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
        return null;
    }
    
    public void update(Asegurado asegurado){
        PreparedStatement pstmt = null;
        try {
            getConnection().setAutoCommit(false);
            try {
                pstmt = getConnection().prepareStatement(UPDATE);

                pstmt.setString(1, asegurado.getNombreSeguro());
                pstmt.setString(2, asegurado.getNumCredencial());
                pstmt.setInt(3, (Integer.parseInt(asegurado.getIdAsegurado())));
                
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
