package ast.arith;

import dynamic_analysis.Environment;
import dynamic_analysis.VariableNotDefinedException;

public class DivExpr extends ArithExpr {

	private ArithExpr expression1;
	private ArithExpr expression2;
	
	public DivExpr(ArithExpr expression1, ArithExpr expression2) {
		this.expression1 = expression1;
		this.expression2 = expression2;
	}
	
	@Override
	public int evaluate(Environment env) throws VariableNotDefinedException, ArithmeticException {
		
		return expression1.evaluate(env) / expression2.evaluate(env);
	}
	
	@Override
	public String toString() {
		return expression1.toString() + "/" + expression2.toString();
	}

}
