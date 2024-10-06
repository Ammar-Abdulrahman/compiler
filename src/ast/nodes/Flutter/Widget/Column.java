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

    @Override
    public String generateCode() {
        String x = "" ;
              for (int i=0;i<widgets.size();i++){
                  x = x + widgets.get(i).generateCode() ;
              }
              if(cross != null && main_axis != null){
                  return "<div style=' display:flex; flex-direction: column; " +
                          "height: "+main_axis +";"+
                          "width: "+cross+";"+
                          "' >"+x+"</div>";
              }else if (cross != null){
                  return "<div style=' display:flex; flex-direction: column; " +
                          "width: "+cross+";"+
                          "' >"+x+"</div>";
              }else if (main_axis != null){
                  return "<div style=' display:flex; flex-direction: column; " +
                          "height: "+main_axis +";"+
                          "' >"+x+"</div>";
              } else {
                return "<div style=' display:flex; flex-direction: column; ' >"+x+"</div>";
              }
    }
}
