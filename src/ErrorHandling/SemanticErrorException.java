package ErrorHandling;

import SymbolTable.SymbolTable;
import antlr.DartParser;
import ast.nodes.Dart.*;
import ast.nodes.Dart.Statements.Declare;
import java.util.ArrayList;
import java.util.HashMap;
import SymbolTable.*;
import ast.nodes.Dart.Statements.Statement;

public class SemanticErrorException {
    SymbolTable symbolTable = new SymbolTable();
    public boolean DuplicatedDeclare(Declare declare, HashMap<String, VarSymbol> varSymbols, HashMap<String, ListSymbol> listSymbols) {
        //TODO: check Variable
        if (declare.getDefineVariable() != null) {
            if (varSymbols.containsKey(declare.getDefineVariable().getVarSymbol().getKey())) {
                throw new RuntimeException("error in line " +
                        declare.getDefineVariable().getLine()
                        + " the variable " + "'" + declare.getDefineVariable().getVarSymbol().getKey() + "'" + " is already declared");
            } else {
                InvalidDeclare(declare);
                varSymbols.put(declare.getDefineVariable().getVar(),
                        declare.getDefineVariable().getVarSymbol());
            }
        }
        //TODO: check RXVariable
        if (declare.getDefineRXvariable() != null) {
            if (varSymbols.containsKey(declare.getDefineRXvariable().getVarSymbol().getKey())) {
                throw new RuntimeException("error in line " +
                        declare.getDefineRXvariable().getLine()
                        + " the variable " + "'" + declare.getDefineRXvariable().getVarSymbol().getKey() + "'" + " is already declared");
            } else
                varSymbols.put(declare.getDefineRXvariable().getVar(),
                        declare.getDefineRXvariable().getVarSymbol());
        }
        //TODO: check LIST
        if (declare.getList() != null) {
            if (listSymbols.containsKey(declare.getList().getListSymbol().getKey())
            ) {
                throw new RuntimeException("error in line " +
                        declare.getList().getLine()
                        + " the variable " + "'" + declare.getList().getListSymbol().getKey() + "'" + " is already declared");
            } else
                listSymbols.put(declare.getList().getVar(),
                        declare.getList().getListSymbol());
        }
        return false;
    }

    public Boolean InvalidDeclare(Declare declare) {
        if (declare.getDefineVariable().getType().getType().equals("int") &&
                declare.getDefineVariable().getValue() instanceof String)
            throw new RuntimeException("error in line " +
                    declare.getDefineVariable().getLine()
                    + " Invalid value for int variable");
        else if (declare.getDefineVariable().getType().getType().equals("String") &&
                declare.getDefineVariable().getValue() instanceof Integer) {
            throw new RuntimeException("error in line " +
                    declare.getDefineVariable().getLine()
                    + " Invalid value for String variable");
        }
        return false;
    }

    public Boolean NewDeclare(Statement statement, HashMap<String, VarSymbol> varSymbols) {
        if (statement.getChangingValue() != null) {
            if (!varSymbols.containsKey(statement.getChangingValue().getVar()))
                throw new RuntimeException("error in line " +
                        statement.getChangingValue().getLine()
                        + " the variable " + "'" + statement.getChangingValue().getVar() + "'" + " is not declared");
            else {
                if (varSymbols.get(statement.getChangingValue().getVarSymbol().getKey()).getType().equals("int")
                        && statement.getChangingValue().getVarSymbol().getValue() instanceof String)
                    throw new RuntimeException("error in line " + statement.getChangingValue().getLine()
                            + " Invalid value for int variable");
                else if (varSymbols.get(statement.getChangingValue().getVarSymbol().getKey()).getType().equals("String")
                        && statement.getChangingValue().getVarSymbol().getValue() instanceof Integer) {
                    throw new RuntimeException("error in line " + statement.getChangingValue().getLine()
                            + " Invalid value for String variable");
                } else {
                    String type = varSymbols.get(statement.getChangingValue().getVarSymbol().getKey()).getType();
                    varSymbols.put(statement.getChangingValue().getVarSymbol().getKey(),
                            statement.getChangingValue().getVarSymbol());
                    varSymbols.get(statement.getChangingValue().getVarSymbol().getKey()).setType(type);
                }
            }
        }
        return false;
    }

    public Boolean FunctionDeclared(Def def, HashMap<String, FunSymbol> funSymbols) {
        if (def.getFunction().getFunSymbol() != null) {
            if (funSymbols.containsKey(def.getFunction().getFunSymbol().getKey())) {
                throw new RuntimeException("function is already defined in line " + def.getLine() + "\n");
            }
            funSymbols.put(def.getFunction().getFunSymbol().getKey(),
                    def.getFunction().getFunSymbol());
        }
        return false;
    }

    public Boolean FunctionReturnType(FunSymbol funSymbol, DartParser.FunctionContext ctx) {
        if (!funSymbol.getType().equals(funSymbol.getReturn_type())) {
            if (ctx.RETURN() != null) {
                int returnLine = ctx.RETURN().getSymbol().getLine();
                throw new RuntimeException("error in line " + returnLine + " the function must return "
                        + funSymbol.getType());
            } else {
                int returnLine = ctx.LEFTCURLY().getSymbol().getLine();
                throw new RuntimeException("error in line " + returnLine + " the function must return "
                        + funSymbol.getType());
            }
        }
        return false;
    }

    public Boolean DuplicateClass(ArrayList<Scope> scopes, ClassSymbol classSymbol, int line) {

        for (int i = 0; i < scopes.size(); i++) {
            if (scopes.get(i) instanceof ClassSymbol &&
                    scopes.get(i).getKey().equals(classSymbol.getKey())
            ){
                throw new RuntimeException("error in line: " + line
                        + " the class is already exists");
            }
        }
        return false;
    }

    public Boolean ExtendsNotDeclaredClass(ArrayList<Scope> scopes, String extension, int line) {
        ArrayList<Boolean> check = new ArrayList<>();
        for (int i = 0; i < scopes.size(); i++) {
            if (scopes.get(i) instanceof ClassSymbol) {
                if (!scopes.get(i).getKey().equals(extension)) {
                    check.add(true);
                } else
                    check.add(false);
            }
        }
        if (!check.contains(false)){
            throw new RuntimeException("error in line: " + line + " extension failed the class is not declared");
        }
        return false;
    }

    public Boolean CallNotDefinedFunction(ClassSymbol classSymbol, CallFunction callFunction) {
        if ((!classSymbol.getFunSymbols().isEmpty()
                && !classSymbol.getFunSymbols().containsKey(callFunction.getName()))
                || callFunction.getParameters().size() !=
                classSymbol.getFunSymbols().get(callFunction.getName()).getParameters().size()){
            throw new RuntimeException("error in line: "+ callFunction.getLine() +" function not declared");
        }
        return false;
    }

    public Boolean FunctionParameterNotDefined(HashMap<String, VarSymbol>varSymbols, CallFunction callFunction){
        ArrayList<Boolean> check = new ArrayList<>();
        for (int j = 0 ; j < callFunction.getParameters().size(); j++) {
            if (!varSymbols.containsKey(callFunction.getParameters().get(j))){
                check.add(false);
            }else {
                check.add(true);
            }
        }
        if (!check.contains(true)){
            throw new RuntimeException("error in line: "+ callFunction.getLine()+ " undefined variable");
        }
        return false;
    }
}
