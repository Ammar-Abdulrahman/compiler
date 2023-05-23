package ast.nodes.Dart;

import ast.nodes.Flutter.Widget.Widget;
import ast.nodes.Node;

public class BuildFunction extends Node {
    private Widget widget;

    public Widget getWidget() {
        return widget;
    }

    public void setWidget(Widget widget) {
        this.widget = widget;
    }

    @Override
    public String toString() {
        return "\nBuildFunction{" +
                "\nwidget=" + widget +
                "\n}";
    }
}
