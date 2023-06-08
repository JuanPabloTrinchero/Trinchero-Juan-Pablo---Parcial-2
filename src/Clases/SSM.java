package Clases;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class SSM {  //Clase Contenedora
    private String nombreEntidad;
    private ArrayList<Persona> listaPacientes;
    private boolean reservaKits;
    private ArrayList<Persona> sanos;
    private ArrayList<Persona> aislar;
    //----------
    public SSM() {
    }
    public SSM(String nombreEntidad) {
        this.nombreEntidad = nombreEntidad;
        this.listaPacientes = new ArrayList<>();
        this.sanos = null;
        this.aislar = null;
    }
    //----------
    public String getNombreEntidad() {
        return nombreEntidad;
    }
    public ArrayList<Persona> getListaPacientes() {
        return listaPacientes;
    }

    public boolean getReservaKits() {
        return reservaKits;
    }

    public ArrayList<Persona> getSanos() {
        return sanos;
    }

    public ArrayList<Persona> getAislar() {
        return aislar;
    }
    //----------
    public void setReservaKits(boolean tieneONo) {
        this.reservaKits = reservaKits;
    }
    //----------
    public void verListaPersonas(){
        for(Persona p : listaPacientes){
            p.verPersona();
        }
    }

    //----------
    //Tenemos una cantidad limitada de reactivos por lo cual al realizar un registro
    //sin el kit debemos lanzar una excepción creada por nosotros. Al ser lanzada la excepción le
    //preguntaremos al SSM si cuenta con más test y si es positivo, ingresaremos la nueva
    //cantidad

    public boolean tieneKit(Persona persona) throws ExcepcionPropia{
        boolean disponible;
        if(persona.getKitAsignado()!=null){
            disponible = true;
        }else{
            disponible = false;
            throw new ExcepcionPropia();
        }
        return disponible;
    }

    //Las personas se almacenan por orden de llegada. Debemos controlar que no se
    //repita el DNI. Al ingresar esa persona, le asignaremos un número de kit (generado por el
    //sistema).

    public void agregarPersona(Persona persona){
        try{
            if(tieneKit(persona)){
                if(dniExiste(persona.getRegistro().getDni())){
                    System.out.println("DNI ya existente");
                }else{
                    listaPacientes.add(persona);
                    System.out.println("Persona agregada");
                }
            }
        }catch(Exception e){
            System.out.println("Sin kit asignado");
            if(reservaKits){
                Integer kitNuevo = kitRandom();
                persona.setKitAsignado(kitNuevo);
                System.out.println("Nuevo Kit Asignado: "+kitNuevo);
            }else{
                System.out.println("Sin reservas de Kits");
            }
        }
    }

    public boolean dniExiste(String dni){
        boolean existe=false;
        for(Persona p : listaPacientes){
            if(p.getRegistro().getDni()==dni){
                existe=true;
            }
        }
        return existe;
    }

    public Integer kitRandom(){
        Random ran = new Random();
        return ran.nextInt(50);
    }

    //Luego de ingresar a estas personas vamos a invocar un método llamado “testear” donde
    //evaluaremos la temperatura de cada una de las personas. Con cada evaluación generamos
    //una tabla donde la clave será el número de kit y el valor contendrá un registro que
    //contendrá el DNI y la temperatura (generada de manera random entre 36 y 39 grados).
    public HashMap<Integer, Registro> testear(){
        HashMap<Integer, Registro> hm = new HashMap<>();
        Registro registroTest = new Registro();
        for(Persona persona : listaPacientes){
            registroTest.setDni(persona.getRegistro().getDni());
            registroTest.setTemperatura(persona.getRegistro().getTemperatura());

            hm.put(persona.getKitAsignado(), registroTest);
        }
        return hm;
    }

    public void verHashMap(HashMap<Integer, Registro> hm){
        for(Integer index : hm.keySet()){
            Registro reg = hm.get(index);
            System.out.println(index+" - DNI: "+reg.getDni()+" - Temperatura: "+reg.getTemperatura()+"ºC");
        }
    }

    public Double intervaloTemperatura(){


        return 0.0;
    }

    //Una vez realizados todos los test, invocamos un método llamado “aislar” donde si la
    //temperatura supera los 38 grados (inclusive) lanzaremos una excepción que contendrá el
    //número de test y el barrio. Como tratamiento de ese error, esos datos se deben almacenar
    //en un archivo binario de objetos llamado “urgente.dat” (opcional).

    public void aislarArchiBin() throws Exception{
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("urgente.dat"));
            ArrayList<RegistroAislar> personasAislar = new ArrayList<>();
            boolean estado = true;
            try {
                for(Persona persona : listaPacientes){
                    try{
                        if(persona.getRegistro().getTemperatura() < 38){
                            estado = true;
                            //sanos.add(persona);
                        }else{
                            estado = false;
                        }
                    }catch(Exception e){
                        //aislar.add(persona);
                        RegistroAislar ra = new RegistroAislar(persona.getKitAsignado(), persona.getRegistro().getTemperatura(), persona.getBarrio());
                        personasAislar.add(ra);
                    }
                }
                oos.writeObject(personasAislar);
                System.out.println("Personas guardadas en el archivo binario.");
            } catch (Throwable var6) {
                try {
                    oos.close();
                } catch (Throwable var5) {
                    var6.addSuppressed(var5);
                }
                throw var6;
            }
            oos.close();
        } catch (IOException var7) {
            var7.printStackTrace();
        }
    }

    public List<Registro> archivoBinAList(String nombreArchivo) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreArchivo));

            List var3;
            try {
                List<Registro> registro = (List)ois.readObject();
                var3 = registro;
            } catch (Throwable var5) {
                try {
                    ois.close();
                } catch (Throwable var4) {
                    var5.addSuppressed(var4);
                }

                throw var5;
            }
            ois.close();
            return var3;
        } catch (ClassNotFoundException | IOException var6) {
            var6.printStackTrace();
            return null;
        }
    }

    //Para finalizar nuestro trabajo generamos un JSON (que también debemos persistir en disco)
    //donde tendremos un objeto con dos claves (“sanos” y “aislar”) que serán arreglos. En el
    //primer arreglo guardaremos los datos de la persona y en el segundo, kit, temperatura y
    //barrio. En la primera clave mostraremos la información de las personas que no superen los
    //38 grados de temperatura tomada y en el segundo los casos sospechosos (opcional)

    public void sanosYAislarson(){
        try(FileWriter writer = new FileWriter("sanosyAislar.json")){
            String jsonSanos=null;
            String jsonAislar=null;

            for(Persona p : listaPacientes){
                Gson gson = new GsonBuilder().create();
                if(p.getRegistro().getTemperatura() < 38){
                    jsonSanos += gson.toJson(p);
                }else{
                    RegistroAislar registroAislamiento = new RegistroAislar();

                    registroAislamiento.setKitTest(p.getKitAsignado());
                    registroAislamiento.setTemperatura(p.getRegistro().getTemperatura());
                    registroAislamiento.setBarrio(p.getBarrio());

                    jsonAislar += gson.toJson(registroAislamiento);
                }
            }
            writer.append(jsonSanos);
            writer.append(jsonAislar);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    //----------
}
