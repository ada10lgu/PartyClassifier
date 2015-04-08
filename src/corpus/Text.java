package corpus;

import java.util.HashMap;
import java.util.HashSet;

public class Text {
	private HashMap<String,Integer> data;
	int size;
	
	public Text() {
		data = new HashMap<>();
	}
	
	public void add(String word) {
		Integer i = data.get(word);
		if (i== null) {
			i = 0;
		}
		i++;
		data.put(word, i);
		size++;
		
	}
	
	public double distanceTo(Text otherText){
		HashSet<String> allWords = new HashSet<String>();
		for(String key : data.keySet()){
			allWords.add(key);
		}
		for(String key : otherText.data.keySet()){
			allWords.add(key);
		}
		
		int tot = 0;
		for (String word : allWords){
			Integer d1, d2;
			d1 = data.get(word);
			d2 = otherText.data.get(word);
			if (d1 == null){
				d1 = 0;
			}
			if (d2 == null){
				d2 = 0;
			}
			int part = (d1 - d2) * (d1 - d2);
			tot += part;
		}
		return Math.sqrt(tot);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("Size:").append(size).append("\n");
		sb.append(data.toString());
		
		return sb.toString();
	}

}	
