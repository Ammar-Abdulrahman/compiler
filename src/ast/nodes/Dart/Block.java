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
                ",\nDefs=" + Defs +
                ",\nvar='" + var + '\'' +
                ",\nnum=" + num +
                "\n}";
    }

    @Override
    public String generateCode() {
        String x = "" , y = "" , z = "" , c = "";
        if(statements != null){
            for (int i=0 ;i<statements.size();i++){
                x = x + statements.get(i).generateCode();
            }
        }
        if(Defs != null ){
            for (int i=0 ;i<Defs.size();i++){
                y = y + Defs.get(i).generateCode();
            }
        }
        if (var != null){
            z = var;
        }
        if (String.valueOf(num) != null){
            c = String.valueOf(num) ;
        }
        return "{\n"+ x +"\n"+ y + "\n"+" return" + z + c + ";\n}";
    }
}
