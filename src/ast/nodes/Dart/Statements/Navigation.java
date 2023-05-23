package ast.nodes.Dart.Statements;

import ast.nodes.Dart.Istedaaaclass;
import ast.nodes.Node;

public class Navigation extends Node {
    Istedaaaclass istedaaaclass;

    public Istedaaaclass getIstedaaaclass() {
        return istedaaaclass;
    }

    public void setIstedaaaclass(Istedaaaclass istedaaaclass) {
        this.istedaaaclass = istedaaaclass;
    }

    @Override
    public String toString() {
        return "\nNavigation{" +
                "\nCallClass=" + istedaaaclass +
                "\n}";
    }
}
