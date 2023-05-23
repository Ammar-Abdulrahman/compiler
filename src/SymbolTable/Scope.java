package SymbolTable;

public class Scope {
    private int level;
    private Scope child;
    private int id;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Scope getChild() {
        return child;
    }

    public void setChild(Scope child) {
        this.child = child;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
