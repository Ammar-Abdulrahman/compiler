package ast.nodes.Dart;

import ast.nodes.Dart.Statements.Type;
import ast.nodes.Node;

import java.util.ArrayList;

public class FunctionOnParameter extends Node {
    private ArrayList<Type> types;

    ArrayList <String> var  = new ArrayList<>();

    public ArrayList<Type> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<Type> types) {
        this.types = types;
    }

    public ArrayList<String> getVar() {
        return var;
    }

    public void setVar(ArrayList<String> var) {
        this.var = var;
    }

    @Override
    public String toString() {
        return "\nFunctionOnParameter{" +
                "\ntype=" + types +
                "\nvar=" + var +
                "\n}";
    }


    @Override
    public String generateCode() {
        String x = "" ,  y = "" , z = "" ;
        for (int i=0 ; i<types.size();i++) {
            x = types.get(i).getType();
            y = var.get(i);
            z = z +  x + " " + y + "," ;
        }
        return z;
    }
}
