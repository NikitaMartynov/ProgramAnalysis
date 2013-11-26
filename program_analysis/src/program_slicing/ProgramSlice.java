package program_slicing;
import free_variables.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import graphs.Block;
import graphs.fg.FlowGraph;

public class ProgramSlice {
	private static int pointOfInterest;
	private static List<Integer> slice;
	private static List<Integer> workList;
	
	private static void initialize(FlowGraph fg,int _pointOfInterest){
		workList = new LinkedList<Integer>();
		slice = new LinkedList<Integer>();
		pointOfInterest = _pointOfInterest;
		ReachingDefinitionAnalysis.initialize(fg);
		ReachingDefinitionAnalysis.analyze();
		ReachingDefinitionAnalysis.printAnalysis();
	}
	
	public static List<Integer> getudchain(FreeVariable fv, int pointOfInterest ){
		List<Integer> udChain = new LinkedList<Integer>();
		ReachingDefinitionColln rdEntry = new ReachingDefinitionColln();
		rdEntry = ReachingDefinitionAnalysis.getRdEntry(pointOfInterest);
		for(ReachingDefinition rd: rdEntry){
			if(rd.getVariableName() == fv.getVariableName()){
				udChain.add(rd.getLineNumber());
			}
		}
		return udChain;
	}
	
	public static List<Integer> getProgramSlice(FlowGraph fg,int pointOfInterest){
		initialize(fg,pointOfInterest);
		int currentLineOfInterest;
		Vector<FreeVariable> variablesInLine = new Vector<>();
		List<Integer> udChain = new LinkedList<>();
		if (pointOfInterest <=0){
		   return null;	
		}
		workList.add(pointOfInterest);
		while(!workList.isEmpty()){
			currentLineOfInterest = workList.get(0);
			workList.remove(0);
			slice.add(currentLineOfInterest);
			variablesInLine = FreeVariableGenerator.getFreeVariablesinLine(currentLineOfInterest);
			if(!variablesInLine.isEmpty()){
				for(FreeVariable fv:variablesInLine){
					if(fv.getVariablePosition() != VariablePosition.left){
						udChain = getudchain(fv,currentLineOfInterest);
						for(int label:udChain){
						if(!slice.contains(label)){
							slice.add(label);
						}
					}
				}
			
			}
		/*	if(currentLineOfInterest.hasBoolAncestor() && !slice.contains(currentLineOfInterest)){
					slice.add(booleanAncestor);
			}*/
			}
		}
		return slice;
	}
	
	public int getPointOfInterest() {
		return pointOfInterest;
	}

	public static void printProgramSlice(){
		Vector<Block> blocks = FlowGraph.getBlocks();
		if(!slice.isEmpty()){
			System.out.println("Program Slice:");
		for(int i=0; i < slice.size() ; i++){
			System.out.println(slice.get(0).toString() + " : " + blocks.get(slice.get(0)).toString());
		}
		}
	}
	

}
