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
	private SecurityLevel level;
	
	public ArrayDeclaration(String name, int size) {
		this.name = name;
		this.size = size;
	}
	
	public ArrayDeclaration(SecurityLevel level, String name, int size) {
		this.name = name;
		this.size = size;
		this.level = level;
	}
	
	@Override
	public void evaluate(Environment env) throws DuplicateDefinitionException{
		env.newArray(name, size);
	}
	
	@Override
	public String toString() {
		String ret = "";
		if(level != null)
			ret += level.toString() + " ";
		
		ret += "int " + name + "[" + size + "];";

		return ret;
	}
	
	public String getName(){
		return name;
	}
}
