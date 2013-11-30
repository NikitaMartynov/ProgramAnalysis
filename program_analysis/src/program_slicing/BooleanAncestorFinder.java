package program_slicing;

import graphs.pg.Edge;
import graphs.pg.ProgramGraph;

import java.util.ArrayList;

public class BooleanAncestorFinder {
	private static ArrayList<Integer> ancestors; // the boolean ancestor of each
												// label in the graph
	public static void computeBoolAncestor(){
		ArrayList<Edge> boolEdges = ProgramGraph.boolEndingedges;
		initializeAncestor(ProgramGraph.GreatestNumUsed-1);
		for(Edge edge:boolEdges){
			for(int i=edge.getQs(); i <edge.getQt()-1;i++)
			if(ancestors.get(i)<edge.getQs()){
				ancestors.set(i, edge.getQs());
			}
		}
	}
	private static void initializeAncestor(int numOfLines){
		ancestors = new ArrayList<Integer>();
		for(int i=0; i< numOfLines;++i){
			ancestors.add(0);
		}
	}

	public static ArrayList<Integer> getAncestors() {
		return ancestors;

	}

}
