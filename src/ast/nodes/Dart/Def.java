package ast.nodes.Dart;

import ast.nodes.Dart.Statements.ControllerPutFind;
import ast.nodes.Dart.Statements.Declare;
import ast.nodes.Node;

public class Def extends Node {
    Function function;
    Declare declare;
    ControllerPutFind controllerPutFind;

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

    public ControllerPutFind getControllerPutFind() {
        return controllerPutFind;
    }

    public void setControllerPutFind(ControllerPutFind controllerPutFind) {
        this.controllerPutFind = controllerPutFind;
    }

    @Override
    public String toString() {
        String x = "" , y ="" , z ="";
        if (function != null){
             y =  "\nDef{" +
                "\nfunction=" + function +
                    "\n}";
        }
        if (declare != null) {
             x = "\nDef{" +
                    ", \ndeclare=" + declare +
                    "\n}";
        } if (controllerPutFind != null) {
            z = "\nDef{" +
                    ", \ncontrollerPutFind=" + controllerPutFind +
                    "\n}";
        }
        return x + y + z;
    }

    @Override
    public String generateCode() {
        if (function != null)
        {
            return function.generateCode();
        }
        if(declare != null)
        {
            return declare.generateCode();
        }
        if (controllerPutFind != null)
        {
            return controllerPutFind.generateCode();
        }
        return "";
    }
}
