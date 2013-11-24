package detectionOfSign_analysis;

import graphs.pg.Edge;


import graphs.pg.ProgramGraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class DSWorklist {
	
	private ArrayList<Edge> workList;
	public static Vector<HashMap<String, Signs>> solutionsTable;
	
	int loopCounter;
	
	public DSWorklist(ArrayList<Edge> pgEdges, Vector<String> freeVars){
		this.workList = new ArrayList<Edge>(pgEdges);
		
		if (solutionsTable == null)
			solutionsTable = new Vector<HashMap<String, Signs>>( 
					ProgramGraph.GreatestNumUsed + 1);
		else solutionsTable.clear();


		HashMap<String, Signs> allVarsZeroized = new HashMap<String, Signs>();
		for( String var : freeVars){
			if(!allVarsZeroized.containsKey(var)) allVarsZeroized.put(var, new Signs());
		}
		HashMap<String, Signs> nullVarsZeroized = new HashMap<String, Signs>();
		for( String var : freeVars){
			if(!nullVarsZeroized.containsKey(var)) nullVarsZeroized.put(var, new Signs("null"));
		}
		// init all lines of table with   0 for all vars
		for (int i = 0; i < solutionsTable.capacity(); i++) {
			if(i==0)
				solutionsTable.add(allVarsZeroized);
			else solutionsTable.add(nullVarsZeroized);
		}
		loopCounter = 0;
		while(!workList.isEmpty()){
			loopCounter++;
			Edge currentEdge  = workList.get(0);
			workList.remove(0);
			int startNodeIndex = currentEdge.getQs()-1;
			int endNodeIndex = currentEdge.getQt()-1;
			DSTransFuncs dsa = new DSTransFuncs(currentEdge, solutionsTable.get(startNodeIndex)
												 );
			HashMap<String, Signs> resAfterTrFunc = dsa.getNewAllVarSigns();
			if (resAfterTrFunc ==null){
				resAfterTrFunc = solutionsTable.get(endNodeIndex);
			}

			if(!isParam1SubsetOfParam2(resAfterTrFunc, 
									solutionsTable.get(endNodeIndex) ) ){
				solutionsTable.set(endNodeIndex, mergeSigns("mergeUnion", 
						resAfterTrFunc, solutionsTable.get(endNodeIndex)) );
				for (Edge edge : pgEdges){
					if (currentEdge.getQs() == edge.getQs()){
						workList.add(edge);
					}
				}
			}
			//printSolutionsTable();
		}
	}
	
	public void printSolutionsTable() {
		System.out.println("Detection of signs solutions table " + loopCounter+ ":");
		int i = 1;
		for (HashMap<String, Signs> solutions : solutionsTable) {
			if (solutions != null) {
				System.out.print(i++ + ": ");
				for (Map.Entry<String,Signs> entry : solutions.entrySet()){
					String strSigns="";
					int spaceCount = 8;
					for( Sign sign:  entry.getValue().getSigns()){
						switch(sign){
							case minus: strSigns+="-,"; break;
							case zero: strSigns+="0,"; break;
							case plus: strSigns+="+,"; break;
							default: assert false : "default in switch";
						}
						//spaceCount-=1;
					}
					if(strSigns.length()>0){
						strSigns = strSigns.substring(0, strSigns.length() -1 );
					}
					
					System.out.print( padRight(entry.getKey() + "={" + strSigns + "}",spaceCount) );
					
				}
				System.out.println();
			}
		}
	}
	
	String padRight(String s, int n) {
	     return String.format("%1$-" + n + "s", s);  
	}
	
	boolean isParam1SubsetOfParam2( HashMap<String, Signs> signs1, HashMap<String, Signs> signs2){
		 if ((signs1 == null) || (signs2 == null) )
			 assert 1==0 : "Error in isEqualLines() one of lines is null!";
		if(signs1.size() != signs2.size())
			assert false : "Error in isEqualLines(), not equal size of hashmaps with signs!";

		for (Map.Entry<String,Signs> entry : signs1.entrySet())
			if (!(entry.getValue().isSubsetOf(signs2.get(entry.getKey())) ) )
					return false;
		
		return true;
	}
	
	//copied from BoolDS
	//cmd={mergeUnion,mergeIntersection}
	HashMap<String, Signs> mergeSigns(String cmd, 
						HashMap<String, Signs> signs1, HashMap<String, Signs> signs2){
			HashMap<String, Signs> signs = new HashMap<String, Signs>();
			if ((signs1 == null) && (signs2 == null) )
				return null;
			else if ((signs1 == null) || (signs2 == null) )
				return signs1 == null ? signs2 : signs1;
			
			if(signs1.size() != signs2.size())
				assert false : "Error in mergeSigns(), not equal size of hashmaps with signs!";

			for (Map.Entry<String,Signs> entry : signs1.entrySet())
				signs.put(entry.getKey(), new Signs(cmd, entry.getValue(), 
													signs2.get(entry.getKey()))  );
			
			return signs;
		}
	

}
