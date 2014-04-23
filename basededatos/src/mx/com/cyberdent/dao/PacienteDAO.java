/*
 * PacienteDAO.java
 *
 * Created on 17 de octubre de 2006, 03:06 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package mx.com.cyberdent.dao;

import java.sql.*;
import mx.com.cyberdent.objects.Paciente;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author Usuario
 */
public class PacienteDAO {
    
    //definimos columnas de la tabla paciente
    public static final String ID = "idPaciente";
    public static final String NOMBRE = "nombreCompleto";
    public static final String TELP = "telParticular";
    public static final String TELO = "telOficina";
    public static final String TELC = "telCelular";
    public static final String DIRECCION = "direccion";
    public static final String OCUPACION = "ocupacion";
    public static final String EDOCIV = "edoCivil";
    public static final String MAIL = "correoElectronico";
    public static final String FECHANAC = "fechaNac";
    //public static final String ESTATUS = "estatusPaciente";
    public static final String IDDENT = "idDentista";
    public static final String IDUSUARIO = "idUsuario";
    public static final String ASEGURADO = "asegurado"; 
    
    //public static final String ID = "idPaciente";
    public static final String NOMBRES = "nombreSeguro";
    public static final String NUMC = "numeroCredencial";
   
    private static final String INSERT = "INSERT INTO " +
            MySQLDAOFactory.PACIENTE + " ( " + NOMBRE + ", " + TELP + " , " + TELO + " , " + TELC + " , " + DIRECCION + " , " + 
            OCUPACION + " , " + EDOCIV + " , " + MAIL + " , " + FECHANAC  + " , " + IDDENT + " , " + IDUSUARIO + " , " 
            + ASEGURADO + " ) " + " values "
            + "( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    
    private static final String INSERT_ASEGURADO = "INSERT INTO " +
            MySQLDAOFactory.ASEGURADO + " values (?, ?, ?);";
    
    private static final String UPDATE_ASEGURADO = "UPDATE " + MySQLDAOFactory.ASEGURADO + 
            " SET " + NOMBRES + " = ?, " + NUMC + " = ? WHERE " + ID + " = ?;";
    
    private static final String DELETE_ASEGURADO = "DELETE FROM " + MySQLDAOFactory.ASEGURADO + 
            " WHERE " + ID + " = ?;";
    
    private static final String EXISTS_ASEGURADO = "SELECT * FROM " +
            MySQLDAOFactory.ASEGURADO +
            " WHERE " + ID + " = ?;";
    
    private static final String UPDATE = "UPDATE " + MySQLDAOFactory.PACIENTE +
            " SET " + NOMBRE + " = ?, " +
            TELP + " = ?, " +
            TELO + " = ?, " +
            TELC + " = ?, " +
            DIRECCION + " = ?, " +
            OCUPACION + " = ?, " +
            EDOCIV + " = ?, " +
            MAIL + " = ?, " +
            FECHANAC + " = ?, " 
            + IDDENT + " = ?, "  
            + ASEGURADO +
            " = ? WHERE " +
            ID + " = ?;";
    
    private static final String EXISTS_TEL = "SELECT * FROM " +
            MySQLDAOFactory.PACIENTE +
            " WHERE " + TELP + " = ?;";
    
    private static final String EXISTS = "SELECT * FROM " +
            MySQLDAOFactory.CONSULTA_PACIENTE +
            " WHERE nomPac" + "= ?;";
   
    private static final String SELECT_ALL = "SELECT * FROM " +
            MySQLDAOFactory.PACIENTE + ";";
    
    private static final String SELECT_NOMBRE = "SELECT * FROM " + MySQLDAOFactory.PACIENTE_TELEFONO 
            + " WHERE nomCom LIKE ? ORDER BY nomCom;"; 
    
   // private static final String SELECT_ASEGURADO = "SELECT " + NOMBRES + " , " + NUMC + " FROM " + MySQLDAOFactory.ASEGURADO 
        //    + " WHERE " + ID + " = ?;"; 
    
    private static final String SELECT_ID = "SELECT * FROM " + MySQLDAOFactory.CONSULTA_PACIENTE + " WHERE idPac= ?;";
    
