package ast.nodes.Dart.Statements;

import ast.nodes.Dart.Block;
import ast.nodes.Dart.ChangingValue;
import ast.nodes.Dart.Statements.Expressions.Expr;
import ast.nodes.Dart.Statements.Expressions.Expression;
import ast.nodes.Node;

public class Statement extends Node {

    WhileStatement whileStatement;
    IfStatement ifStatement;
    SwitchStatement switchStatement;
    TryStatement tryStatement;
    Block block;
    Print print;
    Navigation navigation;
    SetState state;
    Expr expr;
    Expression expression;
    ChangingValue changingValue;
    ControllerFunctions controllerFunctions;
    ControllerAttributes controllerAttributes;


    public WhileStatement getWhileStatement() {
        return whileStatement;
    }

    public void setWhileStatement(WhileStatement whileStatement) {
        this.whileStatement = whileStatement;
    }

    public IfStatement getIfStatement() {
        return ifStatement;
    }

    public void setIfStatement(IfStatement ifStatement) {
        this.ifStatement = ifStatement;
    }

    public SwitchStatement getSwitchStatement() {
        return switchStatement;
    }

    public void setSwitchStatement(SwitchStatement switchStatement) {
        this.switchStatement = switchStatement;
    }

    public TryStatement getTryStatement() {
        return tryStatement;
    }

    public void setTryStatement(TryStatement tryStatement) {
        this.tryStatement = tryStatement;
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public Print getPrint() {
        return print;
    }

    public void setPrint(Print print) {
        this.print = print;
    }

    public Navigation getNavigation() {
        return navigation;
    }

    public void setNavigation(Navigation navigation) {
        this.navigation = navigation;
    }

    public SetState getState() {
        return state;
    }

    public void setState(SetState state) {
        this.state = state;
    }

    public Expr getExpr() {
        return expr;
    }

    public void setExpr(Expr expr) {
        this.expr = expr;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public ChangingValue getChangingValue() {
        return changingValue;
    }

    public void setChangingValue(ChangingValue changingValue) {
        this.changingValue = changingValue;
    }


    public ControllerFunctions getControllerFunctions() {
        return controllerFunctions;
    }

    public void setControllerFunctions(ControllerFunctions controllerFunctions) {
        this.controllerFunctions = controllerFunctions;
    }

    public ControllerAttributes getControllerAttributes() {
        return controllerAttributes;
    }

    public void setControllerAttributes(ControllerAttributes controllerAttributes) {
        this.controllerAttributes = controllerAttributes;
    }

    @Override
    public String toString() {
        if (whileStatement != null) {
            return "Statement{" +
                    ", whileStatement=" + whileStatement + "\n}";
        } else if (ifStatement != null) {
            return "Statement{" +
                    ", ifStatement=" + ifStatement + "\n}";
        } else if (switchStatement != null) {
            return "Statement{" +
                    ", switchStatement=" + switchStatement + "\n}";
        } else if (tryStatement != null) {
            return "Statement{" +
                    ", tryStatement=" + tryStatement + "\n}";
        } else if (block != null) {
            return "Statement{" +
                    ", block=" + block + "\n}";
        } else if (print != null) {
            return "Statement{" +
                    ", print=" + print + "\n}";
        } else if (navigation != null) {
            return "Statement{" +
                    ", navigation=" + navigation + "\n}";
        } else if (state != null) {
            return "Statement{" +
                    ", state=" + state + "\n}";
        } else if (expr != null) {
            return "Statement{" +
                    ", expr=" + expr + "\n}";
        } else if (expression != null) {
            return "Statement{" +
                    ", expression=" + expression + "\n}";
        } else if (changingValue != null) {
            return "Statement{" +
                    ", changingValue=" + changingValue + "\n}";
        } else if (controllerFunctions != null) {
            return "Statement{" +
                    ", controllerFunctions=" + controllerFunctions + "\n}";
        } else if (controllerAttributes != null) {
            return "Statement{" +
                    ", controllerAttributes=" + controllerAttributes + "\n}";
        }
        return null;
    }

    @Override
    public String generateCode() {
        String whilee="",iff="",change="",swi="", tri="" ,blo ="" , pr ="" , nav ="" , stat="" ,exp ="" , expres ="" , contAtt  = "" , contFunc ="";
        if(whileStatement != null){
            whilee = whileStatement.generateCode();
        }
        if ( ifStatement != null){
            iff = ifStatement.generateCode();
        }
        if (switchStatement != null){
            swi = switchStatement.generateCode();
        }
        if (tryStatement != null){
           tri = tryStatement.generateCode();
        }
        if (block != null){
            blo = block.generateCode();
        }
        if (print != null){
            pr = print.generateCode();
        }
        if (navigation != null){
            nav = navigation.generateCode();
        }
        if(expr != null){
            exp = expr.generateCode();
        }
        if (expression != null){
            expres = expression.generateCode();
        }
        if (changingValue != null){
            change = changingValue.generateCode() + "\n";
        }
        if (controllerFunctions != null){
            contFunc = controllerFunctions.generateCode();
        }
        if (controllerAttributes != null){
            contAtt = controllerAttributes.generateCode();
        }
        return  whilee+iff+swi+tri+blo+pr+nav+exp+expres+change+contFunc+contAtt;
    }
}
