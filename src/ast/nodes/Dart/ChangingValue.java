package ast.nodes.Dart;

import SymbolTable.ListSymbol;
import SymbolTable.VarSymbol;
import ast.nodes.Dart.Statements.Expressions.Expr;
import ast.nodes.Node;


public class ChangingValue extends Node {
    String var;
    Expr expr;
    VarSymbol varSymbol;
    ListSymbol listSymbol;

    String string_line;
    //int num;

    public String getString_line() {
        return string_line;
    }

    public void setString_line(String string_line) {
        this.string_line = string_line;
    }

    //public int getNum() {
      //  return num;
    //}

//    public void setNum(int num) {
  //      this.num = num;
   // }

    public VarSymbol getVarSymbol() {
        return varSymbol;
    }

    public void setVarSymbol(VarSymbol varSymbol) {
        this.varSymbol = varSymbol;
    }

    public ListSymbol getListSymbol() {
        return listSymbol;
    }

    public void setListSymbol(ListSymbol listSymbol) {
        this.listSymbol = listSymbol;
    }

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
                ",\nexpr=" + expr +
                ",\nstring_line=" + string_line +
//                ",\nnum=" + num +
                "\n";
    }

    @Override
    public String generateCode() {
        if (var != null ){
            return var+" = "+ expr.getNum() +  ";";
        }
        if (var != null && string_line != null ){
            return var+" = "+"\""+string_line+"\"" + ";";
        }
            return "";
    }
}
