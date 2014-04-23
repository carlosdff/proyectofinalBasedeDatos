/*
 * UsuarioDAO.java
 *
 * Created on 18 de octubre de 2006, 05:41 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package mx.com.cyberdent.dao;

import java.sql.*;
import java.util.ArrayList;

import mx.com.cyberdent.objects.*;

/**
 *
 * @author Usuario
 */
public class UsuarioDAO {

    private static final String USERNAME="idUsuario";
    private static final String PASSWORD="password";
    private static final String FULLNAME="nombreCompleto";
    
    private static final String INSERT = "";
    
    private static final String UPDATE = "";
    
    private static final String DELETE = "";
    
    private static final String SELECT = "";
    
    private static final String EXISTS = "SELECT * FROM " + 
            MySQLDAOFactory.USUARIO + " WHERE " + USERNAME + " = ? ;";
    
    private static final String AUTHENTHICATE = " SELECT * FROM " + MySQLDAOFactory.USUARIO + " WHERE " + USERNAME + " = ? AND " 
            + PASSWORD  + " = ?; ";
    
    private Connection conn;
    
    public UsuarioDAO(Connection conn) {
        
        setConnection(conn);
    }
    
    public Connection getConnection() {
        return conn;
    }
    
    public void setConnection(Connection conn) {
        this.conn = conn;
    }
    
    public void create(Usuario user){
        
    }
    
    public void delete(String username){
        
    }
    
    public boolean exists(String username){
        
        //solo para que compile
        return false;
    } 
    
    private void loadObject(Usuario user, ResultSet rs) throws SQLException{
        
    }
    
    public Usuario find(String username){
        
        //solo para que compile
        return null;
    } 
    
    public ArrayList<Usuario> getUsuarios(){
        
        //solo para que compile
        return null;
    }
    
    public void update(Usuario user){
        
    }
    
    public boolean authenticate(Usuario user){
        //aqui van a hacer un select de usuarios donde el usuario y el 
        //password en md5 concuerden
        //si cocncuerdan es que es correcto
        //recuerden que para hacer un string a md5 mando el string en parentesis
        
        //por decir algo
        //INSERT INTO libros values(1,2,3,md5('algo que voy a sacar su hash'));
        //solo para que compile
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            getConnection().setAutoCommit(false);
            try {
                pstmt = getConnection().prepareStatement(this.AUTHENTHICATE);
                pstmt.setString(1, user.getIdUsuario());
                pstmt.setString(2, user.getPassword());
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
    
    
}
