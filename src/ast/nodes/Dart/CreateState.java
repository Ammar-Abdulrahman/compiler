package ast.nodes.Dart;

import ast.nodes.Node;
import java.util.ArrayList;

public class CreateState extends Node {
    private ArrayList<String> vars ;

    public ArrayList<String> getVars() {
        return vars;
    }

    public void setVars(ArrayList<String> vars) {
        this.vars = vars;
    }

    @Override
    public String toString() {
        return "\nCreateState{" +
                "\nvars=" + vars +
                "\n}";
    }
}
