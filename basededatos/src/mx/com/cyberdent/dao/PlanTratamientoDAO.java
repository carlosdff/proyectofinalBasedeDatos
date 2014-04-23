package mx.com.cyberdent.dao;

import java.sql.Connection;
import java.sql.*;
import mx.com.cyberdent.objects.PlanTratamiento;
import java.util.ArrayList;
import java.util.LinkedList;

public class PlanTratamientoDAO {
    
    public static final String ID = "idPlan";
    public static final String ACTOCLIN = "actoClinico";
    public static final String COSTO = "costo";
    public static final String ACUENTA = "acuenta";
    public static final String SALDO = "saldo";
 //   public static final String RADIOGRAFANT = "radiografiaAntes";
 //   public static final String RADIOGRAFDESP = "redriografiaDespues"; 
    public static final String FECHATRAT = "fechaTrat";
    public static final String EVOL = "evolucion";
    public static final String DIAG = "diagnostico";
    public static final String PRONOST = "pronostico";
    public static final String IDHIST = "idHistorial"; 
    
    private static final String INSERT = "INSERT INTO " +
            MySQLDAOFactory.PLANTRATAMIENTO + " ( " + ACTOCLIN + " , " + COSTO + " , " + ACUENTA + " , " + SALDO + " , " 
            //+ RADIOGRAFANT + " , " + RADIOGRAFDESP + " , "  
            + FECHATRAT +  " , " + EVOL + " , " + DIAG + " , "
            + PRONOST + " , " + IDHIST + ")" + " values "
            + "( ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    
    private static final String UPDATE = "UPDATE " + MySQLDAOFactory.PLANTRATAMIENTO +
            " SET " + ACTOCLIN + " = ?, " +
            COSTO + " = ?, " +
            ACUENTA + " = ?, " +
            SALDO + " = ?, " +
         //   RADIOGRAFANT + " = ?, " +
         //   RADIOGRAFDESP + " = ?, " +
            FECHATRAT + " = ?, " +
            EVOL + " = ?, " +
            DIAG + " = ?, " +
            PRONOST + " = ? WHERE " + ID + "=?;";   
    
    //private static final String DELETE = "DELETE FROM " +
      //      MySQLDAOFactory.PLANTRATAMIENTO + " WHERE " + ID + " = ?;";
    
     private static final String EXISTS = "SELECT * FROM " + MySQLDAOFactory.PLANTRATAMIENTO + " WHERE " + ID + " = ? ;";
    
     private static final String SELECT = "SELECT * FROM " + MySQLDAOFactory.PLANTRATAMIENTO + " WHERE " + IDHIST +  " = ?;";
    
     private Connection conn;
    
    //constructor, solo asigna la referencia al objeto de la clase
    public PlanTratamientoDAO(Connection conn){
            setConnection(conn);
    }
    
    public Connection getConnection() {
        return conn;
    }
    
    public void setConnection(Connection conn) {
        this.conn = conn;
    }
    
