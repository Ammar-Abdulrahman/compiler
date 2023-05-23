package ast.nodes.Dart;

import ast.nodes.Node;

public class Istedaaaclass extends Node {
    private String var;

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }



    @Override
    public String toString() {
        return "\nCallClass{" +
                "\nvar='" + var + '\'' +
                "\n}";
    }
}
