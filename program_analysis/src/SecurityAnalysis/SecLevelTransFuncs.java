package SecurityAnalysis;

import graphs.pg.Edge;

import java.util.HashMap;
import java.util.Map;

import ast.bool.BoolExpr;
import ast.statement.ArrayAssignStatement;
import ast.statement.AssignStatement;
import ast.statement.ReadArrayStatement;
import ast.statement.ReadStatement;
import ast.statement.SkipStatement;
import ast.statement.WriteStatement;

public class SecLevelTransFuncs {
	private HashMap<String, SecLevel> newAllVarSecLevel;   // secLevel after transfer function
	
	public SecLevelTransFuncs(Edge edge,HashMap<String, SecLevel> baseElemSecLevel){
		newAllVarSecLevel = Func.deepLineCopy(baseElemSecLevel);
		
		if(edge.getBlock() instanceof ReadStatement) //Read do nothing
			;//readStatementSign((ReadStatement)edge.getBlock());
		else if (edge.getBlock() instanceof ReadArrayStatement) //ReadArray do nothing
			;//readArrayStatementSign((ReadArrayStatement)edge.getBlock());
		else if (edge.getBlock() instanceof AssignStatement) //Assign
			assignStatementSign((AssignStatement)edge.getBlock());
		else if (edge.getBlock() instanceof ArrayAssignStatement) //Array
			arrayAssignStatementSign((ArrayAssignStatement)edge.getBlock());
		else if (edge.getBlock() instanceof SkipStatement || //Skip
				edge.getBlock() instanceof WriteStatement); //Write do nothing
		else if  (edge.getBlock() instanceof BoolExpr)
			newAllVarSecLevel = new BoolSecLevel( (BoolExpr)edge.getBlock(), 
													baseElemSecLevel).getNewAllVarSecLevel();
			
		else assert false : "Assert in function detectSign(), shouldn't reach it. Check, did you forget any class?";
		
		
		//TODO  check what if  ()?
	
	}
	
	/*void readStatementSign(ReadStatement readSt){
		String var = readSt.getName();
		SecLevel  secLevel = newAllVarSecLevel.get(var);
		secLevel.setAll();
		newAllVarSecLevel.put( var, secLevel );
		
	}
	
	void readArrayStatementSign(ReadArrayStatement readArraySt){
		String var = readArraySt.getName();
		SecLevel  secLevel = newAllVarSecLevel.get(var);
		secLevel.setAll();
		newAllVarSecLevel.put( var, secLevel );
		
	}*/
	
	void assignStatementSign(AssignStatement assignSt){
		String var = assignSt.getName();
		SecLevel secLevel = new ArithSec( assignSt.getExpression(), newAllVarSecLevel).getSecLevel();
		newAllVarSecLevel.put( var, secLevel );
	}
	
	void arrayAssignStatementSign(ArrayAssignStatement assignArraySt){
		String var = assignArraySt.getName();
		SecLevel newSecLevel = new ArithSec( assignArraySt.getValueExpression(), newAllVarSecLevel).getSecLevel();
		newAllVarSecLevel.put( var, newSecLevel );
	}
	
	public HashMap<String, SecLevel> getNewAllVarSecLevel(){
		return newAllVarSecLevel;
	}
	
/*	void zerizeNewAllVarSecLevel(){
		HashMap <String,SecLevel> tmp = new HashMap<String, SecLevel>();
		for (Map.Entry<String,SecLevel> entry : newAllVarSecLevel.entrySet()){
			tmp.put(entry.getKey(), entry.getValue().setNone());
		}
		newAllVarSecLevel = tmp;
	}*/
	
	public String secLevelToString(){
		String str = "";
		for (Map.Entry<String,SecLevel> entry : newAllVarSecLevel.entrySet()){
			String strSecLevel="";
				switch(entry.getValue()){
					case low: strSecLevel+="low"; break;
					case high: strSecLevel+="high"; break;
					case none: strSecLevel+="none"; break;

					default: assert false : "default in switch";
				}
			
			if(strSecLevel.length()>0){
				strSecLevel = strSecLevel.substring(0, strSecLevel.length() -1 );
			}
			str += entry.getKey() + "={" + strSecLevel + "};\n";
			
		}
		return str;
	}
}

