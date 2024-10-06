package ast.nodes.Dart;

import SymbolTable.FunSymbol;
import ast.nodes.Dart.Statements.Declare;
import ast.nodes.Dart.Statements.Statement;
import ast.nodes.Dart.Statements.Type;
import ast.nodes.Node;

import java.util.ArrayList;

public class Function extends Node {
    FunSymbol funSymbol;

    ArrayList<FunctionOnParameter> functionOnParameters;
    String returnType;
    String var ;
    Type type;
    ArrayList<Statement> statements ;
    ArrayList<Declare> declares ;

    public FunSymbol getFunSymbol() {
        return funSymbol;
    }

    public void setFunSymbol(FunSymbol funSymbol) {
        this.funSymbol = funSymbol;
    }

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

    @Override
    public String generateCode() {
        String x = ""  , y = "" , z = "";
        if (functionOnParameters != null) {
            for (int i=0 ; i <functionOnParameters.size();i++){
                x = x + functionOnParameters.get(i).generateCode();
           }
        }
        if(statements != null){
                for (int i =0 ; i<statements.size();i++){
                    y = y + statements.get(i).generateCode();
                }
            }
        if(declares != null){
                for (int i =0 ; i<declares.size();i++){
                    z = z + declares.get(i).generateCode();
                }
        }
        return "function " + var +"( " + x +" )"+ ":"+type+"{\n"
                +y + "\n"
                +z + "\n"
                +"}\n";
    }
}
