package ast.declaration;

import dynamic_analysis.DuplicateDefinitionException;
import dynamic_analysis.Environment;

/**
 * int x;
 * @author zhenli
 *
 */
public class VariableDeclaration extends Declaration{

	private String name;
	
	public VariableDeclaration(String name) {
		this.name = name;
	}
	
	@Override
	public void evaluate(Environment env) throws DuplicateDefinitionException {
		env.newVariable(name);
	}
	
	@Override
	public String toString() {
		return "int " + name + ";";
	}
	
	public String getName(){
		return name;
	}
}
