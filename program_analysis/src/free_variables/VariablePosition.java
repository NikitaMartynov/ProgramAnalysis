package free_variables;
/**
* Possible variable positions within a statement
* @author anushasivakumar
*/
public enum VariablePosition {
left, //left side of assignment operator
right, //right side of assignment operator
read, // variable present in a read statement
write, //variable present in a write statement
index,//variable present in an array index
none;  // variable present in a statement other than assignment statement
}
