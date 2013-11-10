package detectionOfSign_analysis;

import graphs.pg.Edge;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import ast.arith.ArithExpr;
import ast.arith.ArrayExpr;
import ast.arith.DivExpr;
import ast.arith.IdExpr;
import ast.arith.MinusExpr;
import ast.arith.MultExpr;
import ast.arith.NumExpr;
import ast.arith.ParenExpr;
import ast.arith.PlusExpr;
import ast.arith.UnMinExpr;
import ast.statement.ArrayAssignStatement;
import ast.statement.AssignStatement;
import ast.statement.ReadArrayStatement;
import ast.statement.ReadStatement;

public class DetectionOfSign {
	
	public static HashMap<String, Signs> elemSigns;
	
	public void initialize (Vector<String> variables){
		
		elemSigns = new HashMap<String, Signs>();
		//assert ProgramGraph.edges != null : "Program graph edges not buit at initialization of Detection Of Signs";
		
		for( String var : variables){
			if(!elemSigns.containsKey(var)) elemSigns.put(var, new Signs());
		}	
	}
	
	public void detectSign(Edge edge){
		if(edge.getBlock() instanceof ReadStatement) //Read
			readStatementSign((ReadStatement)edge.getBlock());
		else if (edge.getBlock() instanceof ReadArrayStatement) //ReadArray
			readArrayStatementSign((ReadArrayStatement)edge.getBlock());
		else if (edge.getBlock() instanceof AssignStatement) //Assign
			assignStatementSign((AssignStatement)edge.getBlock());
		else if (edge.getBlock() instanceof ArrayAssignStatement) //Array
			arrayAssignStatementSign((ArrayAssignStatement)edge.getBlock());
			
		//TODO
		//Do we  need to do anything with skip and write?
		//How deal with BoolExpr?
	}
	
	void readStatementSign(ReadStatement readSt){
		String var = readSt.getName();
		Signs  signs = elemSigns.get(var);
		signs.setAll();
		elemSigns.put( var, signs );
		
	}
	
	void readArrayStatementSign(ReadArrayStatement readArraySt){
		String var = readArraySt.getName();
		Signs  signs = elemSigns.get(var);
		signs.setAll();
		elemSigns.put( var, signs );
		
	}
	
	void assignStatementSign(AssignStatement assignSt){
		String var = assignSt.getName();
		Signs signs = exprSigns( assignSt.getExpression());
		elemSigns.put( var, signs );
	}
	
	void arrayAssignStatementSign(ArrayAssignStatement assignArraySt){
		String var = assignArraySt.getName();
		Signs  signs = elemSigns.get(var);
		Signs newSigns = exprSigns( assignArraySt.getValueExpression());
		signs.add(newSigns);
		elemSigns.put( var, signs );
	}
	
	Signs exprSigns(ArithExpr arithExpr){
		if(arithExpr instanceof IdExpr) //Variable
			return elemSigns.get( ( (IdExpr)arithExpr ).toString() );
		else if (arithExpr instanceof NumExpr) //Number
			return new Signs( ( (NumExpr)arithExpr ).getValue() );
		else if (arithExpr instanceof ParenExpr) //ParenExpr
			return exprSigns(arithExpr); 
		else if ( arithExpr instanceof PlusExpr) // PlusExpr
			return plusSigns((PlusExpr) arithExpr);
		else if ( arithExpr instanceof MinusExpr) // MinusExpr
			return minusExprSigns((MinusExpr) arithExpr);
		else if ( arithExpr instanceof UnMinExpr) // UnMinExpr
			return unMinExprSigns((UnMinExpr) arithExpr);
		else if ( arithExpr instanceof MultExpr) // MultExpr
			return multExprSigns((MultExpr) arithExpr);
		else if ( arithExpr instanceof DivExpr) // DivExpr
			return divExprSigns((DivExpr) arithExpr);
		
		assert false : "Error in function exprSigns(), shouldn't reach it. Check did you forget to add smth?";
		return null;	
	}
	
