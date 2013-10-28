package graphs.pg;

import ast.statement.SeqStatement;


public class SeqProgramGraph extends ProgramGraph {
	public SeqProgramGraph (SeqStatement st, int initialNode, int finalNode) {   //constructor  
		// Create factory 
		ProgramGraphFactory.create(st.getStatement1(), initialNode, 0);
		ProgramGraphFactory.create(st.getStatement2(),edges.get(edges.size()-1).qt, finalNode);
	}
}
