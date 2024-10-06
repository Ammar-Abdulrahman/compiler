package ast.Visitors;

import ErrorHandling.SemanticError;
import SymbolTable.*;
import antlr.DartParser;
import antlr.DartParserBaseVisitor;
import ast.nodes.*;
import ast.nodes.Dart.*;
import ast.nodes.Dart.Classes;
import ast.nodes.Dart.Statements.Declare;
import ast.nodes.Dart.Statements.*;
import ast.nodes.Dart.Statements.Expressions.*;
import ast.nodes.Dart.Statements.Print;
import ast.nodes.Flutter.*;
import ast.nodes.Flutter.Widget.*;
import org.antlr.v4.runtime.Token;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Stack;

import Main.Program;

public class BaseVisitor extends DartParserBaseVisitor {
    int level;
    int id = 0;
    Stack<Scope> scopeStack = new Stack<>();
    ArrayList<Scope> scopes = new ArrayList<>();
    public SemanticError semanticError = new SemanticError();

    //________________________________________________________________
    @Override
    public Prog visitProg(DartParser.ProgContext ctx) {
        Prog prog = new Prog();
        ArrayList<Classes> classes = new ArrayList<>();
        if (ctx.classes() != null) {
            for (int i = 0; i < ctx.classes().size(); i++)
                classes.add(visitClasses(ctx.classes().get(i)));
            prog.setClasses(classes);
        }
        prog.setWidgetClass(visitWidgetclass(ctx.widgetclass()));
        prog.setMain1(visitMain(ctx.main()));
        Program.symbolTable.setScopes(scopes);
        SymbolTable.nodes1.add(prog);
        if (semanticError.messages.isEmpty())
        {
            for (int m=0 ; m<classes.size() ; m++){
                try {
                FileWriter fileWriterHtml = new FileWriter("Files//file_"+ (m+5) + ".php");

                fileWriterHtml.write("<html>\n");
                    fileWriterHtml.write("\n"+classes.get(m).generateCode());
                fileWriterHtml.write("\n</html>");
                fileWriterHtml.close();

            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            }
        }

        return prog;
    }

    @Override
    public Main visitMain(DartParser.MainContext ctx) {
        HashMap<String, VarSymbol> varSymbols = new HashMap<>();
        HashMap<String, ListSymbol> listSymbols = new HashMap<>();
        Main main = new Main();
        main.setLine(ctx.MAIN().getSymbol().getLine());
        main.setCol(ctx.MAIN().getSymbol().getCharPositionInLine());
        ArrayList<Declare> declares = new ArrayList<>();
        ArrayList<Statement> statements = new ArrayList<>();
        main.setType(visitType(ctx.type()));
        main.setRunAppFunction(visitRunappfunction(ctx.runappfunction()));

        FunSymbol mainSymbol = new FunSymbol();
        mainSymbol.setKey("main");
        mainSymbol.setType(main.getType().getType());
        if (ctx.declare() != null) {
            for (int i = 0; i < ctx.declare().size(); i++) {
                declares.add(visitDeclare(ctx.declare().get(i)));
                //TODO: check for duplicate declarations
                semanticError.DuplicatedDeclare(declares.get(i), varSymbols, listSymbols);
            }
            main.setDeclares(declares);
        }
        if (ctx.statement() != null) {
            for (int i = 0; i < ctx.statement().size(); i++) {
                statements.add(visitStatement(ctx.statement().get(i)));
                //TODO: check if the variable is not declared
                semanticError.NewDeclare(statements.get(i), varSymbols);
            }
            main.setStatements(statements);
        }
        mainSymbol.setVarSymbol(varSymbols);
        mainSymbol.setListSymbolHashMap(listSymbols);
        Program.symbolTable.setMain(mainSymbol);

//        {
//            try {
//                FileWriter fileWriterHtml = new FileWriter("Files//file_main22" + ".html");
//
//                fileWriterHtml.write("<html>\n");
//                fileWriterHtml.write("\n"+ main.generateCode());
//                fileWriterHtml.write("\n</html>");
//                fileWriterHtml.close();
//
//            } catch (IOException e) {
//                System.out.println("An error occurred.");
//                e.printStackTrace();
//            }
//        }
        return main;
    }

    @Override
    public WidgetClass visitWidgetclass(DartParser.WidgetclassContext ctx) {
        WidgetClass widgetClass = new WidgetClass();
        if (ctx.stfulclass() != null) {
            widgetClass.setStfulClass(visitStfulclass(ctx.stfulclass()));
        }
        if (ctx.stlessclass() != null) {
            widgetClass.setStlessClass(visitStlessclass(ctx.stlessclass()));
        }

//        {
//            try {
//                FileWriter fileWriterHtml = new FileWriter("Files//file_widget22"+ ".html");
//
//                fileWriterHtml.write("<html>\n");
//                fileWriterHtml.write("\n"+ widgetClass.generateCode());
//                fileWriterHtml.write("\n</html>");
//                fileWriterHtml.close();
//
//            } catch (IOException e) {
//                System.out.println("An error occurred.");
//                e.printStackTrace();
//            }
//        }
        return widgetClass;
    }

    @Override
    public StfulClass visitStfulclass(DartParser.StfulclassContext ctx) {
        level = 1;
        ClassSymbol classSymbol = new ClassSymbol();
        HashMap<String, VarSymbol> varSymbols = new HashMap<>();
        HashMap<String, ListSymbol> listSymbols = new HashMap<>();
        HashMap<String, FunSymbol> funSymbols = new HashMap<>();
        classSymbol.setId(id++);
        classSymbol.setLevel(level);
        scopeStack.push(classSymbol);

        StfulClass stfulClass = new StfulClass();
        ArrayList<Def> defs = new ArrayList<>();
        stfulClass.setVar(ctx.VARIABLE().toString());
        stfulClass.setLine(ctx.CLAS().getSymbol().getLine());
        CreateState createState = visitCreatestate(ctx.createstate());
        StateClass stateClass = visitStateclass(ctx.stateclass());
        Constructor constructor;
        ArrayList<CallFunction> callFunction = new ArrayList<>();
        stfulClass.setCreateState(createState);
        stfulClass.setStateClass(stateClass);
        classSymbol.setKey(stfulClass.getVar());
        //TODO: check for duplicated classes
        semanticError.DuplicateClass(scopes, classSymbol, stfulClass.getLine());
        if (ctx.constructor() != null) {
            constructor = visitConstructor(ctx.constructor());
            stfulClass.setConstructor(constructor);
            constructor = visitConstructor(ctx.constructor());
            if (!Objects.equals(constructor.getVars().get(0), stfulClass.getVar())) {
                throw new RuntimeException("error in line " +
                        constructor.getLine()
                        + " the constructor is invalid");
            }
        }
        if (ctx.def() != null) {
            for (int i = 0; i < ctx.def().size(); i++) {
                defs.add(visitDef(ctx.def().get(i)));
                if (defs.get(i).getDeclare() != null) {
                    //TODO: check for duplicate declarations
                    semanticError.DuplicatedDeclare(defs.get(i).getDeclare(),
                            varSymbols, listSymbols);
                }
                if (defs.get(i).getFunction() != null) {
                    scopeStack.peek().setVarSymbol(varSymbols);
                    scopeStack.peek().setListSymbolHashMap(listSymbols);
                    //TODO: check for duplicate function
                    semanticError.FunctionDeclared(defs.get(i), funSymbols);
                }
            }
            stfulClass.setDef(defs);
            classSymbol.setVarSymbol(varSymbols);
            classSymbol.setListSymbolHashMap(listSymbols);
            classSymbol.setFunSymbols(funSymbols);
        }

        if (ctx.callfunction() != null) {
            for (int i = 0; i < ctx.callfunction().size(); i++) {
                callFunction.add(visitCallfunction(ctx.callfunction().get(i)));
                //TODO: check that the called function is defined
                semanticError.CallNotDefinedFunction(classSymbol, callFunction.get(i));
                //TODO: check that the called function parameters are defined
                semanticError.FunctionParameterNotDefined(varSymbols, callFunction.get(i));
            }

            stfulClass.setCallFunctions(callFunction);
        }
        stfulClass.setClassSymbol(classSymbol);
        scopes.add(scopeStack.peek());
        scopeStack.pop();
        return stfulClass;
    }

    @Override
    public StlessClass visitStlessclass(DartParser.StlessclassContext ctx) {
        level = 1;
        ClassSymbol classSymbol = new ClassSymbol();
        classSymbol.setId(id++);
        classSymbol.setLevel(level);
        scopeStack.push(classSymbol);
        HashMap<String, FunSymbol> funSymbols = new HashMap<>();

        StlessClass stlessClass = new StlessClass();
        stlessClass.setVar(ctx.VARIABLE().toString());
        stlessClass.setStlessClassBody(visitStlessclassbody(ctx.stlessclassbody()));
        stlessClass.setLine(ctx.CLAS().getSymbol().getLine());
        HashMap<String, VarSymbol> varSymbols = new HashMap<>();
        HashMap<String, ListSymbol> listSymbols = new HashMap<>();
        classSymbol.setKey(stlessClass.getVar());
        //TODO: check for duplicated classes
        semanticError.DuplicateClass(scopes, classSymbol, stlessClass.getLine());
        if (stlessClass.getStlessClassBody().getDefs() != null) {
            for (int i = 0; i < stlessClass.getStlessClassBody().getDefs().size(); i++) {
                if (stlessClass.getStlessClassBody().getDefs().get(i).getDeclare() != null) {
                    //TODO: check for duplicate declarations
                    semanticError.DuplicatedDeclare(stlessClass.getStlessClassBody().getDefs().get(i).getDeclare(),
                            varSymbols, listSymbols);
                }
                if (stlessClass.getStlessClassBody().getDefs().get(i).getFunction() != null) {
                    scopeStack.peek().setVarSymbol(varSymbols);
                    scopeStack.peek().setListSymbolHashMap(listSymbols);
                    //TODO: check for duplicate function
                    semanticError.FunctionDeclared(stlessClass.getStlessClassBody().getDefs().get(i), funSymbols);
                }
            }
            classSymbol.setVarSymbol(varSymbols);
            classSymbol.setListSymbolHashMap(listSymbols);
            classSymbol.setFunSymbols(funSymbols);
        }
        if (stlessClass.getStlessClassBody().getCallFunctions() != null) {
            for (int i = 0; i < stlessClass.getStlessClassBody().getCallFunctions().size(); i++) {
                //TODO: check that the called function is defined
                semanticError.CallNotDefinedFunction(classSymbol, stlessClass.getStlessClassBody().getCallFunctions().get(i));
                //TODO: check that the called function parameters are defined
                semanticError.FunctionParameterNotDefined(varSymbols, stlessClass.getStlessClassBody().getCallFunctions().get(i));
            }
        }
        stlessClass.setClassSymbol(classSymbol);
        scopes.add(scopeStack.peek());
        scopeStack.pop();
        return stlessClass;
    }

    @Override
    public Constructor visitConstructor(DartParser.ConstructorContext ctx) {
        Constructor constructor = new Constructor();
        constructor.setLine(ctx.VARIABLE().get(0).getSymbol().getLine());
        ArrayList<String> vars = new ArrayList<>();
        for (int i = 0; i < ctx.VARIABLE().size(); i++) {
            vars.add(ctx.VARIABLE().get(i).toString());
        }
        constructor.setVars(vars);
        return constructor;
    }

    @Override
    public Def visitDef(DartParser.DefContext ctx) {
        Def def = new Def();
        Declare declare = new Declare();
        Function function = new Function();
        ControllerPutFind controllerPutFind = new ControllerPutFind();
        if (ctx.function() != null) {
            function = visitFunction(ctx.function());
        }
        if (ctx.declare() != null) {
            declare = visitDeclare(ctx.declare());

        }
        if (ctx.controllerPutFind() != null) {
            controllerPutFind = visitControllerPutFind(ctx.controllerPutFind());
        }
        def.setFunction(function);
        def.setDeclare(declare);
        def.setControllerPutFind(controllerPutFind);
        return def;
    }

    @Override
    public CreateState visitCreatestate(DartParser.CreatestateContext ctx) {
        CreateState createState = new CreateState();
        ArrayList<String> vars = new ArrayList<>();
        for (int i = 0; i < ctx.VARIABLE().size(); i++) {
            vars.add(ctx.VARIABLE().get(i).toString());
        }
        createState.setVars(vars);
        return createState;
    }

    @Override
    public StateClass visitStateclass(DartParser.StateclassContext ctx) {
        StateClass stateClass = new StateClass();
        ArrayList<Def> defs = new ArrayList<>();
        ArrayList<String> vars = new ArrayList<>();
        stateClass.setBuildFunction(visitBuildfunction(ctx.buildfunction()));
        if (ctx.def() != null) {
            for (int i = 0; i < ctx.def().size(); i++) {
                defs.add(visitDef(ctx.def().get(i)));
            }
            stateClass.setDef(defs);
        }

        for (int i = 0; i < ctx.VARIABLE().size(); i++) {
            vars.add(ctx.VARIABLE().get(i).toString());
        }
        stateClass.setVars(vars);
        //stateClass.setVar(ctx.VARIABLE().toString());
        return stateClass;
    }

    @Override
    public StlessClassBody visitStlessclassbody(DartParser.StlessclassbodyContext ctx) {
        StlessClassBody stlessClassBody = new StlessClassBody();
        Constructor constructor;
        ArrayList<Def> defs = new ArrayList<>();
        BuildFunction buildFunction = visitBuildfunction(ctx.buildfunction());
        ArrayList<CallFunction> callFunctions = new ArrayList<>();
        stlessClassBody.setBuildFunction(buildFunction);
        if (ctx.constructor() != null) {
            constructor = visitConstructor(ctx.constructor());
            stlessClassBody.setConstructor(constructor);
        }
        if (ctx.def() != null) {
            for (int i = 0; i < ctx.def().size(); i++) {
                defs.add(visitDef(ctx.def().get(i)));
            }
            stlessClassBody.setDefs(defs);
        }
        if (ctx.callfunction() != null) {
            for (int i = 0; i < ctx.callfunction().size(); i++) {
                callFunctions.add(visitCallfunction(ctx.callfunction().get(i)));
            }
            stlessClassBody.setCallFunctions(callFunctions);
        }
        return stlessClassBody;
    }

    @Override
    public BuildFunction visitBuildfunction(DartParser.BuildfunctionContext ctx) {
        BuildFunction buildFunction = new BuildFunction();
        Widget widget = visitWidget(ctx.widget());
        //SymbolTable.nodes1.add(widget);
        buildFunction.setWidget(widget);
        return buildFunction;
    }

    @Override
    public Function visitFunction(DartParser.FunctionContext ctx) {
        FunSymbol funSymbol = new FunSymbol();
        funSymbol.setId(scopeStack.peek().getId());
        funSymbol.setLevel(scopeStack.peek().getLevel() + 1);
        funSymbol.setParent(scopeStack.peek());
        scopeStack.push(funSymbol);
        Function function = new Function();
        function.setType(visitType(ctx.type()));
        function.setVar(ctx.VARIABLE().get(0).toString());
        function.setLine(ctx.VARIABLE().get(0).getSymbol().getLine());
        ArrayList<FunctionOnParameter> functionOnParameters = new ArrayList<>();
        ArrayList<Statement> statements = new ArrayList<>();
        ArrayList<Declare> declares = new ArrayList<>();

        funSymbol.setKey(function.getVar());
        funSymbol.setType(function.getType().getType());
        HashMap<String, FunSymbol.ParameterSymbol> parameterSymbols = new HashMap<>();
        HashMap<String, VarSymbol> varSymbols;
        HashMap<String, ListSymbol> listSymbols;
        varSymbols = funSymbol.getParent().getVarSymbol();
        listSymbols = funSymbol.getParent().getListSymbolHashMap();

        if (ctx.functionparameter() != null) {
            for (int i = 0; i < ctx.functionparameter().size(); i++) {
                functionOnParameters.add(visitFunctionparameter(ctx.functionparameter().get(i)));
                for (int j = 0; j < ctx.functionparameter().size(); j++) {
                    VarSymbol varSymbol = new VarSymbol();
                    FunSymbol.ParameterSymbol parameterSymbol = funSymbol.new ParameterSymbol();
                    parameterSymbol.setName(functionOnParameters.get(i).getVar().get(j));
                    varSymbol.setKey(parameterSymbol.getName());
                    parameterSymbol.setType(functionOnParameters.get(i).getTypes().get(j).getType());
                    varSymbol.setType(parameterSymbol.getType());
                    parameterSymbols.put(parameterSymbol.getName(), parameterSymbol);
                    varSymbols.put(varSymbol.getKey(),varSymbol);
                }
            }
            function.setFunctionOnParameters(functionOnParameters);
            funSymbol.setParameters(parameterSymbols);

        }
        if (ctx.declare() != null) {
            for (int i = 0; i < ctx.declare().size(); i++) {
                declares.add(visitDeclare(ctx.declare().get(i)));
                //TODO: check for duplicate declarations
                semanticError.DuplicatedDeclare(declares.get(i), varSymbols, listSymbols);
                if (varSymbols != null)
                    scopeStack.peek().setVarSymbol(varSymbols);
                if (listSymbols != null)
                    scopeStack.peek().setListSymbolHashMap(listSymbols);

            }
        }
        if (ctx.statement() != null) {
            scopeStack.peek().setVarSymbol(varSymbols);
            for (int i = 0; i < ctx.statement().size(); i++) {
                statements.add(visitStatement(ctx.statement().get(i)));
                //TODO: check if the variable is not declared
                semanticError.NewDeclare(statements.get(i), varSymbols);
            }
            function.setStatements(statements);
        }
        if (ctx.NUM() != null) {
            function.setReturnType("int");
        } else if (ctx.VARIABLE().size() >= 2) {
            String returnValue = ctx.VARIABLE().get(ctx.VARIABLE().size() - 1).toString();
            if (Objects.equals(returnValue, "true") || Objects.equals(returnValue, "false"))
                function.setReturnType("boolean");
            else
                function.setReturnType("String");
        } else
            function.setReturnType("void");
        funSymbol.setReturn_type(function.getReturnType());
        //TODO: check if function returns same as its type
        semanticError.FunctionReturnType(funSymbol, ctx);
        if (varSymbols != null)
            funSymbol.setVarSymbol(varSymbols);
        if (listSymbols != null)
            funSymbol.setListSymbolHashMap(listSymbols);
        function.setFunSymbol(funSymbol);

        scopes.add(scopeStack.peek());
        scopeStack.pop();
        return function;
    }
    //________________________________________________________________

    //________________________________________________________________
    //widgets
    @Override
    public Widget visitWidget(DartParser.WidgetContext ctx) {
        Widget widget = new Widget();
        MaterialApp materialApp;
        Button button;
        Image image;
        Scaffold scaffold;
        Column column;
        Row row;
        Container container;
        Expanded expanded;
        Padding padding;
        Center center;
        SizedBox sizedBox;
        Text text;
        Obx obx;
        Icon icon;

        if (ctx.materialapp() != null) {
            materialApp = visitMaterialapp(ctx.materialapp());
            widget.setMaterialApp(materialApp);
            //SymbolTable.nodes1.add(materialApp);
        }
        if (ctx.button() != null) {
            button = visitButton(ctx.button());
            widget.setButton(button);
            //SymbolTable.nodes1.add(button);
        }
        if (ctx.image() != null) {
            image = visitImage(ctx.image());
            widget.setImage(image);
            //SymbolTable.nodes1.add(image);
        }
        if (ctx.scaffold() != null) {
            scaffold = visitScaffold(ctx.scaffold());
            widget.setScaffold(scaffold);
            //SymbolTable.nodes1.add(scaffold);
        }
        if (ctx.column() != null) {
            column = visitColumn(ctx.column());
            widget.setColumn(column);
            //SymbolTable.nodes1.add(column);
        }
        if (ctx.row() != null) {
            row = visitRow(ctx.row());
            widget.setRow(row);
            //SymbolTable.nodes1.add(row);
        }
        if (ctx.container() != null) {
            container = visitContainer(ctx.container());
            widget.setContainer(container);
            //SymbolTable.nodes1.add(container);
        }
        if (ctx.expanded() != null) {
            expanded = visitExpanded(ctx.expanded());
            widget.setExpanded(expanded);
        }
        if (ctx.padding() != null) {
            padding = visitPadding(ctx.padding());
            widget.setPadding(padding);
            //SymbolTable.nodes1.add(padding);
        }
        if (ctx.center() != null) {
            center = visitCenter(ctx.center());
            widget.setCenter(center);
            //SymbolTable.nodes1.add(center);
        }
        if (ctx.text() != null) {
            text = visitText(ctx.text());
            widget.setText(text);
           // SymbolTable.nodes1.add(text);
          //  System.out.println(text+"\n");
        }
        if (ctx.sizedbox() != null) {
            sizedBox = visitSizedbox(ctx.sizedbox());
            widget.setSizedBox(sizedBox);
            //SymbolTable.nodes1.add(sizedBox);
        }
        if (ctx.obx() != null) {
            obx = visitObx(ctx.obx());
            widget.setObx(obx);
            //SymbolTable.nodes1.add(obx);
        }
        if (ctx.icon() != null) {
            icon = visitIcon(ctx.icon());
            widget.setIcon(icon);
            //SymbolTable.nodes1.add(icon);
        }

        return widget;
    }

    @Override
    public MaterialApp visitMaterialapp(DartParser.MaterialappContext ctx) {
        MaterialApp materialApp = new MaterialApp();
        Istedaaaclass istedaaaclass;
        if (ctx.materialtitle() != null) {
            materialApp.setTitle(visitMaterialtitle(ctx.materialtitle()));
        }
        istedaaaclass = visitIstedaaaclass(ctx.istedaaaclass());
        istedaaaclass.setCol(istedaaaclass.getCol());
        materialApp.setIstedaaaclass(istedaaaclass);
        return materialApp;
    }

    @Override
    public Button visitButton(DartParser.ButtonContext ctx) {
        Button button = new Button();
        Child child;
        child = visitChild(ctx.child());
        button.setChild(child);
        return button;
    }

    @Override
    public Image visitImage(DartParser.ImageContext ctx) {
        Image image = new Image();
        if (ctx.STRING_LINE() != null) {
            image.setStringLine(ctx.STRING_LINE().getText());
         //   System.out.println(ctx.STRING_LINE().toString());
        }
        ArrayList<String> vars = new ArrayList<>();
        for (int i = 0; i < ctx.VARIABLE().size(); i++) {
            vars.add(ctx.VARIABLE().get(i).toString());
        }
        image.setVar(vars);
        //image.setLine(ctx.VARIABLE().get(0).getSymbol().getLine());
        //image.setCol(ctx.VARIABLE().get(0).getSymbol().getCharPositionInLine() + 1);
        if (ctx.NUM() != null)
            image.setNum(Integer.parseInt(ctx.NUM().toString()));
        return image;
    }

    @Override
    public Scaffold visitScaffold(DartParser.ScaffoldContext ctx) {
        Scaffold scaffold = new Scaffold();
        Text texts;
        Widget body;
        body = visitWidget(ctx.widget());
        if (ctx.text() != null) {
            texts = visitText(ctx.text());
            scaffold.setTexts(texts);
        }
        scaffold.setBody(body);
        return scaffold;
    }

    @Override
    public Column visitColumn(DartParser.ColumnContext ctx) {
        Column column = new Column();
        if (ctx.CROSSAXIS() != null) {
            column.setCross(ctx.CROSSAXIS().getText());
        }
        if (ctx.MAINAXIS() != null) {
            column.setMain_axis(ctx.MAINAXIS().getText());
        }

        if (ctx.widget() != null) {
            ArrayList<Widget> widgets = new ArrayList<>();
            for (int i = 0; i < ctx.widget().size(); i++)
                widgets.add(visitWidget(ctx.widget().get(i)));
            column.setWidgets(widgets);
        }

        return column;
    }

    @Override
    public Row visitRow(DartParser.RowContext ctx) {
        Row row = new Row();
        if (ctx.CROSSAXIS() != null) {
            row.setCross(ctx.CROSSAXIS().toString());
        }
        if (ctx.MAINAXIS() != null) {
            row.setMain_axis(ctx.MAINAXIS().toString());
        }
        if (ctx.widget() != null) {
            ArrayList<Widget> widgets = new ArrayList<>();
            for (int i = 0; i < ctx.widget().size(); i++)
                widgets.add(visitWidget(ctx.widget().get(i)));
            row.setWidgets(widgets);
        }
        return row;
    }

    @Override
    public Container visitContainer(DartParser.ContainerContext ctx) {
        Container container = new Container();
        if (ctx.height() != null) {
            container.setHeight(visitHeight(ctx.height()));
        }
        if (ctx.width() != null) {
            container.setWidth(visitWidth(ctx.width()));
        }
        if (ctx.child() != null) {
            Child child;
            child = visitChild(ctx.child());
            container.setChild(child);
        }
        if (ctx.COLORVALUE() != null) {
            container.setColor_value(ctx.COLORVALUE().getText());
        }
        if (ctx.boxdecoration() != null) {
            container.setBoxDecoration(visitBoxdecoration(ctx.boxdecoration()));
        }
        return container;
    }

    @Override
    public Expanded visitExpanded(DartParser.ExpandedContext ctx) {
        Expanded expanded = new Expanded();
        Child child = visitChild(ctx.child());
        expanded.setChild(child);
        return expanded;
    }

    @Override
    public Padding visitPadding(DartParser.PaddingContext ctx) {
        Padding padding = new Padding();
        padding.setNum(ctx.num().getText());
        padding.setChild(visitChild(ctx.child()));
        return padding;
    }

    @Override
    public Center visitCenter(DartParser.CenterContext ctx) {
        Center center = new Center();
        center.setChild(visitChild(ctx.child()));
        return center;
    }

    @Override
    public Text visitText(DartParser.TextContext ctx) {
        Text text = new Text();
        if (ctx.STRING_LINE() != null) {
            text.setStringLine(ctx.STRING_LINE().getText());
        }
        ArrayList<String> vars = new ArrayList<>();
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < ctx.VARIABLE().size(); i++) {
            vars.add(ctx.VARIABLE().get(i).getText());
        }
        text.setVars(vars);
        text.setLine(ctx.VARIABLE().get(0).getSymbol().getLine());
        text.setCol(ctx.VARIABLE().get(0).getSymbol().getCharPositionInLine() + 1);
        if (ctx.NUM() != null) {
            for (int i = 0; i < ctx.NUM().size(); i++) {
                numbers.add(Integer.parseInt(ctx.NUM().get(i).toString()));
            }
            text.setNums(numbers);
        }
        return text;
    }

