package mx.com.cyberdent.objects; 

public class Historial {
	
	private String idHistorial;
        private String idPaciente;
        private String idUsuario;
	
	public Historial(){
		this.idHistorial="";
	}

        public void setIdHistorial(String id){
		this.idHistorial=id;
	}
        
	public String getIdHistorial() {
		return idHistorial;
	}
        
        public void setIdPaciente(String id){
		this.idPaciente=id;
	}
        
	public String getIdPaciente() {
		return idPaciente;
	}
        
        public void setIdUsuario(String id){
		this.idUsuario=id;
	}
        
	public String getIdUsuario() {
		return idUsuario;
	}

}
