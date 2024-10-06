package ast.nodes.Flutter.Widget;

import ast.nodes.Flutter.Child;
import ast.nodes.Node;

public class Center extends Node {
    Child child = new Child();

    public Child getChild() {
        return child;
    }

    public void setChild(Child child) {
        this.child = child;
    }

    @Override
    public String toString() {
        return "\nCenter{" +
                "\nchild=" + child +
                "\n}";
    }
    @Override
    public String generateCode() {
        return "<div"+ " style=' align-items: center; '" +">"
                +child.generateCode()
                +"</div>";
    }
}
