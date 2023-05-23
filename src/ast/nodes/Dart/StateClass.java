package ast.nodes.Dart;

import ast.nodes.Dart.Statements.Declare;
import ast.nodes.Node;

import java.util.ArrayList;

public class StateClass extends Node {
    private String var;
    private ArrayList<Def> defs = new ArrayList<>();
    private BuildFunction buildFunction;
    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }

    public ArrayList<Def> getDef() {
        return defs;
    }

    public void setDef(ArrayList<Def> defs) {
        this.defs = defs;
    }

    public BuildFunction getBuildFunction() {
        return buildFunction;
    }

    public void setBuildFunction(BuildFunction buildFunction) {
        this.buildFunction = buildFunction;
    }

    @Override
    public String toString() {
        return "\nStateClass{" +
                "\nclass='" + var + '\'' +
                ",\n def=" + defs +
                ",\n buildFunction=" + buildFunction +
                "\n}";
    }
}
