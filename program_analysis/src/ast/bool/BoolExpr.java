package ast.bool;

import graphs.Block;
import dynamic_analysis.Environment;
import dynamic_analysis.VariableNotDefinedException;

public abstract class BoolExpr implements Block{

	public abstract boolean evaluate(Environment env) throws VariableNotDefinedException;
	
}
