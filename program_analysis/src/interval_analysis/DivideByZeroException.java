package interval_analysis;

public class DivideByZeroException extends Exception{

	private String variableName;
	
	public DivideByZeroException() {}
	
	public DivideByZeroException(String name) {
		this.variableName = name;
	}
	public void setVariableName(String name) {
		this.variableName = name;
	}
	
	public String toString() {
		return "There is a possibility that the divisor could be zero.";
	}
}
