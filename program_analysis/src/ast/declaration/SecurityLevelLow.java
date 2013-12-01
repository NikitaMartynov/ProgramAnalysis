package ast.declaration;

import SecurityAnalysis.SecLevel;

public class SecurityLevelLow implements SecurityLevel{

	@Override
	public SecLevel getSecurityLevel() {
		return SecLevel.low;
	}
	@Override
	public String toString() {
		return "low";
	}

}
