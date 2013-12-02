package program_slicing;

public class ReachingDefinition {
	private int lineNumber;
	private String variableName;

	public ReachingDefinition(String variableName, int lineNumber) {
		this.lineNumber = lineNumber;
		this.variableName = variableName;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	public String getVariableName() {
		return variableName;
	}

	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof ReachingDefinition)) {
			return false;
		}
		ReachingDefinition other = (ReachingDefinition) obj;
		return (this.variableName.equals(other.variableName) && this.lineNumber == other.lineNumber);
	}

	@Override
	public int hashCode() {
		return variableName.hashCode() * lineNumber;
	}

	@Override
	public String toString() {
		if(lineNumber != 0){
		return variableName + "," + String.valueOf(lineNumber);
		}
		else{
			return variableName + ",?";	
		}
	}
}
