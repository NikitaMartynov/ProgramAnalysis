package ast.bool;

import dynamic_analysis.Environment;
import dynamic_analysis.VariableNotDefinedException;
import ast.arith.ArithExpr;

public class LessThanExpr extends BoolExpr {

	private ArithExpr expression1;
	private ArithExpr expression2;
	
	public LessThanExpr(ArithExpr expression1, ArithExpr expression2) {
		this.expression1 = expression1;
		this.expression2 = expression2;
	}
	
	@Override
	public boolean evaluate(Environment env) throws VariableNotDefinedException {
		return expression1.evaluate(env) < expression2.evaluate(env);
	}
	@Override
	public String toString() {
		return expression1 + "<" + expression2;
	}
}
