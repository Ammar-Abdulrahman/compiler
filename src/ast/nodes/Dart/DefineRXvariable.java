package ast.nodes.Dart;

import SymbolTable.VarSymbol;
import ast.nodes.Node;

public class DefineRXvariable extends Node {
    private int num;
    private  String var;

    VarSymbol varSymbol;

    public VarSymbol getVarSymbol() {
        return varSymbol;
    }

    public void setVarSymbol(VarSymbol varSymbol) {
        this.varSymbol = varSymbol;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }

    @Override
    public String toString() {
        return "\nDefineRXvariable{" +
                "\nnum=" + num +
                ", \nvar='" + var + '\'' +
                "\n}";
    }

    @Override
    public String generateCode() {
        if(var != null){
            return "$"+var +" = " + num + ";" + "\n";
        }
        return "";
    }
}
