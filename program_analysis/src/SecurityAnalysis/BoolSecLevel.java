package SecurityAnalysis;

import java.util.HashMap;
import java.util.Map;

import ast.arith.ArithExpr;
import ast.arith.ArrayExpr;
import ast.arith.DivExpr;
import ast.arith.IdExpr;
import ast.arith.MinusExpr;
import ast.arith.MultExpr;
import ast.arith.NumExpr;
import ast.arith.PlusExpr;
import ast.bool.AndExpr;
import ast.bool.BoolExpr;
import ast.bool.BoolValueExpr;
import ast.bool.EqualsExpr;
import ast.bool.GreaterThanEqualsExpr;
import ast.bool.GreaterThanExpr;
import ast.bool.LessThanEqualsExpr;
import ast.bool.LessThanExpr;
import ast.bool.NotEqualsExpr;
import ast.bool.NotExpr;
import ast.bool.OrExpr;

public class BoolSecLevel {
	
	private HashMap<String, SecLevel> baseAllVarSecLevel; //input secLevel
	public HashMap<String, SecLevel> newAllVarSecLevel;   // secLevel after transfer function 
	
	public BoolSecLevel(BoolExpr boolExpr,HashMap<String, SecLevel> baseElemSecLevel){
		
		newAllVarSecLevel = Func.deepLineCopy(baseElemSecLevel);
		this.baseAllVarSecLevel = Func.deepLineCopy(baseElemSecLevel);
		
		if((boolExpr instanceof LessThanExpr)||(boolExpr instanceof LessThanEqualsExpr)
				||(boolExpr instanceof GreaterThanExpr)||(boolExpr instanceof GreaterThanEqualsExpr)
				||(boolExpr instanceof EqualsExpr)||(boolExpr instanceof NotEqualsExpr))
				relatOperSecLevel ((BoolExpr)boolExpr);
		else if (boolExpr instanceof OrExpr)
			orExprHoldsSecLevel((OrExpr) boolExpr);
		else if (boolExpr instanceof AndExpr)
			andExprHoldsSecLevel((AndExpr) boolExpr);
		else if(boolExpr instanceof BoolValueExpr){
				//if(((BoolValueExpr) boolExpr).getBoolValue() == false ){
				//	newAllVarSecLevel = null;
				//}
			}
		 else if (boolExpr instanceof NotExpr) 
			 notExprSecLevel ((NotExpr)boolExpr);
		else 
			assert false : "Error in function BoolDetectionOfSign(), shouldn't reach it. Check did you forget to add smth?";
		
	}
		
	boolean orExprHoldsSecLevel(OrExpr orExpr){
		BoolExpr boolExpr1 = orExpr.getExpression1();
		BoolExpr boolExpr2 = orExpr.getExpression2();
		HashMap<String, SecLevel> secLevel1 =null, secLevel2 = null;
		boolean value1, value2;
		
		secLevel1 = new BoolSecLevel(boolExpr1, baseAllVarSecLevel).getNewAllVarSecLevel();
		value1 = secLevel1 == null ? false : true;
		
		secLevel2 = new BoolSecLevel(boolExpr2, baseAllVarSecLevel).getNewAllVarSecLevel();
		value2 = secLevel2 == null ? false : true;
		
		if (value1 || value2 ){
			newAllVarSecLevel =  mergeSecLevel("mergeUnion",secLevel1,secLevel2);
			return true;
		}
		else {
			newAllVarSecLevel = null;
			return false;
		}
	}
	
	boolean andExprHoldsSecLevel(AndExpr andExpr){
		BoolExpr boolExpr1 = andExpr.getExpression1();
		BoolExpr boolExpr2 = andExpr.getExpression2();
		HashMap<String, SecLevel> secLevel1 =null, secLevel2 = null;
		boolean value1, value2;
		
		secLevel1 = new BoolSecLevel(boolExpr1, baseAllVarSecLevel).getNewAllVarSecLevel();
		value1 = secLevel1 == null ? false : true;
		
		secLevel2 = new BoolSecLevel(boolExpr2, baseAllVarSecLevel).getNewAllVarSecLevel();
		value2 = secLevel2 == null ? false : true;
		
		if (value1 && value2 ){
			newAllVarSecLevel =  mergeSecLevel("mergeIntersection",secLevel1,secLevel2);
			return true;
		}
		else {
			newAllVarSecLevel = null;
			return false;
		}
	}
	
