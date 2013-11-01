package graphs.pg;

import ast.statement.ReadStatement;


public class ReadProgramGraph extends ProgramGraph {
	public ReadProgramGraph (ReadStatement st, int initialNode, int finalNode) {   //constructor  
		String block = st.toString();
		if (edges.isEmpty()== false)
			edges.add(new Edge(initialNode, block, finalNode > 0 ? finalNode : edges.get(edges.size()-1).qt +1)); 
		else 
			edges.add( new Edge(1, block,2) ); 
		if (GreatestNumUsed < edges.get(edges.size()-1).qt) 
			GreatestNumUsed = edges.get(edges.size()-1).qt;
		
	}
}
