package ast.declaration;

import dynamic_analysis.DuplicateDefinitionException;
import dynamic_analysis.Environment;

public abstract class Declaration {
	public abstract void evaluate(Environment env) throws DuplicateDefinitionException;
}
