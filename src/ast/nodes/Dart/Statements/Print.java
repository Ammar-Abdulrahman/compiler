package ast.nodes.Dart.Statements;

import ast.nodes.Node;

public class Print extends Node {
    private String var;
    private String stringLine;

    public String getStringLine() {
        return stringLine;
    }

    public void setStringLine(String stringLine) {
        this.stringLine = stringLine;
    }

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }

    @Override
    public String toString() {
        if (var != null) {
            return "\nPrint{" +
                    ",\nvar='" + var + '\'' +
                    "\n}";
        } else if (stringLine != null) {
            return "\nPrint{" +
                    ",\nstringLine='" + stringLine + '\'' +
                    "\n}";
        }
        return null;
    }

    @Override
    public String generateCode() {
        String x = "" , y = "";
        if (stringLine != null){
            x = stringLine;
        }
        if (var != null){
            y = var;
        }
        return "echo \"$" + x + y +"\";" ;
    }
}
