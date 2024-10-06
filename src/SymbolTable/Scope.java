package SymbolTable;

import java.util.HashMap;

public class Scope {
    String key;
    private int level;
    private Scope parent;
    private int id;
    HashMap<String,VarSymbol> varSymbolHashMap = new HashMap<>();
    HashMap<String,ListSymbol> listSymbolHashMap = new HashMap<>();

    public HashMap<String, ListSymbol> getListSymbolHashMap() {
        return listSymbolHashMap;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setListSymbolHashMap(HashMap<String,ListSymbol> listSymbolHashMap) {
        this.listSymbolHashMap = listSymbolHashMap;
    }

    public HashMap<String, VarSymbol> getVarSymbol() {
        return varSymbolHashMap;
    }

    public void setVarSymbol(HashMap<String, VarSymbol> varSymbolHashMap) {
        this.varSymbolHashMap = varSymbolHashMap;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Scope getParent() {
        return parent;
    }

    public void setParent(Scope parent) {
        this.parent = parent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
