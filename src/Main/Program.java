package Main;

import ErrorHandling.SemanticError;
import ErrorHandling.SyntaxError;
import SymbolTable.SymbolTable;
import antlr.DartLexer;
import antlr.DartParser;
import ast.Visitors.BaseVisitor;
import ast.nodes.Prog;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.FileWriter;
import java.io.IOException;

import static org.antlr.v4.runtime.CharStreams.fromFileName;

public class Program {
    public static SymbolTable symbolTable = new SymbolTable();

    public static void main(String[] args) throws IOException {
        try {
            SyntaxError syntaxError = new SyntaxError();
//            String source = "Files/required_test.txt";
//            String source = "Files/required_test_errors.txt";
            String source = "Files/test.txt";
            CharStream charStream = fromFileName(source);
            DartLexer lexer = new DartLexer(charStream);
            lexer.removeErrorListeners();
            lexer.addErrorListener(syntaxError.INSTANCE);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            DartParser parser = new DartParser(tokens);
            parser.removeErrorListeners();
            parser.addErrorListener(syntaxError.INSTANCE);
            ParseTree ast = parser.prog();
            BaseVisitor baseVisitor = new BaseVisitor();
            //AST
            Prog prog = (Prog) baseVisitor.visit(ast);
            FileWriter fileWriter = new FileWriter("Files/out.txt");
            fileWriter.write(String.valueOf(prog));
//            System.out.println(prog);
            //Semantic Check
            SemanticError semanticError = baseVisitor.semanticError;
            FileWriter object = new FileWriter("Files/SemanticError.txt");
            for (int i = 0; i < semanticError.messages.size() ; i++) {
                object.write(semanticError.messages.get(i));
            }
            object.close();
            if (!semanticError.messages.isEmpty()){
                throw new RuntimeException(semanticError.messages.get(0));
            }
            //Symbol Table
            prog.generateCode();
            symbolTable.print();
            SymbolTable symbolTable =SymbolTable.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
