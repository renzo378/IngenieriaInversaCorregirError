package com.example.demo.gen;


import com.example.demo.entities.Atributo;
import com.example.demo.entities.Clase;
import com.example.demo.entities.Metodo;
import com.example.demo.entities.Modificador;
import com.example.demo.entities.ComplejidadCiclomatica;
import lombok.Setter;
import org.antlr.v4.runtime.ParserRuleContext;

@Setter
public class VisitorAtributoMetodo extends JavaParserBaseVisitor {

    Clase clase;
    VisitorMetricas visitorMetricas;

    //carga el tipo y nombre de un atributo
    @Override
    public Object visitFieldDeclaration(JavaParser.FieldDeclarationContext ctx) {

        //el bucle se repite para cada atributo nuevo(aunque esten definidos en la misma linea)
        for (JavaParser.VariableDeclaratorContext x : ctx.variableDeclarators().variableDeclarator()) {
            Atributo atributo = new Atributo();
            atributo.setModificador(setearModificador(ctx.getParent().getParent()));
            //si el atributo es de tipo primitivo
            if(ctx.typeType().children.contains(ctx.typeType().primitiveType())){
                atributo.setNombre(x.variableDeclaratorId().IDENTIFIER().toString());
                atributo.setTipo(ctx.typeType().primitiveType().getText());
                clase.getAtributos().add(atributo);
            //si el atributo no es de tipo primitivo
            }else if(ctx.typeType().children.contains(ctx.typeType().classOrInterfaceType())) {
                String identifier = ctx.typeType().classOrInterfaceType().getText();
                //si el atributo no pertenece a una lista
                if(identifier.contains("String") ||
                        identifier.contains("Integer")||
                        identifier.contains("Date")){
                    atributo.setNombre(x.variableDeclaratorId().IDENTIFIER().toString());
                    atributo.setTipo(identifier);
                    clase.getAtributos().add(atributo);
                }
            }
        }
        return super.visitFieldDeclaration(ctx);
    }

    //carga el nombre, el tipo de retorno y los parametros de un metodo
    @Override
    public Object visitMethodDeclaration(JavaParser.MethodDeclarationContext ctx) {

        Metodo metodo = new Metodo();
        metodo.setComplejidadCiclomatica(new ComplejidadCiclomatica());
        visitorMetricas.setMetodo(metodo);

        metodo.setModificador(setearModificador(ctx.getParent().getParent()));
        metodo.setTipoRetorno(ctx.typeTypeOrVoid().getText());
        metodo.setNombre(ctx.IDENTIFIER().toString());
        //condicional en caso de que no existan parametros
        if(ctx.formalParameters().children.contains(ctx.formalParameters().formalParameterList())) {
            for (JavaParser.FormalParameterContext x : ctx.formalParameters().formalParameterList().formalParameter()) {
                String parametro = x.typeType().getText() +" "+ x.variableDeclaratorId().getText();
                metodo.getParametros().add(parametro);
            }
        }
        clase.getMetodos().add(metodo);
        return super.visitMethodDeclaration(ctx);
    }


    //Metodo auxiliar para setear el modificador ya sea
    //de un metodo o de un atributo
    private Modificador setearModificador(ParserRuleContext ctx){
        Modificador modificador = new Modificador();
        //Controla que tenga por lo menos un modificador que no sea el Default
        if(ctx.getChild(0) instanceof JavaParser.ModifierContext) {
            for(int i = 0; i<ctx.getChildCount()-1; i++){
                if(ctx.getChild(i).getText().contains("static")){
                    modificador.setStatic(true);
                }
                if(ctx.getChild(i).getText().contains("final")){
                    modificador.setFinal(true);
                }
                if(ctx.getChild(i).getText().contains("public")){
                    modificador.setVisibilidad("Public");
                }else if(ctx.getChild(i).getText().contains("private")){
                    modificador.setVisibilidad("Private");
                }else if(ctx.getChild(i).getText().contains("protected")){
                    modificador.setVisibilidad("Protected");
                }
            }
        }
        return modificador;
    }

}
