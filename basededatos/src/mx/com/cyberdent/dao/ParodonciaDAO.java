package mx.com.cyberdent.dao; 

import java.sql.Connection;
import java.sql.*;
import mx.com.cyberdent.objects.Parodoncia;
import java.util.ArrayList;
import java.util.LinkedList;
import mx.com.cyberdent.objects.PlanTratamiento;

public class ParodonciaDAO {
    
    public static final String DIENTE = "diente";
    public static final String BUCALDIS = "bucalDistal";
    public static final String BUCALMED = "bucalMedio";
    public static final String BUCALMES = "bucalMesial";
    public static final String LINGUALDIS = "lingualDistal";
    public static final String LINGUALMED = "lingualMedio";
    public static final String LINGUALMES = "lingualMesial";
    public static final String VITALITY = "vitality"; 
    public static final String MOBILITY = "mobility";
    public static final String PROGNOSIS= "prognosis"; 
    public static final String ID = "idPlan";
    
    
    private static final String INSERT = "INSERT INTO " + MySQLDAOFactory.PLANTRAT_PAR + " VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    
    private static final String UPDATE = "UPDATE " + MySQLDAOFactory.PLANTRAT_PAR +
            " SET " + BUCALDIS + " = ?, " + BUCALMED + " = ?, " +
            BUCALMES + " = ?, " + LINGUALDIS + " = ?, " +
            LINGUALMED + " = ?, " + LINGUALMES + " = ?, " +
            VITALITY + " = ?, " + MOBILITY + " =? WHERE " + ID + "=? AND " + DIENTE + "=?;";   
    
    //private static final String DELETE = "DELETE FROM " +
      //      MySQLDAOFactory.PLANTRAT_END + " WHERE " + DIENTE + " = ?;";
    
    private static final String SELECT = "SELECT * FROM " + MySQLDAOFactory.PLANTRAT_PAR + " WHERE " + DIENTE + " = ? AND " 
            + ID + " =? ;";
    
    private static final String EXISTS = "SELECT * FROM " + MySQLDAOFactory.PLANTRAT_PAR + " WHERE " + DIENTE + " = ? AND " 
            + ID + " =? ;";
    
    private static final String SELECTALL = "SELECT * FROM " + MySQLDAOFactory.PLANTRAT_PAR + " WHERE " + ID + " =? ORDER BY " + ID + ";";
    
    private Connection conn;
    
    public ParodonciaDAO(Connection conn){
            setConnection(conn);
    }
    
    public Connection getConnection() {
        return conn;
    }
    
    public void setConnection(Connection conn) {
        this.conn = conn;
    }
    
        public void create(Parodoncia parodoncia){
        
        PreparedStatement pstmt = null;
        try {
            getConnection().setAutoCommit(false);
            try {
                                
                pstmt = getConnection().prepareStatement(INSERT);
                
                pstmt.setInt(1, Integer.valueOf(parodoncia.getDiente()));
                pstmt.setDouble(2, parodoncia.getBucalDistal());
                pstmt.setDouble(3, parodoncia.getBucalMedio());
                pstmt.setDouble(4, parodoncia.getBucalMesial());
                pstmt.setDouble(5, parodoncia.getLingualDistal());
                pstmt.setDouble(6, parodoncia.getLingualMedio());
                pstmt.setDouble(7, parodoncia.getLingualMesial());
                pstmt.setBoolean(8, parodoncia.isVitality());
                pstmt.setString(9, parodoncia.getMovility());
                pstmt.setString(10, parodoncia.getPrognosis());
                pstmt.setString(11, parodoncia.getIdPlan()); 
                    
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
    
        public boolean exists(Parodoncia parodoncia){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            getConnection().setAutoCommit(false);
            try {
                pstmt = getConnection().prepareStatement(EXISTS);
                pstmt.setInt(1, parodoncia.getDiente());
                pstmt.setString(2, parodoncia.getIdPlan());
                
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
    
     private void loadObject(Parodoncia parodoncia, ResultSet rs) throws SQLException{
        
       parodoncia.setDiente(rs.getInt(1)); 
       parodoncia.setBucalDistal(rs.getDouble(2));
       parodoncia.setBucalMedio(rs.getDouble(3)); 
       parodoncia.setBucalMesial(rs.getDouble(4)); 
       parodoncia.setLingualDistal(rs.getDouble(5)); 
       parodoncia.setLingualMedio(rs.getDouble(6));
       parodoncia.setLingualMesial(rs.getDouble(7)); 
       parodoncia.setVitality(rs.getBoolean(8)); 
       parodoncia.setMovility(rs.getString(9)); 
       parodoncia.setPrognosis(rs.getString(10)); 
       parodoncia.setIdPlan(rs.getString(11)); 
       
     }
     
      public Parodoncia find(Parodoncia e){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Parodoncia parodoncia = null;
        try {
            getConnection().setAutoCommit(false);
            try {
                pstmt = getConnection().prepareStatement(EXISTS);
                pstmt.setInt(1, e.getDiente());
                pstmt.setString(2, e.getIdPlan()); 
             
                rs = pstmt.executeQuery();
                getConnection().commit();
                if (rs.next()) {
                   parodoncia = new Parodoncia();
                    loadObject(parodoncia, rs);
                    return parodoncia;
                }
                return parodoncia;
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
     
     public ArrayList<Parodoncia> getParodoncias(String idPlan){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        LinkedList<Parodoncia> linkedList = null;
       Parodoncia e = null;
        try {
            getConnection().setAutoCommit(false);
            try {
                pstmt = getConnection().prepareStatement(SELECTALL);
                pstmt.setString(1, idPlan); 

                rs = pstmt.executeQuery();
                getConnection().commit();
                if (rs.next()) {
                    linkedList = new LinkedList<Parodoncia>();
                    e = new Parodoncia();
                    loadObject(e, rs);
                    linkedList.add(e);
                    while (rs.next()) {
                        e= new Parodoncia();
                        loadObject(e, rs);
                        linkedList.add(e);

                    }
                    return new ArrayList<Parodoncia>(linkedList);
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
     
     public void update(Parodoncia parodoncia){
        PreparedStatement pstmt = null;
        try {
            getConnection().setAutoCommit(false);
            try {
                pstmt = getConnection().prepareStatement(UPDATE);
                
                pstmt.setDouble(1, parodoncia.getBucalDistal());
                pstmt.setDouble(2, parodoncia.getBucalMedio());
                pstmt.setDouble(3, parodoncia.getBucalMesial());
                pstmt.setDouble(4, parodoncia.getLingualDistal());
                pstmt.setDouble(5, parodoncia.getLingualMedio());
                pstmt.setDouble(6, parodoncia.getLingualMesial());
                pstmt.setBoolean(7, parodoncia.isVitality());
                pstmt.setString(8, parodoncia.getMovility());
                pstmt.setString(9, parodoncia.getPrognosis());
                pstmt.setString(10, parodoncia.getIdPlan()); 
                pstmt.setInt(11,parodoncia.getDiente());
                
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