	Signs plusSigns(PlusExpr plusExpr){
		ArithExpr arithExpr1 = plusExpr.getExpression1();
		ArithExpr arithExpr2 = plusExpr.getExpression2();
		Signs signs1, signs2, resultSigns;
		
		//Signs for expr1
		if(arithExpr1 instanceof IdExpr)
			signs1 =  elemSigns.get( ( (IdExpr)arithExpr1 ).toString() );
		else if (arithExpr1 instanceof NumExpr)
			signs1 =  new Signs( ( (NumExpr)arithExpr1 ).getValue() );
		else if (arithExpr1 instanceof ArrayExpr)
			signs1 =  elemSigns.get( ( (ArrayExpr)arithExpr1 ).getName() );
		else signs1 = exprSigns(arithExpr1);
		
		
		//Signs for expr2
		if(arithExpr2 instanceof IdExpr)
			signs2 =  elemSigns.get( ( (IdExpr)arithExpr2 ).toString() );
		else if (arithExpr2 instanceof NumExpr)
			signs2 =  new Signs( ( (NumExpr)arithExpr2 ).getValue() );
		else if (arithExpr2 instanceof ArrayExpr)
			signs2 =  elemSigns.get( ( (ArrayExpr)arithExpr2 ).getName() );
		else signs2 = exprSigns(arithExpr2);
		
		//Summing signs (table 3.1 +)
		resultSigns = new Signs("null");
		for(Sign sign1 : signs1.getSigns()){
			for(Sign sign2 : signs2.getSigns()){
				switch(sign1){
					case minus: 
						switch(sign2){
							case minus:
							case zero: resultSigns.add(Sign.minus); break;
							case plus: resultSigns.setAll(); break;
							default: assert false : "default in swith!"; 
						}
					break;
					
					case zero: 
						switch(sign2){
							case minus: resultSigns.add(Sign.minus); break;
							case zero: resultSigns.add(Sign.zero); break;
							case plus: resultSigns.add(Sign.plus); break;
							default: assert false : "default in swith!"; 
						}
					break;
					
					case plus:
						switch(sign2){
							case minus: resultSigns.setAll(); break;
							case zero:
							case plus: resultSigns.add(Sign.plus); break;
							default: assert false : "default in swith!"; 
						}
					break;
					default: assert false : "default in swith!"; 
				}
				if(resultSigns.isAll()) break;
			}
			if(resultSigns.isAll()) break;
		}
		
		return resultSigns;
	}
	
	Signs minusExprSigns(MinusExpr minusExpr){
		
		ArithExpr arithExpr1 = minusExpr.getExpression1();
		ArithExpr arithExpr2 = minusExpr.getExpression2();
		Signs signs1, signs2, resultSigns;
		
		//Signs for expr1
		if(arithExpr1 instanceof IdExpr)
			signs1 =  elemSigns.get( ( (IdExpr)arithExpr1 ).toString() );
		else if (arithExpr1 instanceof NumExpr)
			signs1 =  new Signs( ( (NumExpr)arithExpr1 ).getValue() );
		else if (arithExpr1 instanceof ArrayExpr)
			signs1 =  elemSigns.get( ( (ArrayExpr)arithExpr1 ).getName() );
		else signs1 = exprSigns(arithExpr1);
		
		//Signs for expr2
		if(arithExpr2 instanceof IdExpr)
			signs2 =  elemSigns.get( ( (IdExpr)arithExpr2 ).toString() );
		else if (arithExpr2 instanceof NumExpr)
			signs2 =  new Signs( ( (NumExpr)arithExpr2 ).getValue() );
		else if (arithExpr2 instanceof ArrayExpr)
			signs2 =  elemSigns.get( ( (ArrayExpr)arithExpr2 ).getName() );
		else signs2 = exprSigns(arithExpr2);
		
		//Subtracting signs (table 3.1 -)
		resultSigns = new Signs("null");
		for(Sign sign1 : signs1.getSigns()){
			for(Sign sign2 : signs2.getSigns()){
				switch(sign1){
					case minus: 
						switch(sign2){
							case minus: resultSigns.setAll(); break;
							case zero: 
							case plus: resultSigns.add(Sign.minus); break;
							default: assert false : "default in swith!"; 
						}
					break;
					
					case zero: 
						switch(sign2){
							case minus: resultSigns.add(Sign.plus); break;
							case zero: resultSigns.add(Sign.zero); break;
							case plus: resultSigns.add(Sign.minus); break;
							default: assert false : "default in swith!"; 
						}
					break;
					
					case plus:
						switch(sign2){
							case minus: 
							case zero: resultSigns.add(Sign.plus); break;
							case plus: resultSigns.setAll(); break;
							default: assert false : "default in swith!"; 
						}
					break;
					default: assert false : "default in swith!"; 
				}
				if(resultSigns.isAll()) break;
			}
			if(resultSigns.isAll()) break;
		}
		
		return resultSigns;
	}
	
