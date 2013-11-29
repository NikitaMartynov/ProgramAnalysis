package graphs.pg;

import graphs.Block;
import ast.bool.*;
import ast.statement.IfStatement;
import ast.statement.endBoolStatement;


public class IfProgramGraph extends ProgramGraph {
	//constructor
	public IfProgramGraph (IfStatement st, int initialNode, int finalNode) {   
		Block boolBlock = st.getCondition();
		Block notBoolBlock = new NotExpr((BoolExpr)boolBlock);
		Block endBoolBlock = new endBoolStatement("fi1");
		if (edges.isEmpty()== false){
			edges.add(new Edge(initialNode, boolBlock, edges.get(edges.size()-1).qt +1)); 
			edges.add(new Edge(edges.get(edges.size()-1).qs, notBoolBlock, edges.get(edges.size()-1).qt+1));
			endBoolBlock = new endBoolStatement("fi"+initialNode);
		}
		else {
			edges.add( new Edge(1, boolBlock,2)); 
			edges.add(new Edge(edges.get(edges.size()-1).qs, notBoolBlock, edges.get(edges.size()-1).qt+1)); 
		}
		if (GreatestNumUsed < edges.get(edges.size()-1).qt) 
			GreatestNumUsed = edges.get(edges.size()-1).qt;
		
		// graph is created recursively for each branch separate recursion 
		int qsElseBranch = edges.get(edges.size()-1).qt;
		
		ProgramGraphFactory.create(st.getIfBranch (), edges.get(edges.size()-2).qt, finalNode);
		finalNode = edges.get(edges.size()-1).qt; 
		ProgramGraphFactory.create(st.getElseBranch(), qsElseBranch, finalNode);
		boolEndingedges.add(new Edge(GreatestNumUsed+1,endBoolBlock,GreatestNumUsed +1));
	}
}
