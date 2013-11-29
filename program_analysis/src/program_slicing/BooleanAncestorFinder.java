package program_slicing;

import graphs.Block;
import graphs.fg.Flow;
import graphs.fg.FlowGraph;

import java.util.Vector;

import ast.bool.BoolExpr;

public class BooleanAncestorFinder {
	private static Vector<Integer> ancestors; // the boolean ancestor of each
												// label in the graph

	public static void computeAncestorBool(Vector<Flow> flows,
			Vector<Integer> labels) {
		ancestors = new Vector<Integer>();
		int counter = -1;
		int ancestorBool = 0;
		for (Block b : FlowGraph.getBlocks()) {
			++counter;
			if (b instanceof BoolExpr) {
				// if a statement has a boolean condition we update boolean
				// ancestor
				ancestors.add(ancestorBool);
				ancestorBool = labels.get(counter);
			} else if (hasMultipleEntry(counter + 1, flows)) {
				// statement following an if then else statement has multiple
				// entries
				// we are out of an if then else, so updating boolean ancestor
				ancestorBool = ancestors.get(ancestorBool - 1);
				ancestors.add(ancestorBool);
			} else if (hasMutlipleExits(ancestorBool, counter + 1, flows)) {
				// while loop has multiple exits one to within the loop and
				// other outside the loop.
				// When we come out of a while loop, we modify the boolean ancestor
				ancestorBool = ancestors.get(ancestorBool - 1);
				ancestors.add(ancestorBool);
			} else {
				ancestors.add(ancestorBool);
			}
		}
		System.out.println(ancestors);
	}

	private static boolean hasMutlipleExits(int startLabel, int endLabel,
			Vector<Flow> flows) {
		int exitCounter = 0;
		boolean hasFlow=false;
		for(Flow flow : flows){
			if (flow.getPri() == startLabel) {
				++exitCounter;
				if(flow.getNext()==endLabel){
					hasFlow=true;
				}
			}
		}
			if ((exitCounter > 1) && hasFlow) {
				return true;
			} else {
				return false;
			}
	}

	private static boolean hasMultipleEntry(int label, Vector<Flow> flows) {
		int entryCounter = 0;
		for (Flow flow : flows) {
			if (flow.getNext() == label) {
				++entryCounter;
			}
		}
		if (entryCounter > 1) {
			return true;
		} else {
			return false;
		}
	}
	public static void computeAncestors(FlowGraph fg){
		Vector<Flow> flows = fg.getFlow();
		initializeAncestor(FlowGraph.getBlocks().size());
		for(Flow flow:flows){
			/*if(flow.getNext() < flow.getPri()){
				System.out.println("While Anesor" + flow.toString());
				updateWhileAncestor(flow.getNext(),flow.getPri());
			}
			else*/
				if((flow.getNext()-flow.getPri())>1){
				System.out.println("if Anesor" + flow.toString());
			//	updateIfAncestor(flow.getPri(),flow.getNext());
			}
		}
		System.out.println(ancestors);
	}
	private static void initializeAncestor(int numOfLines){
		ancestors = new Vector<Integer>();
		for(int i=0; i< numOfLines;++i){
			ancestors.add(0);
		}
	}
	private static void updateWhileAncestor(int ancestorLabel, int endLabel){
		
		for(int i=ancestorLabel;i < endLabel-1;i++){
			if(ancestors.get(i) < ancestorLabel){
				System.out.println("i"+ i);
				System.out.println("ancestorLabel"+ ancestorLabel);
				ancestors.set(i, ancestorLabel);
			}
		}	
	}
	private static void updateIfAncestor(int startLabel, int endLabel){
		int ancestorLabel = 0;

		if(FlowGraph.getBlocks().get(startLabel) instanceof BoolExpr){
		ancestorLabel = startLabel;
		}
		else{
			ancestorLabel = ancestors.get(startLabel-1);
		}
		for(int i=startLabel;i<endLabel-1;i++){
			if(ancestors.get(i) < ancestorLabel){
			ancestors.set(i, ancestorLabel);
			}
			
		}
			
		}

	public static Vector<Integer> getAncestors() {
		return ancestors;

	}

}
