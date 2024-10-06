package ast.nodes.Flutter.Widget;

import ast.nodes.Node;

public class Obx extends Node {
    Widget widget ;

    public Widget getWidget() {
        return widget;
    }

    public void setWidget(Widget widget) {
        this.widget = widget;
    }

    @Override
    public String toString() {
        return "\nObx{" +
                "\nwidget=" + widget +
                "\n}";
    }
    @Override
    public String generateCode() {
        return "<div obx>"
                +widget.generateCode()
                +"</div>" ;
    }
}
