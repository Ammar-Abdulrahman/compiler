package ast.nodes.Dart.Statements;

import ast.nodes.Node;

import java.util.ArrayList;

public class ControllerFunctions extends Node {
    private int num;

    private ArrayList<String> vars;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

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

    @Override
    public String generateCode() {
        int x=0 ;
        String y ="" , z="";
        x = vars.size();
        if( x == 1){
            y = vars.get(0) + " -> " +vars.get(1) +"("+num +");\n" ;
        }
        if (x==2){
            z = vars.get(0) + " -> " +vars.get(1) +"("+ vars.get(2) +");\n" ;
        }
        return y+z ;
    }
}
