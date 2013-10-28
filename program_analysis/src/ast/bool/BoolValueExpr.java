package ast.bool;

import dynamic_analysis.Environment;
import dynamic_analysis.VariableNotDefinedException;

public class BoolValueExpr extends BoolExpr {

	private boolean value;
	
	public BoolValueExpr(boolean value) {
		this.value = value;
	}
	
	@Override
	public boolean evaluate(Environment env) throws VariableNotDefinedException {
		return value;
	}
	
	@Override
	public String toString() {
		if (value)
			return "true";
		else 
			return "false";
	}

}
