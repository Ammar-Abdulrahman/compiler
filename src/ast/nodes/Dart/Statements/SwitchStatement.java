package ast.nodes.Dart.Statements;

import ast.nodes.Dart.Statements.Expressions.Expression;
import ast.nodes.Node;

import java.util.ArrayList;

public class SwitchStatement extends Node {
    Expression expression;
    ArrayList<SwitchCase> switchCase;
    DefaultCase defaultCase;

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public ArrayList<SwitchCase> getSwitchCase() {
        return switchCase;
    }

    public void setSwitchCase(ArrayList<SwitchCase> switchCase) {
        this.switchCase = switchCase;
    }

    public DefaultCase getDefaultCase() {
        return defaultCase;
    }

    public void setDefaultCase(DefaultCase defaultCase) {
        this.defaultCase = defaultCase;
    }

    @Override
    public String toString() {
        return "\nSwitchStatement{" +
                "\nexpression=" + expression +
                ", \nswitchCase=" + switchCase +
                ", \ndefaultCase=" + defaultCase +
                "\n}";
    }

    @Override
    public String generateCode() {
        String x = "" , y = "";
        if (switchCase != null){
            for (int i =0 ;i<switchCase.size();i++){
                x = x + switchCase.get(i).generateCode();
            }
        }
        if (defaultCase != null ){
            y = defaultCase.generateCode();
        }
        return "switch ( " + expression.generateCode() + ") " +
                "{\n" +x + y +"\n}\n";
    }
}
