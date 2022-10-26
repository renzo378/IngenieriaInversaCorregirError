package com.example.demo.gen;

import com.example.demo.entities.Clase;
import com.example.demo.entities.Metodo;
import lombok.Setter;

@Setter
public class VisitorMetricas extends JavaParserBaseVisitor< Object >{

    private Clase clase;
    private Metodo metodo;

    @Override
    public Object visitMethodDeclaration(JavaParser.MethodDeclarationContext ctx) {

        //Cuenta la cantidad de lineas de un metodo
        String todosLosCaracteres = ctx.methodBody().getText();
        String caracteresSinComa = ctx.methodBody().getText().replaceAll(";","");
        metodo.setCantLineas(todosLosCaracteres.length() - caracteresSinComa.length());

        return super.visitMethodDeclaration(ctx);
    }


    public void visitStatemen(JavaParser.StatementContext ctx) {

        //Cuenta la cantidad de FOR
        if(ctx.children.contains(ctx.FOR())){
            //Diferencia entre FOR y FOR EACH
            if (!ctx.forControl().children.contains(ctx.forControl().enhancedForControl())){
                metodo.getComplejidadCiclomatica().incrementarFor();
            }else{
                metodo.getComplejidadCiclomatica().incrementarForEach();
            }
        }
        //Cuenta la cantidad de IF
        if(ctx.children.contains(ctx.IF())){
            metodo.getComplejidadCiclomatica().incrementarIf();
        }
        //Cuenta la cantidad de ELSE
        if(ctx.children.contains(ctx.ELSE())){
            metodo.getComplejidadCiclomatica().incrementarElse();
        }
        //Cuenta la cantidad de TRY
        if(ctx.children.contains(ctx.TRY())){
            metodo.getComplejidadCiclomatica().incrementarTry();

        }
        //Cuenta la cantidad de TRHOW
        if(ctx.children.contains(ctx.THROW())){
            metodo.getComplejidadCiclomatica().incrementarThrow();

        }
        //Cuenta la cantidad de CATCH
        if(ctx.catchClause().size()>0){
            for (JavaParser.CatchClauseContext x : ctx.catchClause()) {
                System.out.println("Entre a un catch");
                metodo.getComplejidadCiclomatica().incrementarCatch();
            }
        }
        //Cuenta la cantidad de FINALLY
        if(ctx.children.contains(ctx.finallyBlock())){
            metodo.getComplejidadCiclomatica().incrementarFinally();
        }
        //Cuenta los case y los default
        if(ctx.children.contains(ctx.SWITCH())){
            for (JavaParser.SwitchBlockStatementGroupContext x : ctx.switchBlockStatementGroup()) {
                metodo.getComplejidadCiclomatica().incrementarCase();
            }
        }
        //Cuenta los DO WHILE
        if(ctx.children.contains(ctx.WHILE())){
            metodo.getComplejidadCiclomatica().incrementarDoWhile();
        }
        //Cuenta los Return
        if(ctx.children.contains(ctx.RETURN())){
            metodo.getComplejidadCiclomatica().incrementarReturn();
        }

    }
}
