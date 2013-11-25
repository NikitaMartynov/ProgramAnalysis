package interval_analysis;

import java.util.Arrays;

public class Interval {
	public static int _minusInfinity = Integer.MIN_VALUE;
	public static int _plusInfinity = Integer.MAX_VALUE;

	private int low;
	private int high;

	// constructors
	// assign boundaries to be all possible values
	public Interval() {
		low = _minusInfinity;
		high = _plusInfinity;
	}

	// NumInterval
	// A[[n]]
	public Interval(int num) {
		setBoundaries(num);
	}

	public Interval(int low, int high) {
		setBoundaries(low, high);
	}

	// getters and setters
	public int getLowBoundary() {
		return low;
	}

	public int getHighBoundary() {
		return high;
	}


	public void setBoundaries(Interval i) {
		setBoundaries(i.getLowBoundary(), i.getHighBoundary());
	}

	public void setBoundaries(int num) {
		setBoundaries(num, num);
	}

	// num2Interval
	public void setBoundaries(int low, int high) {
		int lcomp = compareBoundaries(low);
		int hcomp = compareBoundaries(high);
		if (lcomp == 0 && hcomp == 0) {
			this.low = low;
			this.high = high;
		}
		else if (hcomp < 0) {// && lcomp < 0
			this.low = _minusInfinity;
			this.high = IntervalAnalysis.getMin() - 1;
		}
		else if (lcomp > 0) {// && hcomp > 0
			this.low = IntervalAnalysis.getMax() + 1;
			this.high = _plusInfinity;
		}
		else if (hcomp == 0) {// && lcomp < 0
			this.low = _minusInfinity;
			this.high  = high;
		}
		else if (lcomp == 0) {// && hcomp > 0
			this.low = low;
			this.high = _plusInfinity;
		}
		else {// hcomp > 0 && lcomp < 0
			this.low = _minusInfinity;
			this.high = _plusInfinity;
		}
	}
	
	private static int compareBoundaries(int i) {
		int min = IntervalAnalysis.getMin();
		int max = IntervalAnalysis.getMax();

		if (i >= min && i <= max)
			return 0;
		else if (i < min)
			return -1;
		else
			// i > max
			return 1;
	}

	public static Interval plus(Interval i1, Interval i2) {
		Interval ret = new Interval(plus(i1.getLowBoundary(),
				i2.getLowBoundary(), 1), plus(i2.getHighBoundary(),
				i2.getHighBoundary(), 2));

		return ret;
	}

	private static int plus(int a1, int a2, int pos) {
		int result = 0;
		if ((a1 == _plusInfinity && a2 == _minusInfinity)
				|| (a1 == _minusInfinity && a2 == _plusInfinity)) {
			if (pos == 1)
				result = _minusInfinity;
			else //if (pos == 2)
				result = _plusInfinity;
		} else if (a1 == _plusInfinity || a2 == _plusInfinity
				|| a1 + a2 > IntervalAnalysis.getMax()) {
			result = _plusInfinity;
		} else if (a1 == _minusInfinity || a2 == _minusInfinity
				|| a1 + a2 < IntervalAnalysis.getMin()) {
			result = _minusInfinity;
		} else
			result = a1 + a2;

		return result;
	}

	public static Interval minus(Interval i1, Interval i2) {
		return plus(i1, unaryMinus(i2));
	}

	public static Interval unaryMinus(Interval i1) {
		int l = -i1.getHighBoundary();
		int h = -i1.getLowBoundary();

		return new Interval(l, h);
	}



	public static Interval multiply(Interval i1, Interval i2) {
		int[] temp = new int[]{
				multiply(i1.getLowBoundary(), i2.getLowBoundary()),
				multiply(i1.getHighBoundary(), i2.getLowBoundary()),
				multiply(i1.getLowBoundary(), i2.getHighBoundary()),
				multiply(i1.getHighBoundary(), i2.getHighBoundary()),
		};
		Arrays.sort(temp);
		
		Interval ret = new Interval(temp[0], temp[3]);

		return ret;
	}
	
	public static int multiply(int a1, int a2) {
		int result = 0;
		
		if(a1 == 0 || a2 == 0) {
			result = 0;
		}
		else if(a1 == _plusInfinity && a2 < 0 || a1 == _minusInfinity && a2 > 0 
				|| a2 == _plusInfinity && a1 < 0 || a2 == _minusInfinity && a1 > 0 
				|| a1 * a2 < IntervalAnalysis.getMin()) {
			result = _minusInfinity;
		}
		else if(a1 == _plusInfinity && a2 > 0 || a1 == _minusInfinity && a2 < 0 
				|| a2 == _plusInfinity && a1 > 0 || a2 == _minusInfinity && a1 < 0 
				|| a1 * a2 > IntervalAnalysis.getMax()) {
			result = _plusInfinity;
		}
		else
			result = a1 * a2;
		
		return result;
	}

	public static Interval divide(Interval i1, Interval i2)
			throws DivideByZeroException {
		return multiply(i1, oneDividebyX(i2));
	}

	private static Interval oneDividebyX(Interval i1)
			throws DivideByZeroException {
		// test whether the interval i2 contains 0
		if (i1.getLowBoundary() <= 0 && i1.getHighBoundary() >= 0) {
			throw new DivideByZeroException();
		} else { // no zero is in the interval
			return new Interval(1 / i1.getHighBoundary(),
					1 / i1.getLowBoundary());
		}
	}

	public static Interval union(Interval i1, Interval i2) {
		// TODO need to be modified?
		return new Interval(Math.min(i1.getLowBoundary(), i2.getLowBoundary()),
				Math.max(i1.getHighBoundary(), i2.getHighBoundary()));
	}

	public String toString() {
		String low = "" + this.low;
		if (this.low == _minusInfinity)
			low = "-INF";
		String high = "" + this.high;
		if (this.high == _plusInfinity)
			high = "+INF";

		return "[" + low + ", " + high + "]";
	}

	public static Interval intersection(Interval i1, Interval i2) {
		// TODO Auto-generated method stub
		int l = Math.max(i1.getLowBoundary(), i2.getLowBoundary());
		int h = Math.min(i1.getHighBoundary(), i2.getHighBoundary());
		
		if(l > h)
			return null;
		else
			return new Interval(l, h);
	}

	public boolean hasSingleValue() {
		return this.low == this.high;
	}

	public boolean isSubsetOf(Interval interval) {
		if(this.low >= interval.low && this.high <= interval.high)
			return true;
		return false;
	}
}
