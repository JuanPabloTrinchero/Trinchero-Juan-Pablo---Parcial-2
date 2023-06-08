package Clases;

public class Persona {
    private String nombre;
    private String apellido;
    private int edad;
    private String barrio;
    private String ocupacion;
    private Registro registro;
    private Integer kitAsignado;
    //---------
    public Persona() {
    }
    public Persona(String nombre, String apellido, int edad, String barrio, String ocupacion, Registro registro) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.barrio = barrio;
        this.ocupacion = ocupacion;
        this.registro = registro;
    }
    //---------

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getEdad() {
        return edad;
    }

    public String getBarrio() {
        return barrio;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public Registro getRegistro() {
        return registro;
    }

    public Integer getKitAsignado() {
        return kitAsignado;
    }

    //---------

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public void setRegistro(Registro registro) {
        this.registro = registro;
    }

    public void setKitAsignado(Integer kitAsignado) {
        this.kitAsignado = kitAsignado;
    }

    //---------
    //Nombre y Apellido, edad, barrio, DNI y ocupación
    public void verPersona(){
        System.out.println("PERSONA");
        System.out.println("Nombre: "+getNombre());
        System.out.println("Apellido: "+getApellido());
        System.out.println("Edad: "+getEdad());
        System.out.println("Barrio: "+getBarrio());
        System.out.println("Ocupacion: "+getOcupacion());
        if(kitAsignado!=null){
            System.out.println("Kit Asignado: "+getKitAsignado());
        }else{
            System.out.println("Kit Asignado: Ninguno");
        }
        getRegistro().verRegistro();
        System.out.printf("\n");
    }

    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", edad=" + edad +
                ", barrio='" + barrio + '\'' +
                ", ocupacion='" + ocupacion + '\'' +
                ", registro=" + registro +
                ", kitAsignado=" + kitAsignado +
                '}';
    }
    //---------
    //---------
}
