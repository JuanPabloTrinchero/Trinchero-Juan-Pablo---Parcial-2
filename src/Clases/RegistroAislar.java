package Clases;
public class RegistroAislar {
    private int kitTest;
    private Double temperatura;
    private String barrio;
    //----------
    public RegistroAislar() {
    }
    public RegistroAislar(int kitTest, Double temperatura, String barrio) {
        this.kitTest = kitTest;
        this.temperatura = temperatura;
        this.barrio = barrio;
    }
    //----------
    public int getKitTest() {
        return kitTest;
    }

    public Double getTemperatura() {
        return temperatura;
    }

    public String getBarrio() {
        return barrio;
    }
    //----------
    public void setKitTest(int kitTest) {
        this.kitTest = kitTest;
    }

    public void setTemperatura(Double temperatura) {
        this.temperatura = temperatura;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }
    //----------
}
