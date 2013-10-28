package ast.statement;

import dynamic_analysis.Environment;
import dynamic_analysis.VariableNotDefinedException;
import ast.arith.ArithExpr;

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
