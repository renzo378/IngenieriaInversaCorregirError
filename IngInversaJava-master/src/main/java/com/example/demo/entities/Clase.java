package com.example.demo.entities;

import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Clase {

    private String nombre = " ";

    //Cambiar por boolean cuando se implemente
    private String esAbstracta;
    //Cambiar por boolean cuando se implemente
    private String esAuditable;

    private String nombrePaquete;

    private String clasePadre;

    private int cantidadImportaciones = 0;

    private List<String> clasesGenericas = new ArrayList<String>();

    private List<String> interfacesImplementadas = new ArrayList<String>();

    private List<String> anotacionesExternas = new ArrayList<String>();

    private List<String> anotacionesInternas = new ArrayList<String>();

    private List<Metodo> metodos = new ArrayList<Metodo>();

    private List<Atributo> atributos = new ArrayList<Atributo>();


}
