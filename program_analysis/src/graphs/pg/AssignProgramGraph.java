package graphs.pg;

import ast.statement.AssignStatement;

public class AssignProgramGraph extends ProgramGraph {
	public AssignProgramGraph (AssignStatement st, int initialNode, int finalNode) {   //constructor  
		String block = st.toString();
		if (edges.isEmpty()== false)
			edges.add(new Edge(initialNode, block, finalNode > 0 ? finalNode : edges.get(edges.size()-1).qt +1)); 
		else edges.add( new Edge(1, block,2) ); 
	}
}