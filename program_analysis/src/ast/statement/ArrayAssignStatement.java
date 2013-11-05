package ast.statement;

import java.util.Vector;

import dynamic_analysis.Environment;
import dynamic_analysis.VariableNotDefinedException;
import ast.arith.ArithExpr;
/**
 * A[a1] := a2;
 * @author zhenli
 *
 */
public class ArrayAssignStatement extends Statement{

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
			if(name!=null){
				vars.add(name);
			}
		}
		catch(Exception e){
		}
		try{
			vars.addAll(arrayExpression.getVariables());
		}
		catch(Exception e){
		}
		vars.add("=");
		try{
			vars.addAll(valueExpression.getVariables());
		} catch (Exception e) {
		}
			if (!vars.isEmpty())
				return vars;
			else
				return null;
	}
	@Override
	public String toString() {
		return name + "[" + arrayExpression + "]" + " := " + valueExpression + ";";  
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