	//reduce secLevel only if variable at least on one side
	void relatOperSecLevel (BoolExpr boolExpr){
				
		ArithExpr arithExpr1 = null;
		ArithExpr arithExpr2 = null;
		if ( boolExpr instanceof LessThanExpr){	
			arithExpr1 = ((LessThanExpr)boolExpr).getExpression1();
			arithExpr2 = ((LessThanExpr)boolExpr).getExpression2();
		} else if ( boolExpr instanceof LessThanEqualsExpr){	
			arithExpr1 = ((LessThanEqualsExpr)boolExpr).getExpression1();
			arithExpr2 = ((LessThanEqualsExpr)boolExpr).getExpression2();
		} else if ( boolExpr instanceof GreaterThanExpr){	
			arithExpr1 = ((GreaterThanExpr)boolExpr).getExpression1();
			arithExpr2 = ((GreaterThanExpr)boolExpr).getExpression2();
		} else if ( boolExpr instanceof GreaterThanEqualsExpr){	
			arithExpr1 = ((GreaterThanEqualsExpr)boolExpr).getExpression1();
			arithExpr2 = ((GreaterThanEqualsExpr)boolExpr).getExpression2();
		} else if ( boolExpr instanceof EqualsExpr){	
			arithExpr1 = ((EqualsExpr)boolExpr).getExpression1();
			arithExpr2 = ((EqualsExpr)boolExpr).getExpression2();
		} else if ( boolExpr instanceof NotEqualsExpr){	
			arithExpr1 = ((NotEqualsExpr)boolExpr).getExpression1();
			arithExpr2 = ((NotEqualsExpr)boolExpr).getExpression2();
		}else assert false : "Error in function arithOperSecLevel(), shouldn't reach it.";
		
		SecLevel secLevel1, secLevel2;
		SecLevel  resSecLevel;
		
		SecLevel ctxSecLevel = baseAllVarSecLevel.get(SecCtx.CTX.getSecCtx());
		//SecLevel for expr1
		if(arithExpr1 instanceof IdExpr){
			secLevel1 =  baseAllVarSecLevel.get( ( (IdExpr)arithExpr1 ).toString() );
		}
		else if (arithExpr1 instanceof NumExpr)
			secLevel1 =  SecLevel.low;
		else if (arithExpr1 instanceof ArrayExpr){
			secLevel1 =  baseAllVarSecLevel.get( ( (ArrayExpr)arithExpr1 ).getName() );
		}
		else secLevel1 = new ArithSec( arithExpr1, baseAllVarSecLevel).getSecLevel();
		
		
		//SecLevel for expr2
		if(arithExpr2 instanceof IdExpr){
			secLevel2 =  baseAllVarSecLevel.get( ( (IdExpr)arithExpr2 ).toString() );
		}
		else if (arithExpr2 instanceof NumExpr)
			secLevel2 =  SecLevel.low;
		else if (arithExpr2 instanceof ArrayExpr){
			secLevel2 =  baseAllVarSecLevel.get( ( (ArrayExpr)arithExpr2 ).getName() );
		}
		else secLevel2 = new ArithSec( arithExpr2, baseAllVarSecLevel).getSecLevel();
		
		//SecLevelWorklist.secLevelCtxBeforeBools.put
		resSecLevel = (secLevel1 == SecLevel.high)|| (secLevel2 == SecLevel.high) || (ctxSecLevel == SecLevel.high)
				? SecLevel.high : SecLevel.low;
		//TODO check if it can be none and implement context

		if(resSecLevel == SecLevel.high){
			ctxSecLevel = SecLevel.high;
			newAllVarSecLevel.put(SecCtx.CTX.getSecCtx(),ctxSecLevel);
		}
		
	}
	
	public HashMap<String, SecLevel> getNewAllVarSecLevel(){
		return newAllVarSecLevel;
	}

	private void notExprSecLevel (NotExpr exp) {

		BoolExpr insideExpr = exp.getExpression();
		BoolExpr newExpr = null;

		if (insideExpr instanceof AndExpr) {
			newExpr = new OrExpr(
					((AndExpr) insideExpr).getExpression1(), 
					((AndExpr) insideExpr).getExpression2());

		} else if (insideExpr instanceof OrExpr) {
			newExpr = new AndExpr(
					((OrExpr) insideExpr).getExpression1(),
					((OrExpr) insideExpr).getExpression2());
		} else if (insideExpr instanceof BoolValueExpr) {
			if (((BoolValueExpr) insideExpr).getBoolValue() == true)
				newExpr = new BoolValueExpr(false);
			else
				newExpr = new BoolValueExpr(true);
		} else if (insideExpr instanceof EqualsExpr) {
			newExpr = new NotEqualsExpr(
					((EqualsExpr) insideExpr).getExpression1(),
					((EqualsExpr) insideExpr).getExpression2());
		} else if (insideExpr instanceof NotEqualsExpr) {
			newExpr = new EqualsExpr(
					((NotEqualsExpr) insideExpr).getExpression1(),
					((NotEqualsExpr) insideExpr).getExpression2());
		} else if (insideExpr instanceof GreaterThanEqualsExpr) {
			newExpr = new LessThanExpr(
					((GreaterThanEqualsExpr) insideExpr).getExpression1(),
					((GreaterThanEqualsExpr) insideExpr).getExpression2());
		} else if (insideExpr instanceof GreaterThanExpr) {
			newExpr = new LessThanEqualsExpr(
					((GreaterThanExpr) insideExpr).getExpression1(),
					((GreaterThanExpr) insideExpr).getExpression2());
		} else if (insideExpr instanceof LessThanEqualsExpr) {
			newExpr = new GreaterThanExpr(
					((LessThanEqualsExpr) insideExpr).getExpression1(),
					((LessThanEqualsExpr) insideExpr).getExpression2());
		} else if (insideExpr instanceof LessThanExpr) {
			newExpr = new GreaterThanEqualsExpr(
					((LessThanExpr) insideExpr).getExpression1(),
					((LessThanExpr) insideExpr).getExpression2());
		} else if (insideExpr instanceof NotExpr) {
			newExpr = insideExpr;
		}else {
			assert false : "Error in function NotExpr(), shouldn't reach it. Check did you forget to add smth?";
		}

		this.newAllVarSecLevel = new BoolSecLevel(newExpr, baseAllVarSecLevel)
												.getNewAllVarSecLevel();

	}
	
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
			secLevel.put(entry.getKey(),  
								(entry.getValue() == SecLevel.high)|| 
								(secLevel2.get(entry.getKey()) == SecLevel.high) 
												? SecLevel.high : SecLevel.low);
								//TODO check if it can be none
		
		return secLevel;
	}
	 
/*	 HashMap<String, SecLevel> setOffAllSecLevel(HashMap<String, SecLevel> secLevel){
		 HashMap<String, SecLevel> newSecLevel = new HashMap<String, SecLevel>();
		 for (Map.Entry<String,SecLevel> entry : secLevel.entrySet())
			 secLevel.put(entry.getKey(), new SecLevel("null")  );
		 return newSecLevel;
	 } */
}
