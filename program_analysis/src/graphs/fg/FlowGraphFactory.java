package graphs.fg;

import ast.statement.*;

public class FlowGraphFactory {

	/**
	 * The factory method for creating flow graph based on the type of input
	 * statement
	 * 
	 * @param st
	 * @return
	 */
	public static FlowGraph create(Statement st) {
		if (st instanceof IfStatement)
			return new IfFlowGraph((IfStatement) st);
		else if (st instanceof SeqStatement)
			return new SeqFlowGraph((SeqStatement) st);
		else if (st instanceof WhileStatement)
			return new WhileFlowGraph((WhileStatement) st);
		else
			return new ElementaryFlowGraph(st);
	}

}
