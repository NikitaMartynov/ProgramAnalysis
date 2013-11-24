package detectionOfSign_analysis;

import java.util.HashMap;
import java.util.Map.Entry;

public class Func {
	
	public static HashMap<String, Signs> deepLineCopy(
			HashMap<String, Signs> original){

		HashMap<String, Signs> copy = new HashMap<String, Signs>();
		    for(Entry<String, Signs>  entry : original.entrySet()){
		        copy.put(entry.getKey(), new Signs(entry.getValue()) );
		    }
		    return copy;
		}

}
