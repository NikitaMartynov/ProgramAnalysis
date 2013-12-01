package ast.declaration;

public class SecurityLevelHigh implements SecurityLevel{
	
	@Override
	public String getSecurityLevel() {
		return "high";
	}
	@Override
	public String toString() {
		return "high";
	}
}
