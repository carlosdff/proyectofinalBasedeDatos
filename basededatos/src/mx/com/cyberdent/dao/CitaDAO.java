/*
 * CitaDao.java
 *
 * Created on 20 de octubre de 2006, 08:32 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package mx.com.cyberdent.dao;

import java.sql.Connection;
import java.sql.*;
import mx.com.cyberdent.objects.Cita;
import java.util.ArrayList;
import java.util.LinkedList;
import mx.com.cyberdent.objects.Paciente;


public class CitaDAO {
    public static final String ID = "idCita";
    public static final String HORA = "horaCita";
    public static final String FECHA = "fechaCita";
    public static final String ESTATUS = "estatusCita";
    public static final String COMENTARIO = "comentario";
    public static final String PACIENTE = "idPaciente";
    public static final String USUARIO = "idUsuario"; 
  
   
    private static final String INSERT = "INSERT INTO " +
            MySQLDAOFactory.CITA + " ( " + HORA + " , " + FECHA + " , " + ESTATUS + " , " + COMENTARIO + " , " 
            + PACIENTE + " , " + USUARIO + ")" + " values "
            + "( ?, ?, ?, ?, ?, ?);";
    
    private static final String UPDATE = "UPDATE " + MySQLDAOFactory.CITA +
            " SET " + HORA + " = ?, " +
            FECHA + " = ?, " +
            ESTATUS + " = ?, " +
            COMENTARIO + " = ? WHERE " +
            ID + " =? ;";   
    
    private static final String DELETE = "DELETE FROM " +
            MySQLDAOFactory.CITA + " WHERE " + ID + " = ?;";
    
    private static final String SELECT = "SELECT * FROM " + MySQLDAOFactory.CITA + " ;"; 
    
    private static final String SELECT_FECHA = "SELECT * FROM " + MySQLDAOFactory.CITA + " WHERE " + FECHA + " = ?;";
    
     private static final String EXISTS = "SELECT * FROM " + MySQLDAOFactory.CITA + " WHERE " + FECHA + " = ? and " + HORA 
             +  " = ? ORDER BY horaCita;";
    
     private static final String EXISTS_ID = "SELECT * FROM " + MySQLDAOFactory.CITA + " WHERE " + ID + " = ?;";
     
     private static final String SELECT_PACIENTE = "SELECT * FROM " + MySQLDAOFactory.CITA + " WHERE " + PACIENTE + " =?;";
     
     private Connection conn;
    
    //constructor, solo asigna la referencia al objeto de la clase
    public CitaDAO(Connection conn){
            setConnection(conn);
    }
    
    public Connection getConnection() {
        return conn;
    }
    
    public void setConnection(Connection conn) {
        this.conn = conn;
    }
    public void create(Cita cita){
        
        PreparedStatement pstmt = null;
        try {
            getConnection().setAutoCommit(false);
            try {
                pstmt = getConnection().prepareStatement(INSERT);
                pstmt.setString(1,cita.getHoraCita());
                pstmt.setString(2, cita.getFechaCita());
                pstmt.setString(3, cita.getEstatusCita());
                pstmt.setString(4, cita.getComentarioCita());
                pstmt.setInt(5,Integer.parseInt(cita.getIdPaciente()));
                pstmt.setString(6,cita.getIdUsuario());
                
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
    public void delete(int idCita){
        PreparedStatement pstmt = null;
        try {
            getConnection().setAutoCommit(false);
            try {
                pstmt = getConnection().prepareStatement(DELETE);
                pstmt.setInt(1, idCita);
                
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
     
     public boolean exists(String fecha,String hora){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            getConnection().setAutoCommit(false);
            try {
                pstmt = getConnection().prepareStatement(EXISTS);
                pstmt.setString(1, fecha);
                pstmt.setString(2, hora); 
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
     
     public boolean existsID(String id){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            getConnection().setAutoCommit(false);
            try {
                pstmt = getConnection().prepareStatement(EXISTS_ID);
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
     
    
    private void loadObject(Cita cita, ResultSet rs) throws SQLException{
        //DUDA SOBRE EL ID
       cita.setIdCita(rs.getString(1)); 
       cita.setHoraCita(rs.getString(2));
       cita.setFechaCita(rs.getString(3));
       cita.setEstatusCita(rs.getString(4));
       cita.setComentarioCita(rs.getString(5));        
       cita.setIdPaciente(rs.getString(6)); 
       cita.setIdUsuario(rs.getString(7)); 
    }
    
    public Cita find(String fecha,String hora){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Cita cita = null;
        try {
            getConnection().setAutoCommit(false);
            try {
                pstmt = getConnection().prepareStatement(EXISTS);
                pstmt.setString(1,fecha);
                pstmt.setString(2,hora); 

                rs = pstmt.executeQuery();
                getConnection().commit();
                if (rs.next()) {
                   cita = new Cita();
                    loadObject(cita, rs);
                    return cita;
                }
                return cita;
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
    
    
    
    
    public Cita findID(String id){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Cita cita = null;
        try {
            getConnection().setAutoCommit(false);
            try {
                pstmt = getConnection().prepareStatement(EXISTS_ID);
                pstmt.setString(1,id);
                

                rs = pstmt.executeQuery();
                getConnection().commit();
                if (rs.next()) {
                   cita = new Cita();
                    loadObject(cita, rs);
                    return cita;
                }
                return cita;
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
    
    
    public ArrayList<Cita> getCita(){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        LinkedList<Cita> linkedList = null;
       Cita cita = null;
        try {
            getConnection().setAutoCommit(false);
            try {
                pstmt = getConnection().prepareStatement(SELECT);

                rs = pstmt.executeQuery();
                getConnection().commit();
                if (rs.next()) {
                    linkedList = new LinkedList<Cita>();
                    cita= new Cita();
                    loadObject(cita, rs);
                    linkedList.add(cita);
                    while (rs.next()) {
                        cita= new Cita();
                        loadObject(cita, rs);
                        linkedList.add(cita);

                    }
                    return new ArrayList<Cita>(linkedList);
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
    
    public ArrayList<Cita> getCitas(String fecha){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        LinkedList<Cita> linkedList = null;
       Cita cita = null;
        try {
            getConnection().setAutoCommit(false);
            try {
                pstmt = getConnection().prepareStatement(SELECT_FECHA);
                pstmt.setString(1,fecha); 

                rs = pstmt.executeQuery();
                getConnection().commit();
                if (rs.next()) {
                    linkedList = new LinkedList<Cita>();
                    cita= new Cita();
                    loadObject(cita, rs);
                    linkedList.add(cita);
                    while (rs.next()) {
                        cita= new Cita();
                        loadObject(cita, rs);
                        linkedList.add(cita);

                    }
                    return new ArrayList<Cita>(linkedList);
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
    
    public ArrayList<Cita> getCitasPac(String idPaciente){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        LinkedList<Cita> linkedList = null;
       Cita cita = null;
        try {
            getConnection().setAutoCommit(false);
            try {
                pstmt = getConnection().prepareStatement(SELECT_PACIENTE);
                pstmt.setString(1,idPaciente); 

                rs = pstmt.executeQuery();
                getConnection().commit();
                if (rs.next()) {
                    linkedList = new LinkedList<Cita>();
                    cita= new Cita();
                    loadObject(cita, rs);
                    linkedList.add(cita);
                    while (rs.next()) {
                        cita= new Cita();
                        loadObject(cita, rs);
                        linkedList.add(cita);

                    }
                    return new ArrayList<Cita>(linkedList);
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
    
    
    
    public void update(Cita cita){
        PreparedStatement pstmt = null;
        try {
            getConnection().setAutoCommit(false);
            try {
                pstmt = getConnection().prepareStatement(UPDATE);

                //Cambiar ID al final
                pstmt.setString(1,cita.getHoraCita());
                pstmt.setString(2, cita.getFechaCita());
                pstmt.setString(3, cita.getEstatusCita());
                pstmt.setString(4, cita.getComentarioCita());
                pstmt.setString(5,cita.getIdCita());

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
