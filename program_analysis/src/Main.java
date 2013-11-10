

import free_variables.FreeVariableGenerator;
import graphs.fg.*;
import graphs.pg.*;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.CommonTokenStream;

import detectionOfSign_analysis.DetectionOfSign;
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
        
        System.out.println("\nProgram graph: ");
        ProgramGraph pg = new ProgramGraph(program.getStatement());
        System.out.println(pg.toString());
        
        // then take the program as the input for graph generation
        // such as 
        System.out.println("\nFlow graph:");
        FlowGraph fg = FlowGraphFactory.create(program.getStatement());
        System.out.println(fg.toString());
        // ...
        FreeVariableGenerator fvg = new FreeVariableGenerator();
        System.out.println(fvg.toString());
        
        //Detect signs
        DetectionOfSign ds = new DetectionOfSign();
        ds.initialize(fvg.getAllVariables());
        ds.detectSign(ProgramGraph.edges.get(1));;
        System.out.println(ds.signsToString());
	}
}
