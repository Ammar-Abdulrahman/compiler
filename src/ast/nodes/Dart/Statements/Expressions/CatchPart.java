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

    @Override
    public String generateCode() {
        int n = 0 ;
        String x = "",y = "";
        n = var.size();
        if (n==0){
            x = var.get(0);
        }
        if (n==1){
            y = var.get(0) + "," + var.get(1);
        }
        return "catch (" + x + y + " )" ;
    }
}