	Signs unMinExprSigns(UnMinExpr unMinExpr){
		ArithExpr arithExpr = unMinExpr.getExpression();
		Signs signs, resultSigns;
		
		//Signs for expr
		if(arithExpr instanceof IdExpr)
			signs =  elemSigns.get( ( (IdExpr)arithExpr ).toString() );
		else if (arithExpr instanceof NumExpr)
			signs =  new Signs( ( (NumExpr)arithExpr ).getValue() );
		else if (arithExpr instanceof ArrayExpr)
			signs =  elemSigns.get( ( (ArrayExpr)arithExpr ).getName() );
		else signs = exprSigns(arithExpr);
		
		//Unmin signs (table 3.2)
		resultSigns = new Signs("null");
		for(Sign sign : signs.getSigns()){
			if(resultSigns.isAll()) break;
			switch(sign){
				case minus: resultSigns.add(Sign.plus); break;
				case zero: resultSigns.add(Sign.zero); break;
				case plus: resultSigns.add(Sign.minus); break;
				default: assert false : "default in swith!"; 
			}
		}	
		return resultSigns;
	}
	
	Signs multExprSigns(MultExpr multExpr){
		ArithExpr arithExpr1 = multExpr.getExpression1();
		ArithExpr arithExpr2 = multExpr.getExpression2();
		Signs signs1, signs2, resultSigns;
		
		//Signs for expr1
		if(arithExpr1 instanceof IdExpr)
			signs1 =  elemSigns.get( ( (IdExpr)arithExpr1 ).toString() );
		else if (arithExpr1 instanceof NumExpr)
			signs1 =  new Signs( ( (NumExpr)arithExpr1 ).getValue() );
		else if (arithExpr1 instanceof ArrayExpr)
			signs1 =  elemSigns.get( ( (ArrayExpr)arithExpr1 ).getName() );
		else signs1 = exprSigns(arithExpr1);
		
		//Signs for expr2
		if(arithExpr2 instanceof IdExpr)
			signs2 =  elemSigns.get( ( (IdExpr)arithExpr2 ).toString() );
		else if (arithExpr2 instanceof NumExpr)
			signs2 =  new Signs( ( (NumExpr)arithExpr2 ).getValue() );
		else if (arithExpr2 instanceof ArrayExpr)
			signs2 =  elemSigns.get( ( (ArrayExpr)arithExpr2 ).getName() );
		else signs2 = exprSigns(arithExpr2);
		
		//Mult signs (table 3.1 +)
		resultSigns = new Signs("null");
		for(Sign sign1 : signs1.getSigns()){
			for(Sign sign2 : signs2.getSigns()){
				switch(sign1){
					case minus: 
						switch(sign2){
							case minus: resultSigns.add(Sign.plus); break;
							case zero: resultSigns.add(Sign.zero); break;
							case plus: resultSigns.add(Sign.minus); break;
							default: assert false : "default in swith!"; 
						}
					break;
					
					case zero: 
						switch(sign2){
							case minus: 
							case zero: 
							case plus: resultSigns.add(Sign.zero); break;
							default: assert false : "default in swith!"; 
						}
					break;
					
					case plus:
						switch(sign2){
							case minus: resultSigns.add(Sign.minus); break;
							case zero: resultSigns.add(Sign.zero); break;
							case plus: resultSigns.add(Sign.plus); break;
							default: assert false : "default in swith!"; 
						}
					break;
					default: assert false : "default in swith!"; 
				}
				if(resultSigns.isAll()) break;
			}
			if(resultSigns.isAll()) break;
		}
		
		return resultSigns;	
	}
	
