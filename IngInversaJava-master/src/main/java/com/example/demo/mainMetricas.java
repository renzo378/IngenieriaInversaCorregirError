package com.example.demo;


import com.example.demo.entities.Atributo;
import com.example.demo.entities.Clase;
import com.example.demo.entities.Metodo;
import com.example.demo.gen.*;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import static org.antlr.v4.runtime.CharStreams.fromFileName;

public class mainMetricas {



    public static void main(String[] args){

        try{
            String fuente = "C:\\Users\\Usuario\\eclipse-workspace\\testeosIngInversaJava\\Datos\\Persona.java";
            CharStream cs = fromFileName(fuente);
            JavaLexer lexer = new JavaLexer(cs);
            CommonTokenStream tokenStream = new CommonTokenStream(lexer);
            JavaParser parser = new JavaParser(tokenStream);
            ParseTree tree = parser.compilationUnit();
            Clase clase = new Clase();
            VisitorMetricas visitorMetricas = new VisitorMetricas();
            visitorMetricas.setClase(clase);
            VisitorAtributoMetodo visitorAtributoMetodo = new VisitorAtributoMetodo();
            visitorAtributoMetodo.setClase(clase);
            visitorAtributoMetodo.setVisitorMetricas(visitorMetricas);
            VisitorClase visitorClase = new VisitorClase(visitorAtributoMetodo, visitorMetricas);
            visitorClase.setClase(clase);

            visitorClase.visit(tree);

            System.out.println(clase.toString());

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
