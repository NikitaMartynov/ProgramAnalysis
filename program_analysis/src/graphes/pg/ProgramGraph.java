package graphes.pg;

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
	
	public static ArrayList < Edge >  edges = new ArrayList < Edge > (); 
}
