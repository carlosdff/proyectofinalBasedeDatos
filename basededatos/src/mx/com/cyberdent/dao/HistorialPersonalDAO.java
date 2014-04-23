/*
 * HistorialPersonalDAO.java
 *
 * Created on 15 de noviembre de 2006, 09:37 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package mx.com.cyberdent.dao;

import java.sql.*;
import mx.com.cyberdent.objects.HistorialPersonal;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author Barbara Silva Ramos
 */
public class HistorialPersonalDAO {
     
    public static final String ID = "idHistorial";
    public static final String PESO = "peso";
    public static final String ESTATURA = "estatura";
    public static final String ANTECEDENTES = "antecedente";
    public static final String ANTECEDENTESNOPAT = "antecedenteNoPat";
    public static final String ANTECEDENTESQUIRURGICOS = "antecedenteQuirurgico";
    public static final String RESUMENPREVIO = "resumenPrevio";
    public static final String PADECIMIENTOACTUAL = "padecimientoActual";
    public static final String ALERGIAS = "alergia";
    public static final String ESTATUSPACIENTE = "estatusPaciente";   
  
    
    private static final String INSERT = "INSERT INTO " + MySQLDAOFactory.HISTORIALPERSONAL + " VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    
    private static final String INSERT_ALERGIA = "INSERT INTO histpersonal_alergias VALUES ( ?, ?);";
    
    private static final String UPDATE = "UPDATE " + MySQLDAOFactory.HISTORIALPERSONAL + " SET " + PESO + " = ?, " 
            + ESTATURA + " = ?, " + ANTECEDENTES + " = ?, " + ANTECEDENTESNOPAT + " = ?, " + ANTECEDENTESQUIRURGICOS 
            + " = ?, " + RESUMENPREVIO + " = ?, " + PADECIMIENTOACTUAL + " = ?, " + ESTATUSPACIENTE + " =?" 
            + " WHERE " + ID + " = ?;";
    
    /*private static final String SELECT = "SELECT * FROM " +
            MySQLDAOFactory.HISTORIALPERSONAL + ";"; //" WHERE " + NOMBRE + " = ?;";*/
    
    private static final String EXISTS = "SELECT * FROM " + 
            MySQLDAOFactory.HISTORIALPERSONAL + " WHERE " + ID + " = ?;";
    
    private static final String EXISTS_ALERGIA = "SELECT * FROM histpersonal_alergias WHERE " + ID + " = ?;";
    
    private static final String DELETE_ALERGIA = "DELETE FROM histpersonal_alergias WHERE " + ID + " = ?;";
    
    private static final String SELECT_ID = "SELECT " + ID + " , " + ID + " FROM " + 
            MySQLDAOFactory.HISTORIALPERSONAL + " ;";  
    
    //atributo conexion
    private Connection conn;
    
    //constructor, solo asigna la referencia al objeto de la clase
    
    /** Creates a new instance of DentistaDAO */
    public HistorialPersonalDAO(Connection conn) {
        
        setConnection(conn);
    }
    
    public Connection getConnection() {
        return conn;
    }
    
    public void setConnection(Connection conn) {
        this.conn = conn;
    }
    
