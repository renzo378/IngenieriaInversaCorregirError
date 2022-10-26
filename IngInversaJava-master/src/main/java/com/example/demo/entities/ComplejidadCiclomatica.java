package com.example.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ComplejidadCiclomatica {


    //se necesitan diferenciar para poder calcular la cantidad de lineas
    //de un metodo correctamente
    private int cantFor = 0;

    private int cantForEach = 0;

    private int cantIf = 0;

    private int cantElse = 0;

    private int cantTry = 0;

    private int cantThrow = 0;

    private int cantCatch = 0;

    private int cantFinally = 0;

    private int cantCase = 0; //cuenta tanto case como default, debido a que siempre tendran la misma ponderacion

    private int cantDoWhile = 0;

    private int cantReturn = 0;



    public void incrementarFor(){
        this.cantFor++;
    }
    public void incrementarForEach(){
        this.cantForEach++;
    }
    public void incrementarIf(){
        this.cantIf++;
    }
    public void incrementarElse(){
        this.cantElse++;
    }
    public void incrementarTry(){
        this.cantTry++;
    }
    public void incrementarThrow(){
        this.cantThrow++;
    }
    public void incrementarCatch(){
        this.cantCatch++;
    }
    public void incrementarFinally(){
        this.cantFinally++;
    }
    public void incrementarCase(){
        this.cantCase++;
    }
    public void incrementarDoWhile(){
        this.cantDoWhile++;
    }
    public void incrementarReturn(){
        this.cantReturn++;
    }


}