    @Override
    public SizedBox visitSizedbox(DartParser.SizedboxContext ctx) {
        SizedBox sizedBox = new SizedBox();
        Height height;
        Width width;
        if (ctx.height() != null) {
            height = visitHeight(ctx.height());
            sizedBox.setHeight(height);
        }
        if (ctx.width() != null) {
            width = visitWidth(ctx.width());
            sizedBox.setWidth(width);
        }
        return sizedBox;
    }

    @Override
    public Obx visitObx(DartParser.ObxContext ctx) {
        Obx obx = new Obx();
        Widget widget;
        widget = visitWidget(ctx.widget());
        obx.setWidget(widget);
        return obx;
    }

    @Override
    public Icon visitIcon(DartParser.IconContext ctx) {
        Icon icon = new Icon();
        icon.setIcon_value(ctx.ICONSVALUE().getText());
        return icon;
    }
    //________________________________________________________________

    //statement________________________________________________________________
    @Override
    public Statement visitStatement(DartParser.StatementContext ctx) {
        Statement statement = new Statement();
        WhileStatement whileStatement;
        IfStatement ifStatement;
        SwitchStatement switchStatement;
        TryStatement tryStatement;
        Block block;
        Print print;
        Navigation navigation;
        SetState state;
        Expr expr;
        Expression expression;
        ChangingValue changingValue;
        ControllerFunctions controllerFunctions;
        ControllerAttributes controllerAttributes;
        if (ctx.whileStatement() != null) {
            whileStatement = visitWhileStatement(ctx.whileStatement());
            statement.setWhileStatement(whileStatement);
        }
        if (ctx.ifStatement() != null) {
            ifStatement = visitIfStatement(ctx.ifStatement());
            statement.setIfStatement(ifStatement);
        }
        if (ctx.switchStatement() != null) {
            switchStatement = visitSwitchStatement(ctx.switchStatement());
            statement.setSwitchStatement(switchStatement);
        }
        if (ctx.tryStatement() != null) {
            tryStatement = visitTryStatement(ctx.tryStatement());
            statement.setTryStatement(tryStatement);
        }
        if (ctx.block() != null) {
            block = visitBlock(ctx.block());
            statement.setBlock(block);
        }
        if (ctx.print() != null) {
            print = visitPrint(ctx.print());
            statement.setPrint(print);
        }
        if (ctx.navigation() != null) {
            navigation = visitNavigation(ctx.navigation());
            statement.setNavigation(navigation);
        }
        if (ctx.setstate() != null) {
            state = visitSetstate(ctx.setstate());
            statement.setState(state);
        }
        if (ctx.expr() != null) {
            expr = visitExpr(ctx.expr());
            statement.setExpr(expr);
        }
        if (ctx.expression() != null) {
            expression = visitExpression(ctx.expression());
            statement.setExpression(expression);
        }
        if (ctx.changingvalue() != null) {
            changingValue = visitChangingvalue(ctx.changingvalue());
            statement.setChangingValue(changingValue);
        }

        if (ctx.controllerAttributes() != null) {
            controllerAttributes = visitControllerAttributes(ctx.controllerAttributes());
            statement.setControllerAttributes(controllerAttributes);
        }
        if (ctx.controllerFunctions() != null) {
            controllerFunctions = visitControllerFunctions(ctx.controllerFunctions());
            statement.setControllerFunctions(controllerFunctions);
        }
        return statement;
    }

