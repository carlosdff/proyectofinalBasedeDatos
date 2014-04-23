/*
 * DentistaDAO.java
 *
 * Created on 19 de octubre de 2006, 10:33 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package mx.com.cyberdent.dao;

import java.sql.*;
import mx.com.cyberdent.objects.Dentista;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author Barbara Silva Ramos
 */
public class DentistaDAO {
    
    //definimos columnas de la tabla dentista
    public static final String ID = "idDentista";
    public static final String NOMBRE = "nombreCompleto";
    public static final String TELC = "telCelular";
    public static final String TELCONS = "telCons";
    public static final String DIRECCIONCONS = "direccionCons";
    public static final String MAIL = "correoElectronico";
    public static final String FECHANAC = "fechaNac"; 
    public static final String ESPECIALIDAD = "especialidad";
    
    private static final String INSERT = "INSERT INTO " +
            MySQLDAOFactory.DENTISTA + " ( " + NOMBRE + " , " + TELC + " , " + TELCONS + " , " + DIRECCIONCONS + " , " + 
            MAIL + " , " + FECHANAC + " , " + ESPECIALIDAD + ")" + " values "
            + "( ?, ?, ?, ?, ?, ?, ?);";
    
    private static final String UPDATE = "UPDATE " + MySQLDAOFactory.DENTISTA +
            " SET " + NOMBRE + " = ?, " + TELC + " = ?, " + TELCONS + " = ?, " + DIRECCIONCONS + " = ?, " + MAIL + " = ?, " +
            FECHANAC + " = ?, " + ESPECIALIDAD + " = ? " + " WHERE " + ID + " = ?;";
    
/*    private static final String DELETE = "DELETE FROM " +
            MySQLDAOFactory.DENTISTA +
            " WHERE " +
            ID + " = ?;";  */
    
    private static final String SELECT = "SELECT * FROM " +
            MySQLDAOFactory.DENTISTA + " ORDER BY " + NOMBRE + ";"; 
    
    private static final String EXISTS = "SELECT * FROM " +
            MySQLDAOFactory.DENTISTA + " WHERE " + NOMBRE + " = ?;";
    
    private static final String EXISTS_ID = "SELECT * FROM " +
            MySQLDAOFactory.DENTISTA + " WHERE " + ID + " = ?;";
   
    private static final String SELECT_NOMBRE = "SELECT * FROM " +
            MySQLDAOFactory.DENTISTA + " WHERE " + NOMBRE + " LIKE ? ORDER BY " + NOMBRE + ";";  
    
    //atributo conexion
    private Connection conn;
    
    //constructor, solo asigna la referencia al objeto de la clase
    
    /** Creates a new instance of DentistaDAO */
    public DentistaDAO(Connection conn) {
        
        setConnection(conn);
    }
    
    public Connection getConnection() {
        return conn;
    }
    
    public void setConnection(Connection conn) {
        this.conn = conn;
    }
    
     public void create(Dentista dentista){
        
        PreparedStatement pstmt = null;
        try {
            getConnection().setAutoCommit(false);
            try {
                pstmt = getConnection().prepareStatement(INSERT);
                //pstmt.setInt(1,Integer.parseInt(paciente.getIdPaciente()));
                pstmt.setString(1, dentista.getNombre());
                pstmt.setString(2, dentista.getTelCelular());
                pstmt.setString(3, dentista.getTelCons());  
                pstmt.setString(4, dentista.getDireccionCons());
                pstmt.setString(5, dentista.getCorreoElecDentista());
                pstmt.setString(6, dentista.getFechaNacDentista());
                pstmt.setString(7, dentista.getEspecialidadDentista());
               
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
     
     public boolean exists(String nombre){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            getConnection().setAutoCommit(false);
            try {
                pstmt = getConnection().prepareStatement(EXISTS);
                pstmt.setString(1, nombre);
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
     
     private void loadObject(Dentista dentista, ResultSet rs) throws SQLException{
        //DUDA SOBRE EL ID
        dentista.setIdDentista(rs.getString(1)); 
        dentista.setNombre(rs.getString(2));
        dentista.setTelCelular(rs.getString(3));
        dentista.setTelCons(rs.getString(4));
        dentista.setDireccionCons(rs.getString(5));
        dentista.setCorreoElecDentista(rs.getString(6));
        dentista.setFechaNacDentista(rs.getString(7)); 
        dentista.setEspecialidadDentista(rs.getString(8)); 
    
    }
     
     public Dentista find(String nombre){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Dentista dentista = null;
        try {
            getConnection().setAutoCommit(false);
            try {
                pstmt = getConnection().prepareStatement(EXISTS);
                pstmt.setString(1,nombre);

                rs = pstmt.executeQuery();
                getConnection().commit();
                if (rs.next()) {
                    dentista = new Dentista();
                    loadObject(dentista, rs);
                    return dentista;
                }
                return dentista;
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
     
     public Dentista findID(String id){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Dentista dentista = null;
        try {
            getConnection().setAutoCommit(false);
            try {
                pstmt = getConnection().prepareStatement(EXISTS_ID);
                pstmt.setString(1,id);

                rs = pstmt.executeQuery();
                getConnection().commit();
                if (rs.next()) {
                    dentista = new Dentista();
                    loadObject(dentista, rs);
                    return dentista;
                }
                return dentista;
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
     
     
     
     public ArrayList<Dentista> getDentista(){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        LinkedList<Dentista> linkedList = null;
        Dentista dentista = null;
        try {
            getConnection().setAutoCommit(false);
            try {
                pstmt = getConnection().prepareStatement(SELECT);

                rs = pstmt.executeQuery();
                getConnection().commit();
                if (rs.next()) {
                    linkedList = new LinkedList<Dentista>();
                    dentista = new Dentista();
                    loadObject(dentista, rs);
                    linkedList.add(dentista);
                    while (rs.next()) {
                        dentista = new Dentista();
                        loadObject(dentista, rs);
                        linkedList.add(dentista);

                    }
                    return new ArrayList<Dentista>(linkedList);
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
     
     public ArrayList<Dentista> getNomDent(String nombre){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        LinkedList<Dentista> linkedList = null;
        Dentista dentista = null;
        try {
            getConnection().setAutoCommit(false);
            try {
                pstmt = getConnection().prepareStatement(SELECT_NOMBRE);
                pstmt.setString(1, nombre);
                
                rs = pstmt.executeQuery();
                getConnection().commit();
                if (rs.next()) {
                    linkedList = new LinkedList<Dentista>();
                    dentista = new Dentista();
                    loadObject(dentista, rs);
                    linkedList.add(dentista);
                    while (rs.next()) {
                        dentista = new Dentista();
                        loadObject(dentista, rs);
                        linkedList.add(dentista);

                    }
                    return new ArrayList<Dentista>(linkedList);
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
     
     
     public void update(Dentista dentista){
        PreparedStatement pstmt = null;
        try {
            getConnection().setAutoCommit(false);
            try {
                pstmt = getConnection().prepareStatement(UPDATE);
                
                pstmt.setString(1, dentista.getNombre());
                pstmt.setString(2, dentista.getTelCelular());
                pstmt.setString(3, dentista.getTelCons());
                pstmt.setString(4, dentista.getDireccionCons());
                pstmt.setString(5, dentista.getCorreoElecDentista());
                pstmt.setString(6, dentista.getFechaNacDentista());
                pstmt.setString(7, dentista.getEspecialidadDentista());
                pstmt.setString(8, dentista.getIdDentista());
                
                String coso=pstmt.toString();
                
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
