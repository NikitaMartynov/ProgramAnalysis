package detectionOfSign_analysis;

import interval_analysis.BoolIntervals;
import interval_analysis.BoolNeverSatisfiedException;
import interval_analysis.DivideByZeroException;
import interval_analysis.Interval;
import interval_analysis.UnknownErrorException;

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

public class BoolDetectionOfSign {
	
	private HashMap<String, Signs> baseAllVarSigns; //input signs
	public HashMap<String, Signs> newAllVarSigns;   // signs after transfer function 
	
	public BoolDetectionOfSign(BoolExpr boolExpr,HashMap<String, Signs> baseElemSigns){
		
		newAllVarSigns = new  HashMap<String, Signs>(baseElemSigns);
		this.baseAllVarSigns = new  HashMap<String, Signs>(baseElemSigns);
		
		if(boolExpr instanceof LessThanExpr)
			lessThanExprSignsReduction((LessThanExpr)boolExpr);
		else if(boolExpr instanceof LessThanEqualsExpr)
			lessThanEqualsExprSignsReduction((LessThanEqualsExpr)boolExpr);
		else if(boolExpr instanceof GreaterThanExpr)
			greaterThanExprSignsReduction((GreaterThanExpr)boolExpr);
		else if(boolExpr instanceof GreaterThanEqualsExpr)
			greaterThanEqualsExprSignsReduction((GreaterThanEqualsExpr)boolExpr);
		else if(boolExpr instanceof EqualsExpr)
			equalsExprSignsReduction((EqualsExpr)boolExpr);
		else if(boolExpr instanceof NotEqualsExpr)
			notEqualsExprSignsReduction((NotEqualsExpr)boolExpr);
		if (boolExpr instanceof OrExpr)
			orExprHoldsSigns((OrExpr) boolExpr);
		if (boolExpr instanceof AndExpr)
			andExprHoldsSigns((AndExpr) boolExpr);
		else if(boolExpr instanceof BoolValueExpr){
				if(((BoolValueExpr) boolExpr).getBoolValue() == false )
					newAllVarSigns = null;
			}
		 else if (boolExpr instanceof NotExpr) 
			 notExprSignsReduction((NotExpr)boolExpr);
		else 
			assert false : "Error in function BoolDetectionOfSign(), shouldn't reach it. Check did you forget to add smth?";
		
	}
		
	boolean orExprHoldsSigns(OrExpr orExpr){
		BoolExpr boolExpr1 = orExpr.getExpression1();
		BoolExpr boolExpr2 = orExpr.getExpression2();
		HashMap<String, Signs> signs1 =null, signs2 = null;
		boolean value1, value2;
		
		signs1 = new BoolDetectionOfSign(boolExpr1, baseAllVarSigns).getNewAllVarSigns();
		value1 = signs1 == null ? false : true;
		
		signs2 = new BoolDetectionOfSign(boolExpr2, baseAllVarSigns).getNewAllVarSigns();
		value2 = signs2 == null ? false : true;
		
		if (value1 || value2 ){
			newAllVarSigns =  mergeSigns("mergeOr",signs1,signs2);
			return true;
		}
		else {
			newAllVarSigns = null;
			return false;
		}
	}
	
	boolean andExprHoldsSigns(AndExpr andExpr){
		BoolExpr boolExpr1 = andExpr.getExpression1();
		BoolExpr boolExpr2 = andExpr.getExpression2();
		HashMap<String, Signs> signs1 =null, signs2 = null;
		boolean value1, value2;
		
		signs1 = new BoolDetectionOfSign(boolExpr1, baseAllVarSigns).getNewAllVarSigns();
		value1 = signs1 == null ? false : true;
		
		signs2 = new BoolDetectionOfSign(boolExpr2, baseAllVarSigns).getNewAllVarSigns();
		value2 = signs2 == null ? false : true;
		
		if (value1 && value2 ){
			newAllVarSigns =  mergeSigns("mergeAnd",signs1,signs2);
			return true;
		}
		else {
			newAllVarSigns = null;
			return false;
		}
	}
	
	//reduce signs only if variable at least on one side
	boolean lessThanExprSignsReduction(LessThanExpr lessThanExpr){
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
		else signs1 = new ArithDetectionOfSign( arithExpr1, baseAllVarSigns).getSigns();
		
		
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
		else signs2 = new ArithDetectionOfSign( arithExpr2, baseAllVarSigns).getSigns();
		
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
		
/*		if(trueSigns1.isAny() && trueSigns2.isAny()){
			HashMap<String, Signs> updateToAllVarSigns = new  HashMap<String, Signs>(this.baseAllVarSigns);	
			if(varName1!=null)//update signs if we were dealing with var or Array
				updateToAllVarSigns.put(varName1,trueSigns1);
			if(varName2!=null)
				updateToAllVarSigns.put(varName2,trueSigns1);
			return updateToAllVarSigns;	
		}
		else 
			return null;*/
		if(trueSigns1.isAny() && trueSigns2.isAny()){
			if(varName1!=null)//update signs if we were dealing with var or Array
				newAllVarSigns.put(varName1,trueSigns1);
			if(varName2!=null)
				newAllVarSigns.put(varName2,trueSigns1);
			return true;	
		}
		else {
			newAllVarSigns = null;	
			return false;
		}		
	}
	
