package ast.declaration;

import dynamic_analysis.DuplicateDefinitionException;
import dynamic_analysis.Environment;

/**
 * D1 D2
 * @author zhenli
 *
 */
public class SeqDeclaration extends Declaration{

	private Declaration declaration1;
	private Declaration declaration2;
	
	public SeqDeclaration(Declaration declaration1, Declaration declaration2) {
		this.declaration1 = declaration1;
		this.declaration2 = declaration2;
	}
	
	@Override
	public void evaluate(Environment env) throws DuplicateDefinitionException {
		declaration1.evaluate(env);
		declaration2.evaluate(env);
	}
	
	@Override
	public String toString() {
		return declaration1.toString() + "\n" + declaration2.toString();
	}
}
