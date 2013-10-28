package graphs.pg;

public class Edge {
	public Edge(int qs, String block, int qt){
		this.qs = qs;
		this.block = block;
		this.qt = qt;
	}
	
	int qs;
	String block; // see some comments below
	/* 
	 * how about to change String to Block. The Block class is located in package graphs?
	 * For example, if we have a := 10; we might want to get a or get 10 when we do analysis.
	 * If we use String, then it requires some additional work to get the symbol we need.
	 * 
	 * -Zhen
	 */
	int qt;
}