    @Override
    public IfStatement visitIfStatement(DartParser.IfStatementContext ctx) {
        Scope scope = new Scope();
        scope.setId(scopeStack.peek().getId());
        scope.setLevel(scopeStack.peek().getLevel() + 1);
        scope.setParent(scopeStack.peek());
        scope.setKey("If");
        scopeStack.push(scope);
        HashMap<String, VarSymbol> varSymbols;
        HashMap<String, ListSymbol> listSymbols;
        varSymbols = scope.getParent().getVarSymbol();
        listSymbols = scope.getParent().getListSymbolHashMap();
        IfStatement ifStatement = new IfStatement();
        ArrayList<Expression> expressions = new ArrayList<>();
        ArrayList<Block> blocks = new ArrayList<>();
        for (int i = 0; i < ctx.expression().size(); i++) {
            expressions.add(visitExpression(ctx.expression().get(i)));
            if (expressions.get(i).getVar() != null) {
                if (!varSymbols.containsKey(expressions.get(i).getVar())){
                    throw new RuntimeException("error in line: " + expressions.get(i).getLine() +  " variable not declared");
                }
            }
        }
        ifStatement.setExpressions(expressions);

        for (int i = 0; i < ctx.block().size(); i++) {
            blocks.add(visitBlock(ctx.block().get(i)));
            if (blocks.get(i).getDefs() != null) {
                for (int j = 0; j < blocks.get(i).getDefs().size(); j++) {
                    if (blocks.get(i).getDefs().get(j).getDeclare() != null) {
                        //TODO: check for duplicate declarations
                        semanticError.DuplicatedDeclare(blocks.get(i).getDefs().get(j).getDeclare(), varSymbols, listSymbols);
                    }
                }
            }
            if (blocks.get(i).getStatements() != null) {
                for (int j = 0; j < blocks.get(i).getStatements().size(); j++){
                    //TODO: check if the variable is not declared
                    semanticError.NewDeclare(blocks.get(i).getStatements().get(j), varSymbols);
                }
            }
        }
        if (ctx.ANDOR()!= null){
            ifStatement.setAndOr(ctx.ANDOR().toString());
        }
        ifStatement.setBlocks(blocks);
        scope.setVarSymbol(varSymbols);
        scopes.add(scopeStack.peek());
        scopeStack.pop();
        return ifStatement;
    }

