package dynamic_analysis;

import java.util.HashMap;
import java.util.Map.Entry;

public class Environment {

	private HashMap<String,Integer> variableValues = new HashMap<String,Integer>();
	private HashMap<String,Integer[]>arrayValues = new HashMap<String, Integer[]>();
	
	public Environment() { }
	
	// methods used by variable declarations
	public void newVariable(String name) throws DuplicateDefinitionException{
		if(variableValues.containsKey(name))
			throw new DuplicateDefinitionException(name);
		variableValues.put(name, 0);
	}
	public void newArray(String name, int size ) throws DuplicateDefinitionException{
		if(arrayValues.containsKey(name))
			throw new DuplicateDefinitionException(name);
		Integer[] value = new Integer[size];
		for(int i = 0; i < size; i ++) {
			value[i] = 0;
		}
		arrayValues.put(name, value);
	}
	
	
	// methods used by assignments
	public void setVariable(String name, int value) throws VariableNotDefinedException{
		if(!variableValues.containsKey(name))
			throw new VariableNotDefinedException(name);
		variableValues.put(name, value);
	}
	
	public void setArray(String name, int index, int value) throws VariableNotDefinedException, IndexOutOfBoundsException {
		if(!arrayValues.containsKey(name))
			throw new VariableNotDefinedException(name);
		arrayValues.get(name)[index] = value;
	}
	
	// used in expressions
	public int getVariable(String name) throws VariableNotDefinedException {
		Integer value = variableValues.get(name); 
		if (value == null) throw new VariableNotDefinedException(name);
		return value.intValue();
	}
	
	public Integer[] getArray(String name) throws VariableNotDefinedException {
		Integer[] array = arrayValues.get(name); 		
		if (array == null) throw new VariableNotDefinedException(name);
		return array;
	}
	
	public int getArray(String name, int index) throws VariableNotDefinedException, IndexOutOfBoundsException {
		Integer[] array = arrayValues.get(name); 
		if (array == null) throw new VariableNotDefinedException(name);
		return array[index];
	}
	
	public String toString() {
		String table = "";
		for (Entry<String,Integer> entry : variableValues.entrySet()) {
			table += entry.getKey() + "\t-> " + entry.getValue() + "\n";
		}
		
		for (Entry<String,Integer[]> entry : arrayValues.entrySet()) {
			table += entry.getKey() + "\t-> [";
			for(Integer i :  entry.getValue())
				table += " " + i;
			table += " ]\n";
		}
		return table;
	}
	
}
