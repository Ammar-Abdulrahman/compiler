package ast.nodes.Dart;

import ast.nodes.Node;

public class Classes extends Node {
    private WidgetClass widgetClass;
    private RegularClass regularClass;

    private ControllerClass controllerClass;

    public ControllerClass getControllerClass() {
        return controllerClass;
    }

    public void setControllerClass(ControllerClass controllerClass) {
        this.controllerClass = controllerClass;
    }

    public WidgetClass getWidgetClass() {
        return widgetClass;
    }

    public void setWidgetClass(WidgetClass widgetClass) {
        this.widgetClass = widgetClass;
    }

    public RegularClass getRegularClass() {
        return regularClass;
    }

    public void setRegularClass(RegularClass regularClass) {
        this.regularClass = regularClass;
    }

    @Override
    public String toString() {
        if (widgetClass != null) {
            return "\nClasses{" +
                    "\nwidgetClass=" + widgetClass +
                    "\n}";
        }
        else if (regularClass != null){
            return "\nClasses{" +
                    "\nregularClass=" + regularClass +
                    "\n}";
        } else if (controllerClass != null) {
                return "\nClasses{" +
                        "\ncontrollerClass" + controllerClass +
                        "\n}";
        }
        return null;
    }

    @Override
    public String generateCode() {
        String x = "" , y = "" , z = "";
        if(controllerClass != null) {
            x =  controllerClass.generateCode();
        }
        if (regularClass != null){
            y = regularClass.generateCode();
        }
        if (widgetClass != null){
            z =  widgetClass.generateCode();
        }
        return   x+"\n"
                +y+"\n"
                +z+"\n";
    }
}