       public void create(PlanTratamiento plantratamiento){
        
        PreparedStatement pstmt = null;
        try {
            getConnection().setAutoCommit(false);
            try {
                
                pstmt = getConnection().prepareStatement(INSERT);
                
                pstmt.setString(1,plantratamiento.getActoClinico());
                pstmt.setFloat(2, plantratamiento.getCosto());
                pstmt.setFloat(3, plantratamiento.getACuenta());
                pstmt.setString(4, plantratamiento.getSaldo());
                //pstmt.setBlob(5, plantratamiento.getRadiografiaAntes());
                //pstmt.setBlob(6, plantratamiento.getRadiografiaDespues());
                pstmt.setString(5, plantratamiento.getFechaTratamiento());
                pstmt.setString(6, plantratamiento.getEvolucion());
                pstmt.setString(7, plantratamiento.getDiagnostico());
                pstmt.setString(8, plantratamiento.getPronostico());
                pstmt.setString(9, plantratamiento.getIdHistorial()); 
                    
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
    
     /*public void delete(int idPlan){
        PreparedStatement pstmt = null;
        try {
            getConnection().setAutoCommit(false);
            try {
                pstmt = getConnection().prepareStatement(DELETE);
                pstmt.setInt(1, idPlan);
                
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
        
    }*/
     
     public boolean exists(String idPlan){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            getConnection().setAutoCommit(false);
            try {
                pstmt = getConnection().prepareStatement(EXISTS);
                pstmt.setString(1, idPlan);
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
    
     private void loadObject(PlanTratamiento plantratamiento, ResultSet rs) throws SQLException{
        
       plantratamiento.setIdPlan(rs.getString(1));
       plantratamiento.setActoClinico(rs.getString(2));
       plantratamiento.setCosto(Float.parseFloat(rs.getString(3)));
       plantratamiento.setACuenta(Float.parseFloat(rs.getString(4)));
       plantratamiento.setSaldo(rs.getString(5));
       //plantratamiento.setRadiografiaAntes(rs.getString(6));
       //plantratamiento.setRadiografiaDespues(rs.getString(7));
       plantratamiento.setFechaTratamiento(rs.getString(6));
       plantratamiento.setEvolucion(rs.getString(7));
       plantratamiento.setDiagnostico(rs.getString(8));
       plantratamiento.setPronostico(rs.getString(9));
       plantratamiento.setIdHistorial(rs.getString(10));
   
     }
     
      public PlanTratamiento find(String idPlan){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        PlanTratamiento plantratamiento = null;
        try {
            getConnection().setAutoCommit(false);
            try {
                pstmt = getConnection().prepareStatement(EXISTS);
                pstmt.setString(1, idPlan);
             
                rs = pstmt.executeQuery();
                getConnection().commit();
                if (rs.next()) {
                   plantratamiento = new PlanTratamiento();
                    loadObject(plantratamiento, rs);
                    return plantratamiento;
                }
                return plantratamiento;
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
     
     public ArrayList<PlanTratamiento> getPlanTratamiento(String idHist){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        LinkedList<PlanTratamiento> linkedList = null;
       PlanTratamiento plantratamiento = null;
        try {
            getConnection().setAutoCommit(false);
            try {
                pstmt = getConnection().prepareStatement(SELECT);
                pstmt.setString(1, idHist); 

                rs = pstmt.executeQuery();
                getConnection().commit();
                if (rs.next()) {
                    linkedList = new LinkedList<PlanTratamiento>();
                    plantratamiento= new PlanTratamiento();
                    loadObject(plantratamiento, rs);
                    linkedList.add(plantratamiento);
                    while (rs.next()) {
                        plantratamiento= new PlanTratamiento();
                        loadObject(plantratamiento, rs);
                        linkedList.add(plantratamiento);

                    }
                    return new ArrayList<PlanTratamiento>(linkedList);
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
     
     public void update(PlanTratamiento plantratamiento){
        PreparedStatement pstmt = null;
        try {
            getConnection().setAutoCommit(false);
            try {
                pstmt = getConnection().prepareStatement(UPDATE);
                
                //Cambiar ID al final
                pstmt.setString(1,plantratamiento.getActoClinico());
                pstmt.setFloat(2, plantratamiento.getCosto());
                pstmt.setFloat(3, plantratamiento.getACuenta());
                pstmt.setString(4, plantratamiento.getSaldo());
         //       pstmt.setString(5, plantratamiento.getRadiografiaAntes());
         //       pstmt.setString(6, plantratamiento.getRadiografiaDespues());
                pstmt.setString(5, plantratamiento.getFechaTratamiento());
                pstmt.setString(6, plantratamiento.getEvolucion());
                pstmt.setString(7, plantratamiento.getDiagnostico());
                pstmt.setString(8, plantratamiento.getPronostico());
                pstmt.setString(9, plantratamiento.getIdPlan());

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
