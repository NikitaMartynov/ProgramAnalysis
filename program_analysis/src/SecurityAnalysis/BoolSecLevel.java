package SecurityAnalysis;

import java.util.HashMap;
import java.util.Map;

import ast.arith.ArithExpr;
import ast.arith.ArrayExpr;
import ast.arith.IdExpr;
import ast.arith.NumExpr;
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
		
		if(boolExpr instanceof LessThanExpr)
			lessThanExprSecLevel ((LessThanExpr)boolExpr);
		else if(boolExpr instanceof LessThanEqualsExpr)
			lessThanEqualsExprSecLevel ((LessThanEqualsExpr)boolExpr);
		else if(boolExpr instanceof GreaterThanExpr)
			greaterThanExprSecLevel ((GreaterThanExpr)boolExpr);
		else if(boolExpr instanceof GreaterThanEqualsExpr)
			greaterThanEqualsExprSecLevel ((GreaterThanEqualsExpr)boolExpr);
		else if(boolExpr instanceof EqualsExpr)
			equalsExprSecLevel ((EqualsExpr)boolExpr);
		else if(boolExpr instanceof NotEqualsExpr)
			notEqualsExprSecLevel ((NotEqualsExpr)boolExpr);
		else if (boolExpr instanceof OrExpr)
			orExprHoldsSecLevel((OrExpr) boolExpr);
		else if (boolExpr instanceof AndExpr)
			andExprHoldsSecLevel((AndExpr) boolExpr);
		else if(boolExpr instanceof BoolValueExpr){
				if(((BoolValueExpr) boolExpr).getBoolValue() == false ){
					newAllVarSecLevel = null;
				}
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
	boolean lessThanExprSecLevel (LessThanExpr lessThanExpr){
		ArithExpr arithExpr1 = lessThanExpr.getExpression1();
		ArithExpr arithExpr2 = lessThanExpr.getExpression2();
		SecLevel secLevel1, secLevel2;
		String varName1 = null, varName2 = null;
		SecLevel ctxSecLevel;
		
		//SecLevel for expr1
		if(arithExpr1 instanceof IdExpr){
			varName1 = ( (IdExpr)arithExpr1 ).toString();
			secLevel1 =  baseAllVarSecLevel.get( ( (IdExpr)arithExpr1 ).toString() );
		}
		else if (arithExpr1 instanceof NumExpr)
			secLevel1 =  SecLevel.low;
		else if (arithExpr1 instanceof ArrayExpr){
			varName1 = ( (ArrayExpr)arithExpr1 ).getName();
			secLevel1 =  baseAllVarSecLevel.get( ( (ArrayExpr)arithExpr1 ).getName() );
		}
		else secLevel1 = new ArithSec( arithExpr1, baseAllVarSecLevel).getSecLevel();
		
		
		//SecLevel for expr2
		if(arithExpr2 instanceof IdExpr){
			varName2 = ( (IdExpr)arithExpr2 ).toString();
			secLevel2 =  baseAllVarSecLevel.get( ( (IdExpr)arithExpr2 ).toString() );
		}
		else if (arithExpr2 instanceof NumExpr)
			secLevel2 =  SecLevel.low;
		else if (arithExpr2 instanceof ArrayExpr){
			varName2 = ( (ArrayExpr)arithExpr2 ).getName();
			secLevel2 =  baseAllVarSecLevel.get( ( (ArrayExpr)arithExpr2 ).getName() );
		}
		else secLevel2 = new ArithSec( arithExpr2, baseAllVarSecLevel).getSecLevel();
		
		ctxSecLevel = (secLevel2 == SecLevel.high)|| (secLevel2 == SecLevel.high) 
				? SecLevel.high : SecLevel.low;
		//TODO check if it can be none and implement context

		if(ctxSecLevel == SecLevel.high){
/*			if(varName1!=null)//update secLevel if we were dealing with var or Array
				newAllVarSecLevel.put(varName1,trueSecLevel1);
			if(varName2!=null)
				newAllVarSecLevel.put(varName2,trueSecLevel1);
			*/return true;	
		}
		else {
			newAllVarSecLevel = null;	
			return false;
		}		
	}
	
	//reduce secLevel only if variable at least on one side
	boolean lessThanEqualsExprSecLevel (LessThanEqualsExpr lessThanEqualsExpr){
		ArithExpr arithExpr1 = lessThanEqualsExpr.getExpression1();
		ArithExpr arithExpr2 = lessThanEqualsExpr.getExpression2();
		SecLevel secLevel1, secLevel2;
		String varName1 = null, varName2 = null;
		SecLevel ctxSecLevel;
		
		//SecLevel for expr1
		if(arithExpr1 instanceof IdExpr){
			varName1 = ( (IdExpr)arithExpr1 ).toString();
			secLevel1 =  baseAllVarSecLevel.get( ( (IdExpr)arithExpr1 ).toString() );
		}
		else if (arithExpr1 instanceof NumExpr)
			secLevel1 =  SecLevel.low;
		else if (arithExpr1 instanceof ArrayExpr){
			varName1 = ( (ArrayExpr)arithExpr1 ).getName();
			secLevel1 =  baseAllVarSecLevel.get( ( (ArrayExpr)arithExpr1 ).getName() );
		}
		else secLevel1 = new ArithSec( arithExpr1, baseAllVarSecLevel).getSecLevel();
		
		
		//SecLevel for expr2
		if(arithExpr2 instanceof IdExpr){
			varName2 = ( (IdExpr)arithExpr2 ).toString();
			secLevel2 =  baseAllVarSecLevel.get( ( (IdExpr)arithExpr2 ).toString() );
		}
		else if (arithExpr2 instanceof NumExpr)
			secLevel2 =  SecLevel.low;
		else if (arithExpr2 instanceof ArrayExpr){
			varName2 = ( (ArrayExpr)arithExpr2 ).getName();
			secLevel2 =  baseAllVarSecLevel.get( ( (ArrayExpr)arithExpr2 ).getName() );
		}
		else secLevel2 = new ArithSec( arithExpr2, baseAllVarSecLevel).getSecLevel();
		
		ctxSecLevel = (secLevel2 == SecLevel.high)|| (secLevel2 == SecLevel.high) 
				? SecLevel.high : SecLevel.low;
		

		if(ctxSecLevel == SecLevel.high){
/*			if(varName1!=null)//update secLevel if we were dealing with var or Array
				newAllVarSecLevel.put(varName1,trueSecLevel1);
			if(varName2!=null)
				newAllVarSecLevel.put(varName2,trueSecLevel1);
			*/return true;	
		}
		else {
			newAllVarSecLevel = null;	
			return false;
		}
	}
	
	//reduce secLevel only if variable at least on one side
	boolean greaterThanExprSecLevel (GreaterThanExpr greaterThanExpr){
		ArithExpr arithExpr1 = greaterThanExpr.getExpression1();
		ArithExpr arithExpr2 = greaterThanExpr.getExpression2();
		SecLevel secLevel1, secLevel2;
		String varName1 = null, varName2 = null;
		SecLevel ctxSecLevel;
				
		//SecLevel for expr1
		if(arithExpr1 instanceof IdExpr){
			varName1 = ( (IdExpr)arithExpr1 ).toString();
			secLevel1 =  baseAllVarSecLevel.get( ( (IdExpr)arithExpr1 ).toString() );
		}
		else if (arithExpr1 instanceof NumExpr)
			secLevel1 =  SecLevel.low;
		else if (arithExpr1 instanceof ArrayExpr){
			varName1 = ( (ArrayExpr)arithExpr1 ).getName();
			secLevel1 =  baseAllVarSecLevel.get( ( (ArrayExpr)arithExpr1 ).getName() );
		}
		else secLevel1 = new ArithSec( arithExpr1, baseAllVarSecLevel).getSecLevel();
		
		
		//SecLevel for expr2
		if(arithExpr2 instanceof IdExpr){
			varName2 = ( (IdExpr)arithExpr2 ).toString();
			secLevel2 =  baseAllVarSecLevel.get( ( (IdExpr)arithExpr2 ).toString() );
		}
		else if (arithExpr2 instanceof NumExpr)
			secLevel2 =  SecLevel.low;
		else if (arithExpr2 instanceof ArrayExpr){
			varName2 = ( (ArrayExpr)arithExpr2 ).getName();
			secLevel2 =  baseAllVarSecLevel.get( ( (ArrayExpr)arithExpr2 ).getName() );
		}
		else secLevel2 = new ArithSec( arithExpr2, baseAllVarSecLevel).getSecLevel();
		
		ctxSecLevel = (secLevel2 == SecLevel.high)|| (secLevel2 == SecLevel.high) 
				? SecLevel.high : SecLevel.low;
		

		if(ctxSecLevel == SecLevel.high){
/*			if(varName1!=null)//update secLevel if we were dealing with var or Array
				newAllVarSecLevel.put(varName1,trueSecLevel1);
			if(varName2!=null)
				newAllVarSecLevel.put(varName2,trueSecLevel1);
			*/return true;	
		}
		else {
			newAllVarSecLevel = null;	
			return false;
		}
	}
	
	//reduce secLevel only if variable at least on one side
	boolean greaterThanEqualsExprSecLevel (GreaterThanEqualsExpr greaterThanEqualsExpr){
		ArithExpr arithExpr1 = greaterThanEqualsExpr.getExpression1();
		ArithExpr arithExpr2 = greaterThanEqualsExpr.getExpression2();
		SecLevel secLevel1, secLevel2;
		String varName1 = null, varName2 = null;
		SecLevel ctxSecLevel;
				
		//SecLevel for expr1
		if(arithExpr1 instanceof IdExpr){
			varName1 = ( (IdExpr)arithExpr1 ).toString();
			secLevel1 =  baseAllVarSecLevel.get( ( (IdExpr)arithExpr1 ).toString() );
		}
		else if (arithExpr1 instanceof NumExpr)
			secLevel1 =  SecLevel.low;
		else if (arithExpr1 instanceof ArrayExpr){
			varName1 = ( (ArrayExpr)arithExpr1 ).getName();
			secLevel1 =  baseAllVarSecLevel.get( ( (ArrayExpr)arithExpr1 ).getName() );
		}
		else secLevel1 = new ArithSec( arithExpr1, baseAllVarSecLevel).getSecLevel();
		
		//SecLevel for expr2
		if(arithExpr2 instanceof IdExpr){
			varName2 = ( (IdExpr)arithExpr2 ).toString();
			secLevel2 =  baseAllVarSecLevel.get( ( (IdExpr)arithExpr2 ).toString() );
		}
		else if (arithExpr2 instanceof NumExpr)
			secLevel2 =  SecLevel.low;
		else if (arithExpr2 instanceof ArrayExpr){
			varName2 = ( (ArrayExpr)arithExpr2 ).getName();
			secLevel2 =  baseAllVarSecLevel.get( ( (ArrayExpr)arithExpr2 ).getName() );
		}
		else secLevel2 = new ArithSec( arithExpr2, baseAllVarSecLevel).getSecLevel();
		
		ctxSecLevel = (secLevel2 == SecLevel.high)|| (secLevel2 == SecLevel.high) 
				? SecLevel.high : SecLevel.low;
		

		if(ctxSecLevel == SecLevel.high){
/*			if(varName1!=null)//update secLevel if we were dealing with var or Array
				newAllVarSecLevel.put(varName1,trueSecLevel1);
			if(varName2!=null)
				newAllVarSecLevel.put(varName2,trueSecLevel1);
			*/return true;
		}
		else {
			newAllVarSecLevel = null;	
			return false;
		}
	}
	
	//reduce secLevel only if variable at least on one side
	boolean equalsExprSecLevel (EqualsExpr equalsExpr){
		ArithExpr arithExpr1 = equalsExpr.getExpression1();
		ArithExpr arithExpr2 = equalsExpr.getExpression2();
		SecLevel secLevel1, secLevel2;
		String varName1 = null, varName2 = null;
		SecLevel ctxSecLevel;
				
		//SecLevel for expr1
		if(arithExpr1 instanceof IdExpr){
			varName1 = ( (IdExpr)arithExpr1 ).toString();
			secLevel1 =  baseAllVarSecLevel.get( ( (IdExpr)arithExpr1 ).toString() );
		}
		else if (arithExpr1 instanceof NumExpr)
			secLevel1 =  SecLevel.low;
		else if (arithExpr1 instanceof ArrayExpr){
			varName1 = ( (ArrayExpr)arithExpr1 ).getName();
			secLevel1 =  baseAllVarSecLevel.get( ( (ArrayExpr)arithExpr1 ).getName() );
		}
		else secLevel1 = new ArithSec( arithExpr1, baseAllVarSecLevel).getSecLevel();
		
		//SecLevel for expr2
		if(arithExpr2 instanceof IdExpr){
			varName2 = ( (IdExpr)arithExpr2 ).toString();
			secLevel2 =  baseAllVarSecLevel.get( ( (IdExpr)arithExpr2 ).toString() );
		}
		else if (arithExpr2 instanceof NumExpr)
			secLevel2 =  SecLevel.low;
		else if (arithExpr2 instanceof ArrayExpr){
			varName2 = ( (ArrayExpr)arithExpr2 ).getName();
			secLevel2 =  baseAllVarSecLevel.get( ( (ArrayExpr)arithExpr2 ).getName() );
		}
		else secLevel2 =new ArithSec( arithExpr2, baseAllVarSecLevel).getSecLevel();
		
		ctxSecLevel = (secLevel2 == SecLevel.high)|| (secLevel2 == SecLevel.high) 
				? SecLevel.high : SecLevel.low;
		

		if(ctxSecLevel == SecLevel.high){
/*			if(varName1!=null)//update secLevel if we were dealing with var or Array
				newAllVarSecLevel.put(varName1,trueSecLevel1);
			if(varName2!=null)
				newAllVarSecLevel.put(varName2,trueSecLevel1);
			*/return true;	
		}
		else {
			newAllVarSecLevel = null;	
			return false;
		}
	}
	
	//reduce secLevel only if variable at least on one side
	boolean notEqualsExprSecLevel (NotEqualsExpr notEqualsExpr){
		ArithExpr arithExpr1 = notEqualsExpr.getExpression1();
		ArithExpr arithExpr2 = notEqualsExpr.getExpression2();
		SecLevel secLevel1, secLevel2;
		String varName1 = null, varName2 = null;
		SecLevel ctxSecLevel;
				
		//SecLevel for expr1
		if(arithExpr1 instanceof IdExpr){
			varName1 = ( (IdExpr)arithExpr1 ).toString();
			secLevel1 =  baseAllVarSecLevel.get( ( (IdExpr)arithExpr1 ).toString() );
		}
		else if (arithExpr1 instanceof NumExpr)
			secLevel1 =  SecLevel.low;
		else if (arithExpr1 instanceof ArrayExpr){
			varName1 = ( (ArrayExpr)arithExpr1 ).getName();
			secLevel1 =  baseAllVarSecLevel.get( ( (ArrayExpr)arithExpr1 ).getName() );
		}
		else secLevel1 = new ArithSec( arithExpr1, baseAllVarSecLevel).getSecLevel();
		
		//SecLevel for expr2
		if(arithExpr2 instanceof IdExpr){
			varName2 = ( (IdExpr)arithExpr2 ).toString();
			secLevel2 =  baseAllVarSecLevel.get( ( (IdExpr)arithExpr2 ).toString() );
		}
		else if (arithExpr2 instanceof NumExpr)
			secLevel2 =  SecLevel.low;
		else if (arithExpr2 instanceof ArrayExpr){
			varName2 = ( (ArrayExpr)arithExpr2 ).getName();
			secLevel2 =  baseAllVarSecLevel.get( ( (ArrayExpr)arithExpr2 ).getName() );
		}
		else secLevel2 = new ArithSec( arithExpr2, baseAllVarSecLevel).getSecLevel();
		
		ctxSecLevel = (secLevel2 == SecLevel.high)|| (secLevel2 == SecLevel.high) 
				? SecLevel.high : SecLevel.low;
		

		if(ctxSecLevel == SecLevel.high){
/*			if(varName1!=null)//update secLevel if we were dealing with var or Array
				newAllVarSecLevel.put(varName1,trueSecLevel1);
			if(varName2!=null)
				newAllVarSecLevel.put(varName2,trueSecLevel1);
			*/return true;	
		}
		else {
			newAllVarSecLevel = null;	
			return false;
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
