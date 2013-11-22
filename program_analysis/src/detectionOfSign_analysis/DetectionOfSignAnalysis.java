package detectionOfSign_analysis;

import java.util.HashMap;
import java.util.Vector;

public class DetectionOfSignAnalysis {

	private HashMap<String, Signs> elemSigns;
	
	public void initialize (Vector<String> variables){
		
		elemSigns = new HashMap<String, Signs>();
		//assert ProgramGraph.edges != null : "Program graph edges not buit at initialization of Detection Of Signs";
		
		for( String var : variables){
			if(!elemSigns.containsKey(var)) elemSigns.put(var, new Signs());
		}	
	}
}
