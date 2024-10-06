package ast.nodes.Flutter.Widget;

import ast.nodes.Flutter.Child;
import ast.nodes.Node;

public class Expanded extends Node {

    private Child child;

    public Child getChild() {
        return child;
    }

    public void setChild(Child child) {
        this.child = child;
    }

    @Override
    public String toString() {
        return "\nExpanded{" +
                "\nchild=" + child +
                "\n}";
    }

    @Override
    public String generateCode() {
        return "<div style='display:flex; flex:1; margin:10px'>"
                +child.generateCode()
                + "</div>";
    }
}
