package ast.nodes.Dart.Statements.Expressions;
import java.util.ArrayList;
import java.util.List;

public class Expr {
    private ArrayList<Expr> expr;
    private ArrayList<String> var;
    private int num;
    private String expression;
    public ArrayList<Expr> getExpr() {
        return expr;
    }

    public void setExpr(ArrayList<Expr> expr) {
        this.expr = expr;
    }

    public ArrayList<String> getVar() {
        return var;
    }

    public void setVar(ArrayList<String> var) {
        this.var = var;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "\nExpr{" +
                "\nexpr=" + expr + "  " + expression +
                ", \nvar=" + var +
                ", \nnum=" + num +
                "\n}";
    }
}
