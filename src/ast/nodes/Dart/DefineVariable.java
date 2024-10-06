package ast.nodes.Dart;

import SymbolTable.VarSymbol;
import ast.nodes.Dart.Statements.Type;
import ast.nodes.Node;

import java.util.ArrayList;

public class DefineVariable extends Node {
    private String var;
    private int num;
    private Type type;
    Object value;
    VarSymbol varSymbol;
    private String stringLine;

    public VarSymbol getVarSymbol() {
        return varSymbol;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public void setVarSymbol(VarSymbol varSymbol) {
        this.varSymbol = varSymbol;
    }


    public String getStringLine() {
        return stringLine;
    }

    public void setStringLine(String stringLine) {
        this.stringLine = stringLine;
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "\nDefineVariable{" +
                ",\ntype=" + type +
                "\nvar='" + var + '\'' +
                ",\nnum=" + num +
                ",\nstringLine=" + stringLine +
                "\n}";
    }

    @Override
    public String generateCode() {
        if ( stringLine != null && var != null ){
            return "$"+var +" = "+ stringLine+";"+"\n";
        }
         else if ( String.valueOf(num) != null && var != null ){
            return "$"+var +" = "+ num+";"+"\n";
        }
        return "";
    }
}
