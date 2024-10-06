package ast.nodes.Flutter;
import ast.nodes.Node;

public class MaterialTitle extends Node {
    private String stringLine;

    public String getStringLine() {
        return stringLine;
    }

    public void setStringLine(String stringLine) {
        this.stringLine = stringLine;
    }

    @Override
    public String toString() {
        return "\nMaterialTitle{" +
                "\nstringLine=" + stringLine +
                "\n}";
    }

    @Override
    public String generateCode() {
        return "";
    }
}
