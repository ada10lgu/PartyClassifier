package corpus;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import csv.CSV;
import data.RiksdagMembers;

public class WordCounter {

	public static void main(String[] args) throws IOException {

		ArrayList<CSV> files = new ArrayList<>();

		File folder = new File("data/corpus");

		for (File file : folder.listFiles()) {
			files.add(new CSV(file));
		}

		HashMap<String, ArrayList<Text>> data = new HashMap<>();

		HashMap<String, String> partyReference = new HashMap<>();

		for (CSV csv : files) {

			for (ArrayList<String> list : csv.getData()) {
				String name = list.get(0);
				Text t = getWords(list.get(1));

				ArrayList<Text> textList = data.get(name);
				if (textList == null) {
					textList = new ArrayList<>();
					data.put(name, textList);
				}
				textList.add(t);
				partyReference.put(name, list.get(2));
			}
		}

		for (String key : data.keySet()) {
			System.out.println(key + " " + data.get(key).size());
		}

		System.out.println("----------");

		HashMap<String, Integer> partyRelevance = new HashMap<>();

		partyRelevance.put("C", 0);
		partyRelevance.put("FP", 0);
		partyRelevance.put("KD", 0);
		partyRelevance.put("MP", 0);
		partyRelevance.put("M", 0);
		partyRelevance.put("S", 0);
		partyRelevance.put("SD", 0);
		partyRelevance.put("V", 0);

		for (String key : data.keySet()) {
			String party = partyReference.get(key);
			Integer i = partyRelevance.get(party);
			if (i == null) {
				i = 0;
				System.out.println(key);
			}
			i++;
			partyRelevance.put(party, i);
		}

		System.out.println(partyRelevance);

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
