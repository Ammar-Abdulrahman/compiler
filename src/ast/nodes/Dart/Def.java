package ast.nodes.Dart;

import ast.nodes.Dart.Statements.ControllerPutFind;
import ast.nodes.Dart.Statements.Declare;
import ast.nodes.Node;

public class Def extends Node {
    Function function;
    Declare declare;
    ControllerPutFind controllerPutFind;

    public ControllerPutFind getControllerPutFind() {
        return controllerPutFind;
    }

    public void setControllerPutFind(ControllerPutFind controllerPutFind) {
        this.controllerPutFind = controllerPutFind;
    }

    public Function getFunction() {
        return function;
    }

    public void setFunction(Function function) {
        this.function = function;
    }

    public Declare getDeclare() {
        return declare;
    }

    public void setDeclare(Declare declare) {
        this.declare = declare;
    }

    @Override
    public String toString() {
        if (function != null){
            return "\nDef{" +
                "\nfunction=" + function +
                    "\n}";
        }
        else if (declare != null) {
            return "\nDef{" +
                    ", \ndeclare=" + declare +
                    "\n}";
        } else if (controllerPutFind != null) {
            return "\nDef{" +
                    ", \ncontrollerPutFind=" + controllerPutFind +
                    "\n}";
        }
        return null;
    }
}
