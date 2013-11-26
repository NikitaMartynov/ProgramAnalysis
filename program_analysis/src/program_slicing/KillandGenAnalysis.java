package program_slicing;

import free_variables.*;
import graphs.fg.FlowGraph;
import graphs.Block;
import ast.statement.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class KillandGenAnalysis {
	private static Vector<Block> blocks;
	private static List<ReachingDefinitionColln> killRD;
	private static List<ReachingDefinitionColln> genRD;

	public static void intialize() {
		killRD = new LinkedList<ReachingDefinitionColln>();
		genRD = new LinkedList<ReachingDefinitionColln>();
		blocks = FlowGraph.getBlocks();

	}

	public static void analyze() {
		int label = 0;
		for (Block b : blocks) {
			++label;
			if (b instanceof ReadStatement) {
				killGenForReadStmt(label);
			} else if (b instanceof ArrayAssignStatement){
				killGenForAssignStmt(label);
			} else if (b instanceof AssignStatement) {
				killGenForAssignStmt(label);
			} 
			else{
				ReachingDefinitionColln rdc = null;
				killRD.add(rdc);
				genRD.add(rdc);
			}

		}
	}

	private static void killGenForReadStmt(int label) {
		Vector<FreeVariable> variablesInLine = FreeVariableGenerator
				.getFreeVariablesinLine(label);
		
		for (FreeVariable fv : variablesInLine) {
			createKillRD(fv.getVariableName(),label);
			createGenRD(fv.getVariableName(),label);
		}
	}

	private static void killGenForAssignStmt(int label) {
		Vector<FreeVariable> variablesInLine = FreeVariableGenerator
				.getFreeVariablesinLine(label);
		for (FreeVariable fv : variablesInLine) {
			if (fv.getVariablePosition() == VariablePosition.left) {
				createKillRD(fv.getVariableName(),label);
				createGenRD(fv.getVariableName(),label);						
			}
		}
	}
	
	private static void createKillRD(String variableName,int label){
		ReachingDefinitionColln krdc = new ReachingDefinitionColln();
		krdc.add(new ReachingDefinition(variableName, 0));				
		Vector<Integer> lineNumbers = FreeVariableGenerator.getAssignmentLinesOfFreeVariables(variableName);
		for(int i:lineNumbers){
			krdc.add(new ReachingDefinition(variableName,i));
		}
		if(killRD.size() > (label-1)){
		killRD.get(label-1).union(krdc);
		}
		else{
			killRD.add(krdc);
		}
	}
	
	private static void createGenRD(String variableName,int label){
		ReachingDefinitionColln grdc = new ReachingDefinitionColln();
		grdc.add(new ReachingDefinition(variableName,label));
		genRD.add(grdc);
	}
	

	
	public static ReachingDefinitionColln getKillRD(int label){
		return killRD.get(label-1);
	}
	public static ReachingDefinitionColln getGenRD(int label){
		return genRD.get(label-1);
	}
	public static void printKillGenAnalysis(){
		if(!killRD.isEmpty()){
			System.out.println("killRD:");
			int label = 0;
			for(ReachingDefinitionColln rdc:killRD){
				if(rdc == null){
				System.out.println("killRD("+ ++label + ") = {}");	
				}
				else{
				System.out.println("killRD("+ ++label + ") = {" + rdc.toString() + "}");
				}
			}
		}
		if(!genRD.isEmpty()){
			System.out.println("genRD:");
			int label = 0;
			for(ReachingDefinitionColln rdc:genRD){
				if(rdc == null){
					System.out.println("genRD("+ ++label + ") = {}");	
					}
					else{
				System.out.println("genRD("+ ++label + ") = {" + rdc.toString() + "}");
					}
			}
		}
	}

}
