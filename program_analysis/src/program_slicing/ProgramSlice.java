package program_slicing;

import free_variables.*;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import graphs.Block;
import graphs.fg.FlowGraph;

public class ProgramSlice {
	private static int pointOfInterest;
	private static List<Integer> slice;
	private static List<Integer> workList;

	private static void initialize(FlowGraph fg, int _pointOfInterest) {
		workList = new LinkedList<Integer>();
		slice = new LinkedList<Integer>();
		pointOfInterest = _pointOfInterest;
		ReachingDefinitionAnalysis.initialize(fg);
		ReachingDefinitionAnalysis.analyze();
		ReachingDefinitionAnalysis.printAnalysis();
		FlowGraph.computeAncestorBool(fg.getFlow(), fg.getLabels());
	}

	public static List<Integer> getudchain(FreeVariable fv, int pointOfInterest) {
		List<Integer> udChain = new LinkedList<Integer>();
		ReachingDefinitionColln rdEntry = new ReachingDefinitionColln();
		rdEntry = ReachingDefinitionAnalysis.getRdEntry(pointOfInterest);
		for (ReachingDefinition rd : rdEntry) {
			if (rd.getVariableName().compareTo(fv.getVariableName()) == 0) {
				udChain.add(rd.getLineNumber());
			}
		}
		return udChain;
	}

	public static List<Integer> getProgramSlice(FlowGraph fg,
			int pointOfInterest) {
		initialize(fg, pointOfInterest);
		int currentLineOfInterest;
		Vector<FreeVariable> variablesInLine = new Vector<>();
		List<Integer> udChain = new LinkedList<>();
		if (pointOfInterest <= 0) {
			return null;
		}
		workList.add(pointOfInterest);
		while (!workList.isEmpty()) {
			currentLineOfInterest = workList.get(0);
			workList.remove(0);
			slice.add(currentLineOfInterest);
			variablesInLine = FreeVariableGenerator
					.getFreeVariablesinLine(currentLineOfInterest);
			if (!variablesInLine.isEmpty()) {
				for (FreeVariable fv : variablesInLine) {
					if (fv.getVariablePosition() != VariablePosition.left) {
						udChain = getudchain(fv, currentLineOfInterest);
						for (int label : udChain) {
							if (!slice.contains(label)
									&& !workList.contains(label)) {
								workList.add(label);
							}
						}
					}

				}
				int booleanAncestor = FlowGraph.getAncestors().get(
						currentLineOfInterest - 1);
				if (!workList.contains(booleanAncestor)
						&& !slice.contains(booleanAncestor)
						&& booleanAncestor != 0) {
					workList.add(booleanAncestor);
				}
			}
		}
		Collections.sort(slice);
		return slice;
	}

	public int getPointOfInterest() {
		return pointOfInterest;
	}

	public static void printProgramSlice() {
		Vector<Block> blocks = FlowGraph.getBlocks();
		if (!slice.isEmpty()) {
			System.out.println("Program Slice:");
			for (int i = 0; i < slice.size(); i++) {
				System.out.println(slice.get(i).toString() + " : "
						+ blocks.get(slice.get(i) - 1).toString());
			}
		}
	}

}
