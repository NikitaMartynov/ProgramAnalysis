package ast.declaration;

/**
 * The security level is optional in a variable declaration
 * I just return the security level as a string.
 * @author zhenli
 *
 */
public interface SecurityLevel {
	
	// To Nikita:
	// you could decide how to modify the return type of this method to decide what return type you want
	public abstract String getSecurityLevel();
}
