package ast.nodes.Dart;

import ast.nodes.Node;

import java.util.ArrayList;

public class ControllerClass extends Node {
    private String variable;
    private ArrayList<Def> def;
    private Constructor constructor;

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public ArrayList<Def> getDef() {
        return def;
    }

    public void setDef(ArrayList<Def> def) {
        this.def = def;
    }

    public Constructor getConstructor() {
        return constructor;
    }

    public void setConstructor(Constructor constructor) {
        this.constructor = constructor;
    }

    @Override
    public String toString() {
        return "\nControllerClass{" +
                "\nvariable='" + variable + '\'' +
                ",\ndef=" + def +
                ",\nconstructor=" + constructor +
                "\n}";
    }
}
