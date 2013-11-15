package interval_analysis;

import java.util.HashMap;
import ast.bool.*;

public class BooExprHandler {
	BoolExpr b = null;
	int start = -1;
	int end = -1;

	public BooExprHandler(BoolExpr b, int start, int end) {
		// TODO Auto-generated constructor stub
		this.b = b;
		this.start = start;
		this.end = end;
	}

	public void updateIntervals(HashMap<String, Interval> endIntervals) {
		// TODO Auto-generated method stub
		
	}

}
