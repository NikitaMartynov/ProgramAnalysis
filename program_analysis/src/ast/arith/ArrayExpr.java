package ast.arith;

import java.util.Vector;

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
	public Vector<String> getVariables(){
		
		Vector<String> vars = new Vector<String>();
		try {
			if(name!=null)
				vars.add(name);	
		} catch (Exception e) {
		}
		try{
			vars.addAll(arrayIndexExpression.getVariables());
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
		return name + "[" +arrayIndexExpression.toString() + "]";
	}

}