     public void create(HistorialPersonal historialpersonal){
        
        PreparedStatement pstmt = null;
        try {
            getConnection().setAutoCommit(false);
            try {
                pstmt = getConnection().prepareStatement(INSERT);
                //pstmt.setInt(1,Integer.parseInt(paciente.getIdPaciente()));
                pstmt.setString(1, String.valueOf(historialpersonal.getPeso()));               
                pstmt.setString(2, String.valueOf(historialpersonal.getEstatura()));
                pstmt.setString(3, historialpersonal.getAntecendentes());
                pstmt.setString(4, historialpersonal.getAntecedentesNoPat());
                pstmt.setString(5, historialpersonal.getAntecedentesQuirurgicos());
                pstmt.setString(6, historialpersonal.getResumenPrevio());
                pstmt.setString(7, historialpersonal.getPadecimientoActual());
                pstmt.setString(8, historialpersonal.getIdHistorialPersonal());
                pstmt.setString(9, historialpersonal.getEstatuspaciente());
                
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
     
     public void createAlergia(String alergia, String idHistorial){
        
        PreparedStatement pstmt = null;
        try {
            getConnection().setAutoCommit(false);
            try {
                pstmt = getConnection().prepareStatement(INSERT_ALERGIA);
                pstmt.setString(1, alergia);
                pstmt.setString(2, idHistorial);
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
     
     private void loadObject(HistorialPersonal historialpersonal, ResultSet rs) throws SQLException{
        //DUDA SOBRE EL ID
        historialpersonal.setPeso(Float.parseFloat(rs.getString(1)));
        historialpersonal.setEstatura(Float.parseFloat(rs.getString(2)));
        historialpersonal.setAntecendentes(rs.getString(3));
        historialpersonal.setAntecedentesNoPat(rs.getString(4));
        historialpersonal.setAntecedentesQuirurgicos(rs.getString(5));
        historialpersonal.setResumenPrevio(rs.getString(6));
        historialpersonal.setPadecimientoActual(rs.getString(7));
        historialpersonal.setIdHistorialPersonal(rs.getString(8));
        historialpersonal.setEstatuspaciente(rs.getString(9));
    }
     
     //private void loadObjectAlergias(ArrayList )
     
     public HistorialPersonal find(String id){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        HistorialPersonal historialpersonal = null;
        try {
            getConnection().setAutoCommit(false);
            try {
                pstmt = getConnection().prepareStatement(EXISTS);
                pstmt.setString(1, id);

                rs = pstmt.executeQuery();
                getConnection().commit();
                if (rs.next()) {
                    historialpersonal = new HistorialPersonal();
                    loadObject(historialpersonal, rs);
                    return historialpersonal;
                }
                return historialpersonal;
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
     
     public ArrayList<String> getAlergias(String id){
        PreparedStatement pstmt = null;
        LinkedList<String> linkedList = null;
        String alergia;
        ResultSet rs = null;
        try {
            getConnection().setAutoCommit(false);
            try {
                pstmt = getConnection().prepareStatement(EXISTS_ALERGIA);
                pstmt.setString(1, id);
                rs = pstmt.executeQuery();
                getConnection().commit();
                if (rs.next()) {
                    linkedList = new LinkedList<String>();
                    alergia = rs.getString(1);
                    linkedList.add(alergia);
                    while (rs.next()) {
                        alergia = rs.getString(1);
                        linkedList.add(alergia);
                    }
                    return new ArrayList<String>(linkedList);
                }
                else{
                    ArrayList<String>vacio=null; 
                    return vacio; 
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
        //Should never be reached
        return null;
     }
     
     public void deleteAlergias(String idHistorial){
        PreparedStatement pstmt = null;
        try {
            getConnection().setAutoCommit(false);
            try {
                pstmt = getConnection().prepareStatement(DELETE_ALERGIA);
                pstmt.setString(1, idHistorial);
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
     
     public void update(HistorialPersonal historialpersonal){
        PreparedStatement pstmt = null;
        try {
            getConnection().setAutoCommit(false);
            try {
                pstmt = getConnection().prepareStatement(UPDATE);
                
                pstmt.setString(1, String.valueOf(historialpersonal.getPeso()));
                pstmt.setString(2, String.valueOf(historialpersonal.getEstatura()));
                pstmt.setString(3, historialpersonal.getAntecendentes());
                pstmt.setString(4, historialpersonal.getAntecedentesNoPat());
                pstmt.setString(5, historialpersonal.getAntecedentesQuirurgicos());
                pstmt.setString(6, historialpersonal.getResumenPrevio());
                pstmt.setString(7, historialpersonal.getPadecimientoActual());
                pstmt.setString(8, historialpersonal.getEstatuspaciente());
                pstmt.setString(9, historialpersonal.getIdHistorialPersonal());
                        
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
