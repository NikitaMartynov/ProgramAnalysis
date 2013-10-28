package graphs.fg;


import java.util.Vector;
import ast.statement.*;

public class SeqFlowGraph extends FlowGraph {

	public SeqFlowGraph(SeqStatement st) {
		super(st);
		
		Vector<Integer> labels = getLabels();
		Vector<Flow> flow = getFlow();
		Vector<Integer> fin = getFinal();

		// graph is created recursively
		FlowGraph s1 = FlowGraphFactory.create(st.getStatement1());
		FlowGraph s2 = FlowGraphFactory.create(st.getStatement2());

		// follow the rules in the table 1.2 and 1.3
		labels.addAll(s1.getLabels());
		labels.addAll(s2.getLabels());
		setInit(s1.getInit());
		fin.addAll(s2.getFinal());
		
		flow.addAll(s1.getFlow());
		Vector<Integer> s1Fin = s1.getFinal();
		int s2Init = s2.getInit();
		for(int l : s1Fin) {
			flow.add(new Flow(l, s2Init));
		}
		flow.addAll(s2.getFlow());
	}

}
