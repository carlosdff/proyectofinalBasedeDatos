/*
 * MySQLDAOFactory.java
 *
 * Created on 17 de octubre de 2006, 03:24 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package mx.com.cyberdent.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Usuario
 */
public class MySQLDAOFactory {
    
    private final static String DRIVER_NAME = "com.mysql.jdbc.Driver";
    
    //Aqui ponen su usuario
    private final static String USERNAME = "root";
    
    //El password de usuario
    private final static String PASSWORD = "10=alexis";
    
    //El IP de la base de datos
    private final static String IP = "localhost";
    
    //nombre de la base de datos a conectarse
    private final static String DATABASE = "cyberdent";
    
    //nombres de las tablas
    public final static String ASEGURADO = "asegurado";
    public final static String CITA = "cita";
    public final static String DENTISTA = "dentista";
    public final static String HISTORIAL = "historial";
    public final static String HISTORIALFAMILIAR = "historialfamiliar";
    public final static String HISTORIALPERSONAL = "historialpersonal";
    public final static String HISTORIALPERSONAL_ALERGIAS = "historialpersonal_alergias";
    public final static String MATERIAL = "material";
    public final static String MATERIAL_PLANT = "material_plantratamiento";
    public final static String PACIENTE = "paciente";
    public final static String CONSULTA_PACIENTE = "consulta_paciente";
    public final static String PACIENTE_TELEFONO = "paciente_telefono";
    public final static String PLANTRATAMIENTO = "plantratamiento";
    public final static String PLANTRAT_END = "plantratamiento_endodoncia";
    public final static String PLANTRAT_PAR = "plantratamiento_parodoncia";
    public final static String USUARIO = "usuario";
    public final static String PAC_HIST = "paciente_historial";
    
    //Aqui se almacena la conexion
    private Connection connection;
    
    
    //Con esto se carga el Driver en la memoria
    //y se instancia
    //Si alguna vez les aparece un error que no puede cargar el driver
    //es que no esta encontrando el Driver en el classpath
    //porque aunque no hayan agregado la libreria, esto va a compilar
    public static void loadDriver() throws ClassNotFoundException,
            InstantiationException, IllegalAccessException {
        Class.forName(DRIVER_NAME).newInstance();
    }
    
    
    //constructor de el objeto MYSQLDAOFactory
    //Generala conexion
    //y regresa una conexion
    public MySQLDAOFactory() {
        try {
            if (connection == null) {
                loadDriver();
                String url = "jdbc:mysql://" + IP +
                             "/" + DATABASE;

                connection = DriverManager.getConnection(url, USERNAME,
                        PASSWORD);
            }
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        } catch (InstantiationException ie) {
            ie.printStackTrace();
        } catch (IllegalAccessException iae) {
            iae.printStackTrace();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
    
    //Accesor para darle a los DAOs la conexi[on, nadie mas la puede tomar a
    //menos que heredaran de este
    protected Connection getConnection() {
        return connection;
    }

    
    /*
     *Metodos para cerrar la conexion, cualquiera de los dos es correcto,
     *la diferencia es que el primero lanza la excepcion y el segundo dentro del
     *mismo metodo se cacha
     */
    
    //Cierra la conexi[on
    public void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
    
    //Cierra la conexion
    public void close(){
        try {
            connection.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
    
    /**
     *Metodos para obtener los DAOs,
     *regresan un nuevo objeto de el DAO espec[ifico y le mandan la referencia
     *a la conexion
     *
     */
    
    public PacienteDAO getPacienteDAO(){
        return new PacienteDAO(getConnection());
    }
    
    public UsuarioDAO getUsuarioDAO(){
        return new UsuarioDAO(getConnection());
    }
    
    public AseguradoDAO getAseguradoDAO(){
        return new AseguradoDAO(getConnection());
    }
    
    public CitaDAO getCitaDAO(){
        return new CitaDAO(getConnection());
    }
    
    public DentistaDAO getDentistaDAO(){
        return new DentistaDAO(getConnection());
    }
    
    public HistorialDAO getHistorialDAO(){
        return new HistorialDAO(getConnection()); 
    }
    
    public HistorialFamiliarDAO getHistorialFamiliarDAO(){
        return new HistorialFamiliarDAO(getConnection()); 
    }
    
    public HistorialPersonalDAO getHistorialPersonalDAO(){
        return new HistorialPersonalDAO(getConnection()); 
    }
    
    public MaterialDAO getMaterialDAO(){
        return new MaterialDAO(getConnection()); 
    }
    
    public PlanTratamientoDAO getPlanTratamientoDAO(){
        return new PlanTratamientoDAO(getConnection()); 
    }
    
    public EndodonciaDAO getEndodonciaDAO(){
        return new EndodonciaDAO(getConnection()); 
    }
    
    public ParodonciaDAO getParodonciaDAO(){
        return new ParodonciaDAO(getConnection()); 
    }
}
