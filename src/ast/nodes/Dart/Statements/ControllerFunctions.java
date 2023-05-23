package ast.nodes.Dart.Statements;

import ast.nodes.Node;

import java.util.ArrayList;

public class ControllerFunctions extends Node {

    private ArrayList<String> vars;

    public ArrayList<String> getVars() {
        return vars;
    }

    public void setVars(ArrayList<String> vars) {
        this.vars = vars;
    }

    @Override
    public String toString() {
        if (vars.size()>=3){
        return "\nControllerFunctions{" +
                vars.get(0) +  " . " + vars.get(1)  + "(" + vars.get(2) + ")" +
                "\n}";
    }
        return "\nControllerFunctions{" +
                vars.get(0) +  " . " + vars.get(1)  +
                "\n}";
    }
}
