package ast.nodes.Dart;

import SymbolTable.ListSymbol;
import ast.nodes.Node;

import java.util.ArrayList;

public class List extends Node {
    private String var;
    private ArrayList<String> vars;

    public ArrayList<String> getVars() {
        return vars;
    }

    ListSymbol listSymbol ;

    public ListSymbol getListSymbol() {
        return listSymbol;
    }

    public void setListSymbol(ListSymbol listSymbol) {
        this.listSymbol = listSymbol;
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

    @Override
    public String toString() {
        return "\nList{" +
                "\nvar='" + var + '\'' +
                "\nvars= "+ vars +
                "\n}";
    }

    @Override
    public String generateCode() {
        String x = "";
        String y = "";
        if (var != null){
            for (int i=0 ; i<vars.size() ; i++){
                x = x + vars.get(i);
                x = x + ",";
            }
            return "$"+var + " = " + "array(" + x + ")" + ";" + "\n" ;
        }
        return "";
    }
}
