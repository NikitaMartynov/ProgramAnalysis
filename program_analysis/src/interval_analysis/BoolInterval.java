package interval_analysis;

import java.util.HashMap;
import java.util.Vector;

import ast.arith.*;
import ast.bool.*;

/**
 * The boolean Interval that makes the boolean expression to be tt
 * 
 * @author zhenli
 * 
 */
public class BoolInterval {
	private HashMap<String, Interval> intervals;

	public BoolInterval(BoolExpr exp, HashMap<String, Interval> baseIntervals)
			throws DivideByZeroException, UnknownErrorException,
			BoolNeverSatisfiedException {

		if (exp instanceof AndExpr) { // AndExpr
			initForAndExpr((AndExpr) exp, baseIntervals);
		} else if (exp instanceof OrExpr) { // OrExpr
			initForOrExpr((OrExpr) exp, baseIntervals);
		} else if (exp instanceof GreaterThanExpr) { // GreaterThanExpr
			GreaterThanExpr g = (GreaterThanExpr) exp;
			initForGreaterThanExpr(g, g.getExpression1(), g.getExpression2(),
					baseIntervals);
		} else if (exp instanceof GreaterThanEqualsExpr) {
			GreaterThanEqualsExpr ge = (GreaterThanEqualsExpr) exp;
			initForGreaterThanEqualsExpr(ge, ge.getExpression1(),
					ge.getExpression2(), baseIntervals);
		} else if (exp instanceof LessThanExpr) {
			LessThanExpr l = (LessThanExpr) exp;
			initForLessThanExpr(l, l.getExpression1(), l.getExpression2(),
					baseIntervals);
		} else if (exp instanceof LessThanEqualsExpr) {
			LessThanEqualsExpr le = (LessThanEqualsExpr) exp;
			initForLessThanEqualsExpr(le, le.getExpression1(),
					le.getExpression2(), baseIntervals);
		} else if (exp instanceof BoolValueExpr) {
			BoolValueExpr b = (BoolValueExpr) exp;
			if (b.getBoolValue()) { // true
				intervals = new HashMap<String, Interval>();
				for (String key : exp.getVariables())
					intervals.put(key, baseIntervals.get(key));
			} else { // false
				throw new BoolNeverSatisfiedException(exp, baseIntervals);
			}
		} else if (exp instanceof EqualsExpr) {
			initForEqualsExpr((EqualsExpr) exp, baseIntervals);
		} else if (exp instanceof NotEqualsExpr) {
			initForNotEqualsExpr((NotEqualsExpr) exp, baseIntervals);
		} else if (exp instanceof NotExpr) {
			initForNotExpr((NotExpr) exp, baseIntervals);
		} else {
			throw new UnknownErrorException("Cannot handle class "
					+ exp.getClass());
		}
	}

