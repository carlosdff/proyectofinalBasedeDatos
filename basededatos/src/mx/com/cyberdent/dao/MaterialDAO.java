package mx.com.cyberdent.dao;

import java.sql.Connection;
import java.sql.*;
import mx.com.cyberdent.objects.Material;
import java.util.ArrayList;
import java.util.LinkedList;

public class MaterialDAO {
    
    public static final String ID = "idMaterial";
    public static final String NOMBRE = "nombreMat";
    public static final String MARCA = "marca";
    public static final String DESC = "descripcion";
    public static final String EXIST = "existencia";
    public static final String PTOREORD = "puntoReorden";
    public static final String CANT = "cantidad";
    public static final String IDPLAN = "idPlan";
            
    
    private static final String INSERT = "INSERT INTO " + 
            MySQLDAOFactory.MATERIAL + " ( " + NOMBRE + ", " + MARCA + " , " + DESC + " , " + 
            EXIST + " , " + PTOREORD  + " ) " + "values" + "( ?, ?, ?, ?, ?);";
    
    private static final String INSERTMATPLAN = "INSERT INTO material_planTratamiento values(?,?,?);";
    
    private static final String UPDATE = "UPDATE " + MySQLDAOFactory.MATERIAL +
            " SET " + NOMBRE + " = ?, " + 
            MARCA + " = ?, " +
            DESC + " = ?, "  +
            EXIST + " = ?, " +
            PTOREORD + " = ? WHERE "+
            ID + " = ?;";
     
    private static final String DELETE = "DELETE FROM " +
            MySQLDAOFactory.MATERIAL + " WHERE " + ID + " = ?;";
    
    private static final String SELECT = "SELECT * FROM " +
            MySQLDAOFactory.MATERIAL + " WHERE " + NOMBRE + " =?;";
    
    private static final String SELECTALL = "SELECT * FROM " +
            MySQLDAOFactory.MATERIAL + ";";
    
    private static final String LIKE = "SELECT * FROM " +
            MySQLDAOFactory.MATERIAL + " WHERE " + NOMBRE + " LIKE ? ORDER BY " + NOMBRE + ";";
    
    private static final String SELECTND = "SELECT * FROM " +
            MySQLDAOFactory.MATERIAL + " WHERE " + NOMBRE + " =? AND " + DESC + " =? ;";
    
    private static final String EXISTS = "SELECT * FROM " +
            MySQLDAOFactory.MATERIAL +
            " WHERE " + ID + " = ?;";
     
     private Connection conn;
     
       //constructor, solo asigna la referencia al objeto de la clase
    public MaterialDAO(Connection conn) {
        
        setConnection(conn);
    }
    
    public Connection getConnection() {
        return conn;
    }
      
