package ast.nodes.Dart.Statements.Expressions;

import ast.nodes.Node;

import java.util.ArrayList;

public class CatchPart extends Node {
    private ArrayList<String> var = new ArrayList<>();

    public ArrayList<String> getVar() {
        return var;
    }

    public void setVar(ArrayList<String> var) {
        this.var = var;
    }

    @Override
    public String toString() {
        return "\nCatchPart{" +
                "\nvar=" + var +
                "\n}";
    }
}
