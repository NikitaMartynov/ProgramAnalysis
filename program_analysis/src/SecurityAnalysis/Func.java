package SecurityAnalysis;

import java.util.HashMap;
import java.util.Map.Entry;

public class Func {
	
	public static HashMap<String, SecLevel> deepLineCopy(
			HashMap<String, SecLevel> original){

		HashMap<String, SecLevel> copy = new HashMap<String, SecLevel>();
		    for(Entry<String, SecLevel>  entry : original.entrySet()){
		        copy.put(entry.getKey(), entry.getValue() );
		    }
		    return copy;
		}

}
