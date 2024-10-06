package ast.nodes.Dart;

import ast.nodes.Node;

public class RunAppFunction extends Node {
    private Istedaaaclass istedaaaclass;

    public Istedaaaclass getIstedaaaclass() {
        return istedaaaclass;
    }

    public void setIstedaaaclass(Istedaaaclass istedaaaclass) {
        this.istedaaaclass = istedaaaclass;
    }

    @Override
    public String toString() {
        return "\nRunAppFunction{" +
                "\nClass=" + istedaaaclass +
                "\n}";
    }

    @Override
    public String generateCode() {
        return istedaaaclass.generateCode();
    }
}
