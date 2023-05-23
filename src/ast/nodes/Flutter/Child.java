package ast.nodes.Flutter;

import ast.nodes.Flutter.Widget.Widget;
import ast.nodes.Node;

public class Child extends Node {
    Widget widget;

    public Widget getWidget() {
        return widget;
    }

    public void setWidget(Widget widget) {
        this.widget = widget;
    }

    @Override
    public String toString() {
        return "\nChild{" +
                "\nwidget=" + widget +
                "\n}";
    }
}
