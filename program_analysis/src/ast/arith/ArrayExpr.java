package ast.arith;

import java.util.Vector;

import dynamic_analysis.Environment;
import dynamic_analysis.VariableNotDefinedException;

/**
 * A[a];
 * 
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
		return env.getArray(name, arrayIndexExpression.evaluate(env));
	}

	public Vector<String> getVariables() {

		Vector<String> vars = new Vector<String>();
		try {
			if (name != null)
				vars.add(name);
		} catch (Exception e) {
		}
		try {
			vars.addAll(arrayIndexExpression.getVariables());
		} catch (Exception e) {
		}
		if (!vars.isEmpty())
			return vars;
		else
			return null;
	}

	@Override
	public Vector<String> getArrays() {

		Vector<String> vars = new Vector<String>();
		String element = "";

		if (name != null) {
			element += name; // TODO
			if (arrayIndexExpression instanceof IdExpr) {
				element += "[" + ((IdExpr) arrayIndexExpression).toString()
						+ "]";
			} else if (arrayIndexExpression instanceof NumExpr) {
				element += "[" + ((NumExpr) arrayIndexExpression).toString()
						+ "]";
			} else if (arrayIndexExpression instanceof ArrayExpr) {
				element += "[" + ((ArrayExpr) arrayIndexExpression).getName()
						+ "*]";
			}
			vars.add(element);
		}

		return vars;
	}

	@Override
	public String toString() {
		return name + "[" + arrayIndexExpression.toString() + "]";
	}

	public String getName() {
		return name;
	}

}
