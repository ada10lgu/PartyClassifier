package corpus;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import csv.CSV;

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
		data = new HashMap<>();
		size = 0;
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
	
	public void saveToFile(String filename){
		File file = new File(filename);
		CSV csv;
		try {
			csv = new CSV(file);
			ArrayList<ArrayList<String>> matrix = csv.getData();
			matrix.clear();
			for(String key : data.keySet()){
				ArrayList<String> word = new ArrayList<String>();
				word.add(key);
				word.add(data.get(key).toString());
				matrix.add(word);
			}
			csv.save();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void fetchFromFile(String filename){
		File file = new File(filename);
		try {
			CSV csv = new CSV(file);
			ArrayList<ArrayList<String>> matrix = csv.getData();
			for(int i = 0; i < matrix.size(); i++){
				ArrayList<String> list = matrix.get(i);
				Double count = Double.parseDouble(list.get(1));
				data.put(list.get(0), count);
				size += count;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Double getDataSize() {
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
	
	public Text toTfidf(){
		Text t = new Text();
		File file = new File("data/tfidf/tfidf.csv");
		try {
			TFIDFdata tfidf = new TFIDFdata(file);
			Double size = getDataSize();
			for (String key : data.keySet()) {
				Double tf = data.get(key);
				if (tf != null && size != 0) {
					tf = tf / size;
				} else {
					tf = 0.0;
				}
				Double idf = (double) tfidf.numberOfPages();
				idf /= (1 + tfidf.wordApparance(key));
				t.data.put(key, tf*idf);
			}
		} catch (IOException e) {
			e.printStackTrace();
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
	
	public HashMap<String, Double> getData(){
		return data;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("Size:").append(size).append("\n");
		sb.append(data.toString());

		return sb.toString();
	}

}
