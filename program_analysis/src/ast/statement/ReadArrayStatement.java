package ast.statement;

import java.util.Vector;

import dynamic_analysis.Environment;
import dynamic_analysis.VariableNotDefinedException;
import ast.arith.*;

/**
 * Unfinished evaluate method read A[a];
 * 
 * @author zhenli
 * 
 */
public class ReadArrayStatement extends Statement {
	String name;
	ArithExpr arrayExpression;

	public ReadArrayStatement(String name, ArithExpr arrayExpression) {
		this.name = name;
		this.arrayExpression = arrayExpression;
	}

	@Override
	public void evaluate(Environment env) throws VariableNotDefinedException {
		// TODO Auto-generated method stub

	}

	@Override
	public Vector<String> getVariables() {
		Vector<String> vars = new Vector<String>();
		try {
			if (name != null) {
				vars.add(name);
			}
		} catch (Exception e) {
		}
		try {
			vars.addAll(arrayExpression.getVariables());
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
			if (arrayExpression instanceof IdExpr) {
				element += "[" + ((IdExpr) arrayExpression).toString() + "]";
			} else if (arrayExpression instanceof NumExpr) {
				element += "[" + ((NumExpr) arrayExpression).toString() + "]";
			} else if (arrayExpression instanceof ArrayExpr) {
				element += "[" + ((ArrayExpr) arrayExpression).getName()
						+ "*]";
			}
			vars.add(element);
		}

		return vars;
	}

	@Override
	public String toString() {
		return "read " + name + "[" + arrayExpression + "];";
	}

	// getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArithExpr getArrayExpression() {
		return arrayExpression;
	}

	public void setArrayExpression(ArithExpr arrayExpression) {
		this.arrayExpression = arrayExpression;
	}

}
