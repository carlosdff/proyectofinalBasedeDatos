package mx.com.cyberdent.objects; 

public class HistorialFamiliar extends Historial {
	
        private String idHistorialFamiliar;
	private String antecedentesFamiliares;
	        
	public HistorialFamiliar(){
		super(); 
		this.antecedentesFamiliares="";
	}
	
	public String getIdHistorialFamiliar(){
		return super.getIdHistorial();
	}
        
        public void setIdHistorialFamiliar(String idHistorialFamiliar){
            this.idHistorialFamiliar=idHistorialFamiliar;
            super.setIdHistorial(idHistorialFamiliar);
        }

	public String getAntecedentesFamiliares() {
		return antecedentesFamiliares;
	}

	public void setAntecedentesFamiliares(String antecedentesFamiliares) {
		this.antecedentesFamiliares = antecedentesFamiliares;
	}

}
