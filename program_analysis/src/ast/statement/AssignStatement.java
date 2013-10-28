package ast.statement;

import dynamic_analysis.Environment;
import dynamic_analysis.VariableNotDefinedException;
import ast.arith.ArithExpr;

/**
 * x := a;
 * @author zhenli
 *
 */
public class AssignStatement extends Statement {

	private String name; // variable name
	private ArithExpr expression; // value expression
	
	public AssignStatement(String name, ArithExpr expression) {
		this.name = name;
		this.expression = expression;
	}
	
	@Override
	public void evaluate(Environment env) throws VariableNotDefinedException {
		int value = expression.evaluate(env);
		env.setVariable(name, value);
	}
	
	@Override
	public String toString() {
		return name + " := " + expression + ";";
	}

	//getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArithExpr getExpression() {
		return expression;
	}

	public void setExpression(ArithExpr expression) {
		this.expression = expression;
	}

	
}
