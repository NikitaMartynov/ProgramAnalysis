package free_variables;

/**
 * Represents a variable , its position within a statement and a label in which it occurs
 * Possible variable positions:
 * 'left' - left side of assignment operator , 'right'- right side of assignment operator
 * 'none' - not an assignment statement
 * @author anushasivakumar
 *
 */

public class FreeVariable {
	
	private String variableName;
	private VariablePosition variablePosition;
	private int label;
	
	public FreeVariable(String variableName,VariablePosition variablePosition,int label){
		this.variableName=variableName;
		this.variablePosition=variablePosition;
		this.label=label;	
	}
	
public String getVariableName(){
	return variableName;
}
public VariablePosition getVariablePosition(){
	return variablePosition;
}
public int getlabel(){
	return label;
}
 @Override
 public String toString(){
	return variableName + ", " + variablePosition.toString() + ", " + String.valueOf(label);
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
