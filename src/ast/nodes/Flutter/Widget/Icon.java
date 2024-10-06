package ast.nodes.Flutter.Widget;

import ast.nodes.Node;

public class Icon extends Node {
    private String Icon_value;

    public String getIcon_value() {
        return Icon_value;
    }

    public void setIcon_value(String icon_value) {
        Icon_value = icon_value;
    }

    @Override
    public String toString() {
        return "\nIcon{" +
                "\nvalue='" + Icon_value + '\'' +
                "\n}";
    }

    @Override
    public String generateCode() {
        return "";
    }
}
