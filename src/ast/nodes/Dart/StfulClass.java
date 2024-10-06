package ast.nodes.Dart;

import SymbolTable.ClassSymbol;
import ast.nodes.Dart.Statements.Declare;
import ast.nodes.Node;

import java.util.ArrayList;

public class StfulClass extends Node {
    private String var;
    private Constructor constructor;
    private ArrayList<Def>defs = new ArrayList<>();
    private CreateState createState ;
    private StateClass stateClass;
    ClassSymbol classSymbol;
    ArrayList<CallFunction> callFunctions;

    public ArrayList<CallFunction> getCallFunctions() {
        return callFunctions;
    }

    public void setCallFunctions(ArrayList<CallFunction> callFunctions) {
        this.callFunctions = callFunctions;
    }

    public ClassSymbol getClassSymbol() {
        return classSymbol;
    }

    public void setClassSymbol(ClassSymbol classSymbol) {
        this.classSymbol = classSymbol;
    }

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }

    public Constructor getConstructor() {
        return constructor;
    }

    public void setConstructor(Constructor constructor) {
        this.constructor = constructor;
    }

    public ArrayList<Def> getDef() {
        return defs;
    }

    public void setDef(ArrayList<Def> defs) {
        this.defs = defs;
    }

    public CreateState getCreateState() {
        return createState;
    }

    public void setCreateState(CreateState createState) {
        this.createState = createState;
    }

    public StateClass getStateClass() {
        return stateClass;
    }

    public void setStateClass(StateClass stateClass) {
        this.stateClass = stateClass;
    }

    @Override
    public String toString() {
        return "\n{" +
                "\nclass'" + var + '\'' +
                ", \nconstructor=" + constructor +
                ", \ndef=" + defs +
                ", \ncreateState=" + createState +
                ", \nstateClass=" + stateClass +
                "\n}";
    }

    @Override
    public String generateCode() {
        String x = "" , y = "" , z = "" , c = "";
        if(defs != null){
            for (int i=0 ; i<defs.size();i++ ){
                x = x + defs.get(i).generateCode();
            }
        }
        if (constructor != null){
            y = constructor.generateCode();
        }
        if (callFunctions != null){
            for (int i=0;i<callFunctions.size();i++){
                z = z + callFunctions.get(i).generateCode();
            }
        }
        if (stateClass != null){
            c = stateClass.generateCode();
        }
        return "<?php \n " + x + y + z + "\n ?> \n"
                + c +"\n" ;
    }
}
