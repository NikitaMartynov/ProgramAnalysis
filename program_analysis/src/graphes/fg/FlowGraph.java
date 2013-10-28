package graphes.fg;

import graphes.Block;

import java.util.Vector;

abstract class FlowGraph {
	// instance field
	static Vector<Block> blocks; // all the blocks in the whole graph
	Vector<Integer> labels; 	// the block labels in this graph
	Vector<Flow> flow; 			// the flows in this graph
	int init; 					// init label of this graph
	Vector<Integer> fin; 		// final set of this graph
	int ancestorBoolLabel; 		// used in ud-chain

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

	public int getInit() {
		return this.init;
	}

	public Vector<Integer> getFinal() {
		return this.fin;
	}

	public Vector<Integer> getLables() {
		return this.labels;
	}

	public void setAncestorBoolLabel(int value) {
		this.ancestorBoolLabel = value;
	}

	public int getAncestorBoolLabel() {
		return this.ancestorBoolLabel;
	}

	public String printFlowGraph() {// print the graph

	}

}
