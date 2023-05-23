package ast.nodes.Flutter.Widget;

import ast.nodes.Node;

import java.util.ArrayList;

public class Column extends Node {
    private String main_axis;
    private String cross;
    private ArrayList<Widget> widgets;

    public String getMain_axis() {
        return main_axis;
    }

    public void setMain_axis(String main_axis) {
        this.main_axis = main_axis;
    }

    public String getCross() {
        return cross;
    }

    public void setCross(String cross) {
        this.cross = cross;
    }

    public ArrayList<Widget> getWidgets() {
        return widgets;
    }

    public void setWidgets(ArrayList<Widget> widgets) {
        this.widgets = widgets;
    }

    @Override
    public String toString() {
        return "\nColumn{" +
                "\nmain_axis='" + main_axis + '\'' +
                ", \ncross='" + cross + '\'' +
                ", \nwidgets=" + widgets +
                "\n}";
    }
}
