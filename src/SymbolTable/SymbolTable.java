package SymbolTable;

import ast.nodes.Node;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SymbolTable {
    public static ArrayList<Node>nodes1 = new ArrayList<>();
    public boolean hasSyntaxError = false;
    public boolean hasSemanticError = false;
    List<Scope> scopes = new ArrayList<Scope>();

    FunSymbol main = new FunSymbol();

    public List<Scope> getScopes() {
        return scopes;
    }

    public void setScopes(List<Scope> scopes) {
        this.scopes = scopes;
    }

    public FunSymbol getMain() {
        return main;
    }

    public void setMain(FunSymbol main) {
        this.main = main;
    }

    public void print() {
        System.out.println("{\nScope level: " + 1);
        System.out.println("main type: " + this.main.getType());
        System.out.println("Parent scope: " + "Program");
        System.out.println("-variables-");
        for (VarSymbol varSymbol : this.main.getVarSymbol().values()) {
            System.out.println("variable: ");
            System.out.println("\ttype: " + varSymbol.getType());
            System.out.println("\tvariable name: " + varSymbol.getKey());
            System.out.println("\tvalue: " + varSymbol.getValue());
        }
        for (ListSymbol varSymbols : this.main.getListSymbolHashMap().values()) {
            System.out.println("List: ");
            System.out.println("\tname: " + varSymbols.getKey());
            for (int i = 0; i < varSymbols.values.size(); i++)
                System.out.println("\t\tvalue[" + i + "]: " + varSymbols.getValues().get(i));
        }
        System.out.println("}");
        System.out.println("--------------------");
        for (Scope scope : scopes) {
            if (scope instanceof ClassSymbol) {
                System.out.println("{");
                ClassSymbol classSymbol = (ClassSymbol) scope;
                System.out.println("Scope level: " + scope.getLevel());
                System.out.println("Class name: " + classSymbol.getKey());
                // Print variable symbols
                for (Map.Entry<String, VarSymbol> entry : scope.getVarSymbol().entrySet()) {
                    VarSymbol varSymbol = entry.getValue();
                    System.out.println("\tVariable name: " + varSymbol.getKey() +
                            "\n\ttype: " + varSymbol.getType() +
                            "\n\tvalue: " + varSymbol.getValue()
                    );
                }

                // Print list symbols
                for (Map.Entry<String, ListSymbol> entry : scope.getListSymbolHashMap().entrySet()) {
                    ListSymbol listSymbol = entry.getValue();
                    System.out.println("\tList name: " + listSymbol.getKey() +
                            "\n\tvalues: " + listSymbol.getValues() +
                            "\n\ttype: List");
                }
                System.out.println("}");
            }
            else if (scope instanceof FunSymbol) {
                System.out.println("{");
                // Print function symbols
                FunSymbol funSymbol = (FunSymbol) scope;
                System.out.println("Scope level: " + scope.getLevel());
                System.out.println("function name: " + funSymbol.getKey());
                System.out.print("( ");
                for (Map.Entry<String, FunSymbol.ParameterSymbol> symbolEntry :
                        ((FunSymbol) scope).getParameters().entrySet()) {
                    FunSymbol.ParameterSymbol parameterSymbol = symbolEntry.getValue();
                    System.out.print( parameterSymbol.getType() + " " + parameterSymbol.getName() + " , ");
                }
                System.out.println(" )");
                System.out.println("Parent scope: " + scope.getParent().getKey());

                // Print variable symbols
                for (Map.Entry<String, VarSymbol> entry : scope.getVarSymbol().entrySet()) {
                    VarSymbol varSymbol = entry.getValue();
                    System.out.println("\tVariable name: " + varSymbol.getKey() +
                            "\n\ttype: " + varSymbol.getType() +
                            "\n\tvalue: " + varSymbol.getValue());

                }

                // Print list symbols
                for (Map.Entry<String, ListSymbol> entry : scope.getListSymbolHashMap().entrySet()) {
                    ListSymbol listSymbol = entry.getValue();
                    System.out.println("\tList symbol - key: " + listSymbol.getKey() +
                            "\n\tvalues: " + listSymbol.getValues() +
                            "\n\ttype: List");
                }
                System.out.println("}");
            }
            else {
                System.out.println("{");
                // Print function symbols
                System.out.println("Scope level: " + scope.getLevel());
                System.out.println("Parent scope: " + scope.getParent().getKey());
                System.out.println("Condition Type: " + scope.getKey());
                // Print variable symbols
                for (Map.Entry<String, VarSymbol> entry : scope.getVarSymbol().entrySet()) {
                    VarSymbol varSymbol = entry.getValue();
                    System.out.println("\tVariable name: " + varSymbol.getKey() +
                            "\n\ttype: " + varSymbol.getType() +
                            "\n\tvalue: " + varSymbol.getValue());

                }
            }
            System.out.println("--------------------");
        }
    }

    private static SymbolTable single_instance = null;

    public static SymbolTable getInstance() {
        if (single_instance == null)
            single_instance = new SymbolTable();

        return single_instance;
    }

    public void write(String text) throws IOException {
        FileWriter syntaxError = new FileWriter("Files//SyntaxError.txt");
        syntaxError.write(text);
        syntaxError.close();
    }
}

