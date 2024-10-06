package ast.nodes.Dart.Statements;

import ast.nodes.Dart.Statements.Expressions.Expression;
import ast.nodes.Node;

public class SwitchCase extends Node {
    Expression expression;
    Statement statement;

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    @Override
    public String toString() {
        return "\nSwitchCase{" +
                "\nexpression=" + expression +
                ", \nstatement=" + statement +
                "\n}";
    }

    @Override
    public String generateCode() {
        return "case " + expression.generateCode() + ":" + statement.generateCode() + "break;" ;
    }
}
