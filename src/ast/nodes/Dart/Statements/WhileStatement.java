package ast.nodes.Dart.Statements;

import ast.nodes.Dart.Block;
import ast.nodes.Dart.Statements.Expressions.Expression;
import ast.nodes.Node;

public class WhileStatement extends Node {
    Expression expression ;
    Block block;

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    @Override
    public String toString() {
        return "\nWhileStatement{" +
                "\nexpression=" + expression +
                ", \nblock=" + block +
                "\n}";
    }

    @Override
    public String generateCode() {
        return "while ( "+expression.generateCode() +" )" + block.generateCode();
    }
}
