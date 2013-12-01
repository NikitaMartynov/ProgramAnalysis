package ast.declaration;

public class SecurityLevelLow implements SecurityLevel{

	@Override
	public String getSecurityLevel() {
		return "low";
	}
	@Override
	public String toString() {
		return "low";
	}

}
