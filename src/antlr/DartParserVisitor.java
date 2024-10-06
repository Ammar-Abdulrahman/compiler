// Generated from D:/ITE/4. Fourth Year/2022-2023/«·›’· «·À«‰Ì/ﬂÊ„»«Ì·—2/compiler/src/antlr\DartParser.g4 by ANTLR 4.12.0
package antlr;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link DartParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface DartParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link DartParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(DartParser.ProgContext ctx);
	/**
	 * Visit a parse tree produced by {@link DartParser#imports}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImports(DartParser.ImportsContext ctx);
	/**
	 * Visit a parse tree produced by {@link DartParser#main}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMain(DartParser.MainContext ctx);
	/**
	 * Visit a parse tree produced by {@link DartParser#runappfunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRunappfunction(DartParser.RunappfunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link DartParser#istedaaaclass}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIstedaaaclass(DartParser.IstedaaaclassContext ctx);
	/**
	 * Visit a parse tree produced by {@link DartParser#callfunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallfunction(DartParser.CallfunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link DartParser#widgetclass}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWidgetclass(DartParser.WidgetclassContext ctx);
	/**
	 * Visit a parse tree produced by {@link DartParser#stlessclass}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStlessclass(DartParser.StlessclassContext ctx);
	/**
	 * Visit a parse tree produced by {@link DartParser#stlessclassbody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStlessclassbody(DartParser.StlessclassbodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link DartParser#stfulclass}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStfulclass(DartParser.StfulclassContext ctx);
	/**
	 * Visit a parse tree produced by {@link DartParser#stateclass}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStateclass(DartParser.StateclassContext ctx);
	/**
	 * Visit a parse tree produced by {@link DartParser#classes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClasses(DartParser.ClassesContext ctx);
	/**
	 * Visit a parse tree produced by {@link DartParser#regularclass}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRegularclass(DartParser.RegularclassContext ctx);
	/**
	 * Visit a parse tree produced by {@link DartParser#controllerClass}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitControllerClass(DartParser.ControllerClassContext ctx);
	/**
	 * Visit a parse tree produced by {@link DartParser#declare}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclare(DartParser.DeclareContext ctx);
	/**
	 * Visit a parse tree produced by {@link DartParser#defineRXvariable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefineRXvariable(DartParser.DefineRXvariableContext ctx);
	/**
	 * Visit a parse tree produced by {@link DartParser#definevariable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefinevariable(DartParser.DefinevariableContext ctx);
	/**
	 * Visit a parse tree produced by {@link DartParser#list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitList(DartParser.ListContext ctx);
	/**
	 * Visit a parse tree produced by {@link DartParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(DartParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link DartParser#controllerFunctions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitControllerFunctions(DartParser.ControllerFunctionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link DartParser#controllerAttributes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitControllerAttributes(DartParser.ControllerAttributesContext ctx);
	/**
	 * Visit a parse tree produced by {@link DartParser#controllerPutFind}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitControllerPutFind(DartParser.ControllerPutFindContext ctx);
	/**
	 * Visit a parse tree produced by {@link DartParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(DartParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link DartParser#changingvalue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChangingvalue(DartParser.ChangingvalueContext ctx);
	/**
	 * Visit a parse tree produced by {@link DartParser#print}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrint(DartParser.PrintContext ctx);
	/**
	 * Visit a parse tree produced by {@link DartParser#tryStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTryStatement(DartParser.TryStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link DartParser#catchPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCatchPart(DartParser.CatchPartContext ctx);
	/**
	 * Visit a parse tree produced by {@link DartParser#switchStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSwitchStatement(DartParser.SwitchStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link DartParser#switchCase}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSwitchCase(DartParser.SwitchCaseContext ctx);
	/**
	 * Visit a parse tree produced by {@link DartParser#defaultCase}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefaultCase(DartParser.DefaultCaseContext ctx);
	/**
	 * Visit a parse tree produced by {@link DartParser#whileStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStatement(DartParser.WhileStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link DartParser#ifStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStatement(DartParser.IfStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link DartParser#def}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDef(DartParser.DefContext ctx);
	/**
	 * Visit a parse tree produced by {@link DartParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(DartParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link DartParser#function}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction(DartParser.FunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link DartParser#functionparameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionparameter(DartParser.FunctionparameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link DartParser#assignmentOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentOperator(DartParser.AssignmentOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link DartParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(DartParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link DartParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(DartParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link DartParser#buildfunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBuildfunction(DartParser.BuildfunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link DartParser#constructor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstructor(DartParser.ConstructorContext ctx);
	/**
	 * Visit a parse tree produced by {@link DartParser#createstate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreatestate(DartParser.CreatestateContext ctx);
	/**
	 * Visit a parse tree produced by {@link DartParser#extends}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExtends(DartParser.ExtendsContext ctx);
	/**
	 * Visit a parse tree produced by {@link DartParser#navigation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNavigation(DartParser.NavigationContext ctx);
	/**
	 * Visit a parse tree produced by {@link DartParser#materialtitle}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMaterialtitle(DartParser.MaterialtitleContext ctx);
	/**
	 * Visit a parse tree produced by {@link DartParser#height}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHeight(DartParser.HeightContext ctx);
	/**
	 * Visit a parse tree produced by {@link DartParser#width}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWidth(DartParser.WidthContext ctx);
	/**
	 * Visit a parse tree produced by {@link DartParser#materialtheme}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMaterialtheme(DartParser.MaterialthemeContext ctx);
	/**
	 * Visit a parse tree produced by {@link DartParser#materialdebug}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMaterialdebug(DartParser.MaterialdebugContext ctx);
	/**
	 * Visit a parse tree produced by {@link DartParser#themedata}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitThemedata(DartParser.ThemedataContext ctx);
	/**
	 * Visit a parse tree produced by {@link DartParser#onpressed}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOnpressed(DartParser.OnpressedContext ctx);
	/**
	 * Visit a parse tree produced by {@link DartParser#setstate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetstate(DartParser.SetstateContext ctx);
	/**
	 * Visit a parse tree produced by {@link DartParser#setbody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetbody(DartParser.SetbodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link DartParser#child}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChild(DartParser.ChildContext ctx);
	/**
	 * Visit a parse tree produced by {@link DartParser#scaffold}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScaffold(DartParser.ScaffoldContext ctx);
	/**
	 * Visit a parse tree produced by {@link DartParser#materialapp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMaterialapp(DartParser.MaterialappContext ctx);
	/**
	 * Visit a parse tree produced by {@link DartParser#container}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContainer(DartParser.ContainerContext ctx);
	/**
	 * Visit a parse tree produced by {@link DartParser#boxdecoration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoxdecoration(DartParser.BoxdecorationContext ctx);
	/**
	 * Visit a parse tree produced by {@link DartParser#num}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNum(DartParser.NumContext ctx);
	/**
	 * Visit a parse tree produced by {@link DartParser#padding}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPadding(DartParser.PaddingContext ctx);
	/**
	 * Visit a parse tree produced by {@link DartParser#column}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumn(DartParser.ColumnContext ctx);
	/**
	 * Visit a parse tree produced by {@link DartParser#row}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRow(DartParser.RowContext ctx);
	/**
	 * Visit a parse tree produced by {@link DartParser#expanded}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpanded(DartParser.ExpandedContext ctx);
	/**
	 * Visit a parse tree produced by {@link DartParser#text}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitText(DartParser.TextContext ctx);
	/**
	 * Visit a parse tree produced by {@link DartParser#image}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImage(DartParser.ImageContext ctx);
	/**
	 * Visit a parse tree produced by {@link DartParser#obx}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObx(DartParser.ObxContext ctx);
	/**
	 * Visit a parse tree produced by {@link DartParser#sizedbox}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSizedbox(DartParser.SizedboxContext ctx);
	/**
	 * Visit a parse tree produced by {@link DartParser#button}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitButton(DartParser.ButtonContext ctx);
	/**
	 * Visit a parse tree produced by {@link DartParser#center}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCenter(DartParser.CenterContext ctx);
	/**
	 * Visit a parse tree produced by {@link DartParser#icon}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIcon(DartParser.IconContext ctx);
	/**
	 * Visit a parse tree produced by {@link DartParser#widget}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWidget(DartParser.WidgetContext ctx);
	/**
	 * Visit a parse tree produced by {@link DartParser#stateWidget}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStateWidget(DartParser.StateWidgetContext ctx);
}