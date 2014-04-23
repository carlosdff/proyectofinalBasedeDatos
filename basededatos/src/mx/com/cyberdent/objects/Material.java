package mx.com.cyberdent.objects; 

public class Material {
	
	private String idMaterial;
	private String nombreMat;
	private String marca;
	private String descripcion;
	private int existencia;
	private int puntoReorden;
        private int cantidad; 
	
	public Material(){
		
	}

	public String getIdMaterial() {
		return idMaterial;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getExistencia() {
		return existencia;
	}

	public void setExistencia(int existencia) {
		this.existencia = existencia;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getNombreMat() {
		return nombreMat;
	}

	public void setNombreMat(String nombreMat) {
		this.nombreMat = nombreMat;
	}

	public int getPuntoReorden() {
		return puntoReorden;
	}

	public void setPuntoReorden(int puntoReorden) {
		this.puntoReorden = puntoReorden;
	}

    public void setIdMaterial(String idMaterial) {
        this.idMaterial = idMaterial;
    }
    
     public boolean validateNombre(){
        if(nombreMat==null||nombreMat.trim().length()==0){
            return false; 
        }
        return true; 
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    

}
