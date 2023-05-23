package ast.nodes.Dart.Statements;

public class Type {
    String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return " " + type + " "  ;
    }
}
