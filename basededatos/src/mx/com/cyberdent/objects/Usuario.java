package mx.com.cyberdent.objects; 

public class Usuario {
	
	private String idUsuario;
	private String password;
	private String nombre;
	
	/*public Usuario(String id){
		this.idUsuario=id;
		this.password="";
		this.nombre="";
	}*/

	public String getIdUsuario() {
		return idUsuario;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public boolean validate(){
        if(idUsuario==null||idUsuario.trim().length()==0){
            return false; 
        }
        if(password==null||password.trim().length()==0){
            return false; 
        }
        return true; 
    }
    
}
