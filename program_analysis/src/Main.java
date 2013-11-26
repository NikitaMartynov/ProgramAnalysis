

import java.util.HashMap;

import interval_analysis.IntervalAnalysis;
import free_variables.FreeVariableGenerator;
import graphs.fg.*;
import graphs.pg.*;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.CommonTokenStream;

import detectionOfSign_analysis.DSTransFuncs;
import detectionOfSign_analysis.DSWorklist;
import detectionOfSign_analysis.Signs;
import ast.Program;
import parser.TheLangLexer;
import parser.TheLangParser;
import program_slicing.ProgramSlice;

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
        System.out.println(ProgramGraph.GreatestNumUsed);
        
        // then take the program as the input for graph generation
        // such as 
        System.out.println("\nFlow graph:");
        FlowGraph fg = FlowGraphFactory.create(program.getStatement());
        System.out.println(fg.toString());
        // ...
        FreeVariableGenerator.extractVariables();
        System.out.println(FreeVariableGenerator.printVariables());
        
        //Program slicing
        ProgramSlice.getProgramSlice(fg,4);
        ProgramSlice.printProgramSlice();
        
<<<<<<< HEAD
        //Detect signs
        DetectionOfSign ds = new DetectionOfSign();
        ds.initialize(FreeVariableGenerator.getAllVariables());
        ds.detectSign(ProgramGraph.edges.get(1));;
        System.out.println(ds.signsToString());
=======
        //Detect of signs	
		DSWorklist dsw = new DSWorklist(ProgramGraph.edges, fvg.getAllVariables());
		dsw.printSolutionsTable();
>>>>>>> d4720ed41bc995ad144199e8e28df455ce70db4a
        
        // interval_analysis
        IntervalAnalysis.analyze(0, 4, FreeVariableGenerator.getAllVariables(), ProgramGraph.edges);
        IntervalAnalysis.printSolutionTable();
	}
}
