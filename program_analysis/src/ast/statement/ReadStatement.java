package ast.statement;

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
	public String toString() {
		return "read " + name+ ";";
	}

	// getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
