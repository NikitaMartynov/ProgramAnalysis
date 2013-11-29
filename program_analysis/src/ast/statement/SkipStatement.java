package ast.statement;

import java.util.Vector;

import dynamic_analysis.Environment;
import dynamic_analysis.VariableNotDefinedException;

public class SkipStatement extends Statement {

	public SkipStatement() { }
	
	@Override
	public void evaluate(Environment env) throws VariableNotDefinedException {
		return;
	}
	@Override
	public Vector<String> getVariables() {		
				return null;
	}
	@Override
	public Vector<String> getArrays() {
		Vector<String> vars = new Vector<String>();
		return vars;	
	}
	@Override
	public String toString() {
		return "skip;";
	}
}
