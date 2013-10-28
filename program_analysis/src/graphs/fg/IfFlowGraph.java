package graphs.fg;

import graphs.Block;

import java.util.Vector;

import ast.statement.IfStatement;

public class IfFlowGraph extends FlowGraph {

	// constructor
	public IfFlowGraph(IfStatement st) {
		super(st);

		Vector<Block> blocks = getBlocks();
		Vector<Integer> labels = getLabels();
		Vector<Flow> flow = getFlow();
		Vector<Integer> fin = getFinal();

		// record new blocks and new labels
		int l = blocks.size() + 1;
		Block boolBlock = st.getCondition();
		blocks.add(boolBlock);
		labels.add(l);

		// graph is created recursively
		FlowGraph s1 = FlowGraphFactory.create(st.getIfBranch());
		FlowGraph s2 = FlowGraphFactory.create(st.getElseBranch());
		// set the ancestor labels in the blocks in the graph as l.
		s1.setAncestorBoolLabel(l);
		s2.setAncestorBoolLabel(l);

		// follow the rules in the table 1.2 and 1.3
		labels.addAll(s1.getLabels());
		labels.addAll(s2.getLabels());
		setInit(l);
		fin.addAll(s1.getFinal());
		fin.addAll(s2.getFinal());
		
		
		flow.add(new Flow(l, s1.getInit()));
		flow.addAll(s1.getFlow());
		flow.add(new Flow(l, s2.getInit()));
		flow.addAll(s2.getFlow());
	}

}
