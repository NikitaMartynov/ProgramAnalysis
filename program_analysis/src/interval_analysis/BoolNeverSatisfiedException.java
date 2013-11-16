package interval_analysis;

import java.util.HashMap;

import ast.bool.BoolExpr;

public class BoolNeverSatisfiedException extends Exception {

	String info = "";
	public BoolNeverSatisfiedException(BoolExpr exp,
			HashMap<String, Interval> baseIntervals) {
		// TODO Auto-generated constructor stub
		info = "Boolean condition <" + exp.toString() + "> never satisfied.\n"
				+ "\tVariable -> Intervals:\n";
		for(String key : baseIntervals.keySet()) {
			info += "\t" + key + " -> " + baseIntervals.get(key) + "\n";
		}
	}
	
	public String toString() {
		return info;
	}
}
