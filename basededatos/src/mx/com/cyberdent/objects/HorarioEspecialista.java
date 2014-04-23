package mx.com.cyberdent.objects; 

public class HorarioEspecialista {
	
	private String fechaDisp;
	private String horaDisp;
	
	public HorarioEspecialista(String fecha, String hora){
		this.fechaDisp=fecha;
		this.horaDisp=hora;
	}

	public String getFechaDisp() {
		return fechaDisp;
	}

	public void setFechaDisp(String fecha) {
		this.fechaDisp = fecha;
	}

	public String getHoraDisp() {
		return horaDisp;
	}

	public void setHoraDisp(String hora) {
		this.horaDisp = hora;
	}

}
