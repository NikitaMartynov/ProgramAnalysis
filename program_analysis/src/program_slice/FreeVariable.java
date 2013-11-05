package program_slice;

public class FreeVariable {
	
	private String variableName;
	private String variablePosition;
	private int label;
	
	public FreeVariable(String variableName,String variablePosition,int label){
		this.variableName=variableName;
		this.variablePosition=variablePosition;
		this.label=label;	
	}
 @Override
 public String toString(){
	return variableName + ", " + variablePosition + ", " + String.valueOf(label);
 }
 @Override
 public boolean equals(Object obj) {
     if (obj == this) {
         return true;
     }
     if (!(obj instanceof FreeVariable)) {
         return false;
     }
     FreeVariable other = (FreeVariable) obj;
     return (this.variableName.equals(other.variableName) && this.variablePosition.equals(other.variablePosition) && this.label==other.label);
 }
 @Override
 public int hashCode() {
     return variableName.hashCode() * variablePosition.hashCode() * label;
 }
}
