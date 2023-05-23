package ast.nodes.Flutter.Widget;

import ast.nodes.Flutter.Child;
import ast.nodes.Node;

public class Padding extends Node {

    Child child ;
    String num;

    public Child getChild() {
        return child;
    }

    public void setChild(Child child) {
        this.child = child;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "\nPadding{" +
                "\nchild=" + child +
                ", \nnum=" + num +
                "\n}";
    }


}
