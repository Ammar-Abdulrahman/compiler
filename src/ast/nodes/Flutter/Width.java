package ast.nodes.Flutter;

import ast.nodes.Flutter.Widget.Widget;
import ast.nodes.Node;

public class Width extends Node {
    private int num;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "\nWidth{" +
                "\nnum=" + num +
                "\n}";
    }
}
