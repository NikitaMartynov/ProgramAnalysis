package ast;

import dynamic_analysis.*;
import ast.declaration.*;
import ast.statement.*;

public class Program {
	Declaration declaration;
	Statement statement;

	public Program(Declaration declaration, Statement statement) {
		this.declaration = declaration;
		this.statement = statement;
	}

	public Program(Statement statement) {
		this.declaration = null;
		this.statement = statement;
	}

	public void evaluate(Environment env) throws DuplicateDefinitionException, VariableNotDefinedException {
		if(this.declaration != null)
			this.declaration.evaluate(env);
		this.statement.evaluate(env);	
	}
	
	public String toString() {
		if(declaration !=null){
		return declaration.toString() + "\n" + statement.toString();
		}
		else{
			return statement.toString();
		}
	}

	// getters and setters
	public Declaration getDeclaration() {
		return declaration;
	}

	public void setDeclaration(Declaration declaration) {
		this.declaration = declaration;
	}

	public Statement getStatement() {
		return statement;
	}

	public void setStatement(Statement statement) {
		this.statement = statement;
	}

	
}
