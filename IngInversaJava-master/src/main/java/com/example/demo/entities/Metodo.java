package com.example.demo.entities;

import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Metodo {

    private Modificador modificador;

    private String nombre;

    private String tipoRetorno;

    //Despues cambiar a boolean cuando se implemente
    private String esAbstracto;

    private List<String> parametros = new ArrayList<String>();

    private int cantLineas = 0;

    private ComplejidadCiclomatica complejidadCiclomatica;


    public void incrementarLineas(){
        this.cantLineas = this.cantLineas + 1;
    }
    public int calcularCantLineas(){
        //cuando agregemos bucles corregir ecuacion
        return this.cantLineas - this.complejidadCiclomatica.getCantFor();
    }


}