	//reduce signs only if variable at least on one side
	boolean lessThanEqualsExprSignsReduction(LessThanEqualsExpr lessThanEqualsExpr){
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
		else signs1 = new ArithDetectionOfSign( arithExpr1, baseAllVarSigns).getSigns();
		
		
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
		else signs2 = new ArithDetectionOfSign( arithExpr2, baseAllVarSigns).getSigns();
		
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
			if(varName1!=null)//update signs if we were dealing with var or Array
				newAllVarSigns.put(varName1,trueSigns1);
			if(varName2!=null)
				newAllVarSigns.put(varName2,trueSigns1);
			return true;	
		}
		else {
			newAllVarSigns = null;	
			return false;
		}
	}
	
	//reduce signs only if variable at least on one side
	boolean greaterThanExprSignsReduction(GreaterThanExpr greaterThanExpr){
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
		else signs1 = new ArithDetectionOfSign( arithExpr1, baseAllVarSigns).getSigns();
		
		
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
		else signs2 = new ArithDetectionOfSign( arithExpr2, baseAllVarSigns).getSigns();
		
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
			if(varName1!=null)//update signs if we were dealing with var or Array
				newAllVarSigns.put(varName1,trueSigns1);
			if(varName2!=null)
				newAllVarSigns.put(varName2,trueSigns1);
			return true;	
		}
		else {
			newAllVarSigns = null;	
			return false;
		}
	}
	
	//reduce signs only if variable at least on one side
	boolean greaterThanEqualsExprSignsReduction(GreaterThanEqualsExpr greaterThanEqualsExpr){
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
		else signs1 = new ArithDetectionOfSign( arithExpr1, baseAllVarSigns).getSigns();
		
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
		else signs2 = new ArithDetectionOfSign( arithExpr2, baseAllVarSigns).getSigns();
		
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
			if(varName1!=null)//update signs if we were dealing with var or Array
				newAllVarSigns.put(varName1,trueSigns1);
			if(varName2!=null)
				newAllVarSigns.put(varName2,trueSigns1);
			return true;	
		}
		else {
			newAllVarSigns = null;	
			return false;
		}
	}
	
	//reduce signs only if variable at least on one side
	boolean equalsExprSignsReduction(EqualsExpr equalsExpr){
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
		else signs1 = new ArithDetectionOfSign( arithExpr1, baseAllVarSigns).getSigns();
		
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
		else signs2 =new ArithDetectionOfSign( arithExpr2, baseAllVarSigns).getSigns();
		
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
			if(varName1!=null)//update signs if we were dealing with var or Array
				newAllVarSigns.put(varName1,trueSigns1);
			if(varName2!=null)
				newAllVarSigns.put(varName2,trueSigns1);
			return true;	
		}
		else {
			newAllVarSigns = null;	
			return false;
		}
	}
	
	//reduce signs only if variable at least on one side
	boolean notEqualsExprSignsReduction(NotEqualsExpr notEqualsExpr){
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
		else signs1 = new ArithDetectionOfSign( arithExpr1, baseAllVarSigns).getSigns();
		
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
		else signs2 = new ArithDetectionOfSign( arithExpr2, baseAllVarSigns).getSigns();
		
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
			if(varName1!=null)//update signs if we were dealing with var or Array
				newAllVarSigns.put(varName1,trueSigns1);
			if(varName2!=null)
				newAllVarSigns.put(varName2,trueSigns1);
			return true;	
		}
		else {
			newAllVarSigns = null;	
			return false;
		}
	}
	
	public HashMap<String, Signs> getNewAllVarSigns(){
		return newAllVarSigns;
	}

	private void notExprSignsReduction(NotExpr exp) {

		BoolExpr insideExpr = exp.getExpression();
		BoolExpr newExpr = null;

		if (insideExpr instanceof AndExpr) {
			newExpr = new OrExpr(new NotExpr(
					((AndExpr) insideExpr).getExpression1()), new NotExpr(
					((AndExpr) insideExpr).getExpression2()));

		} else if (insideExpr instanceof OrExpr) {
			newExpr = new AndExpr(new NotExpr(
					((OrExpr) insideExpr).getExpression1()), new NotExpr(
					((OrExpr) insideExpr).getExpression2()));
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

		this.newAllVarSigns = new BoolDetectionOfSign(newExpr, baseAllVarSigns)
												.getNewAllVarSigns();

	}
	
	//cmd={mergeOr,mergeAnd}
	HashMap<String, Signs> mergeSigns(String cmd, HashMap<String, Signs> signs1, HashMap<String, Signs> signs2){
		HashMap<String, Signs> signs = new HashMap<String, Signs>();
		if ((signs1 == null) && (signs2 == null) )
			return null;
		else if ((signs1 == null) || (signs2 == null) )
			return signs1 == null ? signs2 : signs1;
		
		if(signs1.size() != signs2.size())
			assert false : "Error in mergeSigns(), not equal size of hashmaps with signs!";

		for (Map.Entry<String,Signs> entry : signs1.entrySet())
			signs.put(entry.getKey(), new Signs(cmd, entry.getValue(), 
												signs2.get(entry.getKey()))  );
		
		return signs;
	}
}
