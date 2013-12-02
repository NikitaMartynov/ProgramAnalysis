package SecurityAnalysis;

import graphs.pg.Edge;


import graphs.pg.ProgramGraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import detectionOfSign_analysis.Signs;
import ast.arith.NumExpr;
import ast.declaration.Declaration;
import ast.statement.WriteStatement;

import ast.Program;

public class SecLevelWorklist {
	
	private ArrayList<Edge> workList;
	private Vector<HashMap<String, SecLevel>> solutionsTable;
	public static HashMap<Integer, SecLevel> secLevelCtxBeforeBools;
	
	int loopCounter;
	
	public SecLevelWorklist(ArrayList<Edge> pgEdges,ArrayList<Edge> boolEndingEdges, Program program){
		this.workList = new ArrayList<Edge>(pgEdges);
		secLevelCtxBeforeBools = new HashMap<Integer, SecLevel>();
		
		if (solutionsTable == null)
			solutionsTable = new Vector<HashMap<String, SecLevel>>( 
					ProgramGraph.GreatestNumUsed);
		else solutionsTable.clear();


		HashMap<String, SecLevel> allVarsZeroized = new HashMap<String, SecLevel>();
		allVarsZeroized.putAll(program.getSecurityLevel());

			//allVarsZeroized.put("y", SecLevel.low);
			//allVarsZeroized.put("z", SecLevel.low);
			//allVarsZeroized.put("k", SecLevel.low);
		allVarsZeroized.put(SecCtx.CTX.getSecCtx(), SecLevel.low);
		
		HashMap<String, SecLevel> allNullVars = new HashMap<String, SecLevel>();
		
		for (Map.Entry<String,SecLevel> entry : allVarsZeroized.entrySet()){
			allNullVars.put(entry.getKey(), SecLevel.none);
		}
		
		// init all lines of table with   0 for all vars
		for (int i = 0; i < solutionsTable.capacity(); i++) {
			if(i==0)
				solutionsTable.add(allVarsZeroized);
			else solutionsTable.add(allNullVars);
		}
		loopCounter = 0;
		while(!workList.isEmpty()){
			loopCounter++;
			Edge currentEdge  = workList.get(0);
			workList.remove(0);
			int startNodeIndex = currentEdge.getQs()-1;
			int endNodeIndex = currentEdge.getQt()-1;
			
			//Tracking context
			Edge endingEdge = null;
			for( Edge endEdge: boolEndingEdges){
				if( endEdge.getQt() == currentEdge.getQs()){
					endingEdge = endEdge;
					break;
				}
			}
			if(endingEdge!=null){
				HashMap< String,SecLevel > tmp = Func.deepLineCopy(solutionsTable.get(startNodeIndex));
				tmp.put(SecCtx.CTX.getSecCtx(), secLevelCtxBeforeBools.get(endingEdge.getQs()));
				solutionsTable.setElementAt(tmp, startNodeIndex);
				//secLevelCtxBeforeBools.remove(endingEdge.getQs());
			}
			
			SecLevelTransFuncs sla = new SecLevelTransFuncs(currentEdge, solutionsTable.get(startNodeIndex)
												 );
			HashMap<String, SecLevel> resAfterTrFunc = Func.deepLineCopy(sla.getNewAllVarSecLevel());
			if (resAfterTrFunc ==null){
				resAfterTrFunc = solutionsTable.get(endNodeIndex);
			}

			if(!isParam1SubsetOfParam2(resAfterTrFunc, 
									solutionsTable.get(endNodeIndex) ) ){
				solutionsTable.set(endNodeIndex, mergeSecLevel("mergeUnion", 
						resAfterTrFunc, solutionsTable.get(endNodeIndex)) );
				for (Edge edge : pgEdges){
					if (currentEdge.getQt() == edge.getQs()){ // TODO should be currentEdge.getQt() == edge.getQs()???
						workList.add(edge);
					}
				}
			}
			//printSolutionsTable();
		}
	}
	
	public ArrayList<Edge> findSecurityLevelViolations(ArrayList<Edge> pgEdges) {
		ArrayList<Edge> violatedEdges = new ArrayList<Edge>();
		for (Edge edge : pgEdges) {
			Vector<String> vars = null;
			if( edge.getBlock() instanceof WriteStatement){
				vars = edge.getBlock().getVariables();
				if(vars == null)
					vars = edge.getBlock().getArrays();
				if (vars.isEmpty()){
					vars = new Vector<String>();
					vars.add("Number");
				}
				String var = vars.get(0);
				if (var != "Number"){
					int start = edge.getQs()-1;
					SecLevel secLevel = solutionsTable.get(start).get(var);
					if(secLevel== SecLevel.high){
						violatedEdges.add(edge);
					}
				}

			}

		} // for
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
		System.out.println("Security Level Analysis solutions table " + loopCounter+ ":");
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
						strSecLevel = strSecLevel.substring(0, strSecLevel.length() );
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
											? SecLevel.high : 
														(entry.getValue() == SecLevel.low)|| 
														(secLevel2.get(entry.getKey()) == SecLevel.low)
														? SecLevel.low : SecLevel.none);
											//but none in theory couldn't be
			
			return secLevel;
		}
	

}
