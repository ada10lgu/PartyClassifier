package corpus;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import csv.CSV;

public class WordCounter {

	public static void main(String[] args) throws IOException {

		boolean procent = true;

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
				System.out.println(list.get(2));
				Text t = getWords(list.get(1));
				if (procent)
					t.toPercent();

				ArrayList<Text> textList = data.get(name);
				if (textList == null) {
					textList = new ArrayList<>();
					data.put(name, textList);
				}
				textList.add(t);
				partyReference.put(name, list.get(2));
			}
		}

		HashMap<String, ArrayList<Text>> partyTexts = new HashMap<>(8);

		for (String key : data.keySet()) {
			String party = partyReference.get(key);
			ArrayList<Text> texts = partyTexts.get(party);
			if (texts == null) {
				texts = new ArrayList<Text>();
				partyTexts.put(party, texts);
			}
			texts.addAll(data.get(key));
		}

		for (String key : partyTexts.keySet()) {
			System.out.println(key + ":\t" + partyTexts.get(key).size());
		}

		System.out.println("----------");

		for (String key : partyTexts.keySet()) {
			Text t = new Text(partyTexts.get(key));
			String postfix = "";
			if (procent)
				postfix = "_p";
			t.saveToFile(key + postfix);
		}

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
