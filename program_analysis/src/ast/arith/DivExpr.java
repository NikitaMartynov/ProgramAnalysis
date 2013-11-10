package ast.arith;

import java.util.Vector;

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
	public int evaluate(Environment env) throws VariableNotDefinedException,
			ArithmeticException {

		return expression1.evaluate(env) / expression2.evaluate(env);
	}

public Vector<String> getVariables(){
		
		Vector<String> vars = new Vector<String>();
		try {
			vars.addAll(expression1.getVariables());
		} catch (Exception e) {
		}
		try{
			vars.addAll(expression2.getVariables());
		}
		catch (Exception e) {
		}
			if (!vars.isEmpty())
				return vars;
			else
				return null;
	}

	@Override
	public String toString() {
		return expression1.toString() + "/" + expression2.toString();
	}

	public ArithExpr getExpression1(){
		return expression1;
	}
	
	public ArithExpr getExpression2(){
		return expression2;
	}
}
