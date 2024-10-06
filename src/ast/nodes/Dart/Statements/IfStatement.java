package ast.nodes.Dart.Statements;

import SymbolTable.Scope;
import ast.nodes.Dart.Block;
import ast.nodes.Dart.Statements.Expressions.Expression;
import ast.nodes.Node;

import java.util.ArrayList;

public class IfStatement extends Node {
    private String andOr;
    ArrayList<Expression> expressions = new ArrayList<>();
    ArrayList<Block> blocks = new ArrayList<>();

    public ArrayList<Expression> getExpressions() {
        return expressions;
    }

    public void setExpressions(ArrayList<Expression> expressions) {
        this.expressions = expressions;
    }

    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(ArrayList<Block> blocks) {
        this.blocks = blocks;
    }

    public String getAndOr() {
        return andOr;
    }

    public void setAndOr(String andOr) {
        this.andOr = andOr;
    }

    @Override
    public String toString() {
        return "\nIfStatement{" +
                "\nexpressions=" + expressions +
                ", \nblocks=" + blocks +
                "\n}";
    }

    @Override
    public String generateCode() {
        String x = "" ,a="", y = "" , bloc = "";
        int ex =0 , bl =0 ;
        if (andOr != null){
            a = andOr;
        }
        ex = expressions.size();
        bl = blocks.size();
        if (ex == 0){
            x = expressions.get(0).generateCode() +"" ;
        }
        if (ex > 0){
            for (int i = 0 ;i <expressions.size();i++){
                x = x + expressions.get(i).generateCode() + ",";
            }
        }
        if (bl == 1){
            bloc = blocks.get(0).generateCode() + "else " + blocks.get(1).generateCode();
        }
        if (bl == 0){
            bloc = blocks.get(0).generateCode() + "" ;
        }
        return "if (" +  x + ")\n"
                +bloc;
    }
}