    /*private static final String SELECT_NOMBRE = "SELECT paciente." + ID + " , paciente." + NOMBRE + ", " + TELP + " , paciente." 
            + TELO + " , paciente." + TELC + " , " + DIRECCION + " , " + OCUPACION + " , " + EDOCIV + " , paciente." + MAIL 
            + " , paciente." + FECHANAC  + " , " + NOMBRES + " , " + NUMC + " , " + IDDENT + " FROM " +
            MySQLDAOFactory.PACIENTE + " , " + MySQLDAOFactory.ASEGURADO + " WHERE paciente."
            + ID + " = asegurado." + ID + " and " + NOMBRE + " like ?;";
    
    select paciente.idPaciente, paciente.nombreCompleto, telParticular, paciente.telOficina, paciente.telCelular, direccion, ocupacion, edoCivil, paciente.correoElectronico,
paciente.fechaNac, nombreSeguro,numeroCredencial, dentista.nombreCompleto from paciente,asegurado,dentista where paciente.idDentista = dentista.idDentista
and paciente.idPaciente=asegurado.idPaciente;*/
    
   // private static final String SELECT_TEL = "SELECT * FROM " +
     //       MySQLDAOFactory.PACIENTE + " WHERE " + TELP + " = ?;";
    
    //atributo conexion
    private Connection conn;
  //  int x=0; 
    
    //constructor, solo asigna la referencia al objeto de la clase
    public PacienteDAO(Connection conn) {
        
        setConnection(conn);
    }
    
    public Connection getConnection() {
        return conn;
    }
    
    public void setConnection(Connection conn) {
        this.conn = conn;
    }
    
