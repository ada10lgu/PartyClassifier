package corpus;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Text {
	private HashMap<String,Double> data;
	int size;
	
	public Text() {
		data = new HashMap<>();
	}
	
	public Text(List<Text> textList){
		data = new HashMap<>();
		for(Text t : textList){
			for(String key : t.data.keySet()){
				Double v1 = data.get(key);
				if(v1 == null){
					v1 = 0.0;
				}
				data.put(key, v1 + t.data.get(key));
			}
		}
		for(String key : data.keySet()){
			data.put(key, data.get(key)/textList.size());
		}
	}
	
	public void add(String word) {
		Double i = data.get(word);
		if (i== null) {
			i = 0.0;
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
		
		Double tot = 0.0;
		for (String word : allWords){
			Double d1, d2;
			d1 = data.get(word);
			d2 = otherText.data.get(word);
			if (d1 == null){
				d1 = 0.0;
			}
			if (d2 == null){
				d2 = 0.0;
			}
			Double part = (d1 - d2) * (d1 - d2);
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
