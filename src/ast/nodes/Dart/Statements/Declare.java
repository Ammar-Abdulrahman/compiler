package ast.nodes.Dart.Statements;

import SymbolTable.VarSymbol;
import ast.nodes.Dart.DefineRXvariable;
import ast.nodes.Dart.DefineVariable;
import ast.nodes.Dart.List;
import ast.nodes.Node;

public class Declare extends Node {
    List list ;
    DefineVariable defineVariable;
    DefineRXvariable defineRXvariable ;


    public DefineRXvariable getDefineRXvariable() {
        return defineRXvariable;
    }

    public void setDefineRXvariable(DefineRXvariable defineRXvariable) {
        this.defineRXvariable = defineRXvariable;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public DefineVariable getDefineVariable() {
        return defineVariable;
    }

    public void setDefineVariable(DefineVariable defineVariable) {
        this.defineVariable = defineVariable;
    }

    @Override
    public String toString() {
        if (list != null){
        return "{" +
                "\nlist=" + list +
                "\n}";
        }
        if (defineVariable != null){
            return "{" +
                    " \n defineVariable=" + defineVariable +
                    "\n}";
        }
        if(defineRXvariable != null){
            return "{" +
                    " \n RXVariable=" + defineRXvariable +
                    "\n}";
        }
        return null;
    }

    @Override
    public String generateCode() {
        String x = "" , y = "" , z = "" ;
        if (defineVariable != null)
        {
         x = defineVariable.generateCode() ;
        }
        if (defineRXvariable != null)
        {
            y = defineRXvariable.generateCode();
        }
        if (list != null)
        {
            z = list.generateCode();
        }
        return x + y + z ;
    }
}
