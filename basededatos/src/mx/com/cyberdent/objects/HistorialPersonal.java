package mx.com.cyberdent.objects; 

public class HistorialPersonal extends Historial {
	
	private String idHistorialPersonal;
        private float peso;
	private float estatura;
	private String antecendentes;
	private String antecedentesNoPat;
	private String antecedentesQuirurgicos;
	private String resumenPrevio;
	private String padecimientoActual;
	private String alergias;
        private String estatusPaciente;
	
	public HistorialPersonal(){
		super();
		this.idHistorialPersonal="";
                this.peso=0;
		this.estatura=0;
		this.antecendentes="";
		this.antecedentesNoPat="";
		this.antecedentesQuirurgicos="";
		this.resumenPrevio="";
		this.padecimientoActual="";
		this.alergias="";
                this.estatusPaciente="";
	}
	
	public String getIdHistorialPersonal(){
            return super.getIdHistorial();
	}
        
        public void setIdHistorialPersonal(String idHistorialPersonal){
            this.idHistorialPersonal=idHistorialPersonal;
            super.setIdHistorial(idHistorialPersonal);
        }

	public String getEstatuspaciente(){
            return estatusPaciente;
        }
        
        public void setEstatuspaciente(String estatusPaciente){
            this.estatusPaciente=estatusPaciente;
        }
        
        public String getAntecedentesNoPat() {
		return antecedentesNoPat;
	}

	public void setAntecedentesNoPat(String antecedentesNoPat) {
		this.antecedentesNoPat = antecedentesNoPat;
	}

	public String getAntecedentesQuirurgicos() {
		return antecedentesQuirurgicos;
	}

	public void setAntecedentesQuirurgicos(String antecedentesQuirurgicos) {
		this.antecedentesQuirurgicos = antecedentesQuirurgicos;
	}

	public String getAntecendentes() {
		return antecendentes;
	}

	public void setAntecendentes(String antecendentes) {
		this.antecendentes = antecendentes;
	}

	public float getEstatura() {
		return estatura;
	}

	public void setEstatura(float estatura) {
		this.estatura = estatura;
	}

	public String getPadecimientoActual() {
		return padecimientoActual;
	}

	public void setPadecimientoActual(String padecimientoActual) {
		this.padecimientoActual = padecimientoActual;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	public String getResumenPrevio() {
		return resumenPrevio;
	}

	public void setResumenPrevio(String resumenPrevio) {
		this.resumenPrevio = resumenPrevio;
	}

}