	private void initForNotExpr(NotExpr exp,
			HashMap<String, Interval> baseIntervals)
			throws DivideByZeroException, UnknownErrorException,
			BoolNeverSatisfiedException {

		BoolExpr insideExpr = exp.getExpression();
		BoolExpr newExpr = null;

		if (insideExpr instanceof AndExpr) {
			newExpr = new OrExpr(new NotExpr( // TODO error: should not inverse
												// insideExpr
					((AndExpr) insideExpr).getExpression1()), new NotExpr(
			// TODO error: should not inverse insideExpr
					((AndExpr) insideExpr).getExpression2()));

		} else if (insideExpr instanceof OrExpr) {
			newExpr = new AndExpr(new NotExpr(// TODO error: should not inverse
												// insideExpr
					((OrExpr) insideExpr).getExpression1()), new NotExpr(
			// TODO error: should not inverse insideExpr
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
			// I really want to fuck you!
			newExpr = insideExpr;
		} else {
			throw new UnknownErrorException("Cannot handle class "
					+ insideExpr.getClass());
		}

		this.intervals = new BoolInterval(newExpr, baseIntervals)
				.getIntervals();

	}

	private void initForNotEqualsExpr(NotEqualsExpr exp,
			HashMap<String, Interval> baseIntervals)
			throws DivideByZeroException, UnknownErrorException,
			BoolNeverSatisfiedException {

		ArithExpr a1 = exp.getExpression1();
		ArithExpr a2 = exp.getExpression2();

		Interval i1 = new ArithInterval(a1, baseIntervals);
		Interval i2 = new ArithInterval(a2, baseIntervals);

		if (i1.hasSingleValue() && i2.hasSingleValue()
				&& i1.getHighBoundary() == i2.getHighBoundary())
			throw new BoolNeverSatisfiedException(exp, baseIntervals);

		// record all the variables in exp;
		intervals = new HashMap<String, Interval>();
		for (String key : exp.getVariables())
			intervals.put(key, baseIntervals.get(key));

	}

	private void initForEqualsExpr(EqualsExpr exp,
			HashMap<String, Interval> baseIntervals)
			throws DivideByZeroException, UnknownErrorException,
			BoolNeverSatisfiedException {
		ArithExpr a1 = exp.getExpression1();
		ArithExpr a2 = exp.getExpression2();

		Interval i1 = new ArithInterval(a1, baseIntervals);
		Interval i2 = new ArithInterval(a2, baseIntervals);

		Interval i = Interval.intersection(i1, i2);
		if (i == null)
			throw new BoolNeverSatisfiedException(exp, baseIntervals);

		// record all the variables in exp;
		intervals = new HashMap<String, Interval>();
		for (String key : exp.getVariables())
			intervals.put(key, baseIntervals.get(key));
	}

	private void initForLessThanEqualsExpr(LessThanEqualsExpr exp,
			ArithExpr a1, ArithExpr a2, HashMap<String, Interval> baseIntervals)
			throws DivideByZeroException, UnknownErrorException,
			BoolNeverSatisfiedException {
		Interval i1 = new ArithInterval(a1, baseIntervals);
		Interval i2 = new ArithInterval(a2, baseIntervals);

		intervals = new HashMap<String, Interval>();

		// special case for variable <= num
		if ((a1 instanceof IdExpr) && (a2 instanceof NumExpr
		/*
		 * || a2 instanceof UnMinExpr
		 */)) {
			Interval i = null;
			int num = ((NumExpr) a2).getValue(); // positive number
			if (i1.getLowBoundary() <= num)
				i = new Interval(i1.getLowBoundary(), Math.min(num,
						i1.getHighBoundary()));
			else
				throw new BoolNeverSatisfiedException(exp, baseIntervals);
			intervals.put(a1.toString(), i);
		}
		// special case for num <= variable
		else if ((a2 instanceof IdExpr /* || a1 instanceof UnMinExpr */)
				&& (a1 instanceof NumExpr)) {
			Interval i = null;
			int num = ((NumExpr) a1).getValue(); // positive number
			if (i2.getHighBoundary() >= num)
				i = new Interval(Math.max(num, i2.getLowBoundary()),
						i2.getHighBoundary());
			else
				throw new BoolNeverSatisfiedException(exp, baseIntervals);
			intervals.put(a2.toString(), i);
		}

		// common cases
		else if (i2.getHighBoundary() < i1.getLowBoundary()) {
			throw new BoolNeverSatisfiedException(exp, baseIntervals);
		} else {
			// record the original values
			for (String key : a1.getVariables()) {
				intervals.put(key, baseIntervals.get(key));
			}
			for (String key : a2.getVariables()) {
				intervals.put(key, baseIntervals.get(key));
			}
		}

	}

	private void initForLessThanExpr(LessThanExpr exp, ArithExpr a1,
			ArithExpr a2, HashMap<String, Interval> baseIntervals)
			throws DivideByZeroException, UnknownErrorException,
			BoolNeverSatisfiedException {
		// for each possible values of variables in v1 and v2, compute the bool
		// expr result
		// record all the values that make the bool expr to be tt
		//

		Interval i1 = new ArithInterval(a1, baseIntervals);
		Interval i2 = new ArithInterval(a2, baseIntervals);

		intervals = new HashMap<String, Interval>();

		// special case for variable < num
		if ((a1 instanceof IdExpr) && (a2 instanceof NumExpr
		/*
		 * || a2 instanceof UnMinExpr
		 */)) {
			Interval i = null;
			int num = ((NumExpr) a2).getValue(); // positive number
			if (i1.getLowBoundary() < num)
				i = new Interval(i1.getLowBoundary(), Math.min(num - 1,
						i1.getHighBoundary()));
			else
				throw new BoolNeverSatisfiedException(exp, baseIntervals);
			intervals.put(a1.toString(), i);
		}
		// special case for num < variable
		else if ((a2 instanceof IdExpr /* || a1 instanceof UnMinExpr */)
				&& (a1 instanceof NumExpr)) {
			Interval i = null;
			int num = ((NumExpr) a1).getValue(); // positive number
			if (i2.getHighBoundary() > num)
				i = new Interval(Math.max(num + 1, i2.getLowBoundary()),
						i2.getHighBoundary());
			else
				throw new BoolNeverSatisfiedException(exp, baseIntervals);
			intervals.put(a2.toString(), i);
		}

		// common cases
		else if (i2.getHighBoundary() <= i1.getLowBoundary()) {
			throw new BoolNeverSatisfiedException(exp, baseIntervals);
		} else {
			// record the original values
			for (String key : a1.getVariables()) {
				intervals.put(key, baseIntervals.get(key));
			}
			for (String key : a2.getVariables()) {
				intervals.put(key, baseIntervals.get(key));
			}
		}

	}

	private void initForGreaterThanExpr(BoolExpr exp, ArithExpr a1,
			ArithExpr a2, HashMap<String, Interval> baseIntervals)
			throws DivideByZeroException, UnknownErrorException,
			BoolNeverSatisfiedException {

		// for each possible values of variables in v1 and v2, compute the bool
		// expr result
		// record all the values that make the bool expr to be tt
		//

		Interval i1 = new ArithInterval(a1, baseIntervals);
		Interval i2 = new ArithInterval(a2, baseIntervals);

		intervals = new HashMap<String, Interval>();

		// special case for variable > num
		if ((a1 instanceof IdExpr) && (a2 instanceof NumExpr
		/*
		 * || a2 instanceof UnMinExpr
		 */)) {
			Interval i = null;
			int num = ((NumExpr) a2).getValue(); // positive number
			if (i1.getHighBoundary() > num)
				i = new Interval(Math.max(num + 1, i1.getLowBoundary()),
						i1.getHighBoundary());
			else
				throw new BoolNeverSatisfiedException(exp, baseIntervals);
			intervals.put(a1.toString(), i);
		}
		// special case for num > variable
		else if ((a2 instanceof IdExpr /* || a1 instanceof UnMinExpr */)
				&& (a1 instanceof NumExpr)) {
			Interval i = null;
			int num = ((NumExpr) a1).getValue(); // positive number
			if (i2.getLowBoundary() < num)
				i = new Interval(i2.getLowBoundary(), Math.min(num - 1,
						i2.getHighBoundary()));
			else
				throw new BoolNeverSatisfiedException(exp, baseIntervals);
			intervals.put(a2.toString(), i);
		}

		// common cases
		else if (i2.getLowBoundary() >= i1.getHighBoundary()) {
			throw new BoolNeverSatisfiedException(exp, baseIntervals);
		} else {
			// record the original values
			for (String key : a1.getVariables()) {
				intervals.put(key, baseIntervals.get(key));
			}
			for (String key : a2.getVariables()) {
				intervals.put(key, baseIntervals.get(key));
			}
		}
	}

	private void initForGreaterThanEqualsExpr(BoolExpr exp, ArithExpr a1,
			ArithExpr a2, HashMap<String, Interval> baseIntervals)
			throws DivideByZeroException, UnknownErrorException,
			BoolNeverSatisfiedException {

		// for each possible values of variables in v1 and v2, compute the bool
		// expr result
		// record all the values that make the bool expr to be tt
		//

		Interval i1 = new ArithInterval(a1, baseIntervals);
		Interval i2 = new ArithInterval(a2, baseIntervals);

		intervals = new HashMap<String, Interval>();

		// special case for variable >= num
		if ((a1 instanceof IdExpr) && (a2 instanceof NumExpr
		/*
		 * || a2 instanceof UnMinExpr
		 */)) {
			Interval i = null;
			int num = ((NumExpr) a2).getValue(); // positive number
			if (i1.getHighBoundary() >= num)
				i = new Interval(Math.max(num, i1.getLowBoundary()),
						i1.getHighBoundary());
			else
				throw new BoolNeverSatisfiedException(exp, baseIntervals);
			intervals.put(a1.toString(), i);
		}
		// special case for num >= variable
		else if ((a2 instanceof IdExpr /* || a1 instanceof UnMinExpr */)
				&& (a1 instanceof NumExpr)) {
			Interval i = null;
			int num = ((NumExpr) a1).getValue(); // positive number
			if (i2.getLowBoundary() <= num)
				i = new Interval(i2.getLowBoundary(), Math.min(num,
						i2.getHighBoundary()));
			else
				throw new BoolNeverSatisfiedException(exp, baseIntervals);
			intervals.put(a2.toString(), i);
		}

		// common cases
		else if (i2.getLowBoundary() > i1.getHighBoundary()) {
			throw new BoolNeverSatisfiedException(exp, baseIntervals);
		} else {
			// record the original values
			for (String key : a1.getVariables()) {
				intervals.put(key, baseIntervals.get(key));
			}
			for (String key : a2.getVariables()) {
				intervals.put(key, baseIntervals.get(key));
			}
		}

	}

	private void initForAndExpr(AndExpr boolExpr,
			HashMap<String, Interval> baseIntervals)
			throws DivideByZeroException, UnknownErrorException,
			BoolNeverSatisfiedException {
		BoolInterval boolInterval1 = new BoolInterval(
				boolExpr.getExpression1(), baseIntervals);
		BoolInterval boolInterval2 = new BoolInterval(
				boolExpr.getExpression2(), baseIntervals);

		// get the intervals that make either i1 or i2 to be tt
		HashMap<String, Interval> i1 = boolInterval1.getIntervals();
		HashMap<String, Interval> i2 = boolInterval2.getIntervals();

		intervals = i1;
		for (String key : i2.keySet()) {
			if (intervals.containsKey(key)) {
				// for the variables that contain in both i1 and i2
				Interval i = Interval.intersection(intervals.get(key),
						i2.get(key));
				if (i == null)
					throw new BoolNeverSatisfiedException(boolExpr,
							baseIntervals);
				intervals.put(key, i);
			} else {
				intervals.put(key, i2.get(key));
			}
		}
	}

	private void initForOrExpr(OrExpr boolExpr,
			HashMap<String, Interval> baseIntervals)
			throws DivideByZeroException, UnknownErrorException,
			BoolNeverSatisfiedException {

		BoolInterval boolInterval1 = null, boolInterval2 = null;
		HashMap<String, Interval> i1 = null, i2 = null;
		// the intervals that make either i1 or i2 to be tt

		try {
			boolInterval1 = new BoolInterval(boolExpr.getExpression1(),
					baseIntervals);
			i1 = boolInterval1.getIntervals();
		} catch (BoolNeverSatisfiedException e) {
			i1 = null;
		}
		try {
			boolInterval2 = new BoolInterval(boolExpr.getExpression2(),
					baseIntervals);
			i2 = boolInterval2.getIntervals();
		} catch (BoolNeverSatisfiedException e) {
			i2 = null;
		}

		if (i1 == null && i2 == null) {
			// intervals = null;
			throw new BoolNeverSatisfiedException(boolExpr, baseIntervals);
		} else if (i1 == null) {
			intervals = i2;
		} else if (i2 == null) {
			intervals = i1;
		} else {
			intervals = i1;
			for (String key : i2.keySet()) {
				if (intervals.containsKey(key)) {
					// for the variables that contain in both i1 and i2
					intervals.put(key,
							Interval.union(intervals.get(key), i2.get(key)));
				} else {
					intervals.put(key, baseIntervals.get(key));
				}
			}
		}
	}

	public HashMap<String, Interval> getIntervals() {
		return intervals;
	}

}
