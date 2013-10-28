package ast.bool;

import dynamic_analysis.Environment;
import dynamic_analysis.VariableNotDefinedException;

public class NotExpr extends BoolExpr {

	private BoolExpr expression;
	
	public NotExpr(BoolExpr expression) {
		this.expression = expression;
	}
	
	@Override
	public boolean evaluate(Environment env) throws VariableNotDefinedException {
		return !expression.evaluate(env);
	}
	@Override
	public String toString() {
		return  "!" + expression;
	}
}
