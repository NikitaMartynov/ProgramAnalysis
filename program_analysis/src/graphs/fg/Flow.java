package graphs.fg;

public class Flow {
	 int pri;    // the block label which the flow flows from 
     int next;   // the block label which the flow flows to
     
     public Flow(int from, int to) {	// constructor
    	 this.pri = from;
    	 this.next = to;
     }
     
     public String toString() {
    	 return "(" + pri + ", " + next + ")";
     }
}
