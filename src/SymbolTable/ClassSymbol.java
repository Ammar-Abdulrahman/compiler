package SymbolTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ClassSymbol extends Scope{
    private HashMap<String , FunSymbol> funSymbols = new HashMap<String , FunSymbol>();

    public HashMap<String, FunSymbol> getFunSymbols() {
        return funSymbols;
    }

    public void setFunSymbols(HashMap<String, FunSymbol> funSymbols) {
        this.funSymbols = funSymbols;
    }


}
