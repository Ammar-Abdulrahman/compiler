package ast.nodes.Flutter.Widget;

import ast.nodes.Node;

import java.util.ArrayList;

public class Image extends Node {
    private ArrayList <String> var;
    private String stringLine;

    public String getStringLine() {
        return stringLine;
    }

    public void setStringLine(String stringLine) {
        this.stringLine = stringLine;
    }

    private int num;

    public ArrayList<String> getVar() {
        return var;
    }

    public void setVar(ArrayList<String> var) {
        this.var = var;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        if (stringLine != null){
            return "\nImage{" +
                    "\nstringLine=" + stringLine +
                    "\n}";
        }
        return "\nImage{" +
                "\nvar=" + var +
                ", \nnum=" + num +
                "\n}";
    }
}
