package interval_analysis;

import java.util.HashMap;

public class IntervalAnalysis {
	private static int _min = Interval._minusInfinity + 1;
	private static int _max = Interval._plusInfinity - 1;
	
	public static void setMin(int num) {
		if (num <= Interval._minusInfinity) {
			_min = Interval._minusInfinity + 1;
			System.out.println("Warninng: the minimum value you could assign for min is " + _min);
		}
		else 
			_min = num;
	}
	
	public static void setMax(int num) {
		if(num >= Interval._plusInfinity) {
			_max = Interval._plusInfinity - 1;
			System.out.println("Warninng: the maximum value you could assign for max is " + _max);
		}
		else
			_max = num;
	}
	
	public static int getMin() {
		return _min;
	}
	
	public static int getMax() {
		return _max;
	}
	
	public static HashMap<String, IntervalElement> intervalElements;
	
}
