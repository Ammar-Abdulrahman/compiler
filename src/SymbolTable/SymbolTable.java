package SymbolTable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SymbolTable {
    List <Scope> scopes = new ArrayList<Scope>();
    HashMap<String, ClassSymbol> classSymbol = new HashMap<String, ClassSymbol>();

    public List<Scope> getScopes() {
        return scopes;
    }

    public void setScopes(List<Scope> scopes) {
        this.scopes = scopes;
    }

    public HashMap<String, ClassSymbol> getSymbols() {
        return classSymbol;
    }

    public void setSymbols(HashMap<String,ClassSymbol> symbols) {
        this.classSymbol = symbols;
    }
    public void print() {
        for (int i = 0; i < scopes.size(); i++) {

        }
    }
}

