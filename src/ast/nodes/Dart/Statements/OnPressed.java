package ast.nodes.Dart.Statements;

import ast.nodes.Node;

import java.util.ArrayList;

public class OnPressed extends Node {

    ArrayList<Statement> statements;
    SetState state;


    public ArrayList<Statement> getStatements() {
        return statements;
    }

    public void setStatements(ArrayList<Statement> statements) {
        this.statements = statements;
    }

    public SetState getState() {
        return state;
    }

    public void setState(SetState state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "\nOnPressed{" +
                ", \nstatements=" + statements +
                ", \nsetstate=" + state +
                "\n}";
    }

    @Override
    public String generateCode() {
        String x = "" ;
        if (statements != null){
            for (int i=0;i<statements.size();i++){
                x = x +statements.get(i).generateCode();
            }
        }
        return  "() =>{\n"
                +x
                + "\n"
                + "}" ;
    }
}