    @Override
    public WhileStatement visitWhileStatement(DartParser.WhileStatementContext ctx) {
        Scope scope = new Scope();
        scope.setId(scopeStack.peek().getId());
        scope.setLevel(scopeStack.peek().getLevel() + 1);
        scope.setParent(scopeStack.peek());
        scope.setKey("While Loop");
        scopeStack.push(scope);
        HashMap<String, VarSymbol> varSymbols;
        HashMap<String, ListSymbol> listSymbols;
        varSymbols = scope.getParent().getVarSymbol();
        listSymbols = scope.getParent().getListSymbolHashMap();
        WhileStatement whileStatement = new WhileStatement();
        Block block;
        Expression expression;
        block = visitBlock(ctx.block());
        expression = visitExpression(ctx.expression());
        whileStatement.setBlock(block);
        whileStatement.setExpression(expression);
        if (expression.getVar() != null) {
            if (!varSymbols.containsKey(expression.getVar())){
                throw new RuntimeException("error in line: " + expression.getLine() +  " variable not declared");
            }
        }
        if (block.getDefs() != null) {
            for (int j = 0; j < block.getDefs().size(); j++) {
                if (block.getDefs().get(j).getDeclare() != null) {
                    //TODO: check for duplicate declarations
                    semanticError.DuplicatedDeclare(block.getDefs().get(j).getDeclare(), varSymbols, listSymbols);
                }
            }
        }
        if (block.getStatements() != null) {
            for (int j = 0; j < block.getStatements().size(); j++){
                //TODO: check if the variable is not declared
                semanticError.NewDeclare(block.getStatements().get(j), varSymbols);
            }
        }
        scope.setVarSymbol(varSymbols);
        scopes.add(scopeStack.peek());
        scopeStack.pop();
        return whileStatement;
    }

