package ast.nodes.Flutter.Widget;

import ast.nodes.Node;

import java.util.ArrayList;

public class Text extends Node {
    private ArrayList <String> vars  = new ArrayList<>();
    private String stringLine;

    public String getStringLine() {
        return stringLine;
    }

    public void setStringLine(String stringLine) {
        this.stringLine = stringLine;
    }

    public ArrayList<String> getVars() {
        return vars;
    }

    public void setVars(ArrayList<String> vars) {
        this.vars = vars;
    }

    private ArrayList<Integer> nums;

    public ArrayList<Integer> getNums() {
        return nums;
    }

    public void setNums(ArrayList<Integer> nums) {
        this.nums = nums;
    }

    @Override
    public String toString() {
        if (stringLine != null){
            return "\nText{" +
                    "\nstringLine=" + stringLine +
                    ",\nnums=" + nums +
                    "\n}";
        }
        return "\nText{" +
                "\nvars=" + vars +
                ",\nnums=" + nums +
                "\n}";
    }
}
