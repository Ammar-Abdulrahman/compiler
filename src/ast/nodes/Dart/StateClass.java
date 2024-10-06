package ast.nodes.Dart;

import ast.nodes.Dart.Statements.Declare;
import ast.nodes.Node;

import java.util.ArrayList;

public class StateClass extends Node {
    private String var;

    ArrayList<String> vars ;
    private ArrayList<Def> defs = new ArrayList<>();
    private BuildFunction buildFunction;

    public ArrayList<String> getVars() {
        return vars;
    }

    public void setVars(ArrayList<String> vars) {
        this.vars = vars;
    }

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
                "\nclass='" + vars + '\'' +
                ",\n def=" + defs +
                ",\n buildFunction=" + buildFunction +
                "\n}";
    }

    @Override
    public String generateCode() {
        String x = "" , y = "";
        if(defs != null){
            for (int i=0 ; i<defs.size();i++ ){
                x = x + defs.get(i).generateCode();
            }
        }
        if (buildFunction != null) {
            y = buildFunction.generateCode();
        }
        return  "<?php \n"+ x + "\n ?> \n" +
                y +"\n";
    }
}
