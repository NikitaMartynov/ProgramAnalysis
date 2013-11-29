package interval_analysis;

import graphs.pg.Edge;
import graphs.pg.ProgramGraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class IAWorklist {

	protected static Vector<HashMap<String, Interval>> solutionTable;

	private static void initSolutionTable(Vector<String> freeVariables) {
		
		solutionTable = new Vector<HashMap<String, Interval>>(
					ProgramGraph.GreatestNumUsed );
		
		
		//System.out.println(ProgramGraph.GreatestNumUsed);

		// pre-reserve a room for each HashMap
		for (int i = 0; i < solutionTable.capacity(); i++) {
			solutionTable.add(null);
		}
		//
		// init the first line (index: 0, label: 1) in the solution table with
		// the default values for all the variables
		HashMap<String, Interval> defaultIntervals = new HashMap<String, Interval>();
		for (String variableName : freeVariables) {
			defaultIntervals.put(variableName, new Interval(0));
		}
		solutionTable.set(0, defaultIntervals);
	}

	public static void compute(ArrayList<Edge> pgEdges, Vector<String> freeVars)
			throws DivideByZeroException, UnknownErrorException,
			BoolNeverSatisfiedException {

		ArrayList<Edge> workList = new ArrayList<Edge>(pgEdges);
		initSolutionTable(freeVars);

		int loopCounter = 0;
		while (!workList.isEmpty()) {
			loopCounter++;
			Edge currentEdge = workList.get(0);
			int endNodeIndex = currentEdge.getQt() - 1;
			workList.remove(0);
			HashMap<String, Interval> solutionEntry = TransferFunctionFactory
					.create(currentEdge);

			if (!isParam1SubsetOfParam2(solutionEntry,
					solutionTable.get(endNodeIndex))) {
				solutionTable.set(endNodeIndex,
						union(solutionEntry, solutionTable.get(endNodeIndex)));
				for (Edge edge : pgEdges) {
					if (currentEdge.getQt() == edge.getQs()) {
						workList.add(edge);
					}
				}
			}
			 //System.out.println("The solution table in loop " + loopCounter);
			 //printSolutionTable();
		}
	}

	public static void printSolutionTable() {
		System.out.println("Interval analysis solution table:");
		int i = 1;
		for (HashMap<String, Interval> solu : solutionTable) {

			System.out.print(i++ + ": ");
			if (solu != null) {
				for (String key : solu.keySet()) {
					System.out.print(key + solu.get(key) + " ");
				}
			} else
				System.out.print("null");
			System.out.println();

		}
	}

	private static boolean isParam1SubsetOfParam2(
			HashMap<String, Interval> solution1,
			HashMap<String, Interval> solution2) throws UnknownErrorException {
		if ((solution1 == null))
			return true;
		/*
		 * throw new UnknownErrorException("Error: the solutions is null! in " +
		 * IAWorklist.class);
		 */

		if ((solution2 == null)) // has never be inited
			return false;
		else if (solution1.size() != solution2.size())
			throw new UnknownErrorException(
					"Error: the solutions size is different! in "
							+ IAWorklist.class);

		for (Map.Entry<String, Interval> entry : solution1.entrySet())
			if (!(entry.getValue().isSubsetOf(solution2.get(entry.getKey()))))
				return false;
		return true;
	}

	private static HashMap<String, Interval> union(
			HashMap<String, Interval> solution1,
			HashMap<String, Interval> solution2) throws UnknownErrorException {

		HashMap<String, Interval> ret = new HashMap<String, Interval>();
		if ((solution1 == null) && (solution2 == null))
			return null;
		else if ((solution1 == null) || (solution2 == null))
			return solution1 == null ? solution2 : solution1;

		if (solution1.size() != solution2.size())
			throw new UnknownErrorException(
					"Error: the solutions size is different! in "
							+ IAWorklist.class);

		for (Map.Entry<String, Interval> entry : solution1.entrySet())
			ret.put(entry.getKey(),
					Interval.union(entry.getValue(),
							solution2.get(entry.getKey())));

		return ret;
	}

}
