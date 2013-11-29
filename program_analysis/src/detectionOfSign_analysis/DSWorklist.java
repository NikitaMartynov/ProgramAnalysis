package detectionOfSign_analysis;

import graphs.pg.Edge;
import graphs.pg.ProgramGraph;
import interval_analysis.DivideByZeroException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DSWorklist {

	private ArrayList<Edge> workList;
	private Vector<HashMap<String, Signs>> solutionsTable;

	int loopCounter;

	public DSWorklist(ArrayList<Edge> pgEdges, Vector<String> freeVars)
			throws DivideByZeroException {
		this.workList = new ArrayList<Edge>(pgEdges);

		if (solutionsTable == null)
			solutionsTable = new Vector<HashMap<String, Signs>>(
					ProgramGraph.GreatestNumUsed);
		else
			solutionsTable.clear();

		HashMap<String, Signs> allVarsZeroized = new HashMap<String, Signs>();
		for (String var : freeVars) {
			if (!allVarsZeroized.containsKey(var))
				allVarsZeroized.put(var, new Signs());
		}
		HashMap<String, Signs> nullVarsZeroized = new HashMap<String, Signs>();
		for (String var : freeVars) {
			if (!nullVarsZeroized.containsKey(var))
				nullVarsZeroized.put(var, new Signs("null"));
		}
		// init all lines of table with 0 for all vars
		for (int i = 0; i < solutionsTable.capacity(); i++) {
			if (i == 0)
				solutionsTable.add(allVarsZeroized);
			else
				solutionsTable.add(nullVarsZeroized);
		}
		loopCounter = 0;
		while (!workList.isEmpty()) {
			loopCounter++;
			Edge currentEdge = workList.get(0);
			workList.remove(0);
			int startNodeIndex = currentEdge.getQs() - 1;
			int endNodeIndex = currentEdge.getQt() - 1;
			DSTransFuncs dsa = new DSTransFuncs(currentEdge,
					solutionsTable.get(startNodeIndex));
			HashMap<String, Signs> resAfterTrFunc = dsa.getNewAllVarSigns();
			if (resAfterTrFunc == null) {
				resAfterTrFunc = solutionsTable.get(endNodeIndex);
			}

			if (!isParam1SubsetOfParam2(resAfterTrFunc,
					solutionsTable.get(endNodeIndex))) {
				solutionsTable.set(
						endNodeIndex,
						mergeSigns("mergeUnion", resAfterTrFunc,
								solutionsTable.get(endNodeIndex)));
				for (Edge edge : pgEdges) {
					if (currentEdge.getQs() == edge.getQs()) { // TODO should be
																// currentEdge.getQt()
																// ==
																// edge.getQs()???
						workList.add(edge);
					}
				}
			}
			// printSolutionsTable();
		}
	}

	public ArrayList<Edge> findLowBoundaryViolations(ArrayList<Edge> pgEdges) {
		ArrayList<Edge> violatedEdges = new ArrayList<Edge>();
		for (Edge edge : pgEdges) {
			Vector<String> arrayVars = edge.getBlock().getArrays();
			int start = edge.getQs();
			for (String s : arrayVars) {
				// match a[x]
				Pattern pattern = Pattern.compile("(.*?)[(.*?)]");
				Matcher matcher = pattern.matcher(s);
				if (matcher.find()) {
					String a = matcher.group(1);
					String x = matcher.group(2);
				}
			}
			// If B^l is a statement that includes an array as one of the free
			// variables
			// If DS(q’) for index of the array contains {-}
			// then cons((B^l),VL)
		}
		return violatedEdges;
	}

	public String toString(ArrayList<Edge> pgEdges) {
		String str = "";

		for (Edge e : pgEdges) {
			str += '(' + Integer.toString(e.getQs()) + ',' + e.getBlock() + ','
					+ Integer.toString(e.getQt()) + "), ";
		}
		str = str.substring(0, str.length() - 2);
		return str;
	}

	public void printSolutionsTable() {
		System.out.println("Detection of signs solutions table " + loopCounter
				+ ":");
		int i = 1;
		for (HashMap<String, Signs> solutions : solutionsTable) {
			if (solutions != null) {
				System.out.print(i++ + ": ");
				for (Map.Entry<String, Signs> entry : solutions.entrySet()) {
					String strSigns = "";
					int spaceCount = 18;
					for (Sign sign : entry.getValue().getSigns()) {
						switch (sign) {
						case minus:
							strSigns += "-,";
							break;
						case zero:
							strSigns += "0,";
							break;
						case plus:
							strSigns += "+,";
							break;
						default:
							assert false : "default in switch";
						}
						spaceCount -= 1;
					}
					if (strSigns.length() > 0) {
						strSigns = strSigns.substring(0, strSigns.length() - 1);
					}

					System.out.print(padRight(entry.getKey() + "={" + strSigns
							+ "}", spaceCount));

				}
				System.out.println();
			}
		}
	}

	String padRight(String s, int n) {
		return String.format("%1$-" + n + "s", s);
	}

	boolean isParam1SubsetOfParam2(HashMap<String, Signs> signs1,
			HashMap<String, Signs> signs2) {
		if ((signs1 == null) || (signs2 == null))
			assert 1 == 0 : "Error in isEqualLines() one of lines is null!";
		if (signs1.size() != signs2.size())
			assert false : "Error in isEqualLines(), not equal size of hashmaps with signs!";

		for (Map.Entry<String, Signs> entry : signs1.entrySet())
			if (!(entry.getValue().isSubsetOf(signs2.get(entry.getKey()))))
				return false;

		return true;
	}

	// copied from BoolDS
	// cmd={mergeUnion,mergeIntersection}
	HashMap<String, Signs> mergeSigns(String cmd,
			HashMap<String, Signs> signs1, HashMap<String, Signs> signs2) {
		HashMap<String, Signs> signs = new HashMap<String, Signs>();
		if ((signs1 == null) && (signs2 == null))
			return null;
		else if ((signs1 == null) || (signs2 == null))
			return signs1 == null ? signs2 : signs1;

		if (signs1.size() != signs2.size())
			assert false : "Error in mergeSigns(), not equal size of hashmaps with signs!";

		for (Map.Entry<String, Signs> entry : signs1.entrySet())
			signs.put(
					entry.getKey(),
					new Signs(cmd, entry.getValue(), signs2.get(entry.getKey())));

		return signs;
	}

}
