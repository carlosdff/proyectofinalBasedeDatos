package mx.com.cyberdent.objects; 

public class Asegurado extends Paciente {
	
	private String nombreSeguro;
	private String numCredencial;
	
	/*public Asegurado(String id){
		super(id);
		this.nombreSeguro="";
		this.numCredencial="";
	}*/
	
	public String getIdAsegurado(){
		return super.getIdPaciente();
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

}
