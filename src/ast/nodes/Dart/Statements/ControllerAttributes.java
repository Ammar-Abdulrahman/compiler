package ast.nodes.Dart.Statements;

import ast.nodes.Node;

import java.util.ArrayList;

public class ControllerAttributes extends Node {
    private ArrayList<String> vars;

    public ArrayList<String> getVars() {
        return vars;
    }

    public void setVars(ArrayList<String> vars) {
        this.vars = vars;
    }

    @Override
    public String toString() {
        return "\nControllerAttributes{" +
                "vars=" + vars +
                "\n}";
    }
}
