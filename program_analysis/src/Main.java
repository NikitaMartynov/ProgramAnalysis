

import graphes.pg.ProgramGraph;
import graphes.pg.ProgramGraphFactory;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.CommonTokenStream;

import ast.Program;
import parser.TheLangLexer;
import parser.TheLangParser;

/**
 * print the AST built
 * @author zhenli
 *
 */
public class Main {

	public static void main(String args[]) throws Exception {

		if (args.length == 0) {
			System.out.println("Error: No program specified.");
			return;
		}

		// parsing
        TheLangLexer lex = new TheLangLexer(new ANTLRFileStream(args[0]));
        CommonTokenStream tokens = new CommonTokenStream(lex);

        // building ast
        TheLangParser parser = new TheLangParser(tokens);
        Program program = parser.program();
        // print the ast
        System.out.println(program.toString());
        
        ProgramGraph pg = new ProgramGraph(program.getStatement());
        System.out.println(pg.toString());
        
        // then take the program as the input for graph generation
        // such as FlowGraph fg = new FlowGraph(program.getStatement());
        // ...
	}
}
