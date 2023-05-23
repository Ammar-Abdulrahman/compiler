package ast.nodes.Dart;

import ast.nodes.Node;

public class WidgetClass extends Node {
    private StlessClass stlessClass;
    private StfulClass stfulClass;

    public StlessClass getStlessClass() {
        return stlessClass;
    }

    public void setStlessClass(StlessClass stlessClass) {
        this.stlessClass = stlessClass;
    }

    public StfulClass getStfulClass() {
        return stfulClass;
    }

    public void setStfulClass(StfulClass stfulClass) {
        this.stfulClass = stfulClass;
    }

//    if(this.getStfulClass()!=null)
    @Override
    public String toString() {
        if (stlessClass != null){
            return "{" +
                    "\nstateLessClass=" + stlessClass+
                    "\n}";
        }
        if (stfulClass != null){
            return "{" +
                    "\nstateFullClass=" + stfulClass +
                    "\n}";
        }
        return null;
    }
}

