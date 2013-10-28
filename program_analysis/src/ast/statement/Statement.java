package ast.statement;

import graphes.Block;
import dynamic_analysis.Environment;
import dynamic_analysis.VariableNotDefinedException;

public abstract class Statement implements Block{

	public abstract void evaluate(Environment env) throws VariableNotDefinedException;
	
}