    @Override
    public SwitchStatement visitSwitchStatement(DartParser.SwitchStatementContext ctx) {
        SwitchStatement switchStatement = new SwitchStatement();
        Expression expression;
        ArrayList<SwitchCase> switchCase = new ArrayList<>();
        DefaultCase defaultCase;
        expression = visitExpression(ctx.expression());
        switchStatement.setExpression(expression);
        if (ctx.defaultCase() != null) {
            defaultCase = visitDefaultCase(ctx.defaultCase());
            switchStatement.setDefaultCase(defaultCase);
        }
        if (ctx.switchCase() != null) {
            for (int i = 0; i < ctx.switchCase().size(); i++) {
                switchCase.add(visitSwitchCase(ctx.switchCase().get(i)));
            }
            switchStatement.setSwitchCase(switchCase);
        }
        return switchStatement;
    }

    @Override
    public TryStatement visitTryStatement(DartParser.TryStatementContext ctx) {
        TryStatement tryStatement = new TryStatement();
        ArrayList<Block> blocks = new ArrayList<>();
        CatchPart catchPart;
        for (int i = 0; i < ctx.block().size(); i++) {
            blocks.add(visitBlock(ctx.block().get(i)));
        }
        catchPart = visitCatchPart(ctx.catchPart());
        tryStatement.setCatchPart(catchPart);
        tryStatement.setBlocks(blocks);
        return tryStatement;
    }

    @Override
    public Block visitBlock(DartParser.BlockContext ctx) {
        Block block = new Block();
        ArrayList<Statement> statements = new ArrayList<>();
        ArrayList<Def> defs = new ArrayList<>();
        if (ctx.statement() != null) {
            for (int i = 0; i < ctx.statement().size(); i++) {
                statements.add(visitStatement(ctx.statement().get(i)));
            }
            block.setStatements(statements);
        }
        if (ctx.def() != null) {
            for (int j = 0; j < ctx.def().size(); j++) {
                defs.add(visitDef(ctx.def().get(j)));
            }
            block.setDefs(defs);
        }
        if (ctx.NUM() != null) {
            block.setNum(Integer.parseInt(ctx.NUM().toString()));
        }
        if (ctx.VARIABLE() != null) {
            block.setVar(ctx.VARIABLE().toString());
        }
        return block;
    }

