package ast.nodes.Dart;

import ast.nodes.Dart.Statements.Declare;
import ast.nodes.Dart.Statements.Statement;
import ast.nodes.Dart.Statements.Type;
import ast.nodes.Node;

import java.util.ArrayList;

public class Function extends Node {
    ArrayList<FunctionOnParameter> functionOnParameters;
    String returnType;
    String var ;
    Type type;

    ArrayList<Statement> statements ;
    ArrayList<Declare> declares ;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }

    public ArrayList<Statement> getStatements() {
        return statements;
    }

    public void setStatements(ArrayList<Statement> statements) {
        this.statements = statements;
    }

    public ArrayList<Declare> getDeclares() {
        return declares;
    }

    public void setDeclares(ArrayList<Declare> declares) {
        this.declares = declares;
    }

    public ArrayList<FunctionOnParameter> getFunctionOnParameters() {
        return functionOnParameters;
    }

    public void setFunctionOnParameters(ArrayList<FunctionOnParameter> functionOnParameters) {
        this.functionOnParameters = functionOnParameters;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    @Override
    public String toString() {
        return "\nFunction{" +
                ",\ntype='" + type + '\'' +
                ",\nfunction_name='" + var + '\'' +
                "\nParameters=" + functionOnParameters +
                ", \nstatements=" + statements +
                ", \ndeclares=" + declares +
                ", \nreturn: " + returnType +
                "\n}";
    }
}
