package ast.arith;

import dynamic_analysis.Environment;
import dynamic_analysis.VariableNotDefinedException;

public abstract class ArithExpr {

	public abstract int evaluate(Environment env) throws VariableNotDefinedException;
	
}
