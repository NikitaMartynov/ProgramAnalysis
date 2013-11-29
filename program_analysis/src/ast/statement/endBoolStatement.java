package ast.statement;


import java.util.Vector;

import dynamic_analysis.Environment;
import dynamic_analysis.VariableNotDefinedException;

public class endBoolStatement extends Statement {
	String ending;

	public endBoolStatement(String _ending) { ending=_ending; }
	
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
		return null;	
	}
	@Override
	public String toString() {
		return ending;
	}
}

