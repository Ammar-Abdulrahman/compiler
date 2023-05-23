package ast.nodes.Dart.Statements;

import ast.nodes.Node;

public class DefaultCase extends Node {
    Statement statement;

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    @Override
    public String toString() {
        return "\nDefaultCase{" +
                "\nstatement=" + statement +
                "\n}";
    }
}
