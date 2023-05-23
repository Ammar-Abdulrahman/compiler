package ast.nodes.Dart.Statements.Expressions;

import ast.nodes.Node;

public class Expression extends Node {
    String var;
    Expression expression;
    Expr expr;
    int num;

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public Expr getExpr() {
        return expr;
    }

    public void setExpr(Expr expr) {
        this.expr = expr;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "\nExpression{" +
                "\nvar='" + var + '\'' +
                ", \nexpression=" + expression +
                ", \nexpr=" + expr +
                ", \nnum=" + num +
                "\n}";
    }
}
