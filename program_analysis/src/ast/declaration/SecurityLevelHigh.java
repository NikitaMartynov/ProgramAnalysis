package ast.declaration;

import SecurityAnalysis.SecLevel;

public class SecurityLevelHigh implements SecurityLevel{
	
	@Override
	public SecLevel getSecurityLevel() {
		return SecLevel.high;
	}
	@Override
	public String toString() {
		return "high";
	}
}
