package com.example.demo.gen;


import com.example.demo.entities.Atributo;
import com.example.demo.entities.Clase;
import com.example.demo.entities.Metodo;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import static org.antlr.v4.runtime.CharStreams.fromFileName;

public class main {



    public static void main(String[] args){

        try{
            String fuente = "C:\\Users\\Usuario\\eclipse-workspace\\testeosIngInversaJava\\Datos\\Persona.java";
            CharStream cs = fromFileName(fuente);
            JavaLexer lexer = new JavaLexer(cs);
            CommonTokenStream tokenStream = new CommonTokenStream(lexer);
            JavaParser parser = new JavaParser(tokenStream);
            ParseTree tree = parser.compilationUnit();
            VisitorMetricas visitorMetricas = new VisitorMetricas();
            VisitorAtributoMetodo visitorAtributoMetodo = new VisitorAtributoMetodo();
            VisitorClase visitorClase = new VisitorClase(visitorAtributoMetodo, visitorMetricas);
            Clase clase = new Clase();
            Atributo atributo = new Atributo();
            Metodo metodo = new Metodo();
            visitorClase.setClase(clase);
            visitorAtributoMetodo.setAtributo(atributo);
            visitorAtributoMetodo.setMetodo(metodo);
            visitorClase.visit(tree);

            /*
            System.out.println("nombrePaquete");
            System.out.println(clase.getNombrePaquete());
            System.out.println("nombre");
            System.out.println(clase.getNombre());
            System.out.println("interfaces");
            System.out.println(clase.getInterfacesImplementadas());
            System.out.println("padre");
            System.out.println(clase.getClasePadre());
            System.out.println("cantImports");
            System.out.println(clase.getCantidadImportaciones());
            System.out.println("clasesGenericas");
            System.out.println(clase.getClasesGenericas());
            System.out.println("Externas:");
            System.out.println(clase.getAnotacionesExternas());
            System.out.println("Internas");
            System.out.println(clase.getAnotacionesInternas());
            */
            System.out.println(atributo.getTipo());

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