    @Override
    public Print visitPrint(DartParser.PrintContext ctx) {
        Print print = new Print();
        if (ctx.VARIABLE() != null) {
            print.setVar(ctx.VARIABLE().getText());
        }
        if (ctx.STRING_LINE() != null) {
            print.setStringLine(ctx.STRING_LINE().getText());
        }
        return print;
    }

    @Override
    public Navigation visitNavigation(DartParser.NavigationContext ctx) {
        Navigation navigation = new Navigation();
        Istedaaaclass istedaaaclass;
        if (ctx.istedaaaclass() != null) {
            istedaaaclass = visitIstedaaaclass(ctx.istedaaaclass());
            navigation.setIstedaaaclass(istedaaaclass);
        }
        return navigation;
    }

    @Override
    public SetState visitSetstate(DartParser.SetstateContext ctx) {
        SetState state = new SetState();
        SetBody setBody;
        setBody = visitSetbody(ctx.setbody());
        state.setSetBody(setBody);
        return state;
    }

    @Override
    public Expr visitExpr(DartParser.ExprContext ctx) {
        Expr expr = new Expr();
        ArrayList<String> vars = new ArrayList<>();
        ArrayList<Expr> child = new ArrayList<>();
        if (ctx.NUM() != null) {
            expr.setNum(Integer.parseInt(ctx.NUM().toString()));
            expr.setLine(ctx.NUM().getSymbol().getLine());
        }
        if (ctx.VARIABLE() != null) {
            if(ctx.VARIABLE(0) != null) {
                expr.setLine(ctx.VARIABLE(0).getSymbol().getLine());
            }
            for (int i = 0; i < ctx.VARIABLE().size(); i++) {
                vars.add(ctx.VARIABLE(i).toString());
            }
            expr.setVar(vars);
        }
        if (ctx.expr() != null) {
            for (int i = 0; i < ctx.expr().size(); i++) {
                child.add(visitExpr(ctx.expr().get(i)));
            }
            expr.setExpr(child);
        }
        if (ctx.MULTIPLICATIVEOPERATOR() != null)
            expr.setExpression(ctx.MULTIPLICATIVEOPERATOR().toString());
        else if (ctx.ADDITIVEOPERATOR() != null)
            expr.setExpression(ctx.ADDITIVEOPERATOR().toString());
        return expr;
    }

    @Override
    public Expression visitExpression(DartParser.ExpressionContext ctx) {
        Expression expression = new Expression();
        Expression child;
        Expr expr;
        if (ctx.expression() != null) {
            child = visitExpression(ctx.expression());
            expression.setExpression(child);
            expression.setLine(child.getLine());
        }
        if (ctx.expr() != null) {
            expr = visitExpr(ctx.expr());
            expression.setExpr(expr);
        }
        if (ctx.VARIABLE() != null) {
            expression.setVar(ctx.VARIABLE().getText());
            expression.setLine(ctx.VARIABLE().getSymbol().getLine());
        }
        if (ctx.NUM() != null) {
            expression.setNum(Integer.parseInt(ctx.NUM().toString()));
            expression.setLine(ctx.NUM().getSymbol().getLine());

        }

        return expression;
    }

    @Override
    public ChangingValue visitChangingvalue(DartParser.ChangingvalueContext ctx) {
        ChangingValue changingValue = new ChangingValue();
        changingValue.setVar(ctx.VARIABLE().getText());
        changingValue.setLine(ctx.VARIABLE().getSymbol().getLine());
        Expr expr;
        VarSymbol varSymbol = new VarSymbol();
        if (ctx.expr() != null) {
            expr = visitExpr(ctx.expr());
            changingValue.setExpr(expr);
            if (!expr.getVar().isEmpty()) {
                varSymbol.setValue(expr.getVar().get(0));
            } else {
                varSymbol.setValue(expr.getNum());
            }
        } else if (ctx.STRING_LINE() != null) {
            changingValue.setString_line(ctx.STRING_LINE().toString());
            varSymbol.setValue(changingValue.getString_line());
        }
        varSymbol.setKey(changingValue.getVar());
        changingValue.setVarSymbol(varSymbol);
        return changingValue;
    }

    @Override
    public ControllerAttributes visitControllerAttributes(DartParser.ControllerAttributesContext ctx) {
        ControllerAttributes controllerAttributes = new ControllerAttributes();
        ArrayList<String> vars = new ArrayList<>();

        if (ctx.VARIABLE() != null) {
            for (int i = 0; i < ctx.VARIABLE().size(); i++) {
                vars.add(ctx.VARIABLE().get(i).toString());
            }
            controllerAttributes.setVars(vars);
        }
        return controllerAttributes;
    }

    @Override
    public ControllerFunctions visitControllerFunctions(DartParser.ControllerFunctionsContext ctx) {
        ControllerFunctions controllerFunctions = new ControllerFunctions();
        ArrayList<String> vars = new ArrayList<>();
        if (ctx.VARIABLE() != null) {
            for (int i = 0; i < ctx.VARIABLE().size(); i++) {
                vars.add(ctx.VARIABLE().get(i).toString());
            }
            controllerFunctions.setVars(vars);
        }
        if (ctx.NUM() != null){
            controllerFunctions.setNum(Integer.parseInt(ctx.NUM().toString()));
        }
        return controllerFunctions;
    }

    //_________________________________________________________________________
    @Override
    public Child visitChild(DartParser.ChildContext ctx) {
        Child child = new Child();
        child.setWidget(visitWidget(ctx.widget()));
        return child;
    }

    @Override
    public Height visitHeight(DartParser.HeightContext ctx) {
        Height height = new Height();
        height.setNum(Integer.parseInt(ctx.NUM().toString()));
        return height;
    }

    @Override
    public Width visitWidth(DartParser.WidthContext ctx) {
        Width width = new Width();
        width.setNum(Integer.parseInt(ctx.NUM().toString()));
        return width;
    }

    @Override
    public Istedaaaclass visitIstedaaaclass(DartParser.IstedaaaclassContext ctx) {
        Istedaaaclass istedaaaclass = new Istedaaaclass();
        istedaaaclass.setVar(ctx.VARIABLE().toString());
        Token id_token = ctx.VARIABLE().getSymbol();
        istedaaaclass.setLine(id_token.getLine());
        istedaaaclass.setCol(id_token.getCharPositionInLine() + 1);
        return istedaaaclass;
    }

    @Override
    public MaterialTitle visitMaterialtitle(DartParser.MaterialtitleContext ctx) {
        MaterialTitle materialTitle = new MaterialTitle();
        materialTitle.setStringLine(ctx.STRING_LINE().getText());
        return materialTitle;
    }

    @Override
    public SwitchCase visitSwitchCase(DartParser.SwitchCaseContext ctx) {
        SwitchCase switchCase = new SwitchCase();
        Expression expression;
        Statement statement;
        expression = visitExpression(ctx.expression());
        statement = visitStatement(ctx.statement());
        switchCase.setExpression(expression);
        switchCase.setStatement(statement);
        return switchCase;
    }

    @Override
    public DefaultCase visitDefaultCase(DartParser.DefaultCaseContext ctx) {
        DefaultCase defaultCase = new DefaultCase();
        Statement statement;
        statement = visitStatement(ctx.statement());
        defaultCase.setStatement(statement);
        return defaultCase;
    }

