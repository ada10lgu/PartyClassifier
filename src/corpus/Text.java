package corpus;

import java.util.HashMap;

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
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("Size:").append(size).append("\n");
		sb.append(data.toString());
		
		return sb.toString();
	}

}	