	Signs divExprSigns(DivExpr divExpr){
		ArithExpr arithExpr1 = divExpr.getExpression1();
		ArithExpr arithExpr2 = divExpr.getExpression2();
		Signs signs1, signs2, resultSigns;
		
		//Signs for expr1
		if(arithExpr1 instanceof IdExpr)
			signs1 =  elemSigns.get( ( (IdExpr)arithExpr1 ).toString() );
		else if (arithExpr1 instanceof NumExpr)
			signs1 =  new Signs( ( (NumExpr)arithExpr1 ).getValue() );
		else if (arithExpr1 instanceof ArrayExpr)
			signs1 =  elemSigns.get( ( (ArrayExpr)arithExpr1 ).getName() );
		else signs1 = exprSigns(arithExpr1);
		
		//Signs for expr2
		if(arithExpr2 instanceof IdExpr)
			signs2 =  elemSigns.get( ( (IdExpr)arithExpr2 ).toString() );
		else if (arithExpr2 instanceof NumExpr)
			signs2 =  new Signs( ( (NumExpr)arithExpr2 ).getValue() );
		else if (arithExpr2 instanceof ArrayExpr)
			signs2 =  elemSigns.get( ( (ArrayExpr)arithExpr2 ).getName() );
		else signs2 = exprSigns(arithExpr2);
		
		//Divide signs (table 3.1 +)
		resultSigns = new Signs("null");
		for(Sign sign1 : signs1.getSigns()){
			for(Sign sign2 : signs2.getSigns()){
				switch(sign1){
					case minus: 
						switch(sign2){
							case minus: resultSigns.add(Sign.plus); break;
							case zero:
								assert false : "Divide by zero, lets discuss what to do!";
								break; //TODO exception?
							case plus: resultSigns.add(Sign.minus); break;
							default: assert false : "default in swith!"; 
						}
					break;
					
					case zero: 
						switch(sign2){
							case minus: resultSigns.add(Sign.zero); break;
							case zero: 
								assert false : "Divide by zero, lets discuss what to do!";
								break; //TODO exception?
							case plus: resultSigns.add(Sign.zero); break;
							default: assert false : "default in swith!"; 
						}
					break;
					
					case plus:
						switch(sign2){
							case minus: resultSigns.add(Sign.minus); break;
							case zero: 
								assert false : "Divide by zero, lets discuss what to do!";
								break;//TODO exception?
							case plus: resultSigns.add(Sign.plus); break;
							default: assert false : "default in swith!"; 
						}
					break;
					default: assert false : "default in swith!"; 
				}
				if(resultSigns.isAll()) break;
			}
			if(resultSigns.isAll()) break;
		}
		
		return resultSigns;		
	}

	public String signsToString(){
		String str = "";
		for (Map.Entry<String,Signs> entry : elemSigns.entrySet()){
			String strSigns="";
			for( Sign sign:  entry.getValue().getSigns()){
				switch(sign){
					case minus: strSigns+="-,"; break;
					case zero: strSigns+="0,"; break;
					case plus: strSigns+="+,"; break;
					default: assert false : "default in switch";
				}
			}
			strSigns = strSigns.substring(0, strSigns.length() -1 );
			str += entry.getKey() + "={" + strSigns + "};\n";
		}
		return str;
	}
}

