package ast.arith;

import java.util.Vector;

import dynamic_analysis.Environment;
import dynamic_analysis.VariableNotDefinedException;

public abstract class ArithExpr {

	public abstract int evaluate(Environment env) throws VariableNotDefinedException;
	public abstract Vector<String> getVariables();
	
}
