package ast.arith;

import dynamic_analysis.Environment;
import dynamic_analysis.VariableNotDefinedException;

/**
 * A[a];
 * @author zhenli
 *
 */
public class ArrayExpr extends ArithExpr {

	private String name;
	private ArithExpr arrayIndexExpression;
	
	public ArrayExpr(String name, ArithExpr index) {
		this.name = name;
		this.arrayIndexExpression = index;
	}
	
	@Override
	public int evaluate(Environment env) throws VariableNotDefinedException {
		return env.getArray(name,arrayIndexExpression.evaluate(env));
	}

	@Override
	public String toString() {
		return name + "[" +arrayIndexExpression.toString() + "]";
	}

}
