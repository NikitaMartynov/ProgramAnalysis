package interval_analysis;

import java.util.HashMap;

import ast.arith.*;

public class ArithInterval extends Interval {

	public ArithInterval(ArithExpr exp, HashMap<String, Interval> baseIntervals)
			throws DivideByZeroException, UnknownErrorException {
		if (exp instanceof IdExpr) {// Variable
			String name = ((IdExpr) exp).toString();
			setBoundaries(baseIntervals.get(name));
		} else if(exp instanceof ArrayExpr) { // array variable
			String name = ((ArrayExpr) exp).getName();
			setBoundaries(baseIntervals.get(name));
		}
		else if (exp instanceof NumExpr) {// Number
			setBoundaries(((NumExpr) exp).getValue());
		} else if (exp instanceof ParenExpr) {// ParenExpr
			setBoundaries(new ArithInterval(((ParenExpr) exp).getExpression(),
					baseIntervals));
		} else if (exp instanceof PlusExpr) {// PlusExpr
			PlusExpr plusExpr = (PlusExpr) exp;
			ArithInterval a1 = new ArithInterval(plusExpr.getExpression1(),
					baseIntervals);
			ArithInterval a2 = new ArithInterval(plusExpr.getExpression2(),
					baseIntervals);
			setBoundaries(Interval.plus(a1, a2));
		} else if (exp instanceof MinusExpr) {// MinusExpr
			MinusExpr minusExpr = (MinusExpr) exp;
			ArithInterval a1 = new ArithInterval(minusExpr.getExpression1(),
					baseIntervals);
			ArithInterval a2 = new ArithInterval(minusExpr.getExpression2(),
					baseIntervals);
			setBoundaries(Interval.minus(a1, a2));
		} else if (exp instanceof UnMinExpr) {// UnMinExpr
			UnMinExpr unMinExpr = (UnMinExpr) exp;
			ArithInterval i = new ArithInterval(unMinExpr.getExpression(),
					baseIntervals);
			setBoundaries(Interval.unaryMinus(i));
		} else if (exp instanceof MultExpr) {// MultExpr
			MultExpr multExpr = (MultExpr) exp;
			ArithInterval a1 = new ArithInterval(multExpr.getExpression1(),
					baseIntervals);
			ArithInterval a2 = new ArithInterval(multExpr.getExpression2(),
					baseIntervals);
			setBoundaries(Interval.multiply(a1, a2));
		} else if (exp instanceof DivExpr) {// DivExpr
			DivExpr divExpr = (DivExpr) exp;
			ArithInterval a1 = new ArithInterval(divExpr.getExpression1(),
					baseIntervals);
			ArithInterval a2 = new ArithInterval(divExpr.getExpression2(),
					baseIntervals);
			setBoundaries(Interval.divide(a1, a2));
		} else {
			throw new UnknownErrorException("Cannot handle class "
					+ exp.getClass());
		}
	}

}
