package graphs.fg;

import graphs.Block;

import java.util.Vector;

import ast.statement.Statement;

public abstract class FlowGraph {
	// instance field
	private static Vector<Block> blocks; // all the blocks in the whole graph
	private Vector<Integer> labels; // the block labels in this graph
	private Vector<Flow> flow; // the flows in this graph
	private int init; // init label of this graph
	private Vector<Integer> fin; // final set of this graph
	private int ancestorBoolLabel; // used in ud-chain

	/**
	 * init variables
	 * 
	 * @param st
	 */
	public FlowGraph(Statement st) {
		blocks = getBlocks();
		labels = new Vector<Integer>();
		flow = new Vector<Flow>();
		fin = new Vector<Integer>();
		ancestorBoolLabel = -1;
	}

	// methods
	public static Vector<Block> getBlocks() {
		if (blocks == null)
			blocks = new Vector<Block>();
		return blocks;
	}

	public Vector<Flow> getFlow() { // simply returns the flow, the same for
									// other getters
		return this.flow;
	}

	public void setInit(int init) {
		this.init = init;
	}

	public int getInit() {
		return this.init;
	}

	public Vector<Integer> getFinal() {
		return this.fin;
	}

	public Vector<Integer> getLabels() {
		return this.labels;
	}

	public void setAncestorBoolLabel(int value) {
		this.ancestorBoolLabel = value;
	}

	public int getAncestorBoolLabel() {
		return this.ancestorBoolLabel;
	}

	public String toString() {// print the graph

		// all the blocks in the graph
		String ret = "blocks: ";
		if(blocks !=null){
		for (Block b : blocks) {
			if(b!=null){
			ret += "[" + b.toString() + "] ";
			}
		}
		}

		// the labels in this graph
		if(labels !=null){
		ret += "\nlabels: ";
		for (int l : labels) {
			ret += l + " ";
		}
		}

		// the flows in the graph
		if(flow !=null){
		ret += "\nflow: ";
		for (Flow f : flow) {
			ret += f.toString() + " ";
		}
		}

		// the init point
		ret += "\ninit: " + init;

		// the final set
		ret += "\nfinal: ";
		for (int f : fin) {
			ret += f + " ";
		}
		ret += "\n";

		return ret;
	}

}
