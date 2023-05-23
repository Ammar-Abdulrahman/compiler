package ErrorHandling;

import SymbolTable.SymbolTable;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SemanticError {
//    SymbolTable symbolTable = new SymbolTable();
//    public ArrayList<String> Errors=new ArrayList<>();
//
//    public SemanticError() {
//
//    }
//    public void checkLink(){
//        boolean isError=false;
//        for (int i=0;i<symbolTable.sc;i++){
//            if (!isError)
//                for (int j=0;j<symbolTable.pages.get(symbolTable.pageName.get(i)).size();j++)
//                {
//                    for (int k=0;k<symbolTable.pages.get(symbolTable.pageName.get(i)).size();k++){
//                        if (symbolTable.pages.get(symbolTable.pageName.get(i)).get(j).generateCode().contains("href")&&symbolTable.pages.get(symbolTable.pageName.get(i)).get(k).generateCode().contains("href")){
//                            if (symbolTable.pages.get(symbolTable.pageName.get(i)).get(j).generateCode().split("\"")[1].equals(symbolTable.pages.get(symbolTable.pageName.get(i)).get(k).generateCode().split("\"")[1])&&j!=k){
//                                Errors.add("the link with title "+symbolTable.pages.get(symbolTable.pageName.get(i)).get(k).generateCode()+" is already exist in line "+symbolTable.pages.get(symbolTable.pageName.get(i)).get(k).getLine());
//
//
//                            }
//                        }
//                    }
//                }
//
//        }
//
//    }
//
//    public void checkErrors() throws IOException {
//        if (!symbolTable.hasSyntaxError) {
//            checkLink();
//        }
//        if (!Errors.isEmpty()){
//            symbolTable.hasSemanticError=true;
//        }
//        for (int i=0;i<Errors.size();i++){
//            FileWriter test = new FileWriter("output//SemanticError.txt");
//            test.write(Errors.get(i));
//            test.close();
//
//        }
//    }
}
