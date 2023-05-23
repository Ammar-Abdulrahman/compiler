package ast.nodes.Dart.Statements;

import ast.nodes.Node;

import java.util.ArrayList;

public class SetBody extends Node {
    ArrayList <Statement> statements = new ArrayList<>();


    public ArrayList<Statement> getStatements() {
        return statements;
    }

    public void setStatements(ArrayList<Statement> statements) {
        this.statements = statements;
    }

    @Override
    public String toString() {
        return "\nSetBody{" +
                ", \nstatements=" + statements +
                "\n}";
    }
}
