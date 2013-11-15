package interval_analysis;

import java.util.HashMap;
import java.util.Vector;

import graphs.Block;
import graphs.pg.Edge;
import graphs.pg.ProgramGraph;
import ast.bool.BoolExpr;
import ast.statement.*;

/**
 * Mapping function f
 * @author zhenli
 *
 */
public class TransferFunctionFactory {

	public static Vector<HashMap<String, Interval>> solutionTable;

	public static void initSolutionTable(Vector<String> freeVariables) {
		if (solutionTable == null)
			solutionTable = new Vector<HashMap<String, Interval>>(
					ProgramGraph.GreatestNumUsed + 1);
		else
			solutionTable.clear();

		// pre-reserve a room for each HashMap
		for (int i = 0; i < solutionTable.capacity(); i++) {
			solutionTable.add(null);
		}
		// TODO:
		// init the first line (index: 0, label: 1) in the solution table with
		// the default values for all the variables
		HashMap<String, Interval> defaultIntervals = new HashMap<String, Interval>();
		for(String variableName: freeVariables) {
			defaultIntervals.put(variableName, new Interval(0));
		}
		solutionTable.set(0, defaultIntervals);
	}

	/**
	 * This method updates the solution table by executing the block on the edge
	 * once
	 * 
	 * @param e
	 * @throws UnknownErrorException 
	 * @throws DivideByZeroException 
	 */
	public static void create(Edge e) throws DivideByZeroException, UnknownErrorException {

		Block b = e.getBlock();
		int start = e.getQs() - 1; // minus one because the index here starts
									// from 0
		int end = e.getQt() - 1;

		/**
		 * Firstly, copy all the elements in the start intervals to the end
		 * intervals as the variables that are not in the current block b should
		 * have the same values as their previous values
		 */

		HashMap<String, Interval> endIntervals = new HashMap<String, Interval>();
		endIntervals.putAll(solutionTable.get(start));

		// Secondly, update the value if it is necessary
		if (b instanceof AssignStatement) {
			AssignStatement assignSt = (AssignStatement) b;
			Interval i = new ArithInterval(assignSt.getExpression(), endIntervals);
			endIntervals.put(assignSt.getName(), i);
		} else if (b instanceof SkipStatement || b instanceof WriteStatement) {
			// do no change
		} else if (b instanceof ArrayAssignStatement) {
			ArrayAssignStatement arrayAssignSt = (ArrayAssignStatement) b;
			String name = arrayAssignSt.getName();
			Interval i = Interval.union(endIntervals.get(name),
					new ArithInterval(arrayAssignSt.getValueExpression(), endIntervals));
			endIntervals.put(name, i);
		} else if (b instanceof ReadStatement) {
			ReadStatement readSt = (ReadStatement) b;
			Interval i = new Interval();
			endIntervals.put(readSt.getName(), i);
		} else if (b instanceof ReadArrayStatement) {
			ReadArrayStatement readSt = (ReadArrayStatement) b;
			Interval i = new Interval();
			endIntervals.put(readSt.getName(), i);
		} else if (b instanceof BoolExpr) {
			// TODO
			new BooExprHandler((BoolExpr)b, start, end).updateIntervals(endIntervals);
		}

		solutionTable.set(end, endIntervals);
	}
}
