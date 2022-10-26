package com.example.demo.gen;

import com.example.demo.entities.Clase;
import com.example.demo.entities.Metodo;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
public class VisitorClase extends JavaParserBaseVisitor {
    private VisitorMetricas visitorMetricas;
    private VisitorAtributoMetodo visitorAtributoMetodo;
    private Clase clase;

    public VisitorClase(VisitorAtributoMetodo visitorAtributoMetodo, VisitorMetricas visitorMetricas){
        this.visitorAtributoMetodo = visitorAtributoMetodo;
        this.visitorMetricas = visitorMetricas;
    }

    //Carga el nombre del paquete de una clase
    @Override
    public Object visitPackageDeclaration(JavaParser.PackageDeclarationContext ctx) {
        clase.setNombrePaquete(ctx.qualifiedName().IDENTIFIER().toString());
        return super.visitPackageDeclaration(ctx);
    }

    //Carga el nombre de una clase, las interfaces que implementa y la clase de la que extiende
    @Override
    public Object visitClassDeclaration(JavaParser.ClassDeclarationContext ctx) {
        clase.setNombre(ctx.IDENTIFIER().getText());
        if (ctx.children.contains(ctx.IMPLEMENTS())) {
            for (JavaParser.TypeTypeContext x : ctx.typeList().typeType()) {
                clase.getInterfacesImplementadas().add(x.classOrInterfaceType().IDENTIFIER().toString());
            }
        }
        if (ctx.children.contains(ctx.EXTENDS())) {
            clase.setClasePadre(ctx.typeType().classOrInterfaceType().IDENTIFIER().toString());
        }
        return super.visitClassDeclaration(ctx);
    }

    //Carga las clases genericas de una clase parametrizada
    @Override
    public Object visitTypeParameters(JavaParser.TypeParametersContext ctx) {
        for (JavaParser.TypeParameterContext x : ctx.typeParameter()) {
            clase.getClasesGenericas().add(x.IDENTIFIER().toString());
        }

        return super.visitTypeParameters(ctx);
    }

    //Cuenta cuantas importaciones tiene una clase
    @Override
    public Object visitImportDeclaration(JavaParser.ImportDeclarationContext ctx) {
        clase.setCantidadImportaciones(clase.getCantidadImportaciones() + 1);
        return super.visitImportDeclaration(ctx);
    }


    @Override
    public Object visitAnnotation(JavaParser.AnnotationContext ctx) {
        if (clase.getNombre().equals(" ")) {
            clase.getAnotacionesExternas().add(ctx.qualifiedName().getText());
        } else {
            clase.getAnotacionesInternas().add(ctx.qualifiedName().getText());
        }
        return super.visitAnnotation(ctx);
    }

    @Override
    public Object visitFieldDeclaration(JavaParser.FieldDeclarationContext ctx) {
        visitorAtributoMetodo.visitFieldDeclaration(ctx);
        return super.visitFieldDeclaration(ctx);
    }

    @Override
    public Object visitMethodDeclaration(JavaParser.MethodDeclarationContext ctx) {

        visitorAtributoMetodo.visitMethodDeclaration(ctx);
        visitorMetricas.visitMethodDeclaration(ctx);
        return super.visitMethodDeclaration(ctx);
    }

    @Override
    public Object visitStatement(JavaParser.StatementContext ctx) {


        visitorMetricas.visitStatemen(ctx);
        return super.visitStatement(ctx);
    }
}
