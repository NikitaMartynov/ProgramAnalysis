package ast.statement;

import java.util.Vector;

import dynamic_analysis.Environment;
import dynamic_analysis.VariableNotDefinedException;
import ast.arith.ArithExpr;
import ast.arith.ArrayExpr;
import ast.arith.IdExpr;
import ast.arith.NumExpr;

/**
 * A[a1] := a2;
 * 
 * @author zhenli
 * 
 */
public class ArrayAssignStatement extends Statement {

	private String name; // array name
	private ArithExpr arrayExpression; // array index expression
	private ArithExpr valueExpression; // value expression

	public ArrayAssignStatement(String name, ArithExpr a1, ArithExpr a2) {
		this.name = name;
		this.arrayExpression = a1;
		this.valueExpression = a2;
	}

	@Override
	public void evaluate(Environment env) throws VariableNotDefinedException {
		int value = valueExpression.evaluate(env);
		int index = arrayExpression.evaluate(env);
		env.setArray(name, index, value);
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
		vars.add("=");
		try {
			vars.addAll(valueExpression.getVariables());
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
			element += name;
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
		
		vars.addAll(valueExpression.getArrays());
		return vars;
	}

	@Override
	public String toString() {
		return name + "[" + arrayExpression + "]" + " := " + valueExpression
				+ ";";
	}
	public int printWithLabels(int i) {
		System.out.println("["+name + "[" + arrayExpression + "]" + " := " + valueExpression
				+ "]^"+ i+";");
		return ++i;
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

	public ArithExpr getValueExpression() {
		return valueExpression;
	}

	public void setValueExpression(ArithExpr valueExpression) {
		this.valueExpression = valueExpression;
	}

}
