package ast.nodes.Dart;

import SymbolTable.ClassSymbol;
import ast.nodes.Dart.Statements.Declare;
import ast.nodes.Node;

import java.util.ArrayList;

public class RegularClass extends Node {
    private String var;
    private Constructor constructor;
    ClassSymbol classSymbol;
    ArrayList<Def> defs ;
    ArrayList<CallFunction> callFunctions ;

    public ArrayList<CallFunction> getCallFunctions() {
        return callFunctions;
    }

    public void setCallFunctions(ArrayList<CallFunction> callFunctions) {
        this.callFunctions = callFunctions;
    }

    public ClassSymbol getClassSymbol() {
        return classSymbol;
    }

    public void setClassSymbol(ClassSymbol classSymbol) {
        this.classSymbol = classSymbol;
    }

    public ArrayList<Def> getDefs() {
        return defs;
    }

    public void setDefs(ArrayList<Def> defs) {
        this.defs = defs;
    }



    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }

    public Constructor getConstructor() {
        return constructor;
    }

    public void setConstructor(Constructor constructor) {
        this.constructor = constructor;
    }




    @Override
    public String toString() {
        return "\nRegularClass{" +
                "\nvar= '" + var + '\'' +
                ", \nconstructor= " + constructor +
                ", \ndefs= " + defs +
                ", \ncallFunctions= " + callFunctions +
                "\n}";
    }

    @Override
    public String generateCode() {
        String x = "" , y = "" , z = "";
        if(defs != null){
            for (int i=0 ; i<defs.size();i++ ){
                x = x + defs.get(i).generateCode();
            }
        }
        if (constructor != null){
            y = constructor.generateCode();
        }
        if (callFunctions != null){
            for (int i=0;i<callFunctions.size();i++){
                z = z + callFunctions.get(i).generateCode();
            }
        }
        return  "<?php \n" +
                "class "+ var + " extends " + var + " { \n"
                + x + y + z
                +"\n}"
                +"\n?>\n";
    }
}
