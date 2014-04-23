package mx.com.cyberdent.dao;

import java.sql.Connection;
import java.sql.*;
import mx.com.cyberdent.objects.Endodoncia;
import java.util.ArrayList;
import java.util.LinkedList;
import mx.com.cyberdent.objects.PlanTratamiento;

public class EndodonciaDAO {
    
    public static final String DIENTE = "diente";
    public static final String CONDUCTO = "conducto";
    public static final String CONDUCRFANAT = "conductometriaRfAnat";
    public static final String CONDUCLIM = "conductometriaLima";
    public static final String CONDUCMM = "conductometriamm";
    public static final String APICELIM = "apiceLima";
    public static final String APICEMM = "apiceMm";
    public static final String CUERPOLIM = "cuerpoLim"; 
    public static final String CUERPOMM = "cuerpoMm";
    public static final String ID = "idPlan";
    
    private static final String INSERT = "INSERT INTO " + MySQLDAOFactory.PLANTRAT_END + " VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    
    private static final String UPDATE = "UPDATE " + MySQLDAOFactory.PLANTRAT_END +
            " SET " + CONDUCTO + " = ?, " + CONDUCRFANAT + " = ?, " +
            CONDUCLIM + " = ?, " + APICELIM + " = ?, " +
            APICEMM + " = ?, " + CUERPOLIM + " = ?, " +
            CUERPOMM + " = ? WHERE " + ID + "=? AND " + DIENTE + "=?;";   
    
    //private static final String DELETE = "DELETE FROM " +
      //      MySQLDAOFactory.PLANTRAT_END + " WHERE " + DIENTE + " = ?;";
    
    private static final String SELECT = "SELECT * FROM " + MySQLDAOFactory.PLANTRAT_END + " WHERE " + DIENTE + " = ? AND " 
            + ID + " =? ;";
    
    private static final String EXISTS = "SELECT * FROM " + MySQLDAOFactory.PLANTRAT_END + " WHERE " + DIENTE + " = ? AND " 
            + ID + " =? ;";
    
    private static final String SELECTALL = "SELECT * FROM " + MySQLDAOFactory.PLANTRAT_END + " WHERE " + ID + " =? ORDER BY " + ID + ";";
    
    private Connection conn;
    
    public EndodonciaDAO(Connection conn){
            setConnection(conn);
    }
    
    public Connection getConnection() {
        return conn;
    }
    
    public void setConnection(Connection conn) {
        this.conn = conn;
    }
    
      /* private int diente;
    private String conducto;
    private String conductometriaRfAnat;
    private int conductometriaLima;
    private double conductometriaMm;
    private int apiceLima;
    private double apiceMm;
    private int cuerpoLim;
    private double cuerpoMm;
    private int idPlan;*/
    
        public void create(Endodoncia endodoncia){
        
        PreparedStatement pstmt = null;
        try {
            getConnection().setAutoCommit(false);
            try {
                                
                pstmt = getConnection().prepareStatement(INSERT);
                pstmt.setInt(1, endodoncia.getDiente());
                pstmt.setString(2, endodoncia.getConducto());
                pstmt.setString(3, endodoncia.getConductometriaRfAnat());
                pstmt.setInt(4, endodoncia.getConductometriaLima());
                pstmt.setDouble(5, endodoncia.getConductometriaMm());
                pstmt.setInt(6, endodoncia.getApiceLima());
                pstmt.setDouble(7, endodoncia.getApiceMm());
                pstmt.setInt(8, endodoncia.getCuerpoLim());
                pstmt.setDouble(9, endodoncia.getCuerpoMm());
                pstmt.setString(10, endodoncia.getIdPlan());

                    
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
    
        public boolean exists(Endodoncia endodoncia){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            getConnection().setAutoCommit(false);
            try {
                pstmt = getConnection().prepareStatement(EXISTS);
                pstmt.setInt(1, endodoncia.getDiente());
                pstmt.setString(2, endodoncia.getIdPlan());
                
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
    
     private void loadObject(Endodoncia endodoncia, ResultSet rs) throws SQLException{
        
       endodoncia.setDiente(rs.getInt(1)); 
       endodoncia.setConducto(rs.getString(2)); 
       endodoncia.setConductometriaRfAnat(rs.getString(3));
       endodoncia.setConductometriaLima(rs.getInt(4)); 
       endodoncia.setConductometriaMm(rs.getDouble(5));
       endodoncia.setApiceLima(rs.getInt(6));
       endodoncia.setApiceMm(rs.getDouble(7));
       endodoncia.setCuerpoLim(rs.getInt(8)); 
       endodoncia.setCuerpoMm(rs.getDouble(9));
       endodoncia.setIdPlan(rs.getString(10)); 
       
     }
     
      public Endodoncia find(Endodoncia e){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Endodoncia endodoncia = null;
        try {
            getConnection().setAutoCommit(false);
            try {
                pstmt = getConnection().prepareStatement(EXISTS);
                pstmt.setInt(1, e.getDiente());
                pstmt.setString(2, e.getIdPlan()); 
             
                rs = pstmt.executeQuery();
                getConnection().commit();
                if (rs.next()) {
                   endodoncia = new Endodoncia();
                    loadObject(endodoncia, rs);
                    return endodoncia;
                }
                return endodoncia;
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
     
     public ArrayList<Endodoncia> getEndodoncias(String idPlan){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        LinkedList<Endodoncia> linkedList = null;
       Endodoncia e = null;
        try {
            getConnection().setAutoCommit(false);
            try {
                pstmt = getConnection().prepareStatement(SELECTALL);
                pstmt.setString(1, idPlan); 

                rs = pstmt.executeQuery();
                getConnection().commit();
                if (rs.next()) {
                    linkedList = new LinkedList<Endodoncia>();
                    e = new Endodoncia();
                    loadObject(e, rs);
                    linkedList.add(e);
                    while (rs.next()) {
                        e= new Endodoncia();
                        loadObject(e, rs);
                        linkedList.add(e);

                    }
                    return new ArrayList<Endodoncia>(linkedList);
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
     
     public void update(Endodoncia endodoncia){
        PreparedStatement pstmt = null;
        try {
            getConnection().setAutoCommit(false);
            try {
                pstmt = getConnection().prepareStatement(UPDATE);
                
                pstmt.setString(1, endodoncia.getConducto());
                pstmt.setString(2, endodoncia.getConductometriaRfAnat()); 
                pstmt.setInt(3, endodoncia.getConductometriaLima());
                pstmt.setDouble(4, endodoncia.getConductometriaMm());
                pstmt.setInt(5, endodoncia.getApiceLima());
                pstmt.setDouble(6, endodoncia.getApiceMm());
                pstmt.setInt(7, endodoncia.getCuerpoLim());
                pstmt.setDouble(8, endodoncia.getCuerpoMm());
                pstmt.setInt(9,endodoncia.getDiente());
                pstmt.setString(10, endodoncia.getIdPlan()); 
                
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
