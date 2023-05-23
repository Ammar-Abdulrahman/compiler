package ast.nodes.Dart;

import ast.nodes.Node;

import java.util.ArrayList;

public class List extends Node {
    private String var;
    private ArrayList<String> vars;

    public ArrayList<String> getVars() {
        return vars;
    }

    public void setVars(ArrayList<String> vars) {
        this.vars = vars;
    }

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }

    @Override
    public String toString() {
        return "\nList{" +
                "\nvar='" + var + '\'' +
                "\nvars= "+ vars +
                "\n}";
    }
}
