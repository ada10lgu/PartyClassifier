package corpus;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import csv.CSV;

public class WordCounter {

	public static void main(String[] args) throws IOException {

		ArrayList<CSV> files = new ArrayList<>();

		File folder = new File("data/corpus");

		for (File file : folder.listFiles()) {
			files.add(new CSV(file));
		}

		HashMap<String, ArrayList<Text>> data = new HashMap<>();

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

			}
		}
		for (String key : data.keySet()) {
			System.out.println(key + " " + data.get(key).size());
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
