package graphs.pg;

import ast.statement.IfStatement;

public class IfProgramGraph extends ProgramGraph {
	//constructor
	public IfProgramGraph (IfStatement st, int initialNode, int finalNode) {   
		String boolBlock = st.getCondition().toString();
		if (edges.isEmpty()== false){
			edges.add(new Edge(initialNode, boolBlock, edges.get(edges.size()-1).qt +1)); 
			edges.add(new Edge(edges.get(edges.size()-1).qs, '!'+ boolBlock, edges.get(edges.size()-1).qt+1)); 
		}
		else {
			edges.add( new Edge(1, boolBlock,2)); 
			edges.add(new Edge(edges.get(edges.size()-1).qs, '!'+ boolBlock, edges.get(edges.size()-1).qt+1)); 
		}
		if (GreatestNumUsed < edges.get(edges.size()-1).qt) 
			GreatestNumUsed = edges.get(edges.size()-1).qt;
		
		// graph is created recursively for each branch separate recursion 
		int qsElseBranch = edges.get(edges.size()-1).qt;
		
		ProgramGraphFactory.create(st.getIfBranch (), edges.get(edges.size()-2).qt, finalNode);
		finalNode = edges.get(edges.size()-1).qt; 
		ProgramGraphFactory.create(st.getElseBranch(), qsElseBranch, finalNode);
	}
}
