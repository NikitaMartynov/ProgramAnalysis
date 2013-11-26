
package program_slicing;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import free_variables.FreeVariable;

public class ReachingDefinitionColln implements Iterable<ReachingDefinition>{
	private Set<ReachingDefinition> rdSet;
	
	public ReachingDefinitionColln(){
		rdSet = new HashSet<ReachingDefinition>();
	}
	
	 public boolean isSubsetOrEquals(ReachingDefinitionColln otherRDSet){
	    	return otherRDSet.rdSet.containsAll(rdSet);
	    }
	 
	public boolean contains(ReachingDefinition rd){
		 return rdSet.contains(rd);
	}
	
	public void union(ReachingDefinitionColln otherRDList){
		rdSet.addAll(otherRDList.rdSet);
	}
	public void add(ReachingDefinition rd){
		rdSet.add(rd);
	}
	
	public void complement(ReachingDefinitionColln otherRDList){
		rdSet.removeAll(otherRDList.rdSet);
	}

	@Override
	public Iterator<ReachingDefinition> iterator() {
		 Iterator<ReachingDefinition> iRD = rdSet.iterator();
	        return iRD; 
	}
	
	@Override
	public String toString(){
		String rdc="";
		
		for (ReachingDefinition rd : rdSet) {
			rdc += "(" + rd.toString() + ") ";
		}
		return rdc;
	}
	

}
