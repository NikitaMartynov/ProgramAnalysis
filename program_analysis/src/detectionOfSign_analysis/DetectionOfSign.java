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
import ast.bool.BoolExpr;
import ast.bool.EqualsExpr;
import ast.bool.GreaterThanEqualsExpr;
import ast.bool.GreaterThanExpr;
import ast.bool.LessThanEqualsExpr;
import ast.bool.LessThanExpr;
import ast.bool.NotEqualsExpr;
import ast.statement.ArrayAssignStatement;
import ast.statement.AssignStatement;
import ast.statement.ReadArrayStatement;
import ast.statement.ReadStatement;
import ast.statement.SkipStatement;
import ast.statement.WriteStatement;

public class DetectionOfSign {
	
	private HashMap<String, Signs> baseAllVarSigns; //input signs
	public HashMap<String, Signs> newAllVarSigns;   // signs after transfer function 
	
/*	public void initialize (Vector<String> variables){
		
		elemSigns = new HashMap<String, Signs>();
		//assert ProgramGraph.edges != null : "Program graph edges not buit at initialization of Detection Of Signs";
		
		for( String var : variables){
			if(!elemSigns.containsKey(var)) elemSigns.put(var, new Signs());
		}	
	}*/
	
	public DetectionOfSign(Edge edge,HashMap<String, Signs> baseElemSigns){
		
		newAllVarSigns = new  HashMap<String, Signs>(baseElemSigns);
		this.baseAllVarSigns = new  HashMap<String, Signs>(baseElemSigns);
		
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
		else if ( edge.getBlock() instanceof BoolExpr)
			reduceSignsForBoolExprHolds((BoolExpr)edge.getBlock());
		else assert false : "Assert in function detectSign(), shouldn't reach it. Check, did you forget any class?";
			
		//TODO BoolExpr
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
		Signs signs = arithExprSigns( assignSt.getExpression());
		newAllVarSigns.put( var, signs );
	}
	
	void arrayAssignStatementSign(ArrayAssignStatement assignArraySt){
		String var = assignArraySt.getName();
		Signs  signs = newAllVarSigns.get(var);
		Signs newSigns = arithExprSigns( assignArraySt.getValueExpression());
		signs.add(newSigns);
		newAllVarSigns.put( var, signs );
	}
	
