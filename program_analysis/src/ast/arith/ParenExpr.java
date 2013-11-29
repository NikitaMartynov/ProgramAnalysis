package ast.arith;

import java.util.Vector;

import dynamic_analysis.Environment;
import dynamic_analysis.VariableNotDefinedException;

public class ParenExpr extends ArithExpr {
	
	private ArithExpr expression;
	
	public ParenExpr(ArithExpr expression){
		this.expression = expression;
	}

	@Override
	public int evaluate(Environment env) throws VariableNotDefinedException{
		return expression.evaluate(env);
		
	}
	@Override
	public Vector<String> getVariables() {
		Vector<String> vars = new Vector<String>();
		try {
			vars.addAll(expression.getVariables());
			return vars;
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public Vector<String> getArrays() {
		Vector<String> vars = new Vector<String>();
		vars.addAll(expression.getArrays());
		return vars;
	}
	@Override
	public String toString() {
		return "(" + expression.toString() + ")";
	}
	
	public ArithExpr getExpression() {
		return this.expression;
	}
}