    public void setConnection(Connection conn) {
        this.conn = conn;
    }
    
    
   public void create(Material material){
        
        PreparedStatement pstmt = null;
        try {
            getConnection().setAutoCommit(false);
            try {
                pstmt = getConnection().prepareStatement(INSERT);
                pstmt.setString(1, material.getNombreMat());
                pstmt.setString(2, material.getMarca());
                pstmt.setString(3, material.getDescripcion());
                pstmt.setInt(4, material.getExistencia());
                pstmt.setInt(5, material.getPuntoReorden());
                 
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
   
   public void delete(String idMaterial){
        PreparedStatement pstmt = null;
        try {
            getConnection().setAutoCommit(false);
            try {
                pstmt = getConnection().prepareStatement(DELETE);
                pstmt.setString(1, idMaterial);
                
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
   
   public boolean exists(String idMat){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            getConnection().setAutoCommit(false);
            try {
                pstmt = getConnection().prepareStatement(EXISTS);
                pstmt.setString(1, idMat);
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
   
    private void loadObject(Material material, ResultSet rs) throws SQLException{
        //DUDA SOBRE EL ID
  
       material.setIdMaterial(String.valueOf(rs.getInt(1))); 
       material.setNombreMat(rs.getString(2));
       material.setMarca(rs.getString(3));
       material.setDescripcion(rs.getString(4));
       material.setExistencia(rs.getInt(5));        
       material.setPuntoReorden(rs.getInt(6)); 
    
    }
    
   public Material find(String id){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Material material = null;
        try {
            getConnection().setAutoCommit(false);
            try {
                pstmt = getConnection().prepareStatement(EXISTS);
                pstmt.setString(1,id);
               

                rs = pstmt.executeQuery();
                getConnection().commit();
                if (rs.next()) {
                   material = new Material();
                    loadObject(material, rs);
                    return material;
                }
                return material;
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
   
   
   public Material findNDesc(String nombreMat, String descripcion){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Material material = null;
        try {
            getConnection().setAutoCommit(false);
            try {
                pstmt = getConnection().prepareStatement(SELECTND);
                pstmt.setString(1,nombreMat);
                pstmt.setString(2,descripcion);

                rs = pstmt.executeQuery();
                getConnection().commit();
                if (rs.next()) {
                   material = new Material();
                    loadObject(material, rs);
                    return material;
                }
                return material;
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
   
     public ArrayList<Material> getMaterialesNombre(String nombre){
        PreparedStatement pstmt = null;
        LinkedList<Material> linkedList = null;
       Material material = null;
       ResultSet rs = null;
        try {
            getConnection().setAutoCommit(false);
            try {
                pstmt = getConnection().prepareStatement(LIKE);
                pstmt.setString(1, nombre); 

                rs = pstmt.executeQuery();
                
                getConnection().commit();
                if (rs.next()) {
                    linkedList = new LinkedList<Material>();
                    material= new Material();
                    loadObject(material, rs);
                    linkedList.add(material);
                    while (rs.next()) {
                        material= new Material();
                        loadObject(material, rs);
                        linkedList.add(material);

                    }
                    return new ArrayList<Material>(linkedList);
                }
                else{
                    ArrayList<Material>vacio = null;
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
        return null;
    }
     
     public ArrayList<Material> getMateriales(){
        PreparedStatement pstmt = null;
        LinkedList<Material> linkedList = null;
       Material material = null;
       ResultSet rs = null;
        try {
            getConnection().setAutoCommit(false);
            try {
                pstmt = getConnection().prepareStatement(SELECTALL);
                
                rs = pstmt.executeQuery();
                
                getConnection().commit();
                if (rs.next()) {
                    linkedList = new LinkedList<Material>();
                    material= new Material();
                    loadObject(material, rs);
                    linkedList.add(material);
                    while (rs.next()) {
                        material= new Material();
                        loadObject(material, rs);
                        linkedList.add(material);

                    }
                    return new ArrayList<Material>(linkedList);
                }
                else{
                    ArrayList<Material>vacio = null;
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
        return null;
    }
 
     
     public Material actualizaExistencia(Material material, String idPlan){
        PreparedStatement pstmt = null;
        Material m = null;
        try {
            getConnection().setAutoCommit(false);
            try {
                pstmt = getConnection().prepareStatement(INSERTMATPLAN);

                pstmt.setString(1,material.getIdMaterial());
                pstmt.setInt(2, material.getCantidad());
                pstmt.setString(3, idPlan);
                
                pstmt.executeUpdate();
                getConnection().commit();
                //try {
                    m = this.find(material.getIdMaterial());
                    
                    int nuevaExistencia = m.getExistencia() - material.getCantidad();
                    m.setExistencia(nuevaExistencia);
                    
                    this.update(m); 
                    m = null; 
                    
                    m = this.find(material.getIdMaterial());
              //      return m; 
                //} catch (SQLException sqle) {
                //getConnection().rollback();
                //sqle.printStackTrace();
                //}
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
    return m; 
     }
     
     public void update(Material material){
        PreparedStatement pstmt = null;
        try {
            getConnection().setAutoCommit(false);
            try {
                pstmt = getConnection().prepareStatement(UPDATE);

                pstmt.setString(1,material.getNombreMat());
                pstmt.setString(2, material.getMarca());
                pstmt.setString(3, material.getDescripcion());
                pstmt.setInt(4, material.getExistencia());
                pstmt.setInt(5,material.getPuntoReorden());
                pstmt.setString(6, material.getIdMaterial());

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
