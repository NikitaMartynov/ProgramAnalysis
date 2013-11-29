package ast.statement;

import java.util.Vector;

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
	public Vector<String> getVariables() {
		Vector<String> vars = new Vector<String>();
		try {
			if(name!=null){
				vars.add(name);
			}
		}
		catch(Exception e){
		}
		vars.add("=");
		try{
			vars.addAll(expression.getVariables());
		} catch (Exception e) {
		}
			if (!vars.isEmpty())
				return vars;
			else
				return null;
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

	@Override
	public Vector<String> getArrays() {
		Vector<String> vars = new Vector<String>();
		
		vars.addAll(expression.getArrays());
		return vars;
	}

	
}
