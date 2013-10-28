package ast.arith;

import dynamic_analysis.Environment;
import dynamic_analysis.VariableNotDefinedException;

public class UnMinExpr extends ArithExpr {

	private ArithExpr expression;
	
	public UnMinExpr(ArithExpr expression) {
		this.expression = expression;
	}
	
	@Override
	public int evaluate(Environment env) throws VariableNotDefinedException {
		return -expression.evaluate(env);
	}
	
	@Override
	public String toString() {
		return "-" + expression.toString();
	}

}
