package ast.nodes.Flutter.Widget;

import ast.nodes.Flutter.BoxDecoration;
import ast.nodes.Flutter.Child;
import ast.nodes.Flutter.Height;
import ast.nodes.Flutter.Width;
import ast.nodes.Node;

public class Container extends Node {
    private String color_value;
    private Height height;
    private Width width;
    private Child child;

    private BoxDecoration boxDecoration;

    public String getColor_value() {
        return color_value;
    }

    public void setColor_value(String color_value) {
        this.color_value = color_value;
    }

    public Height getHeight() {
        return height;
    }

    public void setHeight(Height height) {
        this.height = height;
    }

    public Width getWidth() {
        return width;
    }

    public void setWidth(Width width) {
        this.width = width;
    }

    public Child getChild() {
        return child;
    }

    public void setChild(Child child) {
        this.child = child;
    }

    public BoxDecoration getBoxDecoration() {
        return boxDecoration;
    }

    public void setBoxDecoration(BoxDecoration boxDecoration) {
        this.boxDecoration = boxDecoration;
    }

    @Override
    public String toString() {
        return "\nContainer{" +
                "\ncolor_value='" + color_value + '\'' +
                ", \nheight=" + height +
                ", \nwidth=" + width +
                ", \nchild=" + child +
                ", \nboxDecoration=" + boxDecoration +
                "\n}";
    }

    @Override
    public String generateCode() {
        if (color_value != null || width != null ) {
            return "<div style='"+ " height: "+height.generateCode()+"; margin:10px"+
                    " width: "+width.generateCode()+ ";" +
                    " color: "+color_value+ ";"+
                    "'"+
                    ">"
                    +child.generateCode()
                    +"</div>";
        }
            return "<div style='"+ " height: "+height.generateCode()+"; margin:10px;"+
                    "'"+
                    ">"
                +child.generateCode()
                +"</div>";
    }
}


//return "<div " + "height= " +"\""+ height.getNum()+"\"" + " width= " +"\""+"\"" + "> "
//        +child.generateCode()
//        +"</div>";
