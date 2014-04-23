package mx.com.cyberdent.objects; 

public class Dentista {
	
	private String idDentista;
	private String nombre;
	private String telCelular;
	private String telCons;
	private String direccionCons;
	private String correoElecDentista;
	private String fechaNacDentista;
	private String especialidadDentista;
	
	public Dentista(){
		this.setIdDentista("");
		this.nombre="";
		this.telCelular="";
		this.telCons="";
		this.direccionCons="";
		this.correoElecDentista="";
		this.especialidadDentista="";
	}

	public String getIdDentista() {
		return idDentista;
	}

	public String getCorreoElecDentista() {
		return correoElecDentista;
	}

	public void setCorreoElecDentista(String correoElecDentista) {
		this.correoElecDentista = correoElecDentista;
	}

	public String getDireccionCons() {
		return direccionCons;
	}

	public void setDireccionCons(String direccionCons) {
		this.direccionCons = direccionCons;
	}

	public String getEspecialidadDentista() {
		return especialidadDentista;
	}

	public void setEspecialidadDentista(String especialidadDentista) {
		this.especialidadDentista = especialidadDentista;
	}

	public String getFechaNacDentista() {
		return fechaNacDentista;
	}

	public void setFechaNacDentista(String fechaNacDentista) {
		this.fechaNacDentista = fechaNacDentista;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelCelular() {
		return telCelular;
	}

	public void setTelCelular(String telCelular) {
		this.telCelular = telCelular;
	}

	public String getTelCons() {
		return telCons;
	}

	public void setTelCons(String telCons) {
		this.telCons = telCons;
	}

    public void setIdDentista(String idDentista) {
        this.idDentista = idDentista;
    }
    
    public boolean validateNombre(){
        if(nombre==null||nombre.trim().length()==0){
            return false; 
        }
        return true; 
    }
    
    public boolean validateTelCons(){
        if(telCons==null||telCons.trim().length()==0){
            return false; }
        return true; 
    }
	
}
