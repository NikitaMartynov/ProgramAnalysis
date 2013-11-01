package graphs.pg;

import ast.statement.WhileStatement;

public class WhileProgramGraph extends ProgramGraph {
	public WhileProgramGraph (WhileStatement st, int initialNode, int finalNode) {   //constructor 
		String boolBlock = st.getCondition().toString();
		
		if (edges.isEmpty()== false)
			edges.add(new Edge(initialNode, boolBlock, edges.get(edges.size()-1).qt +1)); 
		else 
			edges.add( new Edge(1, boolBlock, 2) ); 
		if (GreatestNumUsed < edges.get(edges.size()-1).qt) 
			GreatestNumUsed = edges.get(edges.size()-1).qt;
		
		// graph is created recursively
		ProgramGraphFactory.create(st.getBody (), edges.get(edges.size()-1).qt, initialNode);
		edges.add(new Edge(initialNode, boolBlock = '!'+ boolBlock, finalNode > 0 ? finalNode : GreatestNumUsed+1 ) );
		
	}
}

