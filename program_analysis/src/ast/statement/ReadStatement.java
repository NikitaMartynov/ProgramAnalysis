package ast.statement;

import java.util.Vector;

import dynamic_analysis.Environment;
import dynamic_analysis.VariableNotDefinedException;

/**
 * Unfinished evaluate method read x;
 * 
 * @author zhenli
 * 
 */
public class ReadStatement extends Statement {
	String name; // variable name

	public ReadStatement(String name) {
		this.name = name;
	}

	@Override
	public void evaluate(Environment env) throws VariableNotDefinedException {
		// TODO Auto-generated method stub

	}

	@Override
	public Vector<String> getVariables() {
		Vector<String> vars = new Vector<String>();
		vars.add(name);	
		return vars;
	}

	@Override
	public Vector<String> getArrays() {
		Vector<String> vars = new Vector<String>();
	return vars;
	}

	@Override
	public String toString() {
		return "read " + name + ";";
	}

	// getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
