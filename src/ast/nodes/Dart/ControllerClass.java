package ast.nodes.Dart;

import SymbolTable.ClassSymbol;
import ast.nodes.Node;

import java.util.ArrayList;

public class ControllerClass extends Node {
    private String variable;
    private ArrayList<Def> def;
    private Constructor constructor;
    ClassSymbol classSymbol;
    ArrayList<CallFunction> callFunctions;

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

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public ArrayList<Def> getDef() {
        return def;
    }

    public void setDef(ArrayList<Def> def) {
        this.def = def;
    }

    public Constructor getConstructor() {
        return constructor;
    }

    public void setConstructor(Constructor constructor) {
        this.constructor = constructor;
    }

    @Override
    public String toString() {
        return "\nControllerClass{" +
                "\nvariable='" + variable + '\'' +
                ",\ndef=" + def +
                ",\nconstructor=" + constructor +
                "\n}";
    }

    @Override
    public String generateCode() {
        String x = "" , y = "" , z = "";
        if(def != null){
            for (int i=0 ; i<def.size();i++ ){
                x = x + def.get(i).generateCode();
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
                "class "+ variable + " extends GetxController  { \n"
                + x + y + z
                +"\n}"
                +"\n?>\n";
    }
}
