package graphs.pg;

import graphs.Block;
import ast.bool.BoolExpr;
import ast.bool.NotExpr;
import ast.statement.WhileStatement;
import ast.statement.endBoolStatement;

public class WhileProgramGraph extends ProgramGraph {
	public WhileProgramGraph (WhileStatement st, int initialNode, int finalNode) {   //constructor 
		Block boolBlock = st.getCondition();
		Block notBoolBlock = new NotExpr((BoolExpr)boolBlock);
		Block endBoolBlock = new endBoolStatement("od");
		int boolBlockStarting = 1;
		if (edges.isEmpty()== false){
			edges.add(new Edge(initialNode, boolBlock, edges.get(edges.size()-1).qt +1));
			boolBlockStarting = initialNode;
		}
		else 
			edges.add( new Edge(1, boolBlock, 2) ); 
		if (GreatestNumUsed < edges.get(edges.size()-1).qt) 
			GreatestNumUsed = edges.get(edges.size()-1).qt;
		
		// graph is created recursively
		ProgramGraphFactory.create(st.getBody (), edges.get(edges.size()-1).qt, initialNode);
		edges.add(new Edge(initialNode, boolBlock = notBoolBlock, finalNode > 0 ? finalNode : GreatestNumUsed+1 ) );
		GreatestNumUsed = GreatestNumUsed+1;
		boolEndingedges.add(new Edge(boolBlockStarting,endBoolBlock,GreatestNumUsed));
	}
}

