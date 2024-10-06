package SymbolTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FunSymbol extends Scope{
    String type;
    String return_type;

    public  class ParameterSymbol{
        String type;
        String name;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
    HashMap<String,ParameterSymbol> parameters = new HashMap<>();

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public HashMap<String, ParameterSymbol> getParameters() {
        return parameters;
    }

    public void setParameters(HashMap<String, ParameterSymbol> parameters) {
        this.parameters = parameters;
    }



    public String getReturn_type() {
        return return_type;
    }

    public void setReturn_type(String return_type) {
        this.return_type = return_type;
    }
}
