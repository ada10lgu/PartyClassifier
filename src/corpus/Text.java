package corpus;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Text {
	private HashMap<String, Double> data;
	int size;
	private final static String BASEPATH = "data/text/";

	public Text() {
		data = new HashMap<>();
	}

	public Text(List<Text> textList) {
		data = new HashMap<>();
		for (Text t : textList) {
			for (String key : t.data.keySet()) {
				Double v1 = data.get(key);
				if (v1 == null) {
					v1 = 0.0;
				}
				data.put(key, v1 + t.data.get(key));
			}
		}
		for (String key : data.keySet()) {
			data.put(key, data.get(key) / textList.size());
		}
	}

	public Text(String filename) {
		fetchFromFile(filename);
	}

	public void add(String word) {
		Double i = data.get(word);
		if (i == null) {
			i = 0.0;
		}
		i++;
		data.put(word, i);
		size++;

	}
	
	public void addText(Text otherText){
		for (String key : otherText.data.keySet()){
			Double otherCount = otherText.data.get(key);
			Double count = data.get(key);
			if(count == null){
				data.put(key, otherCount);
			}else{
				data.put(key, count + otherCount);
			}
		}
	}

	public double distanceTo(Text otherText) {
		HashSet<String> allWords = new HashSet<String>();
		for (String key : data.keySet()) {
			allWords.add(key);
		}
		for (String key : otherText.data.keySet()) {
			allWords.add(key);
		}

		Double tot = 0.0;
		for (String word : allWords) {
			Double d1, d2;
			d1 = data.get(word);
			d2 = otherText.data.get(word);
			if (d1 == null) {
				d1 = 0.0;
			}
			if (d2 == null) {
				d2 = 0.0;
			}
			Double part = (d1 - d2) * (d1 - d2);
			tot += part;
		}
		return Math.sqrt(tot);
	}

	public void saveToFile(String filename) {
		try {
			FileOutputStream fileOut = new FileOutputStream(
					getFilePath(filename));
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(data);
			out.close();
			fileOut.close();
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	private void fetchFromFile(String filename) {
		try {
			FileInputStream fileIn = new FileInputStream(getFilePath(filename));
			ObjectInputStream in = new ObjectInputStream(fileIn);
			data = (HashMap<String, Double>) in.readObject();
			fileIn.close();
			in.close();
		} catch (IOException i) {
			System.out.println("IOException");
			i.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			System.out.println("ClassNotFoundException");
			c.printStackTrace();
			return;
		}
	}

	private String getFilePath(String filename) {
		StringBuilder sb = new StringBuilder();
		sb.append(BASEPATH);
		sb.append(filename);
		sb.append(".ser");
		return sb.toString();
	}

	private Double getDataSize() {
		Double size = 0.0;
		for (String key : data.keySet()) {
			Double val = data.get(key);
			if (val == null) {
				val = 0.0;
			}
			size += val;
		}
		return size;
	}

	public Text toPercent() {
		Text t = new Text();

		Double size = getDataSize();

		for (String key : data.keySet()) {
			Double v1 = data.get(key);
			if (v1 != null && v1 != 0.0) {
				t.data.put(key, v1 / size);
			} else {
				t.data.put(key, 0.0);
			}
		}
		return t;
	}

	public Set<String> getWords() {
		return data.keySet();
	}
	
	public boolean contains(String word) {
		Double d = data.get(word);
		return d != null;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("Size:").append(size).append("\n");
		sb.append(data.toString());

		return sb.toString();
	}

}
