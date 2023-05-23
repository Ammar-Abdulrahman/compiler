package ast.nodes.Dart.Statements;

import ast.nodes.Dart.Block;
import ast.nodes.Dart.Statements.Expressions.CatchPart;
import ast.nodes.Node;

import java.util.ArrayList;

public class TryStatement extends Node {
    CatchPart catchPart = new CatchPart();
    ArrayList<Block> blocks;

    public CatchPart getCatchPart() {
        return catchPart;
    }

    public void setCatchPart(CatchPart catchPart) {
        this.catchPart = catchPart;
    }

    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(ArrayList<Block> blocks) {
        this.blocks = blocks;
    }

    @Override
    public String toString() {
        return "\nTryStatement{" +
                " \nblock1= " + blocks.get(0) +
                " \ncatchPart= " + catchPart +
                " \nblock2= " + blocks.get(1) +
                "\n}";
    }
}
