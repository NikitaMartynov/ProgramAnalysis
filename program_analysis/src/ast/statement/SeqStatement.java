package ast.statement;

import java.util.Vector;

import dynamic_analysis.Environment;
import dynamic_analysis.VariableNotDefinedException;

/**
 * S1 S2
 * 
 * @author zhenli
 * 
 */
public class SeqStatement extends Statement {

	private Statement statement1;
	private Statement statement2;

	public SeqStatement(Statement statement1, Statement statement2) {
		this.statement1 = statement1;
		this.statement2 = statement2;
	}

	@Override
	public void evaluate(Environment env) throws VariableNotDefinedException {
		statement1.evaluate(env);
		statement2.evaluate(env);
	}

	@Override
	public Vector<String> getVariables() {
		Vector<String> vars = new Vector<String>();
		try {
			vars.addAll(statement1.getVariables());
		}
		catch(Exception e){			
		}
		try{
			vars.addAll(statement2.getVariables());			
		} catch (Exception e) {
		}
			if (!vars.isEmpty())
				return vars;
			else
				return null;	
	}

	@Override
	public String toString() {
		return statement1 + "\n" + statement2;
	}

	// getters and setters
	public Statement getStatement1() {
		return statement1;
	}

	public void setStatement1(Statement statement1) {
		this.statement1 = statement1;
	}

	public Statement getStatement2() {
		return statement2;
	}

	public void setStatement2(Statement statement2) {
		this.statement2 = statement2;
	}

}
