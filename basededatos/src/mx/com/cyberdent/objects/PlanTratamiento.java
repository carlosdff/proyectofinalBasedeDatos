package mx.com.cyberdent.objects; 

public class PlanTratamiento {
	
	private String idPlan;
	private String actoClinico;
	private float costo;
	private float aCuenta;
	private String saldo;
	//private String radiografiaAntes;
	//private String radiografiaDespues;
	private String fechaTratamiento;
	private String evolucion;
	private String diagnostico;
	private String pronostico;
        private String idHistorial; 
	
	public PlanTratamiento(){
		
	}

	
        public String getIdPlan() {
		return idPlan;
	}

	public String getActoClinico() {
		return actoClinico;
	}

	public void setActoClinico(String actoClinico) {
		this.actoClinico = actoClinico;
	}

	public float getACuenta() {
		return aCuenta;
	}

	public void setACuenta(float cuenta) {
		aCuenta = cuenta;
	}

	public float getCosto() {
		return costo;
	}

	public void setCosto(float costo) {
		this.costo = costo;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}

	public String getEvolucion() {
		return evolucion;
	}

	public void setEvolucion(String evolucion) {
		this.evolucion = evolucion;
	}

	public String getFechaTratamiento() {
		return fechaTratamiento;
	}

	public void setFechaTratamiento(String fechaTratamiento) {
		this.fechaTratamiento = fechaTratamiento;
	}

	public String getPronostico() {
		return pronostico;
	}

	public void setPronostico(String pronostico) {
		this.pronostico = pronostico;
	}

	/*public String getRadiografiaAntes() {
		return radiografiaAntes;
	}

	public void setRadiografiaAntes(String radiografiaAntes) {
		this.radiografiaAntes = radiografiaAntes;
	}

	public String getRadiografiaDespues() {
		return radiografiaDespues;
	}

	public void setRadiografiaDespues(String radiografiaDespues) {
		this.radiografiaDespues = radiografiaDespues;
	}*/

	public String getSaldo() {
		return saldo;
	}

	public void setSaldo(String saldo) {
		this.saldo = saldo;
	}

    public void setIdPlan(String idPlan) {
        this.idPlan = idPlan;
    }

    public String getIdHistorial() {
        return idHistorial;
    }

    public void setIdHistorial(String idHistorial) {
        this.idHistorial = idHistorial;
    }

}
