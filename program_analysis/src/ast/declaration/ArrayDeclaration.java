package ast.declaration;

import dynamic_analysis.DuplicateDefinitionException;
import dynamic_analysis.Environment;

/**
 * int A[n];
 * @author zhenli
 *
 */
public class ArrayDeclaration extends Declaration{

	private String name;
	private int size;
	
	public ArrayDeclaration(String name, int size) {
		this.name = name;
		this.size = size;
	}
	
	@Override
	public void evaluate(Environment env) throws DuplicateDefinitionException{
		env.newArray(name, size);
	}
	
	@Override
	public String toString() {
		return "int " + name + "[" + size + "];";
	}
}
