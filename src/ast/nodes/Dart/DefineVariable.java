package ast.nodes.Dart;

import ast.nodes.Dart.Statements.Type;
import ast.nodes.Node;

public class DefineVariable extends Node {
    private String var;
    private  int num;
    private Type type;
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private String stringLine;

    public String getStringLine() {
        return stringLine;
    }

    public void setStringLine(String stringLine) {
        this.stringLine = stringLine;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "\nDefineVariable{" +
                ",\ntype=" + type +
                "\nvar='" + var + '\'' +
                ",\nnum=" + num +
                ",\nstringLine=" + stringLine +
                "\n}";
    }
}
