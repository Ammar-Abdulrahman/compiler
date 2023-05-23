package ast.nodes.Dart;

import ast.nodes.Node;

public class StlessClass extends Node {
    private String var;
    private StlessClassBody stlessClassBody;

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }

    public StlessClassBody getStlessClassBody() {
        return stlessClassBody;
    }

    public void setStlessClassBody(StlessClassBody stlessClassBody) {
        this.stlessClassBody = stlessClassBody;
    }

    @Override
    public String toString() {
        return "\nStateLessClass{" +
                "\nvar='" + var + '\'' +
                ",\nstateLessClassBody=" + stlessClassBody +
                "\n}";
    }
}
