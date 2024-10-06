package ast.nodes;

import ast.Visitors.BaseVisitor;
import ast.nodes.Dart.Classes;
import ast.nodes.Dart.Main;
import ast.nodes.Dart.WidgetClass;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Prog extends Node {
    private Main main1;
    private WidgetClass widgetClass;
    private ArrayList<Classes> classes = new ArrayList<>();

    public Main getMain1() {
        return main1;
    }

    public void setMain1(Main main1) {
        this.main1 = main1;
    }

    public WidgetClass getWidgetClass() {
        return widgetClass;
    }

    public void setWidgetClass(WidgetClass widgetClass) {
        this.widgetClass = widgetClass;
    }

    public ArrayList<Classes> getClasses() {
        return classes;
    }

    public void setClasses(ArrayList<Classes> classes) {
        this.classes = classes;
    }

    @Override
    public String toString() {
        return "Program{" +
                "\nmain=" + main1 +
                ",\nwidgetClass=" + widgetClass +
                ",\nclasses=" + classes +
                "\n}" + '\n';
    }

    @Override
    public String generateCode() {
        String x = "";
            if (classes != null) {
                for (int i = 0; i < classes.size(); i++) {
                    try {
                        FileWriter fileWriterHtml = new FileWriter("Files//file_" + (i + 5) + ".php");
                        fileWriterHtml.write("<html>\n");
                        fileWriterHtml.write("\n" + classes.get(i).generateCode());
                        fileWriterHtml.write("\n</html>");
                        fileWriterHtml.close();
                    } catch (IOException e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                    }
                    x = x + classes.get(i).generateCode();
                }
            }

        return "<?php" + "\n" +
                "void main(){\n" + main1.generateCode() + " } " + "\n"
                + "?> \n"
                + widgetClass.generateCode() + "\n"
                + x + "\n";
    }

}

