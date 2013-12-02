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
	private static Vector<FreeVariable> freeVariables;
	private static int labelCounter;

	public static void extractVariables() {
		freeVariables = new Vector<FreeVariable>();
		labelCounter = 1;
		for (Block b : FlowGraph.getBlocks()) {
			extractFromBlock(b);
			labelCounter++;
		}

	}

	public static void extractFromBlock(Block b) {
		if (b instanceof ArrayAssignStatement)
			addArrayAssignVariables(((ArrayAssignStatement) b).getVariables());
		else if (b instanceof AssignStatement)
			addAssignFreeVariables(((AssignStatement) b).getVariables());
		else if (b instanceof WriteStatement)
			addWriteFreeVariables(((WriteStatement) b).getVariables());
		else if (b instanceof ReadStatement)
			addReadFreeVariables(((ReadStatement) b).getVariables());
		else if (b instanceof BoolExpr) {
			addBoolFreeVariables(((BoolExpr) b).getVariables());
		} else {
			// could be a case of all elements being numbers such as if(2<3)
			// No free variable and hence no processing done
		}
	}

	private static void addBoolFreeVariables(Vector<String> vars) {
		// boolean expressions
		// The position of variable is not required in further
		// processing
		for (String str : vars) {
			FreeVariable fv = new FreeVariable(str, VariablePosition.none,
					labelCounter);
			freeVariables.add(fv);
		}
	}
	private static void addArrayAssignVariables(Vector<String> vars){
		List<String> varsArray = null;
		FreeVariable fv = null;
		if (vars.contains("[")) {
			varsArray = vars.subList(vars.indexOf("[")+1, vars.indexOf("]"));
			if (varsArray != null) {
				for (String str : varsArray) {
					fv = new FreeVariable(str, VariablePosition.index,
							labelCounter);
					if (!freeVariables.contains(fv))
						freeVariables.add(fv);
				}
			}
			varsArray = vars.subList(0, vars.indexOf("["));
			if (varsArray != null) {
				for (String str : varsArray) {
					fv = new FreeVariable(str, VariablePosition.left,
							labelCounter);
					if (!freeVariables.contains(fv))
						freeVariables.add(fv);
				}
			}
			varsArray = vars.subList(vars.indexOf("=") + 1, vars.size());
			if (varsArray != null) {
				for (String str : varsArray) {
					fv = new FreeVariable(str, VariablePosition.right,
							labelCounter);
					if (!freeVariables.contains(fv))
						freeVariables.add(fv);
				}
			}
		}
	}

	private static void addAssignFreeVariables(Vector<String> vars) {
		List<String> varsArray = null;
		FreeVariable fv = null;
		if (vars.contains("=")) {
			// assignment statement - variables are split into left and
			// right occurring variable
			// ......
			// left side variable(s) in the list occur from start of vector
			// till the string before "=" sign
			varsArray = vars.subList(0, vars.indexOf("="));
			if (varsArray != null) {
				for (String str : varsArray) {
					fv = new FreeVariable(str, VariablePosition.left,
							labelCounter);
					if (!freeVariables.contains(fv))
						freeVariables.add(fv);
				}
			}
			// right side variable(s) in the list start after "=" string and
			// occur till end of list
			varsArray = vars.subList(vars.indexOf("=") + 1, vars.size());
			if (varsArray != null) {
				for (String str : varsArray) {
					fv = new FreeVariable(str, VariablePosition.right,
							labelCounter);
					if (!freeVariables.contains(fv))
						freeVariables.add(fv);
				}
			}
		}
	}

	private static void addReadFreeVariables(Vector<String> vars) {
		for (String str : vars) {
			FreeVariable fv = new FreeVariable(str, VariablePosition.read,
					labelCounter);
			freeVariables.add(fv);
		}
	}

	private static void addWriteFreeVariables(Vector<String> vars) {
		for (String str : vars) {
			FreeVariable fv = new FreeVariable(str, VariablePosition.write,
					labelCounter);
			freeVariables.add(fv);
		}
	}

	public static String printVariables() {
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
	public static Vector<String> getAllVariables() {
		Vector<String> allVariables = new Vector<String>();
		for (FreeVariable fv : freeVariables) {
			if (!allVariables.contains(fv.getVariableName()))
				allVariables.add(fv.getVariableName());
		}
		return allVariables;
	}

	// Returns all information about free variables in a program
	public static Vector<FreeVariable> getFreeVariables() {
		return freeVariables;
	}

	public static Vector<FreeVariable> getFreeVariablesinLine(
			int currentLineOfInterest) {
		Vector<FreeVariable> variablesInLine = new Vector<>();
		for (FreeVariable fv : freeVariables) {
			if (fv.getlabel() == currentLineOfInterest) {
				variablesInLine.add(fv);
			}
		}
		return variablesInLine;
	}

	public static Vector<Integer> getAssignmentLinesOfFreeVariables(
			String variableName) {
		Vector<Integer> linesOfFreeVariable = new Vector<>();

		for (FreeVariable fv : freeVariables) {
			if (fv.getVariableName().equalsIgnoreCase(variableName)
					&& (fv.getVariablePosition() == (VariablePosition.left) || fv
							.getVariablePosition() == VariablePosition.read)) {
				linesOfFreeVariable.add(fv.getlabel());
			}
		}
		return linesOfFreeVariable;
	}
}
