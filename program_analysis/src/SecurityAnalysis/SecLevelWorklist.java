package SecurityAnalysis;

import graphs.pg.Edge;


import graphs.pg.ProgramGraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class SecLevelWorklist {
	
	private ArrayList<Edge> workList;
	private Vector<HashMap<String, SecLevel>> solutionsTable;
	
	int loopCounter;
	
	public SecLevelWorklist(ArrayList<Edge> pgEdges, Vector<String> freeVars){
		this.workList = new ArrayList<Edge>(pgEdges);
		
		if (solutionsTable == null)
			solutionsTable = new Vector<HashMap<String, SecLevel>>( 
					ProgramGraph.GreatestNumUsed);
		else solutionsTable.clear();


		HashMap<String, SecLevel> allVarsZeroized = new HashMap<String, SecLevel>();
		for( String var : freeVars){
			//if(!allVarsZeroized.containsKey(var)) allVarsZeroized.put(var, SecLevel.);//derive level from declaration
			//TODO check if it can be none
			//TODO derive level from declaration
		}
		HashMap<String, SecLevel> nullVarsZeroized = new HashMap<String, SecLevel>();
		for( String var : freeVars){
			if(!nullVarsZeroized.containsKey(var)) nullVarsZeroized.put(var, SecLevel.none);
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
			SecLevelTransFuncs dsa = new SecLevelTransFuncs(currentEdge, solutionsTable.get(startNodeIndex)
												 );
			HashMap<String, SecLevel> resAfterTrFunc = dsa.getNewAllVarSecLevel();
			if (resAfterTrFunc ==null){
				resAfterTrFunc = solutionsTable.get(endNodeIndex);
			}

			if(!isParam1SubsetOfParam2(resAfterTrFunc, 
									solutionsTable.get(endNodeIndex) ) ){
				solutionsTable.set(endNodeIndex, mergeSecLevel("mergeUnion", 
						resAfterTrFunc, solutionsTable.get(endNodeIndex)) );
				for (Edge edge : pgEdges){
					if (currentEdge.getQs() == edge.getQs()){ // TODO should be currentEdge.getQt() == edge.getQs()???
						workList.add(edge);
					}
				}
			}
			//printSolutionsTable();
		}
	}
	
	public ArrayList<Edge> findLowBoundaryViolations(ArrayList<Edge> pgEdges){
		ArrayList<Edge> violatedEdges = new ArrayList<Edge>();
		for(Edge egde : pgEdges){
			//If B^l is a statement that includes an array as one of the free variables 
			//If DS(q’) for index of the array contains {-} 
			//	then cons((B^l),VL)
		}
		return violatedEdges;
	}
	
	public String toString (ArrayList<Edge> pgEdges){
		String str="";
		
		for (Edge e : pgEdges){
			str+= '('+ Integer.toString(e.getQs()) + ',' + e.getBlock() + ','+ Integer.toString(e.getQt()) + "), ";
		}
		str = str.substring(0, str.length() - 2);
	return str;
	}
	
	public void printSolutionsTable() {
		System.out.println("Detection of secLevel solutions table " + loopCounter+ ":");
		int i = 1;
		for (HashMap<String, SecLevel> solutions : solutionsTable) {
			if (solutions != null) {
				System.out.print(i++ + ": ");
				for (Map.Entry<String,SecLevel> entry : solutions.entrySet()){
					String strSecLevel="";
					int spaceCount = 18;
						switch(entry.getValue()){
						case low: strSecLevel+="low"; break;
						case high: strSecLevel+="high"; break;
						case none: strSecLevel+="none"; break;
							default: assert false : "default in switch";
						
						spaceCount-=1;
					}
					if(strSecLevel.length()>0){
						strSecLevel = strSecLevel.substring(0, strSecLevel.length() -1 );
					}
					
					System.out.print( padRight(entry.getKey() + "={" + strSecLevel + "}",spaceCount) );
					
				}
				System.out.println();
			}
		}
	}
	
	String padRight(String s, int n) {
	     return String.format("%1$-" + n + "s", s);  
	}
	
	boolean isParam1SubsetOfParam2( HashMap<String, SecLevel> secLevel1, HashMap<String, SecLevel> secLevel2){
		 if ((secLevel1 == null) || (secLevel2 == null) )
			 assert 1==0 : "Error in isEqualLines() one of lines is null!";
		if(secLevel1.size() != secLevel2.size())
			assert false : "Error in isEqualLines(), not equal size of hashmaps with secLevel!";

		for (Map.Entry<String,SecLevel> entry : secLevel1.entrySet())
			if (!(entry.getValue() == secLevel2.get(entry.getKey())) ) //Check if 1 subset of 2, subset only if equal 
					return false;
		
		return true;
	}
	
	//copied from BoolDS
	//cmd={mergeUnion,mergeIntersection}
	HashMap<String, SecLevel> mergeSecLevel(String cmd, 
						HashMap<String, SecLevel> secLevel1, HashMap<String, SecLevel> secLevel2){
			HashMap<String, SecLevel> secLevel = new HashMap<String, SecLevel>();
			if ((secLevel1 == null) && (secLevel2 == null) )
				return null;
			else if ((secLevel1 == null) || (secLevel2 == null) )
				return secLevel1 == null ? secLevel2 : secLevel1;
			
			if(secLevel1.size() != secLevel2.size())
				assert false : "Error in mergeSecLevel(), not equal size of hashmaps with secLevel!";

			for (Map.Entry<String,SecLevel> entry : secLevel1.entrySet())
				secLevel.put(entry.getKey(), 	(entry.getValue() == SecLevel.high)|| 
										(secLevel2.get(entry.getKey()) == SecLevel.high) 
											? SecLevel.high : SecLevel.low);
											//TODO check if it can be none
			
			return secLevel;
		}
	

}
