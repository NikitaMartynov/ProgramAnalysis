package graphs.pg;

import java.util.ArrayList;

import ast.statement.Statement;

public class ProgramGraph {
	public ProgramGraph(){
	}
	
	public ProgramGraph(Statement st){
		ProgramGraphFactory.create(st, 1, 0);
	}
	
	public String toString (){
		String str="";
		
		for (Edge e : edges){
			str+= '('+ Integer.toString(e.qs) + ',' + e.block + ','+ Integer.toString(e.qt) + "), ";
		}
		str = str.substring(0, str.length() - 2);
	return str;
	}
	public static int GreatestNumUsed;
	public static ArrayList < Edge >  edges = new ArrayList < Edge > (); 
	
	//TODO Just notice if any problems! 
	// Added: GreatestNumUsed => in while loop fixed for correct !boolBlock qt value in case of nested loops
	//Added: in ifPrGr in else{ was no addition of else branch only then branch so now it works 
}
