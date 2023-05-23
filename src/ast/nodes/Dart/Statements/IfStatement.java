package ast.nodes.Dart.Statements;

import ast.nodes.Dart.Block;
import ast.nodes.Dart.Statements.Expressions.Expression;
import ast.nodes.Node;

import java.util.ArrayList;

public class IfStatement extends Node {
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

    @Override
    public String toString() {
        return "\nIfStatement{" +
                "\nexpressions=" + expressions +
                ", \nblocks=" + blocks +
                "\n}";
    }
}
