package ast.nodes.Dart;

import ast.nodes.Dart.Statements.Declare;
import ast.nodes.Node;

import java.util.ArrayList;

public class StlessClassBody extends Node {
    private Constructor constructor;
    private BuildFunction buildFunction;
    private ArrayList<Def> defs = new ArrayList<>();

    public Constructor getConstructor() {
        return constructor;
    }

    public void setConstructor(Constructor constructor) {
        this.constructor = constructor;
    }

    public BuildFunction getBuildFunction() {
        return buildFunction;
    }

    public void setBuildFunction(BuildFunction buildFunction) {
        this.buildFunction = buildFunction;
    }

    public ArrayList<Def> getDefs() {
        return defs;
    }

    public void setDefs(ArrayList<Def> defs) {
        this.defs = defs;
    }

    @Override
    public String toString() {
        return "\nStateLessClassBody{" +
                "\nconstructor=" + constructor +
                ",\nbuildFunction=" + buildFunction +
                ",\ndefs=" + defs +
                "\n}";
    }
}
