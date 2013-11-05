package ast.arith;

import java.util.Vector;

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
	public Vector<String> getVariables() {
				return null;
	}

	@Override
	public String toString() {
		return "" + value;
	}
}
