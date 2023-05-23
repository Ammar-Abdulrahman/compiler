package SymbolTable;

import java.util.ArrayList;
import java.util.HashMap;

public class ClassSymbol extends Scope{
    private String key;
    private HashMap<String , FunSymbol> funSymbols = new HashMap<String , FunSymbol>();
    private ArrayList<VarSymbol> varSymbols;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public ArrayList<VarSymbol> getVarSymbols() {
        return varSymbols;
    }

    public HashMap<String, FunSymbol> getFunSymbols() {
        return funSymbols;
    }

    public void setFunSymbols(HashMap<String, FunSymbol> funSymbols) {
        this.funSymbols = funSymbols;
    }

    public void setVarSymbols(ArrayList<VarSymbol> varSymbols) {
        this.varSymbols = varSymbols;
    }

    public String print(){
        return ("Key: " + this.key +", Functions" + this.funSymbols);
    }

}
