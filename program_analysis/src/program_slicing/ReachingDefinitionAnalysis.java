package program_slicing;

import free_variables.FreeVariable;
import free_variables.FreeVariableGenerator;
import graphs.fg.FlowGraph;
import graphs.fg.Flow;

import java.util.List;
import java.util.LinkedList;
import java.util.Vector;

public class ReachingDefinitionAnalysis {

	private static List<ReachingDefinitionColln> rdEntry;
	private static List<ReachingDefinitionColln> rdExit;
	private static FlowGraph fg;
	private static Vector<Flow> workList;
	private static final int FIRST_ELEM = 0;
	

	public static void initialize(FlowGraph _fg) {
		fg = _fg;
		workList = new Vector<Flow>();
		addFlowsToWorkList();
		initRDEqn();
		KillandGenAnalysis.intialize();
		KillandGenAnalysis.analyze();
		KillandGenAnalysis.printKillGenAnalysis();
	}

	private static void addFlowsToWorkList() {
		workList.addAll(fg.getFlow());
	}

	private static void initRDEqn() {
		rdEntry = new LinkedList<ReachingDefinitionColln>();
		rdExit = new LinkedList<ReachingDefinitionColln>();
		for (int label : fg.getLabels()) {
			rdEntry.add(new ReachingDefinitionColln());
			rdExit.add(new ReachingDefinitionColln());
			for (FreeVariable fv : FreeVariableGenerator.getFreeVariables()) {
				rdEntry.get(label - 1).add(
						new ReachingDefinition(fv.getVariableName(), 0));
			}
		}
	}

	public static void analyze() {
		while (!workList.isEmpty()) {
			Flow flow = workList.firstElement();
			ReachingDefinitionColln priRDExit = rdExit.get(flow.getPri()-1);
			ReachingDefinitionColln nextRDEntry = rdEntry.get(flow.getNext()-1);
			workList.remove(FIRST_ELEM);
			computeRDExit(flow.getPri());
			Boolean isSubSet = priRDExit.isSubsetOrEquals(nextRDEntry);
			if (!isSubSet) {
				nextRDEntry.union(priRDExit);
				for (Flow nextFlow : fg.getFlow()) {
					if (nextFlow.getPri() == flow.getNext()) {
						workList.add(nextFlow);
					}
				}
			}
		}
	}

	private static void computeRDExit(int label) {

		if(KillandGenAnalysis.getKillRD(label) != null){
	rdExit.get(label - 1).complement(KillandGenAnalysis.getKillRD(label));
		}
		if(KillandGenAnalysis.getGenRD(label) != null){
	rdExit.get(label - 1).union(KillandGenAnalysis.getGenRD(label));
		}

	}

	public static ReachingDefinitionColln getRdExit(int line) {
		return rdExit.get(line - 1);
	}

	public static ReachingDefinitionColln getRdEntry(int line) {
		return rdExit.get(line - 1);
	}
	
	public static void printAnalysis(){
		if(!rdEntry.isEmpty()){
			System.out.println("RDEntry:");
			int label = 0;
			for(ReachingDefinitionColln rdc:rdEntry){
				System.out.println("RDEntry("+ ++label + ") = {" + rdc.toString() + "}");
			}
		}
		if(!rdExit.isEmpty()){
			System.out.println("RDExit:");
			int label = 0;
			for(ReachingDefinitionColln rdc:rdExit){
				System.out.println("RDExit("+ ++label + ") = {" + rdc.toString() + "}");
			}
		}
	}

}
