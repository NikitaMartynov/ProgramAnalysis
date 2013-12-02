package ast.declaration;

import java.util.HashMap;

import SecurityAnalysis.SecLevel;
import dynamic_analysis.DuplicateDefinitionException;
import dynamic_analysis.Environment;

public abstract class Declaration {
	public abstract void evaluate(Environment env) throws DuplicateDefinitionException;
	public abstract HashMap<String, SecLevel> getSecurityLevel();
}
