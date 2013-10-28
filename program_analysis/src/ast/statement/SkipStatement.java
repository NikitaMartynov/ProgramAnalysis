package ast.statement;

import dynamic_analysis.Environment;
import dynamic_analysis.VariableNotDefinedException;

public class SkipStatement extends Statement {

	public SkipStatement() { }
	
	@Override
	public void evaluate(Environment env) throws VariableNotDefinedException {
		return;
	}
	
	@Override
	public String toString() {
		return "skip;";
	}
}
