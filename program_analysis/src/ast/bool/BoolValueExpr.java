package ast.bool;

import dynamic_analysis.Environment;
import dynamic_analysis.VariableNotDefinedException;
import java.lang.String;
import java.util.Vector;

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
	public Vector<String> getVariables(){
		return null;
	}
	@Override
	public String toString() {
		if (value)
			return "true";
		else 
			return "false";
	}

	public boolean getBoolValue() {
		return value;
	}
}
