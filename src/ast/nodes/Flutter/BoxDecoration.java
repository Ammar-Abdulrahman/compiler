package ast.nodes.Flutter;

import ast.nodes.Node;

public class BoxDecoration extends Node {
    private String color_value;

    public String getColor_value() {
        return color_value;
    }

    public void setColor_value(String color_value) {
        this.color_value = color_value;
    }


    @Override
    public String toString() {
        return "\nBoxDecoration{" +
                "\ncolor_value='" + color_value + '\'' +
                "\n}";
    }

    @Override
    public String generateCode() {
        if(color_value != null){
            return color_value;
        }
        return "";
    }
}
