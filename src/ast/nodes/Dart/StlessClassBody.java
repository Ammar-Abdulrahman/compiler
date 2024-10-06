package ast.nodes.Dart;

import ast.nodes.Dart.Statements.Declare;
import ast.nodes.Node;

import java.util.ArrayList;

public class StlessClassBody extends Node {
    private Constructor constructor;
    private BuildFunction buildFunction;
    private ArrayList<Def> defs = new ArrayList<>();
    ArrayList<CallFunction> callFunctions = new ArrayList<>();

    public ArrayList<CallFunction> getCallFunctions() {
        return callFunctions;
    }

    public void setCallFunctions(ArrayList<CallFunction> callFunctions) {
        this.callFunctions = callFunctions;
    }

    public Constructor getConstructor() {
        return constructor;
    }

    public void setConstructor(Constructor constructor) {
        this.constructor = constructor;
    }

    public BuildFunction getBuildFunction() {
        return buildFunction;
    }

    public void setBuildFunction(BuildFunction buildFunction) {
        this.buildFunction = buildFunction;
    }

    public ArrayList<Def> getDefs() {
        return defs;
    }

    public void setDefs(ArrayList<Def> defs) {
        this.defs = defs;
    }

    @Override
    public String toString() {
        return "\nStateLessClassBody{" +
                "\nconstructor=" + constructor +
                ",\nbuildFunction=" + buildFunction +
                ",\ndefs=" + defs +
                ",\nCallFunction="+callFunctions +
                "\n}";
    }

    @Override
    public String generateCode() {
//        if (constructor != null && defs != null && callFunctions != null )
//        {
//            for (int i=0 ; i<defs.size();i++ ){
//                 x = x + defs.get(i).generateCode();
//            }
//            for (int j =0 ; j<callFunctions.size() ; j++ ){
//                y = y + callFunctions.get(j).generateCode();
//            }
//            return  constructor.generateCode()
//                    + x +"\n"
//                    + y + "\n";
//        }
//        else if ( defs != null && callFunctions != null)
//        {
//            for (int i=0 ; i<defs.size();i++ ){
//                x = x + defs.get(i).generateCode();
//            }
//            for (int j =0 ; j<callFunctions.size() ; j++ ){
//                y = y + callFunctions.get(j).generateCode();
//            }
//            return  x +"\n"
//                    + y + "\n";
//        }
//        else if (constructor != null && defs != null)
//        {
//            for (int i=0 ; i<defs.size();i++ ){
//                x = x + defs.get(i).generateCode();
//            }
//            return  constructor.generateCode()
//                    + x +"\n";
//        }
//        else if (constructor != null && callFunctions != null)
//        {
//            for (int j =0 ; j<callFunctions.size() ; j++ ){
//                y = y + callFunctions.get(j).generateCode();
//            }
//            return  constructor.generateCode()
//                    + y + "\n";
//        }
//        else if (constructor != null)
//        {
//            return  constructor.generateCode()
//                    +"\n";
//        }
//        else if (callFunctions != null)
//        {
//            for (int j =0 ; j<callFunctions.size() ; j++ ){
//                y = y + callFunctions.get(j).generateCode();
//            }
//            return y + "\n";
//        }
//        else if (defs != null)
//        {
//            for (int i=0 ; i<defs.size();i++ ){
//                x = x + defs.get(i).generateCode();
//            }
//            return   x +"\n"
//                    + "\n";
//        }
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
        if (buildFunction != null){
            c = buildFunction.generateCode()+ "\"\n" ;
        }

        return "<?php"+"\n"+ x+y+z + "\n" +
                "?> \n" + c ;
    }
}
