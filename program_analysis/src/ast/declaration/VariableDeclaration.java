package ast.declaration;

import java.util.HashMap;

import SecurityAnalysis.SecLevel;
import dynamic_analysis.DuplicateDefinitionException;
import dynamic_analysis.Environment;

/**
 * int x;
 * @author zhenli
 *
 */
public class VariableDeclaration extends Declaration{

	private String name;
	private SecurityLevel level;
	
	public VariableDeclaration(String name) {
		this.name = name;
	}
	public VariableDeclaration(SecurityLevel level, String name) {
		this.name = name;
		this.level = level;
	}
	
	@Override
	public void evaluate(Environment env) throws DuplicateDefinitionException {
		env.newVariable(name);
	}
	
	@Override
	public String toString() {
		String ret = "";
		if(level != null)
			ret += level.toString() + " ";
		
		ret += "int " + name + ";";
		return ret;
	}
	
	public String getName(){
		return name;
	}
	public HashMap<String,SecLevel> getSecurityLevel(){
		HashMap<String,SecLevel> secLevel = new HashMap<String,SecLevel>();
		secLevel.put(name,level.getSecurityLevel());
		return secLevel;
	}
}
