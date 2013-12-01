package ast.statement;

import java.util.Vector;

import graphs.Block;
import dynamic_analysis.Environment;
import dynamic_analysis.VariableNotDefinedException;

public abstract class Statement implements Block{

	public abstract void evaluate(Environment env) throws VariableNotDefinedException;
	public abstract Vector<String> getVariables();
	public abstract Vector<String> getArrays();
	public abstract int printWithLabels(int i);
}
