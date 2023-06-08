import Clases.Persona;
import Clases.Registro;
import Clases.SSM;
import java.util.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static <HashMap> void main(String[] args) {
        SSM ssm1 = new SSM("HMC");
        ssm1.setReservaKits(false);

        //CREAR PERSONAS
        Persona p1 = new Persona("Juan", "Trin", 32, "Ruta", "Tornero", new Registro("35420148", 37.0));
        Persona p2 = new Persona("Jona", "Tav", 32, "Esp", "TorneroAutop", new Registro("32518654", 37.0));
        Persona p3 = new Persona("Vik", "Hu", 23, "Yu", "AUGI", new Registro("12489653", 40.0));

        //AGREGAR KITS A PERSONAS
        p1.setKitAsignado(10);
        p2.setKitAsignado(20);
        p3.setKitAsignado(30);

        //REGISTRAR PERSONAS
        ssm1.agregarPersona(p1);
        ssm1.agregarPersona(p2);
        ssm1.agregarPersona(p3);

        //VER LISTA COMPLETA
        ssm1.verListaPersonas();

        //TESTEOS
        java.util.HashMap<Integer, Registro> testeos = new java.util.HashMap<>();
        for(Persona p : ssm1.getListaPacientes()){
            testeos = ssm1.testear();
        }
        ssm1.verHashMap(testeos);

        //AISLAR EN ARCHIVO BINARIO
        try{
            ssm1.aislarArchiBin();
        }catch(Exception e){
            e.printStackTrace();
        }
        //VER ARCHIVO BINARIO
        List<Registro> registros = new ArrayList<>();
        registros = ssm1.archivoBinAList("urgente.dat");

        System.out.println("Mostrar Contenido de Archivo Binario");
        for(Registro reg : registros){
            reg.verRegistro();
        }

        //JASON
        try{
            ssm1.sanosYAislarson();
        }catch(Exception e){
            e.printStackTrace();
        }



    }
}