    //El create es nuestro INSERT
    public void create(Paciente paciente){
        
        PreparedStatement pstmt = null;
        try{
            getConnection().setAutoCommit(false);
            try{
                pstmt = getConnection().prepareStatement(INSERT);
                //pstmt.setInt(1,Integer.parseInt(paciente.getIdPaciente()));
                pstmt.setString(1, paciente.getNombrePaciente());
                pstmt.setString(2, paciente.getTelParticularPaciente());
                pstmt.setString(3, paciente.getTelOfiPaciente());
                pstmt.setString(4, paciente.getTelCelPaciente());
                pstmt.setString(5, paciente.getDirPaciente());
                pstmt.setString(6, paciente.getOcupacionPaciente());
                pstmt.setString(7, paciente.getEdoCivilPaciente());
                pstmt.setString(8, paciente.getCorreoElecPaciente());
                pstmt.setString(9, paciente.getFechaNacPaciente());
                pstmt.setInt(10,Integer.parseInt(paciente.getIdDentista())); 
                pstmt.setString(11, paciente.getIdusuario()); 
                pstmt.setBoolean(12, paciente.isAsegurado()); 
                
                pstmt.executeUpdate();
                
                Paciente a = new Paciente();
                String nombre = paciente.getNombrePaciente();
                
                a = findNombre(nombre); 
                
                if(paciente.isAsegurado() == true){
                    pstmt = null; 
                    pstmt = getConnection().prepareStatement(INSERT_ASEGURADO);
                    pstmt.setString(1, paciente.getNombreSeguro());
                    pstmt.setString(2, paciente.getNumCredencial());
                    pstmt.setString(3, a.getIdPaciente());
                    
                    pstmt.executeUpdate(); 
                }
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
    
    //El delete es nuestro DELETE
    /*public void delete(int isbn){
        PreparedStatement pstmt = null;
        try {
            getConnection().setAutoCommit(false);
            try {
                pstmt = getConnection().prepareStatement(DELETE);
                pstmt.setInt(1, Integer.parseInt(ID));
                
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
    
    private void loadObject(Paciente paciente, ResultSet rs) throws SQLException{

        paciente.setIdPaciente(String.valueOf(rs.getInt(1)));
        paciente.setNombrePaciente(rs.getString(2));
        paciente.setTelParticularPaciente(rs.getString(3));
        paciente.setTelOfiPaciente(rs.getString(4));
        paciente.setTelCelPaciente(rs.getString(5));
        paciente.setDirPaciente(rs.getString(6)); 
        paciente.setOcupacionPaciente(rs.getString(7)); 
        paciente.setEdoCivilPaciente(rs.getString(8)); 
        paciente.setCorreoElecPaciente(rs.getString(9)); 
        paciente.setFechaNacPaciente(String.valueOf(rs.getDate(10))); 
        paciente.setIdDentista(String.valueOf(rs.getInt(11)));
        paciente.setAsegurado(rs.getBoolean(12)); 
        paciente.setNombreSeguro(rs.getString(13));
        paciente.setNumCredencial(rs.getString(14)); 
        
    }
    
    private void loadObjectPaciente(Paciente paciente, ResultSet rs) throws SQLException{
        
//        x=rs.getInt(1);
        
        paciente.setIdPaciente(String.valueOf(rs.getInt(1)));
                //String.valueOf(rs.getInt(1))); 
        paciente.setNombrePaciente(rs.getString(2));
        paciente.setTelParticularPaciente(rs.getString(3));
        paciente.setTelOfiPaciente(rs.getString(4));
        paciente.setTelCelPaciente(rs.getString(5));
        paciente.setDirPaciente(rs.getString(6)); 
        paciente.setOcupacionPaciente(rs.getString(7)); 
        paciente.setEdoCivilPaciente(rs.getString(8)); 
        paciente.setCorreoElecPaciente(rs.getString(9)); 
        paciente.setFechaNacPaciente(String.valueOf(rs.getDate(10))); 
        paciente.setIdDentista(String.valueOf(rs.getInt(11)));
        paciente.setIdusuario(rs.getString(12));
        paciente.setAsegurado(rs.getBoolean(13)); 
        paciente.setNombreSeguro(rs.getString(14));
        paciente.setNumCredencial(rs.getString(15)); 
        
        
    }
    
    
    private void loadObjectNomTel(Paciente paciente, ResultSet rs) throws SQLException{
        
        paciente.setIdPaciente(String.valueOf(rs.getInt(1)));
        paciente.setNombrePaciente(rs.getString(2));
        paciente.setTelParticularPaciente(rs.getString(3)); 
        
    }
    
    private void loadObjectAsegurado(Paciente paciente, ResultSet rs) throws SQLException{
        
        paciente.setNombreSeguro(rs.getString(1)); 
        paciente.setNumCredencial(rs.getString(2));
        paciente.setIdPaciente(String.valueOf(rs.getInt(3)));
        
        
    }
    
    
    public Paciente find(String id){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Paciente paciente = null;
     //   int ID = Integer.parseInt(id);
        try {
            getConnection().setAutoCommit(false);
            try {
                
                pstmt = getConnection().prepareStatement(SELECT_ID);
                pstmt.setString(1,id);

                rs = pstmt.executeQuery();
                getConnection().commit();
                if (rs.next()) {
                    paciente = new Paciente();
                    loadObject(paciente, rs);
                    return paciente;
                }
                return paciente;
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
    
    public Paciente findAsegurado(String id){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Paciente paciente = null;
     //   int ID = Integer.parseInt(id);
        try {
            getConnection().setAutoCommit(false);
            try {
                
                pstmt = getConnection().prepareStatement(EXISTS_ASEGURADO);
                pstmt.setString(1,id);

                rs = pstmt.executeQuery();
                getConnection().commit();
                if (rs.next()) {
                    paciente = new Paciente();
                    loadObjectAsegurado(paciente, rs);
                    return paciente;
                }
                return paciente;
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
    
    public Paciente findNombre(String nombre){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Paciente paciente = null;
        try {
            getConnection().setAutoCommit(false);
            try {
                pstmt = getConnection().prepareStatement(EXISTS);
                pstmt.setString(1,nombre);

                rs = pstmt.executeQuery();
                getConnection().commit();
                if (rs.next()) {
                    paciente = new Paciente();
                    loadObject(paciente, rs);
                    return paciente;
                }
                return paciente;
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
    
    public Paciente findTelP(String telP){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Paciente paciente = null;
        try {
            getConnection().setAutoCommit(false);
            try {
                pstmt = getConnection().prepareStatement(EXISTS);
                pstmt.setString(1,telP);

                rs = pstmt.executeQuery();
                getConnection().commit();
                if (rs.next()) {
                    paciente = new Paciente();
                    loadObject(paciente, rs);
                    return paciente;
                }
                return paciente;
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
    
    public ArrayList<Paciente> getPacientesNombre(String nombre){
        PreparedStatement pstmt = null;
        LinkedList<Paciente> linkedList = null;
        Paciente paciente = null;
        ResultSet rs = null;
        try {
            getConnection().setAutoCommit(false);
            try {
                
                pstmt = getConnection().prepareStatement(SELECT_NOMBRE);
                pstmt.setString(1,nombre); 
                //System.out.println(pstmt);
                
                rs = pstmt.executeQuery();
                
                getConnection().commit();
                if (rs.next()) {
                    linkedList = new LinkedList<Paciente>();
                    
                    paciente = new Paciente();
                    
                    loadObjectNomTel(paciente, rs);
                    
              
                    
                    linkedList.add(paciente);
                    while (rs.next()) {
                        paciente = new Paciente();
                        loadObjectNomTel(paciente, rs);
                       
                        linkedList.add(paciente);

                    }
                    return new ArrayList<Paciente>(linkedList);
                }
                else{
                    ArrayList<Paciente>vacio=null; 
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
    
     public ArrayList<Paciente> getPacientes(){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        LinkedList<Paciente> linkedList = null;
        Paciente paciente = null;
        try {
            getConnection().setAutoCommit(false);
            try {
                pstmt = getConnection().prepareStatement(SELECT_ALL);

                rs = pstmt.executeQuery();
                getConnection().commit();
                if (rs.next()) {
                    linkedList = new LinkedList<Paciente>();
                    paciente = new Paciente();
                    loadObject(paciente, rs);
                    linkedList.add(paciente);
                    while (rs.next()) {
                        paciente = new Paciente();
                        loadObject(paciente, rs);
                        linkedList.add(paciente);

                    }
                    return new ArrayList<Paciente>(linkedList);
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
    
    public void update(Paciente paciente){
        PreparedStatement pstmt = null;
        try {
            getConnection().setAutoCommit(false);
            try {
                pstmt = getConnection().prepareStatement(UPDATE);

                pstmt.setString(1, paciente.getNombrePaciente());
                pstmt.setString(2, paciente.getTelParticularPaciente());
                pstmt.setString(3, paciente.getTelOfiPaciente());
                pstmt.setString(4, paciente.getTelCelPaciente());
                pstmt.setString(5, paciente.getDirPaciente());
                pstmt.setString(6, paciente.getOcupacionPaciente());
                pstmt.setString(7, paciente.getEdoCivilPaciente());
                pstmt.setString(8, paciente.getCorreoElecPaciente());
                pstmt.setString(9, paciente.getFechaNacPaciente());
                pstmt.setString(10, paciente.getIdDentista());
                pstmt.setBoolean(11, paciente.isAsegurado());
            //    pstmt.setString(11, paciente.getNombreSeguro());
//                pstmt.setString(12, paciente.getNumCredencial());
                pstmt.setString(12, paciente.getIdPaciente());

                pstmt.executeUpdate();
                
                if(paciente.isAsegurado() == true){
                    
                    Paciente asegurado = this.findAsegurado(paciente.getIdPaciente());
                    pstmt = null; 
                    
                    if(asegurado == null){
                    
                    pstmt = getConnection().prepareStatement(INSERT_ASEGURADO);
                    pstmt.setString(1, paciente.getNombreSeguro());
                    pstmt.setString(2, paciente.getNumCredencial());
                    pstmt.setString(3, paciente.getIdPaciente());    
                    
                    pstmt.executeUpdate(); 
                    }
                    else{
                    
                    pstmt = getConnection().prepareStatement(UPDATE_ASEGURADO);
                    pstmt.setString(1, paciente.getNombreSeguro());
                    pstmt.setString(2, paciente.getNumCredencial());
                    pstmt.setString(3, paciente.getIdPaciente());
                    
                    pstmt.executeUpdate(); 
                    }
                }
                else{
                    
                    Paciente asegurado = this.findAsegurado(paciente.getIdPaciente());
                    pstmt = null; 
                    
                    if(asegurado != null){
                    
                    pstmt = getConnection().prepareStatement(DELETE_ASEGURADO);
                    pstmt.setString(1, paciente.getIdPaciente());    
                    
                    pstmt.executeUpdate(); 
                    }
                    
                }
                
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
