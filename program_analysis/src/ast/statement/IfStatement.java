package ast.statement;

import dynamic_analysis.Environment;
import dynamic_analysis.VariableNotDefinedException;
import ast.bool.BoolExpr;

/**
 * if b then S1 else S2 fi
 * 
 * @author zhenli
 * 
 */
public class IfStatement extends Statement {

	private BoolExpr condition;
	private Statement ifBranch;
	private Statement elseBranch;

	public IfStatement(BoolExpr condition, Statement ifBranch,
			Statement elseBranch) {
		this.condition = condition;
		this.ifBranch = ifBranch;
		this.elseBranch = elseBranch;
	}

	@Override
	public void evaluate(Environment env) throws VariableNotDefinedException {
		if (condition.evaluate(env)) {
			ifBranch.evaluate(env);
		} else {
			elseBranch.evaluate(env);
		}
	}

	@Override
	public String toString() {
		return "if " + condition + " then " + ifBranch + " else " + elseBranch
				+ " fi ";
	}

	// getters and setters
	public BoolExpr getCondition() {
		return condition;
	}

	public void setCondition(BoolExpr condition) {
		this.condition = condition;
	}

	public Statement getIfBranch() {
		return ifBranch;
	}

	public void setIfBranch(Statement ifBranch) {
		this.ifBranch = ifBranch;
	}

	public Statement getElseBranch() {
		return elseBranch;
	}

	public void setElseBranch(Statement elseBranch) {
		this.elseBranch = elseBranch;
	}

}
