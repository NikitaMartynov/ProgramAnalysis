

import java.util.HashMap;

import interval_analysis.IntervalAnalysis;
import free_variables.FreeVariableGenerator;
import graphs.fg.*;
import graphs.pg.*;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.CommonTokenStream;

import detectionOfSign_analysis.ArithDetectionOfSign;
import detectionOfSign_analysis.DetectionOfSignAnalysis;
import detectionOfSign_analysis.Signs;
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
       // DetectionOfSign ds = new DetectionOfSign();
       // ds.initialize(fvg.getAllVariables());
       // ds.detectSign(ProgramGraph.edges.get(1));;
        
        HashMap<String, Signs> baseSigns = new HashMap<String, Signs>();
		for( String var : fvg.getAllVariables()){
			if(!baseSigns.containsKey(var)) baseSigns.put(var, new Signs());
		}
		DetectionOfSignAnalysis dsa = new DetectionOfSignAnalysis(ProgramGraph.edges.get(0),baseSigns);
        System.out.println(dsa.signsToString());
        
        DetectionOfSignAnalysis dsa2 = new DetectionOfSignAnalysis(ProgramGraph.edges.get(1),dsa.getNewAllVarSigns());
		System.out.println(dsa2.signsToString());
        
        // interval_analysis
        IntervalAnalysis.analyze(0, 4, fvg.getAllVariables(), ProgramGraph.edges);
        IntervalAnalysis.printSolutionTable();
	}
}
