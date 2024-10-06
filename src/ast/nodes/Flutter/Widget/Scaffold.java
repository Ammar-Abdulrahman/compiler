package ast.nodes.Flutter.Widget;

import ast.nodes.Node;

public class Scaffold extends Node {
    private Widget body;

    private Text texts;

    public Text getTexts() {
        return texts;
    }

    public void setTexts(Text texts) {
        this.texts = texts;
    }

    public Widget getBody() {
        return body;
    }

    public void setBody(Widget body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "\nScaffold{" +
                "\nbody=" + body +
                ", \ntexts=" + texts +
                "\n}";
    }

    @Override
    public String generateCode() {
        return "<body>"
                +body.generateCode()
                +"</body>" ;
    }
}
