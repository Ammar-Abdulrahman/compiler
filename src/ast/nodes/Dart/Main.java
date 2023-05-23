package ast.nodes.Dart;
import ast.nodes.Dart.Statements.Declare;
import ast.nodes.Dart.Statements.Type;
import ast.nodes.Node;
import java.util.ArrayList;

public class Main extends Node {
    ArrayList<Declare> declares ;
    Type type;

    public ArrayList<Declare> getDeclares() {
        return declares;
    }

    public void setDeclares(ArrayList<Declare> declares) {
        this.declares = declares;
    }

    private RunAppFunction runAppFunction;

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

    @Override
    public String toString() {
        return "\nMain{" +
                "\ndeclares=" + declares +
                ", \ntype=" + type +
                ", \nrunAppFunction=" + runAppFunction +
                "\n}";
    }
}
