package mx.com.cyberdent.objects;

public class Endodoncia {
    
    private int diente;
    private String conducto;
    private String conductometriaRfAnat;
    private int conductometriaLima;
    private double conductometriaMm;
    private int apiceLima;
    private double apiceMm;
    private int cuerpoLim;
    private double cuerpoMm;
    private String idPlan;
       
    /**
     * Creates a new instance of Endodoncia
     */
    public Endodoncia() {
        this.setDiente(0);
        this.setConducto("");
        this.setConductometriaRfAnat("");
        this.setConductometriaLima(0);
        this.setConductometriaMm(0);
        this.setApiceLima(0);
        this.setApiceMm(0);
        this.setCuerpoLim(0);
        this.setCuerpoMm(0);
        this.setIdPlan("");  
    }

    public int getDiente() {
        return diente;
    }

    public void setDiente(int diente) {
        this.diente = diente;
    }

    public String getConducto() {
        return conducto;
    }

    public void setConducto(String conducto) {
        this.conducto = conducto;
    }

    public String getConductometriaRfAnat() {
        return conductometriaRfAnat;
    }

    public void setConductometriaRfAnat(String conductometriaRfAnat) {
        this.conductometriaRfAnat = conductometriaRfAnat;
    }

    public int getConductometriaLima() {
        return conductometriaLima;
    }

    public void setConductometriaLima(int conductometriaLima) {
        this.conductometriaLima = conductometriaLima;
    }

    public double getConductometriaMm() {
        return conductometriaMm;
    }

    public void setConductometriaMm(double conductometriaMm) {
        this.conductometriaMm = conductometriaMm;
    }

    public int getApiceLima() {
        return apiceLima;
    }

    public void setApiceLima(int apiceLima) {
        this.apiceLima = apiceLima;
    }

    public double getApiceMm() {
        return apiceMm;
    }

    public void setApiceMm(double apiceMm) {
        this.apiceMm = apiceMm;
    }

    public int getCuerpoLim() {
        return cuerpoLim;
    }

    public void setCuerpoLim(int cuerpoLim) {
        this.cuerpoLim = cuerpoLim;
    }

    public double getCuerpoMm() {
        return cuerpoMm;
    }

    public void setCuerpoMm(double cuerpoMm) {
        this.cuerpoMm = cuerpoMm;
    }

    public String getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(String idPlan) {
        this.idPlan = idPlan;
    }
     
}
