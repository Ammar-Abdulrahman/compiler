package SymbolTable;

import java.util.ArrayList;

public class FunSymbol extends Scope{
    String key;
    String return_type;
    ArrayList<VarSymbol> varSymbols;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public ArrayList<VarSymbol> getVarSymbols() {
        return varSymbols;
    }

    public void setVarSymbols(ArrayList<VarSymbol> varSymbols) {
        this.varSymbols = varSymbols;
    }


    public String print(){
        if(varSymbols != null){
            return ("Key: " + this.key + ", Return Type: " + return_type + ", Variables: " + varSymbols + "\n");
        }
        return ("Key: " + this.key + ", Return Type: " + return_type + "\n");
    }
}
