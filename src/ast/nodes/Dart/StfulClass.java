package ast.nodes.Dart;

import ast.nodes.Dart.Statements.Declare;
import ast.nodes.Node;

import java.util.ArrayList;

public class StfulClass extends Node {
    private String var;
    private Constructor constructor;
    private ArrayList<Def>defs = new ArrayList<>();
    private CreateState createState ;
    private StateClass stateClass;

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
}
