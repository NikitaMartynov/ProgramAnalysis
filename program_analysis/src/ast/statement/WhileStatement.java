package ast.statement;

import dynamic_analysis.Environment;
import dynamic_analysis.VariableNotDefinedException;
import ast.bool.BoolExpr;

/**
 * while b do S od
 * @author zhenli
 *
 */
public class WhileStatement extends Statement {

	private BoolExpr condition;
	private Statement body;
	
	public WhileStatement(BoolExpr condition, Statement body) {
		this.condition = condition;
		this.body = body;
	}
	
	@Override
	public void evaluate(Environment env) throws VariableNotDefinedException {
		while (condition.evaluate(env)) {
			body.evaluate(env);	
		}
	}
	
	@Override
	public String toString() {
		return "while " + condition + " do " + body + " od";
	}

	// getters and setters
	public BoolExpr getCondition() {
		return condition;
	}

	public void setCondition(BoolExpr condition) {
		this.condition = condition;
	}

	public Statement getBody() {
		return body;
	}

	public void setBody(Statement body) {
		this.body = body;
	}
	
	

}
