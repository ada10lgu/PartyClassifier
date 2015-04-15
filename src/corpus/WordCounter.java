package corpus;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import corpus.methods.AmmountMethod;
import corpus.methods.FrequencyMethod;
import corpus.methods.Method;
import csv.CSV;

public class WordCounter {

	public static void main(String[] args) throws IOException {

		
		
		HashMap<String, ArrayList<Text>> text = getAllPartyTexts();

		HashSet<String> words = getAllWords(text);
		
		int documents = 0;
		for (String key : text.keySet()) {
			documents += text.get(key).size();

			System.out.println(key + ":\t" + text.get(key).size());
		}
		System.out.println(documents);

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

	private static HashSet<String> getAllWords(HashMap<String, ArrayList<Text>> text) {
		HashSet<String> data = new HashSet<>();
		
		Text all = new Text();
		
		for (String key : text.keySet()) {
			for (Text t : text.get(key)) {
				all.addText(t);
			}
		}
		
		
		
		return data;
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