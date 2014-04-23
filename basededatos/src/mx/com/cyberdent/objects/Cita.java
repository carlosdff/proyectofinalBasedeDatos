package mx.com.cyberdent.objects; 

public class Cita {
	
	private String idCita;
	private String fechaCita;
	private String horaCita;
	private String estatusCita;
	private String comentarioCita;
        private String idPaciente;
        private String idUsuario;
	
	public Cita(){
		this.idCita="";
		this.fechaCita="";
		this.horaCita="";
		this.estatusCita="";
		this.comentarioCita="";
	}

	public String getIdCita() {
		return idCita;
	}
        public void setIdCita(String idCita) {
		this.idCita = idCita;
	}
	
	public String getComentarioCita() {
		return comentarioCita;
	}

	public void setComentarioCita(String comentarioCita) {
		this.comentarioCita = comentarioCita;
	}

	public String getEstatusCita() {
		return estatusCita;
	}

	public void setEstatusCita(String estatusCita) {
		this.estatusCita = estatusCita;
	}

	public String getFechaCita() {
		return fechaCita;
	}

	public void setFechaCita(String fechaCita) {
		this.fechaCita = fechaCita;
	}

	public String getHoraCita() {
		return horaCita;
	}

	public void setHoraCita(String horaCita) {
		this.horaCita = horaCita;
	}

    public String getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

}
