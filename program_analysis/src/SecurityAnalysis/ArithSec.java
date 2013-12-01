package SecurityAnalysis;



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

public class ArithSec {
	
	private SecLevel arithExprSecLevel;
	private HashMap<String, SecLevel> baseAllVarSecLevel;
	
	
	public ArithSec(ArithExpr arithExpr,HashMap<String, SecLevel> baseElemSecLevel){
		
		baseAllVarSecLevel = Func.deepLineCopy(baseElemSecLevel);

		arithExprSecLevel  = arithExprSecLevel(arithExpr);

	}
	
	SecLevel arithExprSecLevel(ArithExpr arithExpr){
		
		if(arithExpr instanceof IdExpr) //Variable
			return baseAllVarSecLevel.get( ( (IdExpr)arithExpr ).toString() );
		else if (arithExpr instanceof NumExpr) //Number
			return  SecLevel.low;
		else if (arithExpr instanceof ParenExpr) //ParenExpr
			return arithExprSecLevel(arithExpr); 
		else if (( arithExpr instanceof PlusExpr) ||( arithExpr instanceof MinusExpr) // PlusExpr, MinusExpr, MultExpr,DivExpr
				||( arithExpr instanceof MultExpr)||( arithExpr instanceof DivExpr))
				return arithOperSecLevel((ArithExpr) arithExpr);
		else if ( arithExpr instanceof UnMinExpr) // UnMinExpr
			return unMinExprSecLevel((UnMinExpr) arithExpr);
		
		assert false : "Error in function exprSecLevel(), shouldn't reach it. Check did you forget to add smth?";
		return null;	
	}
	
	SecLevel arithOperSecLevel(ArithExpr arithExpr){
		ArithExpr arithExpr1 = null;
		ArithExpr arithExpr2 = null;
		if ( arithExpr instanceof PlusExpr){	
			arithExpr1 = ((PlusExpr)arithExpr).getExpression1();
			arithExpr2 = ((PlusExpr)arithExpr).getExpression2();
		} else if ( arithExpr instanceof MinusExpr){	
			arithExpr1 = ((MinusExpr)arithExpr).getExpression1();
			arithExpr2 = ((MinusExpr)arithExpr).getExpression2();
		} else if ( arithExpr instanceof MultExpr){	
			arithExpr1 = ((MultExpr)arithExpr).getExpression1();
			arithExpr2 = ((MultExpr)arithExpr).getExpression2();
		} else if ( arithExpr instanceof DivExpr){	
			arithExpr1 = ((DivExpr)arithExpr).getExpression1();
			arithExpr2 = ((DivExpr)arithExpr).getExpression2();
		}else assert false : "Error in function arithOperSecLevel(), shouldn't reach it.";
				
			
		SecLevel secLevel1, secLevel2, resultSecLevel;
		
		//SecLevel for expr1
		if(arithExpr1 instanceof IdExpr)
			secLevel1 =  baseAllVarSecLevel.get( ( (IdExpr)arithExpr1 ).toString() );
		else if (arithExpr1 instanceof NumExpr)
			secLevel1 =  SecLevel.low;
		else if (arithExpr1 instanceof ArrayExpr)
			secLevel1 =  baseAllVarSecLevel.get( ( (ArrayExpr)arithExpr1 ).getName() );
		else secLevel1 = arithExprSecLevel(arithExpr1);
		
		
		//SecLevel for expr2
		if(arithExpr2 instanceof IdExpr)
			secLevel2 =  baseAllVarSecLevel.get( ( (IdExpr)arithExpr2 ).toString() );
		else if (arithExpr2 instanceof NumExpr)
			secLevel2 =  SecLevel.low;
		else if (arithExpr2 instanceof ArrayExpr)
			secLevel2 =  baseAllVarSecLevel.get( ( (ArrayExpr)arithExpr2 ).getName() );
		else secLevel2 = arithExprSecLevel(arithExpr2);
		
		//Summing secLevel (table 3.1 +)
		resultSecLevel = (secLevel2 == SecLevel.high)||(secLevel1 == SecLevel.high) 
						? SecLevel.high : SecLevel.low;
		
		return resultSecLevel;
	}
	
	SecLevel unMinExprSecLevel(UnMinExpr unMinExpr){
		ArithExpr arithExpr = unMinExpr.getExpression();
		SecLevel secLevel, resultSecLevel;
		
		//SecLevel for expr
		if(arithExpr instanceof IdExpr)
			secLevel =  baseAllVarSecLevel.get( ( (IdExpr)arithExpr ).toString() );
		else if (arithExpr instanceof NumExpr)
			secLevel =  SecLevel.low;
		else if (arithExpr instanceof ArrayExpr)
			secLevel =  baseAllVarSecLevel.get( ( (ArrayExpr)arithExpr ).getName() );
		else secLevel = arithExprSecLevel(arithExpr);
		
		resultSecLevel = secLevel;
		return resultSecLevel;
	}
	

	SecLevel getSecLevel(){
		return arithExprSecLevel;
	}


}

