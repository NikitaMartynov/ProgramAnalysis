package detectionOfSign_analysis;



import java.util.HashMap;

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

public class ArithDetectionOfSign {
	
	private Signs arithExprSigns;
	private HashMap<String, Signs> baseAllVarSigns;
	
/*	public void initialize (Vector<String> variables){
		
		elemSigns = new HashMap<String, Signs>();
		//assert ProgramGraph.edges != null : "Program graph edges not buit at initialization of Detection Of Signs";
		
		for( String var : variables){
			if(!elemSigns.containsKey(var)) elemSigns.put(var, new Signs());
		}	
	}*/
	
	public ArithDetectionOfSign(ArithExpr arithExpr,HashMap<String, Signs> baseElemSigns){
		
		baseAllVarSigns = baseElemSigns;

		arithExprSigns  = arithExprSigns(arithExpr);

	}
	
	
	
	Signs arithExprSigns(ArithExpr arithExpr){
		
		if(arithExpr instanceof IdExpr) //Variable
			return baseAllVarSigns.get( ( (IdExpr)arithExpr ).toString() );
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
			signs1 =  baseAllVarSigns.get( ( (IdExpr)arithExpr1 ).toString() );
		else if (arithExpr1 instanceof NumExpr)
			signs1 =  new Signs( ( (NumExpr)arithExpr1 ).getValue() );
		else if (arithExpr1 instanceof ArrayExpr)
			signs1 =  baseAllVarSigns.get( ( (ArrayExpr)arithExpr1 ).getName() );
		else signs1 = arithExprSigns(arithExpr1);
		
		
		//Signs for expr2
		if(arithExpr2 instanceof IdExpr)
			signs2 =  baseAllVarSigns.get( ( (IdExpr)arithExpr2 ).toString() );
		else if (arithExpr2 instanceof NumExpr)
			signs2 =  new Signs( ( (NumExpr)arithExpr2 ).getValue() );
		else if (arithExpr2 instanceof ArrayExpr)
			signs2 =  baseAllVarSigns.get( ( (ArrayExpr)arithExpr2 ).getName() );
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
			signs1 =  baseAllVarSigns.get( ( (IdExpr)arithExpr1 ).toString() );
		else if (arithExpr1 instanceof NumExpr)
			signs1 =  new Signs( ( (NumExpr)arithExpr1 ).getValue() );
		else if (arithExpr1 instanceof ArrayExpr)
			signs1 =  baseAllVarSigns.get( ( (ArrayExpr)arithExpr1 ).getName() );
		else signs1 = arithExprSigns(arithExpr1);
		
		//Signs for expr2
		if(arithExpr2 instanceof IdExpr)
			signs2 =  baseAllVarSigns.get( ( (IdExpr)arithExpr2 ).toString() );
		else if (arithExpr2 instanceof NumExpr)
			signs2 =  new Signs( ( (NumExpr)arithExpr2 ).getValue() );
		else if (arithExpr2 instanceof ArrayExpr)
			signs2 =  baseAllVarSigns.get( ( (ArrayExpr)arithExpr2 ).getName() );
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
			signs =  baseAllVarSigns.get( ( (IdExpr)arithExpr ).toString() );
		else if (arithExpr instanceof NumExpr)
			signs =  new Signs( ( (NumExpr)arithExpr ).getValue() );
		else if (arithExpr instanceof ArrayExpr)
			signs =  baseAllVarSigns.get( ( (ArrayExpr)arithExpr ).getName() );
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
			signs1 =  baseAllVarSigns.get( ( (IdExpr)arithExpr1 ).toString() );
		else if (arithExpr1 instanceof NumExpr)
			signs1 =  new Signs( ( (NumExpr)arithExpr1 ).getValue() );
		else if (arithExpr1 instanceof ArrayExpr)
			signs1 =  baseAllVarSigns.get( ( (ArrayExpr)arithExpr1 ).getName() );
		else signs1 = arithExprSigns(arithExpr1);
		
		//Signs for expr2
		if(arithExpr2 instanceof IdExpr)
			signs2 =  baseAllVarSigns.get( ( (IdExpr)arithExpr2 ).toString() );
		else if (arithExpr2 instanceof NumExpr)
			signs2 =  new Signs( ( (NumExpr)arithExpr2 ).getValue() );
		else if (arithExpr2 instanceof ArrayExpr)
			signs2 =  baseAllVarSigns.get( ( (ArrayExpr)arithExpr2 ).getName() );
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
			signs1 =  baseAllVarSigns.get( ( (IdExpr)arithExpr1 ).toString() );
		else if (arithExpr1 instanceof NumExpr)
			signs1 =  new Signs( ( (NumExpr)arithExpr1 ).getValue() );
		else if (arithExpr1 instanceof ArrayExpr)
			signs1 =  baseAllVarSigns.get( ( (ArrayExpr)arithExpr1 ).getName() );
		else signs1 = arithExprSigns(arithExpr1);
		
		//Signs for expr2
		if(arithExpr2 instanceof IdExpr)
			signs2 =  baseAllVarSigns.get( ( (IdExpr)arithExpr2 ).toString() );
		else if (arithExpr2 instanceof NumExpr)
			signs2 =  new Signs( ( (NumExpr)arithExpr2 ).getValue() );
		else if (arithExpr2 instanceof ArrayExpr)
			signs2 =  baseAllVarSigns.get( ( (ArrayExpr)arithExpr2 ).getName() );
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

	Signs getSigns(){
		return arithExprSigns;
	}


}