	Signs arithExprSigns(ArithExpr arithExpr){
		if(arithExpr instanceof IdExpr) //Variable
			return newAllVarSigns.get( ( (IdExpr)arithExpr ).toString() );
		else if (arithExpr instanceof NumExpr) //Number
			return new Signs( ( (NumExpr)arithExpr ).getValue() );
		else if (arithExpr instanceof ParenExpr) //ParenExpr
			return arithExprSigns(arithExpr); 
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
			signs1 =  newAllVarSigns.get( ( (IdExpr)arithExpr1 ).toString() );
		else if (arithExpr1 instanceof NumExpr)
			signs1 =  new Signs( ( (NumExpr)arithExpr1 ).getValue() );
		else if (arithExpr1 instanceof ArrayExpr)
			signs1 =  newAllVarSigns.get( ( (ArrayExpr)arithExpr1 ).getName() );
		else signs1 = arithExprSigns(arithExpr1);
		
		
		//Signs for expr2
		if(arithExpr2 instanceof IdExpr)
			signs2 =  newAllVarSigns.get( ( (IdExpr)arithExpr2 ).toString() );
		else if (arithExpr2 instanceof NumExpr)
			signs2 =  new Signs( ( (NumExpr)arithExpr2 ).getValue() );
		else if (arithExpr2 instanceof ArrayExpr)
			signs2 =  newAllVarSigns.get( ( (ArrayExpr)arithExpr2 ).getName() );
		else signs2 = arithExprSigns(arithExpr2);
		
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
			signs1 =  newAllVarSigns.get( ( (IdExpr)arithExpr1 ).toString() );
		else if (arithExpr1 instanceof NumExpr)
			signs1 =  new Signs( ( (NumExpr)arithExpr1 ).getValue() );
		else if (arithExpr1 instanceof ArrayExpr)
			signs1 =  newAllVarSigns.get( ( (ArrayExpr)arithExpr1 ).getName() );
		else signs1 = arithExprSigns(arithExpr1);
		
		//Signs for expr2
		if(arithExpr2 instanceof IdExpr)
			signs2 =  newAllVarSigns.get( ( (IdExpr)arithExpr2 ).toString() );
		else if (arithExpr2 instanceof NumExpr)
			signs2 =  new Signs( ( (NumExpr)arithExpr2 ).getValue() );
		else if (arithExpr2 instanceof ArrayExpr)
			signs2 =  newAllVarSigns.get( ( (ArrayExpr)arithExpr2 ).getName() );
		else signs2 = arithExprSigns(arithExpr2);
		
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
			signs =  newAllVarSigns.get( ( (IdExpr)arithExpr ).toString() );
		else if (arithExpr instanceof NumExpr)
			signs =  new Signs( ( (NumExpr)arithExpr ).getValue() );
		else if (arithExpr instanceof ArrayExpr)
			signs =  newAllVarSigns.get( ( (ArrayExpr)arithExpr ).getName() );
		else signs = arithExprSigns(arithExpr);
		
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
			signs1 =  newAllVarSigns.get( ( (IdExpr)arithExpr1 ).toString() );
		else if (arithExpr1 instanceof NumExpr)
			signs1 =  new Signs( ( (NumExpr)arithExpr1 ).getValue() );
		else if (arithExpr1 instanceof ArrayExpr)
			signs1 =  newAllVarSigns.get( ( (ArrayExpr)arithExpr1 ).getName() );
		else signs1 = arithExprSigns(arithExpr1);
		
		//Signs for expr2
		if(arithExpr2 instanceof IdExpr)
			signs2 =  newAllVarSigns.get( ( (IdExpr)arithExpr2 ).toString() );
		else if (arithExpr2 instanceof NumExpr)
			signs2 =  new Signs( ( (NumExpr)arithExpr2 ).getValue() );
		else if (arithExpr2 instanceof ArrayExpr)
			signs2 =  newAllVarSigns.get( ( (ArrayExpr)arithExpr2 ).getName() );
		else signs2 = arithExprSigns(arithExpr2);
		
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
			signs1 =  newAllVarSigns.get( ( (IdExpr)arithExpr1 ).toString() );
		else if (arithExpr1 instanceof NumExpr)
			signs1 =  new Signs( ( (NumExpr)arithExpr1 ).getValue() );
		else if (arithExpr1 instanceof ArrayExpr)
			signs1 =  newAllVarSigns.get( ( (ArrayExpr)arithExpr1 ).getName() );
		else signs1 = arithExprSigns(arithExpr1);
		
		//Signs for expr2
		if(arithExpr2 instanceof IdExpr)
			signs2 =  newAllVarSigns.get( ( (IdExpr)arithExpr2 ).toString() );
		else if (arithExpr2 instanceof NumExpr)
			signs2 =  new Signs( ( (NumExpr)arithExpr2 ).getValue() );
		else if (arithExpr2 instanceof ArrayExpr)
			signs2 =  newAllVarSigns.get( ( (ArrayExpr)arithExpr2 ).getName() );
		else signs2 = arithExprSigns(arithExpr2);
		
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

	
	void reduceSignsForBoolExprHolds(BoolExpr boolExpr){
		//TODO boolean operations, !; check what if  ()?
	
		newAllVarSigns = relationalExprHoldsSigns(boolExpr);
	}
	
	HashMap<String, Signs> relationalExprHoldsSigns(BoolExpr boolExpr){
		if(boolExpr instanceof LessThanExpr)
			return lessThanExprSignsReduction((LessThanExpr)boolExpr);
		else if(boolExpr instanceof LessThanEqualsExpr)
			return lessThanEqualsExprSignsReduction((LessThanEqualsExpr)boolExpr);
		else if(boolExpr instanceof GreaterThanExpr)
			return greaterThanExprSignsReduction((GreaterThanExpr)boolExpr);
		else if(boolExpr instanceof GreaterThanEqualsExpr)
			return greaterThanEqualsExprSignsReduction((GreaterThanEqualsExpr)boolExpr);
		else if(boolExpr instanceof EqualsExpr)
			return equalsExprSignsReduction((EqualsExpr)boolExpr);
		else if(boolExpr instanceof NotEqualsExpr)
			return notEqualsExprSignsReduction((NotEqualsExpr)boolExpr);
		
		else {
			assert false : "Error in function reduceSignsForBoolExprHolds(), shouldn't reach it. Check did you forget to add smth?";
			return null;
		}
	}
	
	//reduce signs only if variable at least on one side
	HashMap<String, Signs> lessThanExprSignsReduction(LessThanExpr lessThanExpr){
		ArithExpr arithExpr1 = lessThanExpr.getExpression1();
		ArithExpr arithExpr2 = lessThanExpr.getExpression2();
		Signs signs1, signs2;
		String varName1 = null, varName2 = null;
		Signs trueSigns1,trueSigns2;
		
		//Signs for expr1
		if(arithExpr1 instanceof IdExpr){
			varName1 = ( (IdExpr)arithExpr1 ).toString();
			signs1 =  baseAllVarSigns.get( ( (IdExpr)arithExpr1 ).toString() );
		}
		else if (arithExpr1 instanceof NumExpr)
			signs1 =  new Signs( ( (NumExpr)arithExpr1 ).getValue() );
		else if (arithExpr1 instanceof ArrayExpr){
			varName1 = ( (ArrayExpr)arithExpr1 ).toString();
			signs1 =  baseAllVarSigns.get( ( (ArrayExpr)arithExpr1 ).getName() );
		}
		else signs1 = arithExprSigns(arithExpr1);
		
		
		//Signs for expr2
		if(arithExpr2 instanceof IdExpr){
			varName2 = ( (IdExpr)arithExpr2 ).toString();
			signs2 =  baseAllVarSigns.get( ( (IdExpr)arithExpr2 ).toString() );
		}
		else if (arithExpr2 instanceof NumExpr)
			signs2 =  new Signs( ( (NumExpr)arithExpr2 ).getValue() );
		else if (arithExpr2 instanceof ArrayExpr){
			varName2 = ( (ArrayExpr)arithExpr2 ).toString();
			signs2 =  baseAllVarSigns.get( ( (ArrayExpr)arithExpr2 ).getName() );
		}
		else signs2 = arithExprSigns(arithExpr2);
		
		//Summing signs (table 3.3 <)
		trueSigns1 = new Signs("null");
		trueSigns2 = new Signs("null");
		for(Sign sign1 : signs1.getSigns()){
			for(Sign sign2 : signs2.getSigns()){
				switch(sign1){
					case minus: 
						switch(sign2){
							case minus: 
								trueSigns1.add(Sign.minus);
								trueSigns2.add(Sign.minus);
								break;
							case zero: 	
								trueSigns1.add(Sign.minus);
								trueSigns2.add(Sign.zero);
								break;
							case plus: 
								trueSigns1.add(Sign.minus);
								trueSigns2.add(Sign.plus);
								break;
							default: assert false : "default in swith!"; 
						}
					break;
					
					case zero: 
						switch(sign2){
							case minus: 
							case zero: break;
							case plus: 
								trueSigns1.add(Sign.zero);
								trueSigns2.add(Sign.plus);
								break;
							default: assert false : "default in swith!"; 
						}
					break;
					
					case plus:
						switch(sign2){
							case minus: 
							case zero: break;
							case plus: 
								trueSigns1.add(Sign.plus);
								trueSigns2.add(Sign.plus);
								break;
							default: assert false : "default in swith!"; 
						}
					break;
					default: assert false : "default in swith!"; 
				}
			}
		}
		
		if(trueSigns1.isAny() && trueSigns2.isAny()){
			HashMap<String, Signs> updateToAllVarSigns = new  HashMap<String, Signs>(this.baseAllVarSigns);	
			if(varName1!=null)//update signs if we were dealing with var or Array
				updateToAllVarSigns.put(varName1,trueSigns1);
			if(varName2!=null)
				updateToAllVarSigns.put(varName2,trueSigns1);
			return updateToAllVarSigns;	
		}
		else 
			return null;
	}
	
	//reduce signs only if variable at least on one side
	HashMap<String, Signs> lessThanEqualsExprSignsReduction(LessThanEqualsExpr lessThanEqualsExpr){
		ArithExpr arithExpr1 = lessThanEqualsExpr.getExpression1();
		ArithExpr arithExpr2 = lessThanEqualsExpr.getExpression2();
		Signs signs1, signs2;
		String varName1 = null, varName2 = null;
		Signs trueSigns1,trueSigns2;
		
		//Signs for expr1
		if(arithExpr1 instanceof IdExpr){
			varName1 = ( (IdExpr)arithExpr1 ).toString();
			signs1 =  baseAllVarSigns.get( ( (IdExpr)arithExpr1 ).toString() );
		}
		else if (arithExpr1 instanceof NumExpr)
			signs1 =  new Signs( ( (NumExpr)arithExpr1 ).getValue() );
		else if (arithExpr1 instanceof ArrayExpr){
			varName1 = ( (ArrayExpr)arithExpr1 ).toString();
			signs1 =  baseAllVarSigns.get( ( (ArrayExpr)arithExpr1 ).getName() );
		}
		else signs1 = arithExprSigns(arithExpr1);
		
		
		//Signs for expr2
		if(arithExpr2 instanceof IdExpr){
			varName2 = ( (IdExpr)arithExpr2 ).toString();
			signs2 =  baseAllVarSigns.get( ( (IdExpr)arithExpr2 ).toString() );
		}
		else if (arithExpr2 instanceof NumExpr)
			signs2 =  new Signs( ( (NumExpr)arithExpr2 ).getValue() );
		else if (arithExpr2 instanceof ArrayExpr){
			varName2 = ( (ArrayExpr)arithExpr2 ).toString();
			signs2 =  baseAllVarSigns.get( ( (ArrayExpr)arithExpr2 ).getName() );
		}
		else signs2 = arithExprSigns(arithExpr2);
		
		//Summing signs (table 3.3 <=)
		trueSigns1 = new Signs("null");
		trueSigns2 = new Signs("null");
		for(Sign sign1 : signs1.getSigns()){
			for(Sign sign2 : signs2.getSigns()){
				switch(sign1){
					case minus: 
						switch(sign2){
							case minus: 
								trueSigns1.add(Sign.minus);
								trueSigns2.add(Sign.minus);
								break;
							case zero: 	
								trueSigns1.add(Sign.minus);
								trueSigns2.add(Sign.zero);
								break;
							case plus: 
								trueSigns1.add(Sign.minus);
								trueSigns2.add(Sign.plus);
								break;
							default: assert false : "default in swith!"; 
						}
					break;
					
					case zero: 
						switch(sign2){
							case minus: 
								break;
							case zero: 
								trueSigns1.add(Sign.zero);
								trueSigns2.add(Sign.zero);
								break;
							case plus: 
								trueSigns1.add(Sign.zero);
								trueSigns2.add(Sign.plus);
								break;
							default: assert false : "default in swith!"; 
						}
					break;
					
					case plus:
						switch(sign2){
							case minus: 
							case zero: break;
							case plus: 
								trueSigns1.add(Sign.plus);
								trueSigns2.add(Sign.plus);
								break;
							default: assert false : "default in swith!"; 
						}
					break;
					default: assert false : "default in swith!"; 
				}
			}
		}
		
		if(trueSigns1.isAny() && trueSigns2.isAny()){
			HashMap<String, Signs> updateToAllVarSigns = new  HashMap<String, Signs>(this.baseAllVarSigns);	
			if(varName1!=null)//update signs if we were dealing with var or Array
				updateToAllVarSigns.put(varName1,trueSigns1);
			if(varName2!=null)
				updateToAllVarSigns.put(varName2,trueSigns1);
			return updateToAllVarSigns;	
		}
		else 
			return null;
	}
	
	//reduce signs only if variable at least on one side
	HashMap<String, Signs> greaterThanExprSignsReduction(GreaterThanExpr greaterThanExpr){
		ArithExpr arithExpr1 = greaterThanExpr.getExpression1();
		ArithExpr arithExpr2 = greaterThanExpr.getExpression2();
		Signs signs1, signs2;
		String varName1 = null, varName2 = null;
		Signs trueSigns1,trueSigns2;
				
		//Signs for expr1
		if(arithExpr1 instanceof IdExpr){
			varName1 = ( (IdExpr)arithExpr1 ).toString();
			signs1 =  baseAllVarSigns.get( ( (IdExpr)arithExpr1 ).toString() );
		}
		else if (arithExpr1 instanceof NumExpr)
			signs1 =  new Signs( ( (NumExpr)arithExpr1 ).getValue() );
		else if (arithExpr1 instanceof ArrayExpr){
			varName1 = ( (ArrayExpr)arithExpr1 ).toString();
			signs1 =  baseAllVarSigns.get( ( (ArrayExpr)arithExpr1 ).getName() );
		}
		else signs1 = arithExprSigns(arithExpr1);
		
		
		//Signs for expr2
		if(arithExpr2 instanceof IdExpr){
			varName2 = ( (IdExpr)arithExpr2 ).toString();
			signs2 =  baseAllVarSigns.get( ( (IdExpr)arithExpr2 ).toString() );
		}
		else if (arithExpr2 instanceof NumExpr)
			signs2 =  new Signs( ( (NumExpr)arithExpr2 ).getValue() );
		else if (arithExpr2 instanceof ArrayExpr){
			varName2 = ( (ArrayExpr)arithExpr2 ).toString();
			signs2 =  baseAllVarSigns.get( ( (ArrayExpr)arithExpr2 ).getName() );
		}
		else signs2 = arithExprSigns(arithExpr2);
		
		//Summing signs (table 3.3 >)
		trueSigns1 = new Signs("null");
		trueSigns2 = new Signs("null");
		for(Sign sign1 : signs1.getSigns()){
			for(Sign sign2 : signs2.getSigns()){
				switch(sign1){
					case minus: 
						switch(sign2){
							case minus: 
								trueSigns1.add(Sign.minus);
								trueSigns2.add(Sign.minus);
								break;
							case zero: 	
							case plus: 
								break;
							default: assert false : "default in swith!"; 
						}
					break;
					
					case zero: 
						switch(sign2){
							case minus: 
								trueSigns1.add(Sign.zero);
								trueSigns2.add(Sign.minus);
							case zero: 
							case plus: 
								break;
							default: assert false : "default in swith!"; 
						}
					break;
					
					case plus:
						switch(sign2){
							case minus: 
								trueSigns1.add(Sign.plus);
								trueSigns2.add(Sign.minus);
								break;
							case zero: 
								trueSigns1.add(Sign.plus);
								trueSigns2.add(Sign.zero);
								break;
							case plus: 
								trueSigns1.add(Sign.plus);
								trueSigns2.add(Sign.plus);
								break;
							default: assert false : "default in swith!"; 
						}
					break;
					default: assert false : "default in swith!"; 
				}
			}
		}
		
		if(trueSigns1.isAny() && trueSigns2.isAny()){
			HashMap<String, Signs> updateToAllVarSigns = new  HashMap<String, Signs>(this.baseAllVarSigns);	
			if(varName1!=null)//update signs if we were dealing with var or Array
				updateToAllVarSigns.put(varName1,trueSigns1);
			if(varName2!=null)
				updateToAllVarSigns.put(varName2,trueSigns1);
			return updateToAllVarSigns;	
		}
		else 
			return null;
	}
	
	//reduce signs only if variable at least on one side
	HashMap<String, Signs> greaterThanEqualsExprSignsReduction(GreaterThanEqualsExpr greaterThanEqualsExpr){
		ArithExpr arithExpr1 = greaterThanEqualsExpr.getExpression1();
		ArithExpr arithExpr2 = greaterThanEqualsExpr.getExpression2();
		Signs signs1, signs2;
		String varName1 = null, varName2 = null;
		Signs trueSigns1,trueSigns2;
				
		//Signs for expr1
		if(arithExpr1 instanceof IdExpr){
			varName1 = ( (IdExpr)arithExpr1 ).toString();
			signs1 =  baseAllVarSigns.get( ( (IdExpr)arithExpr1 ).toString() );
		}
		else if (arithExpr1 instanceof NumExpr)
			signs1 =  new Signs( ( (NumExpr)arithExpr1 ).getValue() );
		else if (arithExpr1 instanceof ArrayExpr){
			varName1 = ( (ArrayExpr)arithExpr1 ).toString();
			signs1 =  baseAllVarSigns.get( ( (ArrayExpr)arithExpr1 ).getName() );
		}
		else signs1 = arithExprSigns(arithExpr1);
		
		//Signs for expr2
		if(arithExpr2 instanceof IdExpr){
			varName2 = ( (IdExpr)arithExpr2 ).toString();
			signs2 =  baseAllVarSigns.get( ( (IdExpr)arithExpr2 ).toString() );
		}
		else if (arithExpr2 instanceof NumExpr)
			signs2 =  new Signs( ( (NumExpr)arithExpr2 ).getValue() );
		else if (arithExpr2 instanceof ArrayExpr){
			varName2 = ( (ArrayExpr)arithExpr2 ).toString();
			signs2 =  baseAllVarSigns.get( ( (ArrayExpr)arithExpr2 ).getName() );
		}
		else signs2 = arithExprSigns(arithExpr2);
		
		//Summing signs (table 3.3 >=)
		trueSigns1 = new Signs("null");
		trueSigns2 = new Signs("null");
		for(Sign sign1 : signs1.getSigns()){
			for(Sign sign2 : signs2.getSigns()){
				switch(sign1){
					case minus: 
						switch(sign2){
							case minus: 
								trueSigns1.add(Sign.minus);
								trueSigns2.add(Sign.minus);
								break;
							case zero: 	
							case plus: 
								break;
							default: assert false : "default in swith!"; 
						}
					break;
					
					case zero: 
						switch(sign2){
							case minus: 
								trueSigns1.add(Sign.zero);
								trueSigns2.add(Sign.minus);
								break;
							case zero: 
								trueSigns1.add(Sign.zero);
								trueSigns2.add(Sign.zero);
								break;
							case plus: 
								break;
							default: assert false : "default in swith!"; 
						}
					break;
					
					case plus:
						switch(sign2){
							case minus: 
								trueSigns1.add(Sign.plus);
								trueSigns2.add(Sign.minus);
								break;
							case zero: 
								trueSigns1.add(Sign.plus);
								trueSigns2.add(Sign.zero);
								break;
							case plus: 
								trueSigns1.add(Sign.plus);
								trueSigns2.add(Sign.plus);
								break;
							default: assert false : "default in swith!"; 
						}
					break;
					default: assert false : "default in swith!"; 
				}
			}
		}
		
		if(trueSigns1.isAny() && trueSigns2.isAny()){
			HashMap<String, Signs> updateToAllVarSigns = new  HashMap<String, Signs>(this.baseAllVarSigns);	
			if(varName1!=null)//update signs if we were dealing with var or Array
				updateToAllVarSigns.put(varName1,trueSigns1);
			if(varName2!=null)
				updateToAllVarSigns.put(varName2,trueSigns1);
			return updateToAllVarSigns;	
		}
		else 
			return null;
	}
	
	//reduce signs only if variable at least on one side
	HashMap<String, Signs> equalsExprSignsReduction(EqualsExpr equalsExpr){
		ArithExpr arithExpr1 = equalsExpr.getExpression1();
		ArithExpr arithExpr2 = equalsExpr.getExpression2();
		Signs signs1, signs2;
		String varName1 = null, varName2 = null;
		Signs trueSigns1,trueSigns2;
				
		//Signs for expr1
		if(arithExpr1 instanceof IdExpr){
			varName1 = ( (IdExpr)arithExpr1 ).toString();
			signs1 =  baseAllVarSigns.get( ( (IdExpr)arithExpr1 ).toString() );
		}
		else if (arithExpr1 instanceof NumExpr)
			signs1 =  new Signs( ( (NumExpr)arithExpr1 ).getValue() );
		else if (arithExpr1 instanceof ArrayExpr){
			varName1 = ( (ArrayExpr)arithExpr1 ).toString();
			signs1 =  baseAllVarSigns.get( ( (ArrayExpr)arithExpr1 ).getName() );
		}
		else signs1 = arithExprSigns(arithExpr1);
		
		//Signs for expr2
		if(arithExpr2 instanceof IdExpr){
			varName2 = ( (IdExpr)arithExpr2 ).toString();
			signs2 =  baseAllVarSigns.get( ( (IdExpr)arithExpr2 ).toString() );
		}
		else if (arithExpr2 instanceof NumExpr)
			signs2 =  new Signs( ( (NumExpr)arithExpr2 ).getValue() );
		else if (arithExpr2 instanceof ArrayExpr){
			varName2 = ( (ArrayExpr)arithExpr2 ).toString();
			signs2 =  baseAllVarSigns.get( ( (ArrayExpr)arithExpr2 ).getName() );
		}
		else signs2 = arithExprSigns(arithExpr2);
		
		//Summing signs (table 3.3 =)
		trueSigns1 = new Signs("null");
		trueSigns2 = new Signs("null");
		for(Sign sign1 : signs1.getSigns()){
			for(Sign sign2 : signs2.getSigns()){
				switch(sign1){
					case minus: 
						switch(sign2){
							case minus: 
								trueSigns1.add(Sign.minus);
								trueSigns2.add(Sign.minus);
								break;
							case zero: 	
							case plus: 
								break;
							default: assert false : "default in swith!"; 
						}
					break;
					
					case zero: 
						switch(sign2){
							case minus: 
								break;
							case zero: 
								trueSigns1.add(Sign.zero);
								trueSigns2.add(Sign.zero);
								break;
							case plus: 
								break;
							default: assert false : "default in swith!"; 
						}
					break;
					
					case plus:
						switch(sign2){
							case minus: 
								break;
							case zero: 
								break;
							case plus: 
								trueSigns1.add(Sign.plus);
								trueSigns2.add(Sign.plus);
								break;
							default: assert false : "default in swith!"; 
						}
					break;
					default: assert false : "default in swith!"; 
				}
			}
		}
		
		if(trueSigns1.isAny() && trueSigns2.isAny()){
			HashMap<String, Signs> updateToAllVarSigns = new  HashMap<String, Signs>(this.baseAllVarSigns);	
			if(varName1!=null)//update signs if we were dealing with var or Array
				updateToAllVarSigns.put(varName1,trueSigns1);
			if(varName2!=null)
				updateToAllVarSigns.put(varName2,trueSigns1);
			return updateToAllVarSigns;	
		}
		else 
			return null;
	}
	
	//reduce signs only if variable at least on one side
	HashMap<String, Signs> notEqualsExprSignsReduction(NotEqualsExpr notEqualsExpr){
		ArithExpr arithExpr1 = notEqualsExpr.getExpression1();
		ArithExpr arithExpr2 = notEqualsExpr.getExpression2();
		Signs signs1, signs2;
		String varName1 = null, varName2 = null;
		Signs trueSigns1,trueSigns2;
				
		//Signs for expr1
		if(arithExpr1 instanceof IdExpr){
			varName1 = ( (IdExpr)arithExpr1 ).toString();
			signs1 =  baseAllVarSigns.get( ( (IdExpr)arithExpr1 ).toString() );
		}
		else if (arithExpr1 instanceof NumExpr)
			signs1 =  new Signs( ( (NumExpr)arithExpr1 ).getValue() );
		else if (arithExpr1 instanceof ArrayExpr){
			varName1 = ( (ArrayExpr)arithExpr1 ).toString();
			signs1 =  baseAllVarSigns.get( ( (ArrayExpr)arithExpr1 ).getName() );
		}
		else signs1 = arithExprSigns(arithExpr1);
		
		//Signs for expr2
		if(arithExpr2 instanceof IdExpr){
			varName2 = ( (IdExpr)arithExpr2 ).toString();
			signs2 =  baseAllVarSigns.get( ( (IdExpr)arithExpr2 ).toString() );
		}
		else if (arithExpr2 instanceof NumExpr)
			signs2 =  new Signs( ( (NumExpr)arithExpr2 ).getValue() );
		else if (arithExpr2 instanceof ArrayExpr){
			varName2 = ( (ArrayExpr)arithExpr2 ).toString();
			signs2 =  baseAllVarSigns.get( ( (ArrayExpr)arithExpr2 ).getName() );
		}
		else signs2 = arithExprSigns(arithExpr2);
		
		//Summing signs (table 3.3 !=)
		trueSigns1 = new Signs("null");
		trueSigns2 = new Signs("null");
		for(Sign sign1 : signs1.getSigns()){
			for(Sign sign2 : signs2.getSigns()){
				switch(sign1){
					case minus: 
						switch(sign2){
							case minus: 
								trueSigns1.add(Sign.minus);
								trueSigns2.add(Sign.minus);
								break;
							case zero: 								
								trueSigns1.add(Sign.minus);
								trueSigns2.add(Sign.zero);
								break;	
							case plus: 								
								trueSigns1.add(Sign.minus);
								trueSigns2.add(Sign.plus);
								break;
							default: assert false : "default in swith!"; 
						}
					break;
					
					case zero: 
						switch(sign2){
							case minus: 
								trueSigns1.add(Sign.zero);
								trueSigns2.add(Sign.minus);
								break;
							case zero: 
								break;
							case plus: 
								trueSigns1.add(Sign.zero);
								trueSigns2.add(Sign.plus);
								break;
							default: assert false : "default in swith!"; 
						}
					break;
					
					case plus:
						switch(sign2){
							case minus: 
								trueSigns1.add(Sign.plus);
								trueSigns2.add(Sign.minus);
								break;
							case zero: 
								trueSigns1.add(Sign.plus);
								trueSigns2.add(Sign.zero);
								break;
							case plus: 
								trueSigns1.add(Sign.plus);
								trueSigns2.add(Sign.plus);
								break;
							default: assert false : "default in swith!"; 
						}
					break;
					default: assert false : "default in swith!"; 
				}
			}
		}
		
		if(trueSigns1.isAny() && trueSigns2.isAny()){
			HashMap<String, Signs> updateToAllVarSigns = new  HashMap<String, Signs>(this.baseAllVarSigns);	
			if(varName1!=null)//update signs if we were dealing with var or Array
				updateToAllVarSigns.put(varName1,trueSigns1);
			if(varName2!=null)
				updateToAllVarSigns.put(varName2,trueSigns1);
			return updateToAllVarSigns;	
		}
		else 
			return null;
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
			strSigns = strSigns.substring(0, strSigns.length() -1 );
			str += entry.getKey() + "={" + strSigns + "};\n";
		}
		return str;
	}


}

