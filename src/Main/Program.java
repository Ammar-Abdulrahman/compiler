package Main;

import SymbolTable.SymbolTable;
import antlr.DartLexer;
import antlr.DartParser;
import ast.Visitors.BaseVisitor;
import ast.nodes.Prog;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;

import static org.antlr.v4.runtime.CharStreams.fromFileName;

public class Program {
    public static SymbolTable symbolTable = new SymbolTable() ;
    public static void main(String []args) throws IOException {
        try{
        String source = "Files/required_test.txt";
//        String source = "Files/morhaf.txt";
        CharStream charStream = fromFileName(source);
        DartLexer lexer = new DartLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        DartParser parser = new DartParser(tokens);
        ParseTree ast = parser.prog();
        BaseVisitor baseVisitor = new BaseVisitor();
        Prog prog = (Prog) baseVisitor.visit(ast);
        System.out.println(prog);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
