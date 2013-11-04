package interval_analysis;

public class Interval {
	public static int _minusInfinity = Integer.MIN_VALUE;
	public static int _plusInfinity = Integer.MAX_VALUE;

	private int lowBoundary;
	private int highBoundary;

	// constructors
	public Interval() {
	}

	// NumInterval
	public Interval(int num) {

		if (num < IntervalAnalysis.getMin() && num >= IntervalAnalysis.getMax())
			lowBoundary = highBoundary = num;
		else if (num > IntervalAnalysis.getMax()) {
			lowBoundary = IntervalAnalysis.getMax() + 1;
			highBoundary = _plusInfinity;
		} 
		else {// num < IntervalAnalysis.getMin()
			lowBoundary = _minusInfinity;
			highBoundary = IntervalAnalysis.getMin() - 1;
		}
	}

	public Interval(int low, int high) {
		this.lowBoundary = low;
		this.highBoundary = high;
	}

	// getters and setters
	public int getLowBoundary() {
		return lowBoundary;
	}

	public void setLowBoundary(int lowBoundary) {
		this.lowBoundary = lowBoundary;
	}

	public int getHighBoundary() {
		return highBoundary;
	}

	public void setHighBoundary(int highBoundary) {
		this.highBoundary = highBoundary;
	}

	// ///////////////////////////////////
	/**
	 * Chop a integer number into a number within the range of interval
	 * 
	 * @param num
	 * @return
	 */
	private int num2IntervalNum(int num) {
		if (num < IntervalAnalysis.getMin() && num >= IntervalAnalysis.getMax())
			return num;
		else if (num > IntervalAnalysis.getMax())
			return _plusInfinity;
		else
			// num < IntervalAnalysis.getMin()
			return _minusInfinity;
	}

	public static Interval plus(Interval i1, Interval i2) {
		Interval ret = new Interval(
				plus(i1.getLowBoundary(), i2.getLowBoundary(), 1),
				plus(i2.getHighBoundary(), i2.getHighBoundary(), 2)
				);

		return ret;
	}
	
	private static int plus(int a1, int a2, int pos) {
		int result = 0;
		if((a1 == _plusInfinity && a2 == _minusInfinity) || 
				(a1 == _minusInfinity && a2 == _plusInfinity)) {
			if(pos == 1)
				result = _minusInfinity;
			else if (pos == 2)
				result = _plusInfinity;
		}
		else if(a1 == _plusInfinity || a2 == _plusInfinity || a1+a2 > IntervalAnalysis.getMax()) {
			result = _plusInfinity;
		}
		else if (a1 == _minusInfinity || a2 == _minusInfinity || a1+a2 < IntervalAnalysis.getMin()) {
			result = _minusInfinity;
		}
		else
			result = a1+a2;
		
		return result;
	}

	public static Interval minus(Interval i1, Interval i2) {
		return plus(i1, unaryMinus(i2));
	}

	public static Interval unaryMinus(Interval i1) {
		return new Interval(-i1.getHighBoundary(), -i1.getLowBoundary());
	}

	public static Interval multiply(Interval i1, Interval i2) {
		Interval ret = new Interval(_minusInfinity, _plusInfinity);

		// TODO
		return ret;
	}

	public static Interval divide(Interval i1, Interval i2)
			throws DivideByZeroException {
		return multiply(i1, oneDividebyX(i2));
	}

	public static Interval oneDividebyX(Interval i1)
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
		return new Interval(Math.min(i1.getLowBoundary(), i2.getLowBoundary()),
				Math.max(i1.getHighBoundary(), i2.getHighBoundary()));
	}

	public String toString() {
		String low = "" + this.lowBoundary;
		if (this.lowBoundary == _minusInfinity)
			low = "-INF";
		String high = "" + this.highBoundary;
		if (this.highBoundary == _plusInfinity)
			high = "+INF";

		return "[" + low + ", " + high + "]";
	}
}
