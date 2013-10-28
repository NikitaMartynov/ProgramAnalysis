package graphs.fg;

import graphs.Block;

import java.util.Vector;

import ast.statement.Statement;

/**
 * For AssignmentStatement, SkipStatement, ArrayAssignStatement,
 * ReadArrayStatement, ReadStatement, WriteStatement
 * 
 * @author zhenli
 * 
 */
public class ElementaryFlowGraph extends FlowGraph {

	public ElementaryFlowGraph(Statement st) {
		super(st);

		Vector<Block> blocks = getBlocks();
		Vector<Integer> labels = getLabels();
		Vector<Integer> fin = getFinal();

		// record new blocks and new labels
		int l = blocks.size() + 1;
		blocks.add(st);
		labels.add(l);
		setInit(l);
		fin.add(l);
	}

}
