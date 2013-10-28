package ast.arith;

import dynamic_analysis.Environment;
import dynamic_analysis.VariableNotDefinedException;

public class NumExpr extends ArithExpr {

	private int value;
	
	public NumExpr(int value) {
		this.value = value;
	}
	
	@Override
	public int evaluate(Environment env) throws VariableNotDefinedException {
		return value;
	}

	@Override
	public String toString() {
		return "" + value;
	}
}
