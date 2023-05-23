package ast.nodes.Dart;

import ast.nodes.Dart.Statements.Expressions.Expr;
import ast.nodes.Node;

public class ChangingValue extends Node {
    String var;
    Expr expr;

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }

    public Expr getExpr() {
        return expr;
    }

    public void setExpr(Expr expr) {
        this.expr = expr;
    }

    @Override
    public String toString() {
        return "\nChangingValue{" +
                "\nvar='" + var + '\'' +
                ", \nexpr=" + expr +
                "\n";
    }
}
