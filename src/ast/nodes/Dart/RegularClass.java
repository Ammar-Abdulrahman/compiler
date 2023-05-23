package ast.nodes.Dart;

import ast.nodes.Dart.Statements.Declare;
import ast.nodes.Node;

import java.util.ArrayList;

public class RegularClass extends Node {
    private String var;
    private Constructor constructor;

    public ArrayList<Def> getDefs() {
        return defs;
    }

    public void setDefs(ArrayList<Def> defs) {
        this.defs = defs;
    }

    ArrayList<Def> defs = new ArrayList<>();

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }

    public Constructor getConstructor() {
        return constructor;
    }

    public void setConstructor(Constructor constructor) {
        this.constructor = constructor;
    }




    @Override
    public String toString() {
        return "\nRegularClass{" +
                "\nvar='" + var + '\'' +
                ", \nconstructor=" + constructor +
                ", \ndefs=" + defs +
                "\n}";
    }
}