    @Override
    public CatchPart visitCatchPart(DartParser.CatchPartContext ctx) {
        CatchPart catchPart = new CatchPart();
        ArrayList<String> var = new ArrayList<>();
        for (int i = 0; i < ctx.VARIABLE().size(); i++) {
            var.add(ctx.VARIABLE().get(i).toString());
        }
        catchPart.setVar(var);
        return catchPart;
    }

    @Override
    public Type visitType(DartParser.TypeContext ctx) {
        Type type = new Type();
        if (ctx.BOOL() != null) {
            type.setType(ctx.BOOL().getText());
        }
        if (ctx.INT() != null) {
            type.setType(ctx.INT().getText());
        }
        if (ctx.STRING() != null) {
            type.setType(ctx.STRING().getText());
        }
        if (ctx.VOID() != null) {
            type.setType(ctx.VOID().getText());
        }
        return type;
    }

    @Override
    public FunctionOnParameter visitFunctionparameter(DartParser.FunctionparameterContext ctx) {
        FunctionOnParameter functionOnParameter = new FunctionOnParameter();
        ArrayList<Type> types = new ArrayList<>();
        for (int i = 0; i < ctx.type().size(); i++) {
            types.add(visitType(ctx.type().get(i)));
        }
        functionOnParameter.setTypes(types);
        ArrayList<String> var = new ArrayList<>();
        for (int i = 0; i < ctx.VARIABLE().size(); i++) {
            var.add(ctx.VARIABLE().get(i).toString());
        }
        functionOnParameter.setVar(var);
        return functionOnParameter;
    }

    @Override
    public DefineVariable visitDefinevariable(DartParser.DefinevariableContext ctx) {
        DefineVariable defineVariable = new DefineVariable();
        defineVariable.setType(visitType(ctx.type()));
        defineVariable.setVar(ctx.VARIABLE().toString());
        defineVariable.setLine(ctx.VARIABLE().getSymbol().getLine());

        VarSymbol varSymbol = new VarSymbol();
        varSymbol.setType(defineVariable.getType().getType());
        varSymbol.setKey(defineVariable.getVar());
        if (ctx.NUM() != null) {
            defineVariable.setNum(Integer.parseInt(ctx.NUM().toString()));
            defineVariable.setValue(defineVariable.getNum());
            varSymbol.setValue(defineVariable.getNum());

        }
        if (ctx.STRING_LINE() != null) {
            defineVariable.setStringLine(ctx.STRING_LINE().getText());
            defineVariable.setValue(defineVariable.getStringLine());
            varSymbol.setValue(defineVariable.getStringLine());
        }
        defineVariable.setVarSymbol(varSymbol);
        return defineVariable;
    }

    @Override
    public Declare visitDeclare(DartParser.DeclareContext ctx) {
        Declare declare = new Declare();
        DefineRXvariable defineRXvariable;
        DefineVariable defineVariable;
        List list = new List();
        if (ctx.list() != null) {
            list = visitList(ctx.list());
            declare.setList(list);
        }
        if (ctx.definevariable() != null) {
            defineVariable = visitDefinevariable(ctx.definevariable());
            declare.setDefineVariable(defineVariable);
        }
        if (ctx.defineRXvariable() != null) {
            defineRXvariable = visitDefineRXvariable(ctx.defineRXvariable());
            declare.setDefineRXvariable(defineRXvariable);
        }
        return declare;
    }

    @Override
    public List visitList(DartParser.ListContext ctx) {
        List list = new List();
        ArrayList<String> vars = new ArrayList<>();
        ListSymbol listSymbol = new ListSymbol();
        list.setVar(ctx.VARIABLE().toString());
        listSymbol.setKey(list.getVar());
        list.setLine(ctx.LIST().getSymbol().getLine());
        if (ctx.STRING_LINE() != null) {
            for (int i = 0; i < ctx.STRING_LINE().size(); i++)
                vars.add(ctx.STRING_LINE().get(i).toString());
            list.setVars(vars);
            listSymbol.setValues(list.getVars());
        }
        list.setListSymbol(listSymbol);
        return list;
    }

    @Override
    public DefineRXvariable visitDefineRXvariable(DartParser.DefineRXvariableContext ctx) {
        DefineRXvariable defineRXvariable = new DefineRXvariable();
        defineRXvariable.setVar(ctx.VARIABLE().toString());
        defineRXvariable.setNum(Integer.parseInt(ctx.NUM().toString()));
        defineRXvariable.setLine(ctx.RXINT().getSymbol().getLine());
        VarSymbol varSymbol = new VarSymbol();
        varSymbol.setType(ctx.RXINT().getText());
        varSymbol.setKey(defineRXvariable.getVar());
        varSymbol.setValue(ctx.NUM());
        defineRXvariable.setVarSymbol(varSymbol);
        return defineRXvariable;
    }

    @Override
    public ControllerPutFind visitControllerPutFind(DartParser.ControllerPutFindContext ctx) {
        ControllerPutFind find = new ControllerPutFind();
        Istedaaaclass istedaaaclass;
        ArrayList<String> vars = new ArrayList<>();
        if (ctx.VARIABLE() != null) {
            for (int i = 0; i < ctx.VARIABLE().size(); i++) {
                vars.add(ctx.VARIABLE().get(i).toString());
            }
            find.setVars(vars);
        }
        if (ctx.istedaaaclass() != null) {
            istedaaaclass = visitIstedaaaclass(ctx.istedaaaclass());
            find.setIstedaaaclass(istedaaaclass);
        }
        return find;
    }

    @Override
    public SetBody visitSetbody(DartParser.SetbodyContext ctx) {
        SetBody setBody = new SetBody();
        ArrayList<Statement> statements = new ArrayList<>();
        if (ctx.statement() != null) {
            for (int i = 0; i < ctx.statement().size(); i++) {
                statements.add(visitStatement(ctx.statement().get(i)));
            }
            setBody.setStatements(statements);
        }
        return setBody;
    }

    @Override
    public OnPressed visitOnpressed(DartParser.OnpressedContext ctx) {
        OnPressed onPressed = new OnPressed();
        SetState state;
        ArrayList<Statement> statements = new ArrayList<>();

        if (ctx.statement() != null) {
            for (int i = 0; i < ctx.statement().size(); i++) {
                statements.add(visitStatement(ctx.statement().get(i)));
            }
            onPressed.setStatements(statements);
        }
        if (ctx.setstate() != null) {
            state = visitSetstate(ctx.setstate());
            onPressed.setState(state);
        }
        return onPressed;
    }

    @Override
    public RunAppFunction visitRunappfunction(DartParser.RunappfunctionContext ctx) {
        RunAppFunction runAppFunction = new RunAppFunction();
        Istedaaaclass istedaaaclass;
        istedaaaclass = visitIstedaaaclass(ctx.istedaaaclass());
        runAppFunction.setIstedaaaclass(istedaaaclass);
        return runAppFunction;
    }

