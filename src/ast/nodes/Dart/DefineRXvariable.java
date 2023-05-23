package ast.nodes.Dart;

import ast.nodes.Node;

public class DefineRXvariable extends Node {
    private int num;
    private  String var;

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
}
