package corpus;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import corpus.methods.AmmountMethod;
import corpus.methods.FrequencyMethod;
import corpus.methods.Method;
import csv.CSV;

public class WordCounter {

	public static void main(String[] args) throws IOException {

		
		System.out.println("Reading data");
		
		HashMap<String, ArrayList<Text>> text = getAllPartyTexts();

		int documents = 0;
		for (String key : text.keySet()) {
			documents += text.get(key).size();

			System.out.println(key + ":\t" + text.get(key).size());
		}

		
		System.out.println("Creating tfidf data");
		Set<String> words = getAllWords(text);
		createTFIDFData(words, documents, text);

		
		System.out.println("Calculating methods");
		HashMap<String, Method> methods = new HashMap<>();
		methods.put("", new AmmountMethod());
		methods.put("_p", new FrequencyMethod());

		for (String key : text.keySet()) {
			ArrayList<Text> data = text.get(key);
			System.out.println(key);
			for (String methodKey : methods.keySet()) {
				Method m = methods.get(methodKey);
				System.out.println("\t" + m);

				Text t = m.calculate(data);

				t.saveToFile(key + methodKey);
			}
		}

	}

	private static void createTFIDFData(Set<String> words, int documents,
			HashMap<String, ArrayList<Text>> text) throws IOException {

		CSV csv = new CSV(new File("data/tfidf/tfidf.csv"));
		ArrayList<ArrayList<String>> tfidf = csv.getData();
		tfidf.clear();
		ArrayList<String> firstLine = new ArrayList<>();
		firstLine.add("" + documents);
		tfidf.add(firstLine);

		for (String s : words) {
			int i = 0;

			for (String key : text.keySet())
				for (Text t : text.get(key))
					if (t.contains(s))
						i++;

			ArrayList<String> row = new ArrayList<>();
			row.add(s);
			row.add("" + i);
			tfidf.add(row);
		}

		csv.save();

	}

	private static Set<String> getAllWords(HashMap<String, ArrayList<Text>> text) {
		Text all = new Text();

		for (String key : text.keySet()) {
			for (Text t : text.get(key)) {
				all.addText(t);
			}
		}
		return all.getWords();
	}

	private static HashMap<String, ArrayList<Text>> getAllPartyTexts()
			throws IOException {
		HashMap<String, ArrayList<Text>> data = new HashMap<>();

		File folder = new File("data/corpus");

		for (File file : folder.listFiles()) {
			CSV csv = new CSV(file);

			for (ArrayList<String> list : csv.getData()) {
				String party = list.get(2);
				Text t = getWords(list.get(1));

				ArrayList<Text> text = data.get(party);
				if (text == null) {
					text = new ArrayList<>();
					data.put(party, text);
				}
				text.add(t);
			}
		}
		return data;
	}

	private static Text getWords(String s) {
		Text text = new Text();
		String[] list = s.split("([!\\.,])?[\\s]+");
		for (String word : list) {
			text.add(word.toLowerCase());
		}
		return text;
	}
}