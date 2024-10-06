package ast.nodes.Dart;

import ast.nodes.Node;

import java.util.ArrayList;

public class CallFunction extends Node {
    String name;
    ArrayList<String> parameters;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getParameters() {
        return parameters;
    }

    public void setParameters(ArrayList<String> parameters) {
        this.parameters = parameters;
    }

    @Override
    public String toString() {
        return "CallFunction{" +
                "name='" + name + '\'' +
                ",parameters=" + parameters +
                '}';
    }

    @Override
    public String generateCode() {
        String x = "";
        if (name != null && parameters != null )
        {
            for (int i=0 ; i<parameters.size();i++)
            {
                x = x + parameters.get(i)+",";
            }
           return name + "("+ parameters + ");";
        }
        else if(name != null)
        {
            return name + "();" ;
        }
        return "" ;
    }
}