    @Override
    public RegularClass visitRegularclass(DartParser.RegularclassContext ctx) {
        level = 1;
        ClassSymbol classSymbol = new ClassSymbol();
        HashMap<String, VarSymbol> varSymbols = new HashMap<>();
        HashMap<String, ListSymbol> listSymbols = new HashMap<>();
        HashMap<String, FunSymbol> funSymbols = new HashMap<>();
        classSymbol.setId(id++);
        classSymbol.setLevel(level);
        scopeStack.push(classSymbol);
        RegularClass regularClass = new RegularClass();
        ArrayList<Def> defs = new ArrayList<>();
        Constructor constructor;
        ArrayList<CallFunction> callFunction = new ArrayList<>();
        regularClass.setVar(ctx.VARIABLE().get(0).toString());
        regularClass.setLine(ctx.CLAS().getSymbol().getLine());
        classSymbol.setKey(regularClass.getVar());

        if (ctx.EXTENDS() != null) {
            String extension = ctx.VARIABLE().get(1).toString();
            int line = ctx.VARIABLE(1).getSymbol().getLine();

            //TODO: check if the class extends from declared class
            semanticError.ExtendsNotDeclaredClass(scopes, extension, line);
        }

        //TODO: check for duplicated classes
        semanticError.DuplicateClass(scopes, classSymbol, regularClass.getLine());
        if (ctx.def() != null) {
            for (int i = 0; i < ctx.def().size(); i++) {
                defs.add(visitDef(ctx.def().get(i)));
                if (defs.get(i).getDeclare() != null) {
                    //TODO: check for duplicate declarations
                    semanticError.DuplicatedDeclare(defs.get(i).getDeclare(),
                            varSymbols, listSymbols);

                }
                if (defs.get(i).getFunction() != null) {
                    scopeStack.peek().setVarSymbol(varSymbols);
                    scopeStack.peek().setListSymbolHashMap(listSymbols);
                    //TODO check for duplicate function
                    semanticError.FunctionDeclared(defs.get(i), funSymbols);
                }
            }
            classSymbol.setVarSymbol(varSymbols);
            classSymbol.setListSymbolHashMap(listSymbols);
            classSymbol.setFunSymbols(funSymbols);
            if (ctx.constructor() != null) {
                constructor = visitConstructor(ctx.constructor());
                regularClass.setConstructor(constructor);
                if (!Objects.equals(constructor.getVars().get(0), regularClass.getVar())) {
                    throw new RuntimeException("error in line " +
                            constructor.getLine()
                            + " the constructor is invalid");
                }
            }
        }
        regularClass.setDefs(defs);
        if (ctx.callfunction() != null) {
            for (int i = 0; i < ctx.callfunction().size(); i++) {
                callFunction.add(visitCallfunction(ctx.callfunction().get(i)));
                //TODO: check that the called function is defined
                semanticError.CallNotDefinedFunction(classSymbol, callFunction.get(i));
                //TODO: check that the called function parameters are defined
                semanticError.FunctionParameterNotDefined(varSymbols, callFunction.get(i));
            }
            regularClass.setCallFunctions(callFunction);
        }
        regularClass.setClassSymbol(classSymbol);
        scopes.add(scopeStack.peek());
        scopeStack.pop();
        return regularClass;
    }

    @Override
    public Classes visitClasses(DartParser.ClassesContext ctx) {
        Classes classes = new Classes();

        if (ctx.regularclass() != null) {
            RegularClass regularClass;
            regularClass = visitRegularclass(ctx.regularclass());
            classes.setRegularClass(regularClass);
        }
        if (ctx.widgetclass() != null) {
            WidgetClass widgetClass;
            widgetClass = visitWidgetclass(ctx.widgetclass());
            classes.setWidgetClass(widgetClass);
        }
        if (ctx.controllerClass() != null) {
            ControllerClass controllerClass;
            controllerClass = visitControllerClass(ctx.controllerClass());
            classes.setControllerClass(controllerClass);
        }



        return classes;
    }

    @Override
    public ControllerClass visitControllerClass(DartParser.ControllerClassContext ctx) {
        level = 1;
        ClassSymbol classSymbol = new ClassSymbol();
        HashMap<String, VarSymbol> varSymbols = new HashMap<>();
        HashMap<String, ListSymbol> listSymbols = new HashMap<>();
        HashMap<String, FunSymbol> funSymbols = new HashMap<>();
        classSymbol.setId(id++);
        classSymbol.setLevel(level);
        scopeStack.push(classSymbol);
        ControllerClass controllerClass = new ControllerClass();
        ArrayList<Def> defs = new ArrayList<>();
        Constructor constructor;
        ArrayList<CallFunction> callFunctions = new ArrayList<>();
        controllerClass.setVariable(ctx.VARIABLE().toString());
        controllerClass.setLine(ctx.CLAS().getSymbol().getLine());
        classSymbol.setKey(controllerClass.getVariable());
        //TODO: check for duplicate class
        semanticError.DuplicateClass(scopes, classSymbol, controllerClass.getLine());
        if (ctx.def() != null) {
            for (int i = 0; i < ctx.def().size(); i++) {
                defs.add(visitDef(ctx.def().get(i)));
                if (defs.get(i).getDeclare() != null) {
                    //TODO: check for duplicate declarations
                    semanticError.DuplicatedDeclare(defs.get(i).getDeclare(),
                            varSymbols, listSymbols);

                }
                if (defs.get(i).getFunction() != null) {
                    scopeStack.peek().setVarSymbol(varSymbols);
                    scopeStack.peek().setListSymbolHashMap(listSymbols);
                    //TODO check for duplicate function
                    semanticError.FunctionDeclared(defs.get(i), funSymbols);
                }
                controllerClass.setDef(defs);
            }
            classSymbol.setVarSymbol(varSymbols);
            classSymbol.setListSymbolHashMap(listSymbols);
            classSymbol.setFunSymbols(funSymbols);

        }

        if (ctx.constructor() != null) {
            constructor = visitConstructor(ctx.constructor());
            controllerClass.setConstructor(constructor);
            if (!Objects.equals(constructor.getVars().get(0), controllerClass.getVariable())) {
                throw new RuntimeException("error in line " +
                        constructor.getLine()
                        + " the constructor is invalid");
            }
        }
        if (ctx.callfunction() != null) {
            for (int i = 0; i < ctx.callfunction().size(); i++) {
                callFunctions.add(visitCallfunction(ctx.callfunction().get(i)));
                //TODO: check that the called function is defined
                semanticError.CallNotDefinedFunction(classSymbol, callFunctions.get(i));
                //TODO: check that the called function parameters are defined
                semanticError.FunctionParameterNotDefined(varSymbols, callFunctions.get(i));
            }
            controllerClass.setCallFunctions(callFunctions);
        }

        controllerClass.setClassSymbol(classSymbol);

        scopes.add(scopeStack.peek());
        scopeStack.pop();
        return controllerClass;
    }

    @Override
    public BoxDecoration visitBoxdecoration(DartParser.BoxdecorationContext ctx) {
        BoxDecoration boxdecoration = new BoxDecoration();
        if (ctx.COLORVALUE() != null) {
            boxdecoration.setColor_value(ctx.COLORVALUE().getText());
            boxdecoration.setLine(ctx.COLORVALUE().getSymbol().getLine());
            boxdecoration.setCol(ctx.COLORVALUE().getSymbol().getCharPositionInLine() + 1);
        }
        return boxdecoration;
    }

    @Override
    public CallFunction visitCallfunction(DartParser.CallfunctionContext ctx) {
        CallFunction callFunction = new CallFunction();
        ArrayList<String> parameters = new ArrayList<>();
        callFunction.setName(ctx.VARIABLE().get(0).toString());
        callFunction.setLine(ctx.VARIABLE().get(0).getSymbol().getLine());
        if (ctx.VARIABLE().size() > 1) {
            for (int i = 1; i < ctx.VARIABLE().size(); i++) {
                parameters.add(ctx.VARIABLE().get(i).toString());
            }
            callFunction.setParameters(parameters);
        }
        return callFunction;
    }
}
