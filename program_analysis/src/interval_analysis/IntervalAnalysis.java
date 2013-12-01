package interval_analysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ast.arith.NumExpr;
import detectionOfSign_analysis.Signs;
import graphs.pg.Edge;

public class IntervalAnalysis {
	private static int _min = Interval._minusInfinity + 1;
	private static int _max = Interval._plusInfinity - 1;
	private static ArrayList<Edge> violatedEdges;

	public static void setMin(int num) {
		if (num <= Interval._minusInfinity) {
			_min = Interval._minusInfinity + 1;
			System.out
					.println("Warninng: the minimum value you could assign for min is "
							+ _min);
		} else
			_min = num;
	}

	public static void setMax(int num) {
		if (num >= Interval._plusInfinity) {
			_max = Interval._plusInfinity - 1;
			System.out
					.println("Warninng: the maximum value you could assign for max is "
							+ _max);
		} else
			_max = num;
	}

	public static int getMin() {
		return _min;
	}

	public static int getMax() {
		return _max;
	}

	// public static HashMap<String, IntervalElement> intervalElements;

	public static void printSolutionTable() {
		IAWorklist.printSolutionTable();

	}

	public static void printViolatedEdges() {
		System.out.println("Violation found:");
		for (Edge e : violatedEdges) {
			System.out.println("(" + e.getQs() + ", " + e.toString() + ", "
					+ e.getQt() + ")");
		}
	}

	public static ArrayList<Edge> findBoundaryViolations(ArrayList<Edge> pgEdges) {
		violatedEdges = new ArrayList<Edge>();
		for (Edge edge : pgEdges) {
			//System.out.println(edge);
			Vector<String> arrayVars = edge.getBlock().getArrays();
			int start = edge.getQs() - 1;
			for (String s : arrayVars) {
				// match a[x]
				Pattern pattern = Pattern.compile("(.*?)\\[(.*?)\\]");
				Matcher matcher = pattern.matcher(s);
				if (matcher.find()) {
					String a = matcher.group(1);
					String x = matcher.group(2);

					// if x is a number
					Interval interval = null;
					try {
						int i = Integer.parseInt(x);
						interval = new Interval((new NumExpr(
								Integer.parseInt(x))).getValue());
					} catch (Exception e) {
						// if it is a variable
						// if it is a array-array names has a start at end of
						// the array name
						if (x.endsWith("*"))
							x = x.substring(0, x.length() - 1);
						interval = IAWorklist.solutionTable.get(start).get(x);

					}
					if (interval.getLowBoundary() < getMin()
							|| interval.getHighBoundary() > getMax()) {
						violatedEdges.add(edge);
					}

				}
			}
		} // for
		return violatedEdges;
	}

	public static void analyze(int min, int max, Vector<String> freeVariables,
			ArrayList<Edge> edges) throws DivideByZeroException,
			UnknownErrorException, BoolNeverSatisfiedException {
		// TODO Auto-generated method stub
		setMin(min);
		setMax(max);
		IAWorklist.compute(edges, freeVariables);
		// TransferFunctionFactory.create(edges.get(0));
		findBoundaryViolations(edges);
	}

}
