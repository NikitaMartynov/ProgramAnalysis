package free_variables;

/**
 * Extracts free variables present in each line of program,
 * their position within the statement and labels in which they occur
 * @author anushasivakumar
 *
 */

import graphs.Block;
import graphs.fg.FlowGraph;

import java.util.List;
import java.util.Vector;
import ast.bool.BoolExpr;
import ast.statement.*;

public class FreeVariableGenerator {
	private Vector<FreeVariable> freeVariables;

	public FreeVariableGenerator() {
		this.freeVariables = new Vector<FreeVariable>();
		extractVariables();
	}

	public void extractVariables() {
		FreeVariable fv = null;
		List<String> varsArray = null;
		int labelCounter = 1;
		for (Block b : FlowGraph.getBlocks()) {
			Vector<String> vars = extractFromBlock(b);
			if (vars == null) {
				// could be a case of all elements being numbers such as if(2<3)
				// No free variable and hence no processing done
			} else if (vars.contains("=")) {
				// assignment statement - variables are split into left and
				// right occurring variable
				// ......
				// left side variable(s) in the list occur from start of vector
				// till the string before "=" sign
				varsArray = vars.subList(0, vars.indexOf("="));
				if (varsArray != null) {
					for (String str : varsArray) {
						fv = new FreeVariable(str, VariablePosition.left, labelCounter);
						if (!freeVariables.contains(fv))
							freeVariables.add(fv);
					}
				}
				// right side variable(s) in the list start after "=" string and
				// occur till end of list
				varsArray = vars.subList(vars.indexOf("=") + 1, vars.size());
				if (varsArray != null) {
					for (String str : varsArray) {
						fv = new FreeVariable(str, VariablePosition.right, labelCounter);
						if (!freeVariables.contains(fv))
							freeVariables.add(fv);
					}
				}

			} else {
				// Other type of statements and boolean expressions
				// The position of variable is not required in further
				// processing
				for (String str : vars) {
					fv = new FreeVariable(str, VariablePosition.none, labelCounter);
					freeVariables.add(fv);
				}
			}
			labelCounter++;
		}

	}

	public static Vector<String> extractFromBlock(Block b) {
		if (b instanceof ArrayAssignStatement)
			return ((ArrayAssignStatement) b).getVariables();
		else if (b instanceof AssignStatement)
			return ((AssignStatement) b).getVariables();
		else if (b instanceof WriteStatement)
			return ((WriteStatement) b).getVariables();
		else if (b instanceof ReadStatement)
			return ((ReadStatement) b).getVariables();
		else if (b instanceof BoolExpr) {
			return ((BoolExpr) b).getVariables();
		} else
			return null;
	}

	@Override
	public String toString() {
		// all the free variables in the program
		String ret = "Free Variables: \n";
		if (freeVariables.isEmpty()) {
			return "Free Variables list is empty";
		}
		for (FreeVariable fv : freeVariables) {
			ret += "(" + fv.toString() + ") ";
		}
		return ret;
	}
	
	// Returns names of all variables used in a program
	public Vector<String> getAllVariables(){
		Vector<String> allVariables = new Vector<String> ();
		for(FreeVariable fv:freeVariables){
			if(!allVariables.contains(fv.getVariableName()))
					allVariables.add(fv.getVariableName());		
		}
		return allVariables;
	}

}
