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
	
	public void setQs(int qs){
		this.qs = qs;
	}
	public int getQs(){
		return qs;
	}
	
	public void setBlock(Block block){
		this.block = block;
	}
	public Block getBlock(){
		return block;
	}
	
	public void setQt(int qt){
		this.qt = qt;
	}
	public int getQt(){
		return qt;
	}
	
	public String toString() {
		return block.toString();
	}
}
