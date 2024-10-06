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

    @Override
    public String generateCode() {
        return "<div style='"+ "padding: "+num +
                "px;'"+
                ">"
                +child.generateCode()
                +"</div>" ;
    }

//    @Override
//    public boolean generateCodeT() {
//      //  this.generate = "<div padding>" +child.generate +"</div>";
//        return true;
//    }
}
