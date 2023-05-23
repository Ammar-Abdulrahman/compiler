package ast.nodes.Dart;

import ast.nodes.Dart.Statements.Statement;
import ast.nodes.Node;

import java.util.ArrayList;

public class Block extends Node {
    ArrayList<Statement> statements = new ArrayList<>();

    ArrayList<Def> Defs = new ArrayList<>();

    private String var;
    private int num;

    public ArrayList<Def> getDefs() {
        return Defs;
    }

    public void setDefs(ArrayList<Def> defs) {
        Defs = defs;
    }

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public ArrayList<Statement> getStatements() {
        return statements;
    }

    public void setStatements(ArrayList<Statement> statements) {
        this.statements = statements;
    }


    @Override
    public String toString() {
        return "\nBlock{" +
                "\nstatements=" + statements +
                ", \nDefs=" + Defs +
                ", \nvar='" + var + '\'' +
                ", \nnum=" + num +
                "\n}";
    }
}
