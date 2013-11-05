package ast.arith;

import java.util.Vector;

import dynamic_analysis.Environment;
import dynamic_analysis.VariableNotDefinedException;

public class IdExpr extends ArithExpr {

	private String name;

	public IdExpr(String name) {
		this.name = name;
	}

	@Override
	public int evaluate(Environment env) throws VariableNotDefinedException {
		return env.getVariable(name);
	}

	public Vector<String> getVariables() {
		if (name != null) {
			Vector<String> vars = new Vector<String>();
			try {
				vars.add(name);
				return vars;
			} catch (Exception e) {
				return null;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return name;
	}

}
