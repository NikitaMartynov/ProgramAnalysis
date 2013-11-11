package interval_analysis;

/**
 * Abandoned
 * 
 * @author zhenli
 *
 */
public class IntervalElement {
	private int label;
	private String variableName;
	private Interval interval;
	public int getLabel() {
		return label;
	}
	public void setLabel(int label) {
		this.label = label;
	}
	public String getVariableName() {
		return variableName;
	}
	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}
	public Interval getInterval() {
		return interval;
	}
	public void setInterval(Interval interval) {
		this.interval = interval;
	}
	
	public String toString() {
		return label + " " + variableName + " " + interval;
	}
}
