package ast.nodes.Flutter.Widget;

import ast.nodes.Flutter.Height;
import ast.nodes.Flutter.Width;
import ast.nodes.Node;

public class SizedBox extends Node {
    private Height height = new Height();
    private Width width = new Width();

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

    @Override
    public String toString() {
        return "\nSizedBox{" +
                "\nheight=" + height +
                ", \nwidth=" + width +
                "\n}";
    }

    @Override
    public String generateCode() {
        return "";
    }
}
