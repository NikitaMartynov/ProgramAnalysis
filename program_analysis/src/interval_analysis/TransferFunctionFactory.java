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
 * 
 * @author zhenli
 * 
 */
public class TransferFunctionFactory {

	/**
	 * This method updates the solution table by executing the block on the edge
	 * once
	 * 
	 * @param e
	 * @return
	 * @throws UnknownErrorException
	 * @throws DivideByZeroException
	 * @throws BoolNeverSatisfiedException
	 */
	public static HashMap<String, Interval> create(Edge e)
			throws DivideByZeroException, UnknownErrorException {

		Block b = e.getBlock();
		int start = e.getQs() - 1; // minus one because the index here starts
									// from 0
		int end = e.getQt() - 1;

		/**
		 * Firstly, copy all the elements in the start intervals to the end
		 * intervals as the variables that are not in the current block b should
		 * have the same values as their previous values
		 */

		if(IAWorklist.solutionTable.get(start) == null) // result from this branch never being satisfied
			return null;
			
		HashMap<String, Interval> endIntervals = new HashMap<String, Interval>();
		endIntervals.putAll(IAWorklist.solutionTable.get(start));

		// Secondly, update the value if it is necessary
		if (b instanceof AssignStatement) {
			AssignStatement assignSt = (AssignStatement) b;
			Interval i = new ArithInterval(assignSt.getExpression(),
					endIntervals);
			endIntervals.put(assignSt.getName(), i);
		} else if (b instanceof SkipStatement || b instanceof WriteStatement) {
			// do no change
		} else if (b instanceof ArrayAssignStatement) {
			ArrayAssignStatement arrayAssignSt = (ArrayAssignStatement) b;
			String name = arrayAssignSt.getName();
			Interval i = Interval.union(endIntervals.get(name),
					new ArithInterval(arrayAssignSt.getValueExpression(),
							endIntervals));
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
			BoolInterval boolIntervals;
			try {
				boolIntervals = new BoolInterval((BoolExpr) b,
						endIntervals);
				endIntervals.putAll(boolIntervals.getIntervals());
			} catch (BoolNeverSatisfiedException e1) {
				endIntervals = null;
			}
				
		} else {
			throw new UnknownErrorException("Cannot handle class "
					+ b.getClass());
		}

		// solutionTable.set(end, endIntervals);
		return endIntervals;
	}
}
