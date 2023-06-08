package Clases;
public class Registro {
    private String dni;
    private Double temperatura;
    //----------
    public Registro() {
    }
    public Registro(String dni, Double temperatura) {
        this.dni = dni;
        this.temperatura = temperatura;
    }
    //----------

    public String getDni() {
        return dni;
    }

    public Double getTemperatura() {
        return temperatura;
    }

    //----------

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setTemperatura(Double temperatura) {
        this.temperatura = temperatura;
    }

    //----------
    public void verRegistro(){
        System.out.println("REGISTRO");
        System.out.println("DNI: "+getDni());
        System.out.println("Temperatura: "+getTemperatura()+"ºC");
    }
    //----------
    //----------
}
