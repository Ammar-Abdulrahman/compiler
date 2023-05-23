package ast.nodes.Dart.Statements;

import ast.nodes.Node;

public class SetState extends Node {
    SetBody setBody ;

    public SetBody getSetBody() {
        return setBody;
    }

    public void setSetBody(SetBody setBody) {
        this.setBody = setBody;
    }

    @Override
    public String toString() {
        return "\nSetState{" +
                "\nsetBody=" + setBody +
                "\n}";
    }
}
