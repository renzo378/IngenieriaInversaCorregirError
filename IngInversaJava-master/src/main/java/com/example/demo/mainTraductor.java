package com.example.demo;

import com.example.demo.entities.*;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class mainTraductor {
    public static void main(String[] args){

        //Modelo de prueba

        //Creamos un paquete
        Paquete entidades = Paquete.builder().nombre("Entidades").clasesContenidas(new ArrayList<Clase>()).build();
        Paquete controladores = Paquete.builder().nombre("Controllers").build();
        List<Paquete> paquetes = new ArrayList<Paquete>();


        //Creamos clase Persona,Domicilio y Animal, y las agregamos a paquete Entidades
        Clase persona = Clase.builder()
                             .nombre("persona")
                             .esAbstracta("True")
                             .esAuditable("True")
                             .atributos(new ArrayList<Atributo>())
                             .metodos(new ArrayList<Metodo>())
                             .build();
        entidades.getClasesContenidas().add(persona);

        Clase domicilio = Clase.builder()
                .nombre("domicilio")
                .esAbstracta("True")
                .esAuditable("False")
                .build();
        entidades.getClasesContenidas().add(domicilio);

        Clase animal = Clase.builder()
                .nombre("animal")
                .esAbstracta("False")
                .esAuditable("False")
                .build();
        entidades.getClasesContenidas().add(animal);

        //Creamos dos atributos para la clase persona
        Modificador modificadorPersona = Modificador.builder()
                                                    .visibilidad("private")
                                                    .build();
        Atributo nombre = Atributo.builder()
                                  .nombre("nombre")
                                  .tipo("String")
                                  .modificador(modificadorPersona)
                                  .build();
        Modificador modificadorEdad = Modificador.builder()
                                                 .visibilidad("private")
                                                 .build();
        Atributo edad = Atributo.builder()
                                .nombre("edad")
                                .modificador(modificadorEdad)
                                .tipo("int")
                                .build();


        //Creamos un metodo para la clase Persona
        Modificador modificadorCaminar = Modificador.builder()
                                                    .visibilidad("public")
                                                    .build();
        List<String> listaParametrosCaminar = new ArrayList<>();
        listaParametrosCaminar.add("int pasos");
        listaParametrosCaminar.add("Date hora");
        Metodo caminar = Metodo.builder()
                               .nombre("caminar")
                               .tipoRetorno("void")
                               .modificador(modificadorCaminar)
                               .parametros(listaParametrosCaminar)
                               .build();

        persona.getMetodos().add(caminar);

        paquetes.add(entidades);
        paquetes.add(controladores);

        //CONTROLADOR

        try{
            STGroup group= new STGroupFile("src/main/resources/templates/main.stg");
            ST plantilla = group.getInstanceOf("traducir");
            plantilla.add("paquetes",paquetes);

            String ruta = "src/main/resources/generated/resultado.xmi";
            File archivo = new File(ruta);
            BufferedWriter bw;
            bw = new BufferedWriter(new FileWriter(archivo));


            //VISTA

            bw.write(plantilla.render());
            bw.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
