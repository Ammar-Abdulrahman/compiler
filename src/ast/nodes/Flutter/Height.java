package ast.nodes.Flutter;

import ast.nodes.Flutter.Widget.Widget;
import ast.nodes.Node;

public class Height extends Node {
    private int num;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "\nHeight{" +
                "\nnum=" + num +
                "\n}";
    }

    @Override
    public String generateCode() {
        return String.valueOf(num)+"px";
    }
}
