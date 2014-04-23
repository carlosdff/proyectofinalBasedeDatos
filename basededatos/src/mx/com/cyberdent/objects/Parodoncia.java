package mx.com.cyberdent.objects;

public class Parodoncia {
   
    private int diente;
    private double bucalDistal;
    private double bucalMedio;
    private double bucalMesial;
    private double lingualDistal;
    private double lingualMedio;
    private double lingualMesial;
    private boolean vitality;
    private String movility;
    private String prognosis;
    private String idPlan;
       
    /**
     * Creates a new instance of Parodoncia
     */
    public Parodoncia() {
        this.setDiente(0);
        this.setBucalDistal(0);
        this.setBucalMedio(0);
        this.setBucalMesial(0);
        this.setLingualDistal(0);
        this.setLingualMedio(0);
        this.setLingualMesial(0);
        this.setVitality(false);
        this.setMovility("");
        this.setPrognosis("");
        this.setIdPlan("");        
    }

    public int getDiente() {
        return diente;
    }

    public void setDiente(int diente) {
        this.diente = diente;
    }

    public double getBucalDistal() {
        return bucalDistal;
    }

    public void setBucalDistal(double bucalDistal) {
        this.bucalDistal = bucalDistal;
    }

    public double getBucalMedio() {
        return bucalMedio;
    }

    public void setBucalMedio(double bucalMedio) {
        this.bucalMedio = bucalMedio;
    }

    public double getBucalMesial() {
        return bucalMesial;
    }

    public void setBucalMesial(double bucalMesial) {
        this.bucalMesial = bucalMesial;
    }

    public double getLingualDistal() {
        return lingualDistal;
    }

    public void setLingualDistal(double lingualDistal) {
        this.lingualDistal = lingualDistal;
    }

    public double getLingualMedio() {
        return lingualMedio;
    }

    public void setLingualMedio(double lingualMedio) {
        this.lingualMedio = lingualMedio;
    }

    public double getLingualMesial() {
        return lingualMesial;
    }

    public void setLingualMesial(double lingualMesial) {
        this.lingualMesial = lingualMesial;
    }

    public boolean isVitality() {
        return vitality;
    }

    public void setVitality(boolean vitality) {
        this.vitality = vitality;
    }

    public String getMovility() {
        return movility;
    }

    public void setMovility(String movility) {
        this.movility = movility;
    }

    public String getPrognosis() {
        return prognosis;
    }

    public void setPrognosis(String prognosis) {
        this.prognosis = prognosis;
    }

    public String getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(String idPlan) {
        this.idPlan = idPlan;
    }
    
}
