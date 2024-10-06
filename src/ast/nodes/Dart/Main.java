package ast.nodes.Dart;
import ast.nodes.Dart.Statements.Declare;
import ast.nodes.Dart.Statements.Statement;
import ast.nodes.Dart.Statements.Type;
import ast.nodes.Node;
import java.util.ArrayList;

public class Main extends Node {
    ArrayList<Declare> declares ;
    Type type;
    RunAppFunction runAppFunction;

    ArrayList<Statement> statements;


    public ArrayList<Declare> getDeclares() {
        return declares;
    }

    public void setDeclares(ArrayList<Declare> declares) {
        this.declares = declares;
    }

    public RunAppFunction getRunAppFunction() {
        return runAppFunction;
    }

    public void setRunAppFunction(RunAppFunction runAppFunction) {
        this.runAppFunction = runAppFunction;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public ArrayList<Statement> getStatements() {
        return statements;
    }

    public void setStatements(ArrayList<Statement> statements) {
        this.statements = statements;
    }

    @Override
    public String toString() {
        return "\nMain{" +
                "\ndeclares= " + declares +
                ",\ntype= " + type +
                ",\nrunAppFunction= " + runAppFunction +
                ",\nstatements= " + statements +
                "\n}";
    }

    @Override
    public String generateCode() {
        String x = "";
        String y = "";
        if(declares != null && statements != null)
        {
            for (int i=0;i<declares.size();i++){
                x = x + declares.get(i).generateCode();
            }
            for (int i=0;i<statements.size();i++){
                y = y + statements.get(i).generateCode();
            }
            return "<?php"+"\n"+
            "void main(){ \n function "+runAppFunction.generateCode() + "\n" +  x + "\n" + y + "?>\n";
        }
        else if (declares != null)
        {
            for (int i=0;i<declares.size();i++){
                x = x + declares.get(i).generateCode();
            }
            return "<?php"+"\n"+
            "void main(){\n  function "+runAppFunction.generateCode() + "\n" + x + "?>\n";
        }
        else if (statements != null)
        {
            for (int i=0;i<statements.size();i++){
                y = y + statements.get(i).generateCode();
            }
            return "<?php"+"\n"+
            "void main(){\n function "+runAppFunction.generateCode() + "\n" + y + "?>\n";
        }
        return "";

    }
}
