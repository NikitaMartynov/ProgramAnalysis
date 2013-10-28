package ast.arith;

import dynamic_analysis.Environment;
import dynamic_analysis.VariableNotDefinedException;

public class IdExpr extends ArithExpr {

	private String name;
	
	public IdExpr(String name) {
		this.name = name;
	}
	
	@Override
	public int evaluate(Environment env) throws VariableNotDefinedException {
		return env.getVariable(name);
	}
	
	@Override
	public String toString() {
		return name;
	}

}
