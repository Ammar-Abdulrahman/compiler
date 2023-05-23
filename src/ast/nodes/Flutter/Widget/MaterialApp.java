package ast.nodes.Flutter.Widget;

import ast.nodes.Dart.Istedaaaclass;
import ast.nodes.Flutter.MaterialTitle;
import ast.nodes.Node;

public class MaterialApp extends Node {
    private Istedaaaclass istedaaaclass;
    private MaterialTitle title ;
    public Istedaaaclass getIstedaaaclass() {
        return istedaaaclass;
    }

    public MaterialTitle getTitle() {
        return title;
    }

    public void setTitle(MaterialTitle title) {
        this.title = title;
    }

    public void setIstedaaaclass(Istedaaaclass istedaaaclass) {
        this.istedaaaclass = istedaaaclass;
    }

    @Override
    public String toString() {
        return "\nMaterialApp{" +
                "\nCallClass=" + istedaaaclass +
                "\nmaterialTitle=" + title +
                "\n}";
    }
}
