package detectionOfSign_analysis;

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

public class DetectionOfSignAnalysis {
	private HashMap<String, Signs> newAllVarSigns;   // signs after transfer function
	
	public DetectionOfSignAnalysis(Edge edge,HashMap<String, Signs> baseElemSigns){
		newAllVarSigns = new  HashMap<String, Signs>(baseElemSigns);
		
		if(edge.getBlock() instanceof ReadStatement) //Read
			readStatementSign((ReadStatement)edge.getBlock());
		else if (edge.getBlock() instanceof ReadArrayStatement) //ReadArray
			readArrayStatementSign((ReadArrayStatement)edge.getBlock());
		else if (edge.getBlock() instanceof AssignStatement) //Assign
			assignStatementSign((AssignStatement)edge.getBlock());
		else if (edge.getBlock() instanceof ArrayAssignStatement) //Array
			arrayAssignStatementSign((ArrayAssignStatement)edge.getBlock());
		else if (edge.getBlock() instanceof SkipStatement || //Skip
				edge.getBlock() instanceof WriteStatement); //Write do nothing
		else if  (edge.getBlock() instanceof BoolExpr)
			newAllVarSigns = new BoolDetectionOfSign( (BoolExpr)edge.getBlock(), 
													baseElemSigns).getNewAllVarSigns();
			
		else assert false : "Assert in function detectSign(), shouldn't reach it. Check, did you forget any class?";
		
		
		//TODO  !; check what if  ()?
	
	}
	
	void readStatementSign(ReadStatement readSt){
		String var = readSt.getName();
		Signs  signs = newAllVarSigns.get(var);
		signs.setAll();
		newAllVarSigns.put( var, signs );
		
	}
	
	void readArrayStatementSign(ReadArrayStatement readArraySt){
		String var = readArraySt.getName();
		Signs  signs = newAllVarSigns.get(var);
		signs.setAll();
		newAllVarSigns.put( var, signs );
		
	}
	
	void assignStatementSign(AssignStatement assignSt){
		String var = assignSt.getName();
		Signs signs = new ArithDetectionOfSign( assignSt.getExpression(), newAllVarSigns).getSigns();
		newAllVarSigns.put( var, signs );
	}
	
	void arrayAssignStatementSign(ArrayAssignStatement assignArraySt){
		String var = assignArraySt.getName();
		Signs  signs = newAllVarSigns.get(var);
		Signs newSigns = new ArithDetectionOfSign( assignArraySt.getValueExpression(), newAllVarSigns).getSigns();
		signs.add(newSigns);
		newAllVarSigns.put( var, signs );
	}
	
	public HashMap<String, Signs> getNewAllVarSigns(){
		return newAllVarSigns;
	}
	
	void zerizeNewAllVarSigns(){
		HashMap <String,Signs> tmp = new HashMap<String, Signs>();
		for (Map.Entry<String,Signs> entry : newAllVarSigns.entrySet()){
			tmp.put(entry.getKey(), entry.getValue().setNone());
		}
		newAllVarSigns = tmp;
	}
	
	public String signsToString(){
		String str = "";
		for (Map.Entry<String,Signs> entry : newAllVarSigns.entrySet()){
			String strSigns="";
			for( Sign sign:  entry.getValue().getSigns()){
				switch(sign){
					case minus: strSigns+="-,"; break;
					case zero: strSigns+="0,"; break;
					case plus: strSigns+="+,"; break;
					default: assert false : "default in switch";
				}
			}
			if(strSigns.length()>0){
				strSigns = strSigns.substring(0, strSigns.length() -1 );
			}
			str += entry.getKey() + "={" + strSigns + "};\n";
			
		}
		return str;
	}
}

