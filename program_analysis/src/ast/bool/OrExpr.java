package ast.bool;

import java.util.Vector;

import dynamic_analysis.Environment;
import dynamic_analysis.VariableNotDefinedException;

public class OrExpr extends BoolExpr {
	
	private BoolExpr expression1;
	private BoolExpr expression2;
	
	public OrExpr(BoolExpr expression1, BoolExpr expression2) {
		this.expression1 = expression1;
		this.expression2 = expression2;
	}
	
	@Override
	public boolean evaluate(Environment env) throws VariableNotDefinedException {
		return expression1.evaluate(env) || expression2.evaluate(env);
	}
	@Override
	public Vector<String> getVariables() {
		Vector<String> vars = new Vector<String>();
		try {
			vars.addAll(expression1.getVariables());
		}
		catch(Exception e){
		}
		try{
			vars.addAll(expression2.getVariables());
		} catch (Exception e) {
		}
			if (!vars.isEmpty())
				return vars;
			else
				return null;
	}
	@Override
	public String toString() {
		return expression1 + "|" + expression2;
	}

	public BoolExpr getExpression1() {
		return expression1;
	}

	public BoolExpr getExpression2() {
		return expression2;
	}
	
	
}
