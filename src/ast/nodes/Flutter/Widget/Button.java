package ast.nodes.Flutter.Widget;

import ast.nodes.Dart.Statements.OnPressed;
import ast.nodes.Flutter.Child;
import ast.nodes.Node;

public class Button extends Node {
    private Child child;

    private OnPressed onPressed ;

    public Child getChild() {
        return child;
    }

    public void setChild(Child child) {
        this.child = child;
    }

    public OnPressed getOnPressed() {
        return onPressed;
    }

    public void setOnPressed(OnPressed onPressed) {
        this.onPressed = onPressed;
    }

    @Override
    public String toString() {
        return "\nButton{" +
                "\nchild=" + child +
                "\nonPressed=" +onPressed +
                "\n}" ;
    }

    @Override
    public String generateCode() {
        int c = 0;
        c++;
        String x = "";
        if (child.getWidget() != null)  {
        return x = "<button class=\"elevated-button\"  onclick=\"goToOther" + c + "()\">" + child.generateCode()   + "</button>\n" +
                "    <script>\n" +
                "      function goToOther" + c + "(){\n" +
                "        window.location.href = \"file_6" + ".php?mytext=ali" + "\";\n" +
                "      }\n" +
                "    </script>";
    }
        return "";
    }
}