package ast.Visitors;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import Main.Program;
public class BaseVisitor extends DartParserBaseVisitor {
    int level = 1;
    int id = 0;
    Stack scope = new Stack();
    ArrayList scopes = new ArrayList();
    HashMap<String , ClassSymbol> classMap = new HashMap<String , ClassSymbol>();
    private HashMap<String , FunSymbol> funSymbols = new HashMap<String , FunSymbol>();
    SymbolTable symbolTable = new SymbolTable();


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
        symbolTable.setSymbols(classMap);
        symbolTable.print();
        System.out.println("\n");
        return prog;
    }

    @Override
    public Main visitMain(DartParser.MainContext ctx) {
        Scope scope1 = new Scope();
        ClassSymbol classSymbol = new ClassSymbol();
        scope1.setLevel(level);
        scope1.setId(id);
        scope.push(scope1);
        scopes.add(scope1);
        classSymbol.setKey("main");
        ArrayList<VarSymbol> varSymbols = new ArrayList<VarSymbol>();
        Main main = new Main();
        main.setLine(ctx.MAIN().getSymbol().getLine());
        main.setCol(ctx.MAIN().getSymbol().getCharPositionInLine());
        ArrayList<Declare> declares = new ArrayList<>();
        main.setType(visitType(ctx.type()));
        main.setRunAppFunction(visitRunappfunction(ctx.runappfunction()));
        if (ctx.declare() != null) {
            for (int i = 0; i < ctx.declare().size(); i++) {
                VarSymbol varSymbol = new VarSymbol();
                declares.add(visitDeclare(ctx.declare().get(i)));
                varSymbol.setKey(declares.get(i).getDefineVariable().getVar());
                varSymbol.setType(declares.get(i).getDefineVariable().getType().getType());
                varSymbol.setValue(declares.get(i).getDefineVariable().getValue());
                varSymbols.add(varSymbol);
                // TODO check
//                System.out.println("Variables: \n" + varSymbol.print());
            }
            main.setDeclares(declares);
            classSymbol.setVarSymbols(varSymbols);
            classMap.put(classSymbol.getKey(),classSymbol);
        }
        scope.pop();
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
        return widgetClass;
    }

    @Override
    public StfulClass visitStfulclass(DartParser.StfulclassContext ctx) {
        StfulClass stfulClass = new StfulClass();
        ArrayList<Def> defs = new ArrayList<>();
        if (ctx.constructor() != null) {
            stfulClass.setConstructor(visitConstructor(ctx.constructor()));
        }
        if (ctx.def() != null) {
            for (int i = 0; i < ctx.def().size(); i++) {
                defs.add(visitDef(ctx.def().get(i)));
            }
            stfulClass.setDef(defs);
        }
        stfulClass.setCreateState(visitCreatestate(ctx.createstate()));
        stfulClass.setStateClass(visitStateclass(ctx.stateclass()));
        stfulClass.setVar(ctx.VARIABLE().toString());
        //        Swidth swidth = new Swidth();
//        swidth.setKey("StateFul class");
//        swidth.setValue((ctx.VARIABLE().toString()));
//        symbolTable.setSwidth(swidth);
        return stfulClass;
    }

    @Override
    public StlessClass visitStlessclass(DartParser.StlessclassContext ctx) {
        StlessClass stlessClass = new StlessClass();
        stlessClass.setVar(ctx.VARIABLE().toString());
        stlessClass.setStlessClassBody( visitStlessclassbody(ctx.stlessclassbody()));
        //        Swidth swidth = new Swidth();
        //        swidth.setKey("State less var");
        //        swidth.setValue((ctx.VARIABLE().toString()));
        //        //symbolTable.setSwidth(swidth);
        //        Swidth swidth1 = new Swidth();
        //        swidth1.setKey("State Class state");
        //        swidth1.setValue((ctx.VARIABLE().toString()));
        //        //symbolTable.setSwidth(swidth1);
        return stlessClass;
    }

    @Override
    public Constructor visitConstructor(DartParser.ConstructorContext ctx) {
        Constructor constructor = new Constructor();
        ArrayList<String> vars = new ArrayList<>();
        for (int i = 0; i < ctx.VARIABLE().size(); i++) {
            vars.add(ctx.VARIABLE().get(i).toString());
        }
        constructor.setVars(vars);
        //        Swidth swidth = new Swidth();
        //        swidth.setKey("Constructor");
        //        swidth.setValue((ctx.VARIABLE().toString()));
        //        symbolTable.setSwidth(swidth);
        return constructor;
    }

    @Override
    public Def visitDef(DartParser.DefContext ctx) {
        Def def = new Def();
        if (ctx.function() != null) {
            def.setFunction(visitFunction(ctx.function()));
        }
        if (ctx.declare() != null) {
            def.setDeclare(visitDeclare(ctx.declare()));
        }
        if(ctx.controllerPutFind() != null) {
            def.setControllerPutFind(visitControllerPutFind(ctx.controllerPutFind()));
        }
        return def;
    }

    @Override
    public CreateState visitCreatestate(DartParser.CreatestateContext ctx) {
        CreateState createState = new CreateState();
        ArrayList<String> vars = new ArrayList<>();
        for (int i = 0; i < vars.size(); i++) {
            vars.add(ctx.VARIABLE().get(i).toString());
        }
        createState.setVars(vars);
        return createState;
    }

    @Override
    public StateClass visitStateclass(DartParser.StateclassContext ctx) {
        StateClass stateClass = new StateClass();
        ArrayList<Def> defs = new ArrayList<>();
        stateClass.setBuildFunction( visitBuildfunction(ctx.buildfunction()));
        if (ctx.def() != null) {
            for (int i = 0; i < ctx.def().size(); i++) {
                defs.add(visitDef(ctx.def().get(i)));
            }
            stateClass.setDef(defs);
        }
        stateClass.setVar(ctx.VARIABLE().toString());
        //        Swidth swidth = new Swidth();
        //        swidth.setKey("State Class var");
        //        swidth.setValue((ctx.VARIABLE().toString()));
        //        symbolTable.setSwidth(swidth);
        return stateClass;
    }

    @Override
    public StlessClassBody visitStlessclassbody(DartParser.StlessclassbodyContext ctx) {
        StlessClassBody stlessClassBody = new StlessClassBody();
        Constructor constructor = new Constructor();
        ArrayList<Def> defs = new ArrayList<>();
        BuildFunction buildFunction = new BuildFunction();
        if(ctx.constructor() != null) {
            constructor =visitConstructor(ctx.constructor());
            stlessClassBody.setConstructor(constructor);
        }
        buildFunction =visitBuildfunction(ctx.buildfunction());
        stlessClassBody.setBuildFunction(buildFunction);
        if (ctx.def() != null) {
            for (int i = 0; i < ctx.def().size(); i++) {
                defs.add(visitDef(ctx.def().get(i)));
            }
            stlessClassBody.setDefs(defs);
        }
        return stlessClassBody;
    }

    @Override
    public BuildFunction visitBuildfunction(DartParser.BuildfunctionContext ctx) {
        BuildFunction buildFunction = new BuildFunction();
        Widget widget = new Widget();
        widget = visitWidget(ctx.widget());
        buildFunction.setWidget(widget);
        return buildFunction;
    }

    @Override
    public Function visitFunction(DartParser.FunctionContext ctx) {
        Function function = new Function();
        function.setType(visitType(ctx.type()));
        function.setVar(ctx.VARIABLE().get(0).toString());
        ArrayList<FunctionOnParameter> functionOnParameters = new ArrayList<>();
        ArrayList<Statement> statements = new ArrayList<>();
        ArrayList<Declare> declares = new ArrayList<>();
        if (ctx.functionparameter() != null) {
            for (int i = 0; i < ctx.functionparameter().size(); i++) {
                functionOnParameters.add(visitFunctionparameter(ctx.functionparameter().get(i)));
            }
            function.setFunctionOnParameters(functionOnParameters);
        }
        if (ctx.statement() != null) {
            for (int i = 0; i < ctx.statement().size(); i++) {
                statements.add(visitStatement(ctx.statement().get(i)));
            }
            function.setStatements(statements);
        }
        if (ctx.declare() != null) {
            for (int i = 0; i < ctx.declare().size(); i++) {
                declares.add(visitDeclare(ctx.declare().get(i)));
            }
            function.setDeclares(declares);
        }
        if(ctx.NUM() != null) {
            function.setReturnType("int");
        } else if (ctx.VARIABLE().size() >= 2) {
            String returnValue = ctx.VARIABLE().get(ctx.VARIABLE().size()-1).toString();
            if(returnValue == "true" || returnValue == "false")
                function.setReturnType("boolean");
            else
                function.setReturnType("String");
        }
        else
            function.setReturnType("void");
        return function;
    }
    //________________________________________________________________

    //________________________________________________________________
    //widgets
    @Override
    public Widget visitWidget(DartParser.WidgetContext ctx) {
        Widget widget = new Widget();
        MaterialApp materialApp;
        Button button ;
        Image image;
        Scaffold scaffold ;
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
        }
        if (ctx.button() != null) {
            button = visitButton(ctx.button());
            widget.setButton(button);
        }
        if (ctx.image() != null) {
            image = visitImage(ctx.image());
            widget.setImage(image);
        }
        if (ctx.scaffold() != null) {
            scaffold = visitScaffold(ctx.scaffold());
            widget.setScaffold(scaffold);
        }
        if (ctx.column() != null) {
            column = visitColumn(ctx.column());
            widget.setColumn(column);
        }
        if (ctx.row() != null) {
            row = visitRow(ctx.row());
            widget.setRow(row);
        }
        if (ctx.container() != null) {
            container = visitContainer(ctx.container());
            widget.setContainer(container);
        }
        if (ctx.expanded() != null) {
            expanded = visitExpanded(ctx.expanded());
            widget.setExpanded(expanded);
        }
        if (ctx.padding() != null) {
            padding = visitPadding(ctx.padding());
            widget.setPadding(padding);
        }
        if (ctx.center() != null) {
            center = visitCenter(ctx.center());
            widget.setCenter(center);
        }
        if (ctx.text() != null) {
            text = visitText(ctx.text());
            widget.setText(text);
        }
        if (ctx.sizedbox() != null) {
            sizedBox = visitSizedbox(ctx.sizedbox());
            widget.setSizedBox(sizedBox);
        }
        if (ctx.obx() != null) {
            obx = visitObx(ctx.obx());
            widget.setObx(obx);
        }
        if (ctx.icon() != null) {
            icon = visitIcon(ctx.icon());
            widget.setIcon(icon);
        }
        return widget;
    }

    @Override
    public MaterialApp visitMaterialapp(DartParser.MaterialappContext ctx) {
        MaterialApp materialApp = new MaterialApp();
        Istedaaaclass istedaaaclass = new Istedaaaclass();
        if(ctx.materialtitle() != null) {
            materialApp.setTitle(visitMaterialtitle(ctx.materialtitle()));
        }
        istedaaaclass = visitIstedaaaclass(ctx.istedaaaclass());
        //istedaaaclass.setLine(ctx.istedaaaclass());
        istedaaaclass.setCol(istedaaaclass.getCol());
        materialApp.setIstedaaaclass(istedaaaclass);
        return materialApp ;
    }

    @Override
    public Button visitButton(DartParser.ButtonContext ctx) {
        Button button = new Button();
        Child child;
        child = (Child) visitChild(ctx.child());
        button.setChild(child);
        return button;
    }

    @Override
    public Image visitImage(DartParser.ImageContext ctx) {
        Image image = new Image();
        if(ctx.STRING_LINE() != null) {
            image.setStringLine(ctx.STRING_LINE().getText());
        }
        ArrayList<String> vars = new ArrayList<>();
        for (int i = 0; i < ctx.VARIABLE().size(); i++) {
            vars.add(ctx.VARIABLE().get(i).toString());
        }
        image.setVar(vars);
        image.setLine(ctx.VARIABLE().get(0).getSymbol().getLine());
        image.setCol(ctx.VARIABLE().get(0).getSymbol().getCharPositionInLine()+1);
        if (ctx.NUM() != null)
            image.setNum(Integer.parseInt(ctx.NUM().toString()));
        //        Swidth swidth = new Swidth();
//        swidth.setKey("Image");
//        swidth.setValue((ctx.VARIABLE().toString()));
//        symbolTable.setSwidth(swidth);
        return image;
    }

    @Override
    public Scaffold visitScaffold(DartParser.ScaffoldContext ctx) {
        Scaffold scaffold = new Scaffold();
        Text texts = new Text();
        Widget body = new Widget();
        body =  visitWidget(ctx.widget());
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
                widgets.add((Widget) visitWidget(ctx.widget().get(i)));
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
        BoxDecoration boxDecoration = new BoxDecoration();
        if (ctx.height() != null) {
            container.setHeight(visitHeight(ctx.height()));
        }
        if (ctx.width() != null) {
            container.setWidth(visitWidth(ctx.width()));
        }
        if (ctx.child() != null) {
            Child child;
            child = (Child) visitChild(ctx.child());
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
        //        Swidth swidth = new Swidth();
//        swidth.setKey("Padding num ");
//        swidth.setValue((ctx.num().getText()));
        //symbolTable.setSwidth(swidth);
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
        if(ctx.STRING_LINE() != null) {
            text.setStringLine(ctx.STRING_LINE().getText());
        }
        ArrayList<String> vars = new ArrayList<>();
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < ctx.VARIABLE().size(); i++) {
            vars.add(ctx.VARIABLE().get(i).getText());
        }
        text.setVars(vars);
        text.setLine(ctx.VARIABLE().get(0).getSymbol().getLine());
        text.setCol(ctx.VARIABLE().get(0).getSymbol().getCharPositionInLine()+1);
        // System.out.println("text: col= " +text.getCol() +"\n line= " +text.getLine()+ "\n");
        if (ctx.NUM() != null) {
            for (int i = 0; i < ctx.NUM().size(); i++) {
                numbers.add(Integer.parseInt(ctx.NUM().get(i).toString()));
            }
            text.setNums(numbers);
        }
        //Swidth swidth = new Swidth();
        //swidth.setKey("Text");
        //swidth.setValue((ctx.VARIABLE().toString()));
        //symbolTable.setSwidth(swidth);
        return text;
    }

    @Override
    public SizedBox visitSizedbox(DartParser.SizedboxContext ctx) {
        SizedBox sizedBox = new SizedBox();
        Height height = new Height();
        Width width = new Width();
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
        Widget widget = new Widget();
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

    //statment________________________________________________________________
    @Override
    public Statement visitStatement(DartParser.StatementContext ctx) {
        Statement statement = new Statement();
        WhileStatement whileStatement = new WhileStatement();
        IfStatement ifStatement = new IfStatement();
        SwitchStatement switchStatement = new SwitchStatement();
        TryStatement tryStatement = new TryStatement();
        Block block = new Block();
        Print print = new Print();
        Navigation navigation = new Navigation();
        SetState state = new SetState();
        Expr expr = new Expr();
        Expression expression = new Expression();
        ChangingValue changingValue = new ChangingValue();
        ControllerFunctions controllerFunctions = new ControllerFunctions();
        ControllerAttributes controllerAttributes = new ControllerAttributes();
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
        IfStatement ifStatement = new IfStatement();
        ArrayList<Expression> expressions = new ArrayList<>();
        ArrayList<Block> blocks = new ArrayList<>();
        for(int i = 0; i < ctx.expression().size(); i++) {
            expressions.add(visitExpression(ctx.expression().get(i)));
        }
        ifStatement.setExpressions(expressions);

        for(int i = 0; i < ctx.block().size(); i++) {
            blocks.add(visitBlock(ctx.block().get(i)));
        }
        ifStatement.setBlocks(blocks);

        return ifStatement;
    }

    @Override
    public WhileStatement visitWhileStatement(DartParser.WhileStatementContext ctx) {
        WhileStatement whileStatement = new WhileStatement();
        Block block = new Block();
        Expression expression = new Expression();
        block = visitBlock(ctx.block());
        expression = visitExpression(ctx.expression());
        whileStatement.setBlock(block);
        whileStatement.setExpression(expression);
        return whileStatement;
    }

    @Override
    public SwitchStatement visitSwitchStatement(DartParser.SwitchStatementContext ctx) {
        SwitchStatement switchStatement = new SwitchStatement();
        Expression expression = new Expression();
        ArrayList<SwitchCase> switchCase = new ArrayList<SwitchCase>();
        DefaultCase defaultCase = new DefaultCase();
        expression = visitExpression(ctx.expression());
        switchStatement.setExpression(expression);
        if (ctx.defaultCase() != null) {
            defaultCase = visitDefaultCase(ctx.defaultCase());
            switchStatement.setDefaultCase(defaultCase);
        }
        if (ctx.switchCase() != null) {
            for (int i = 0; i < switchCase.size(); i++) {
                switchCase.add(visitSwitchCase(ctx.switchCase().get(i)));
            }
            switchStatement.setSwitchCase(switchCase);
        }
        return switchStatement;
    }

    @Override
    public TryStatement visitTryStatement(DartParser.TryStatementContext ctx) {
        TryStatement tryStatement = new TryStatement();
        ArrayList<Block> blocks = new ArrayList<Block>();
        CatchPart catchPart = new CatchPart();
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
        if(ctx.STRING_LINE() != null){
            print.setStringLine(ctx.STRING_LINE().getText());
        }
        return print;
    }

    @Override
    public Navigation visitNavigation(DartParser.NavigationContext ctx) {
        Navigation navigation = new Navigation();
        Istedaaaclass istedaaaclass = new Istedaaaclass();
        if (ctx.istedaaaclass() != null) {
            istedaaaclass = visitIstedaaaclass(ctx.istedaaaclass());
            navigation.setIstedaaaclass(istedaaaclass);
        }
        return navigation;
    }

    @Override
    public SetState visitSetstate(DartParser.SetstateContext ctx) {
        SetState state = new SetState();
        SetBody setBody = new SetBody();
        setBody = visitSetbody(ctx.setbody());
        state.setSetBody(setBody);
        return state;
    }

    @Override
    public Expr visitExpr(DartParser.ExprContext ctx) {
        Expr expr = new Expr();
        ArrayList<String> vars = new ArrayList<String>();
        ArrayList<Expr> child = new ArrayList<>();
        if (ctx.NUM() != null) {
            expr.setNum(Integer.parseInt(ctx.NUM().toString()));
        }
        if (ctx.VARIABLE() != null) {
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
        Expression child = new Expression();
        Expr expr = new Expr();
        if (ctx.expression() != null) {
            child = visitExpression(ctx.expression());
            expression.setExpression(child);
        }
        if (ctx.expr() != null) {
            expr = visitExpr(ctx.expr());
            expression.setExpr(expr);
        }
        if (ctx.VARIABLE() != null) {
            expression.setVar(ctx.VARIABLE().getText());
        }
        if (ctx.NUM() != null) {
            expression.setNum(Integer.parseInt(ctx.NUM().toString()));
        }
        return expression;
    }

    @Override
    public ChangingValue visitChangingvalue(DartParser.ChangingvalueContext ctx) {
        ChangingValue changingValue = new ChangingValue();
        Expr expr = new Expr();
        if (ctx.expr() != null) {
            expr = visitExpr(ctx.expr());
            changingValue.setExpr(expr);
        }
        changingValue.setVar(ctx.VARIABLE().getText());
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
        ArrayList<String> vars = new ArrayList<String>();
        if (ctx.VARIABLE() != null) {
            for (int i = 0; i < ctx.VARIABLE().size(); i++) {
                vars.add(ctx.VARIABLE().get(i).toString());
            }
            controllerFunctions.setVars(vars);
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
        //        Sheight sheight = new Sheight();
//        sheight.setKey("height");
//        sheight.setValue(ctx.NUM().toString());
//        symbolTable.setSheights(sheight);
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
        istedaaaclass.setCol(id_token.getCharPositionInLine()+1);
        //        variableSymbol.setKey("");
        //System.out.println("col= " + istedaaaclass.getCol()+ "\nline= " + istedaaaclass.getLine() + "\n");
//        Swidth swidth = new Swidth();
//        swidth.setKey("Class var :");
//        swidth.setValue((ctx.VARIABLE().toString()));
//        symbolTable.setSwidth(swidth);
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
        Expression expression = new Expression();
        Statement statement = new Statement();
        expression = visitExpression(ctx.expression());
        statement = visitStatement(ctx.statement());
        switchCase.setExpression(expression);
        switchCase.setStatement(statement);
        return switchCase;
    }

    @Override
    public DefaultCase visitDefaultCase(DartParser.DefaultCaseContext ctx) {
        DefaultCase defaultCase = new DefaultCase();
        Statement statement = new Statement();
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
        if(ctx.BOOL() != null) {
            type.setType( ctx.BOOL().getText());
        }
        if(ctx.INT() != null){
            type.setType( ctx.INT().getText() );
        }
        if(ctx.STRING() != null){
            type.setType( ctx.STRING().getText() );
        }
        if(ctx.VOID() != null){
            type.setType( ctx.VOID().getText() );
        }
        return type;
    }

    @Override
    public FunctionOnParameter visitFunctionparameter(DartParser.FunctionparameterContext ctx) {
        FunctionOnParameter functionOnParameter = new FunctionOnParameter();
        ArrayList<Type> types = new ArrayList<>();
        for(int i = 0; i < ctx.type().size() ; i++) {
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
        if (ctx.NUM() != null) {
            defineVariable.setNum(Integer.parseInt(ctx.NUM().toString()));
            defineVariable.setValue(ctx.NUM().getText());
        }
        if(ctx.STRING_LINE() != null) {
            defineVariable.setStringLine(ctx.STRING_LINE().getText());
            defineVariable.setValue(ctx.STRING_LINE().getText());
        }
        return defineVariable;
    }

    @Override
    public Declare visitDeclare(DartParser.DeclareContext ctx) {
        Declare declare = new Declare();
        DefineRXvariable defineRXvariable = new DefineRXvariable();
        DefineVariable defineVariable = new DefineVariable();
        if (ctx.list() != null) {
            List list = new List();
            list = (List) visitList(ctx.list());
            declare.setList(list);
        }
        if (ctx.definevariable() != null) {
            defineVariable = (DefineVariable) visitDefinevariable(ctx.definevariable());
            declare.setDefineVariable(defineVariable);}
        if (ctx.defineRXvariable() != null) {
            defineRXvariable = (DefineRXvariable) visitDefineRXvariable(ctx.defineRXvariable());
            declare.setDefineRXvariable(defineRXvariable);
        }
        return declare;
    }

    @Override
    public List visitList(DartParser.ListContext ctx) {
        List list = new List();
        ArrayList<String> vars = new ArrayList<String>();
        if(ctx.STRING_LINE() != null) {
            for(int i = 0; i < ctx.STRING_LINE().size(); i++)
                vars.add(ctx.STRING_LINE().get(i).toString());
            list.setVars(vars);
        }
        list.setVar(ctx.VARIABLE().toString());
        //        Swidth swidth = new Swidth();
        //        swidth.setKey("List var");
        //        swidth.setValue((ctx.VARIABLE().toString()));
        return list;
    }

    @Override
    public Object visitDefineRXvariable(DartParser.DefineRXvariableContext ctx) {
        DefineRXvariable defineRXvariable = new DefineRXvariable();
        if (ctx.VARIABLE() != null) {
            defineRXvariable.setVar(ctx.VARIABLE().toString());
        }
        if (ctx.NUM() != null) {
            defineRXvariable.setNum(Integer.parseInt(ctx.NUM().toString()));
        }
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
            for (int i = 0; i < statements.size(); i++) {
                statements.add(visitStatement(ctx.statement().get(i)));
            }
            setBody.setStatements(statements);
        }
        return setBody;
    }

    @Override
    public OnPressed visitOnpressed(DartParser.OnpressedContext ctx) {
        OnPressed onPressed = new OnPressed();
        SetState state = new SetState();
        ArrayList<Statement> statements = new ArrayList<Statement>();

        if (ctx.statement() != null) {
            for (int i = 0; i < statements.size(); i++) {
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
        Istedaaaclass istedaaaclass = new Istedaaaclass();
        istedaaaclass = visitIstedaaaclass(ctx.istedaaaclass());
        runAppFunction.setIstedaaaclass(istedaaaclass);
        return runAppFunction;
    }

    @Override
    public RegularClass visitRegularclass(DartParser.RegularclassContext ctx) {
        RegularClass regularClass = new RegularClass();

        ArrayList<Def> defs = new ArrayList<>();

        if (ctx.def() != null) {

            for (int i = 0; i < ctx.def().size(); i++) {
                defs.add(visitDef(ctx.def().get(i)));
            }
            regularClass.setDefs(defs);
        }
        if (ctx.constructor() != null) {
            Constructor constructor = new Constructor();
            constructor = (Constructor) visitConstructor(ctx.constructor());
            regularClass.setConstructor(constructor);
        }
        regularClass.setVar(ctx.VARIABLE().toString());
        //        Swidth swidth = new Swidth();
//        swidth.setKey("Regular Class var ");
//        swidth.setValue((ctx.VARIABLE().toString()));
//        symbolTable.setSwidth(swidth);
        return regularClass;
    }

    @Override
    public Classes visitClasses(DartParser.ClassesContext ctx) {
        Classes classes = new Classes();
        if (ctx.regularclass() != null) {
            RegularClass regularClass = new RegularClass();
            regularClass = visitRegularclass(ctx.regularclass());
            classes.setRegularClass(regularClass);
        }
        if (ctx.widgetclass() != null) {
            WidgetClass widgetClass = new WidgetClass();
            widgetClass =  visitWidgetclass(ctx.widgetclass());
            classes.setWidgetClass(widgetClass);
        }
        if(ctx.controllerClass() != null) {
            ControllerClass controllerClass = new ControllerClass();
            controllerClass = visitControllerClass(ctx.controllerClass());
            classes.setControllerClass(controllerClass);
        }
        return classes;
    }

    @Override
    public ControllerClass visitControllerClass(DartParser.ControllerClassContext ctx) {
        ControllerClass controllerClass = new ControllerClass();
        ArrayList<Def> defs = new ArrayList<>();
        Constructor constructor = new Constructor();
        if(ctx.def() != null){
            for(int i = 0; i<ctx.def().size() ; i++)
                defs.add(visitDef(ctx.def().get(i)));
            controllerClass.setDef(defs);
        }
        if(ctx.constructor() != null) {
            constructor = visitConstructor(ctx.constructor());
            controllerClass.setConstructor(constructor);
        }
        return controllerClass;
    }

    @Override
    public BoxDecoration visitBoxdecoration(DartParser.BoxdecorationContext ctx) {
        BoxDecoration boxdecoration = new BoxDecoration();
        if (ctx.COLORVALUE() != null){
            boxdecoration.setColor_value(ctx.COLORVALUE().getText());
            boxdecoration.setLine(ctx.COLORVALUE().getSymbol().getLine());
            boxdecoration.setCol(ctx.COLORVALUE().getSymbol().getCharPositionInLine()+1);
        }
        return boxdecoration;
    }

}
