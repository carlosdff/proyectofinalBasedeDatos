package mx.com.cyberdent.objects; 

public class Paciente {
	
	private String idPaciente;
	private String nombrePaciente;
	private String telParticularPaciente;
	private String telCelPaciente;
	private String telOfiPaciente;
	private String dirPaciente;
	private String edoCivilPaciente;
	private String ocupacionPaciente;
	private String correoElecPaciente;
	private String fechaNacPaciente;
	private String idDentista;
        private String idusuario;
        private boolean asegurado; 
	private String nombreSeguro;
	private String numCredencial;
      //  private String nomDentista;
        
        
	/*public Paciente(String id){
		this.idPaciente=id;
		this.nombrePaciente="";
		this.telParticularPaciente="";
		this.telCelPaciente="";
		this.telOfiPaciente="";
		this.dirPaciente="";
		this.edoCivilPaciente="";
		this.ocupacionPaciente="";
		this.correoElecPaciente="";
		this.fechaNacPaciente="";
		this.estatusPaciente="";
	}*/

	public void setIdPaciente(String idPaciente) {
		this.idPaciente = idPaciente; 
	}
        
        public String getIdPaciente() {
		return idPaciente;
	}

	public String getCorreoElecPaciente() {
		return correoElecPaciente;
	}

	public void setCorreoElecPaciente(String correoElecPaciente) {
		this.correoElecPaciente = correoElecPaciente;
	}

	public String getDirPaciente() {
		return dirPaciente;
	}

	public void setDirPaciente(String dirPaciente) {
		this.dirPaciente = dirPaciente;
	}

	public String getEdoCivilPaciente() {
		return edoCivilPaciente;
	}

	public void setEdoCivilPaciente(String edoCivilPaciente) {
		this.edoCivilPaciente = edoCivilPaciente;
	}

	/*public String getEstatusPaciente() {
		return estatusPaciente;
	}

	public void setEstatusPaciente(String estatusPaciente) {
		this.estatusPaciente = estatusPaciente;
	}*/

	public String getFechaNacPaciente() {
		return fechaNacPaciente;
	}

	public void setFechaNacPaciente(String fechaNacPaciente) {
		this.fechaNacPaciente = fechaNacPaciente;
	}

	public String getNombrePaciente() {
		return nombrePaciente;
	}

	public void setNombrePaciente(String nombrePaciente) {
		this.nombrePaciente = nombrePaciente;
	}

	public String getOcupacionPaciente() {
		return ocupacionPaciente;
	}

	public void setOcupacionPaciente(String ocupacionPaciente) {
		this.ocupacionPaciente = ocupacionPaciente;
	}

	public String getTelCelPaciente() {
		return telCelPaciente;
	}

	public void setTelCelPaciente(String telCelPaciente) {
		this.telCelPaciente = telCelPaciente;
	}

	public String getTelOfiPaciente() {
		return telOfiPaciente;
	}

	public void setTelOfiPaciente(String telOfiPaciente) {
		this.telOfiPaciente = telOfiPaciente;
	}

	public String getTelParticularPaciente() {
		return telParticularPaciente;
	}

	public void setTelParticularPaciente(String telParticularPaciente) {
		this.telParticularPaciente = telParticularPaciente;
	}

    public String getIdDentista() {
        return idDentista;
    }

    public void setIdDentista(String iddentisa) {
        this.idDentista = iddentisa;
    }

    public String getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(String idusuario) {
        this.idusuario = idusuario;
    }
        
    public boolean validateNombre(){
        if(nombrePaciente==null||nombrePaciente.trim().length()==0){
            return false; 
        }
        return true; 
    }
    
    public boolean validateTelPar(){
        if(telParticularPaciente==null||telParticularPaciente.trim().length()==0){
            return false; }
        return true; 
    }
    

    public boolean isAsegurado() {
        return asegurado;
    }

    public void setAsegurado(boolean asegurado) {
        this.asegurado = asegurado;
    }

    public String getNombreSeguro() {
        return nombreSeguro;
    }

    public void setNombreSeguro(String nombreSeguro) {
        this.nombreSeguro = nombreSeguro;
    }

    public String getNumCredencial() {
        return numCredencial;
    }

    public void setNumCredencial(String numCredencial) {
        this.numCredencial = numCredencial;
    }

    /*public String getNomDentista() {
        return nomDentista;
    }

    public void setNomDentista(String nomDentista) {
        this.nomDentista = nomDentista;
    }*/

   
    
 
}
