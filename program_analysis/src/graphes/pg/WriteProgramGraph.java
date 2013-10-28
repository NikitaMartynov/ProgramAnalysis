package graphes.pg;

import ast.statement.WriteStatement;

public class WriteProgramGraph extends ProgramGraph {
	public WriteProgramGraph (WriteStatement st, int initialNode, int finalNode) {   //constructor  
		String block = st.getExpression().toString();
		if (edges.isEmpty()== false)
			edges.add(new Edge(initialNode, block, finalNode > 0 ? finalNode : edges.get(edges.size()-1).qt +1)); 
		else edges.add( new Edge(1, block,2) ); 
	}
}
