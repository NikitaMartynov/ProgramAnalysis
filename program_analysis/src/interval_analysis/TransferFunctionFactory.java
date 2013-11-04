package interval_analysis;

import graphs.Block;
import ast.statement.*;

public class TransferFunctionFactory {

	public Interval create(Block b) {
		Interval i = null;
		if(b instanceof AssignStatement) {
			i = new ArithInterval(((AssignStatement) b).getExpression());
		}
		else if(b instanceof SkipStatement) {
			i = null;
		}
		else if(b instanceof ArrayAssignStatement) {
			String name = ((ArrayAssignStatement) b).getName();
			i = Interval.union(
					IntervalAnalysis.intervalElements.get(name).getInterval(),
					new ArithInterval(((ArrayAssignStatement) b).getValueExpression())
					);
		}
		return i;
	}
	
}
