package ast.nodes.Flutter.Widget;

import ast.nodes.Node;

public class Widget extends Node {
    public Widget getChild() {
        return child;
    }

    public void setChild(Widget child) {
        this.child = child;
    }

    Widget child;
    private MaterialApp materialApp;
    private Button button;
    private Image image;
    private Scaffold scaffold;
    private Column column;
    private Row row;
    private Container container;
    private Expanded expanded;
    private Padding padding;
    private Center center;
    private SizedBox sizedBox;
    private Text text;
    private Obx obx;
    private Icon icon;

    public MaterialApp getMaterialApp() {
        return materialApp;
    }

    public void setMaterialApp(MaterialApp materialApp) {
        this.materialApp = materialApp;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Scaffold getScaffold() {
        return scaffold;
    }

    public void setScaffold(Scaffold scaffold) {
        this.scaffold = scaffold;
    }

    public Column getColumn() {
        return column;
    }

    public void setColumn(Column column) {
        this.column = column;
    }

    public Row getRow() {
        return row;
    }

    public void setRow(Row row) {
        this.row = row;
    }

    public Container getContainer() {
        return container;
    }

    public void setContainer(Container container) {
        this.container = container;
    }

    public Expanded getExpanded() {
        return expanded;
    }

    public void setExpanded(Expanded expanded) {
        this.expanded = expanded;
    }

    public Padding getPadding() {
        return padding;
    }

    public void setPadding(Padding padding) {
        this.padding = padding;
    }

    public Center getCenter() {
        return center;
    }

    public void setCenter(Center center) {
        this.center = center;
    }

    public SizedBox getSizedBox() {
        return sizedBox;
    }

    public void setSizedBox(SizedBox sizedBox) {
        this.sizedBox = sizedBox;
    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }

    public Obx getObx() {
        return obx;
    }

    public void setObx(Obx obx) {
        this.obx = obx;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        if (materialApp != null) {
            return "\nWidget{" +
                    "\nmaterialApp=" + materialApp + "\n}";
        } else if (button != null) {
            return "\nWidget{" + "," +
                    " \nbutton=" + button
                    + "\n}";
        } else if (image != null) {
            return "\nWidget{" +
                    ", \nimage=" + image + "\n}";
        } else if (scaffold != null) {
            return "\nWidget{" +
                    ", \nscaffold=" + scaffold + "\n}";
        } else if (column != null) {
            return "\nWidget{" +
                    ", \ncolumn=" + column + "\n}";
        } else if (row != null) {
            return "\nWidget{" +
                    ", \nrow=" + row + "\n}";
        } else if (container != null) {
            return "\nWidget{" +
                    ", \ncontainer=" + container + "\n}";
        } else if (expanded != null) {
            return "\nWidget{" +
                    ", \nexpanded=" + expanded + "\n}";
        } else if (padding != null) {
            return "\nWidget{" +
                    ", \npadding=" + padding + "\n}";
        } else if (center != null) {
            return "\nWidget{" +
                    ", \ncenter=" + center + "\n}";
        } else if (sizedBox != null) {
            return "\nWidget{" +
                    ", \nsizedBox=" + sizedBox + "\n}";
        } else if (text != null) {
            return "\nWidget{" +
                    ", \ntext=" + text + "\n}";
        } else if (obx != null) {
            return "\nWidget{" +
                    ", \nobx=" + obx + "\n}";
        } else if (icon != null) {
            return "\nWidget{" +
                    ", \nicon=" + icon + "\n}";
        }
        return null;
    }

    @Override
    public String generateCode() {
        if (materialApp != null){
            return ""+
                    materialApp.generateCode();
        } else if (button != null){
            return ""+
                    " \n" + button.generateCode()
                    + "\n";
        } else if (image != null){
            return "\n"+image.generateCode()
                     + "\n";
        } else if (scaffold != null) {
            return "" +
                    "\n" + scaffold.generateCode()+ "\n";
        } else if (column != null) {
            return "" +
                    "\n" + column.generateCode()+ "\n";
        } else if (row != null) {
            return "" +
                    "\n" + row.generateCode() + "\n";
        } else if (container != null) {
            return "" +
                    "\n" + container.generateCode() + "\n";
        } else if (expanded != null) {
            return "" +
                    "\n" + expanded.generateCode() + "\n";
        } else if (padding != null) {
            return "" +
                    "\n" + padding.generateCode() + "\n";
        } else if (center != null) {
            return "" +
                    "\n" + center.generateCode() + "\n";
        } else if (sizedBox != null) {
            return "" +
                    "\n" + sizedBox.generateCode() + "\n";
        } if (text != null) {
            return "\n" +
                    "\n" + text.generateCode() + "\n";
        } else if (obx != null) {
            return "" +
                    " \n" + obx.generateCode() + "\n";
        } else if (icon != null) {
            return "" +
                    "\n" + icon.generateCode() + "\n";
        }
        return "";
    }
}
