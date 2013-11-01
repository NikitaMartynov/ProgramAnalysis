package graphs.pg;

import graphs.Block;

public class Edge {
	public Edge(int qs, Block block, int qt){
		this.qs = qs;
		this.block = block;
		this.qt = qt;
	}
	
	int qs;
	Block block;
	int qt;
}
