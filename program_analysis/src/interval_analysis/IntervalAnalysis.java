package interval_analysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import graphs.pg.Edge;

public class IntervalAnalysis {
	private static int _min = Interval._minusInfinity + 1;
	private static int _max = Interval._plusInfinity - 1;

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

	public static void analyze(int min, int max, Vector<String> freeVariables,
			ArrayList<Edge> edges) throws DivideByZeroException, UnknownErrorException, BoolNeverSatisfiedException {
		// TODO Auto-generated method stub
		setMin(min);
		setMax(max);
		IAWorklist.compute(edges, freeVariables);
		//TransferFunctionFactory.create(edges.get(0));
	}

}
