package classifier;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import corpus.Text;
import csv.CSV;

public class TextList implements Iterable<PartyText> {

	private HashMap<String, ArrayList<Text>> data;

	private ArrayList<PartyText> list;

	public TextList(File folder) throws IOException {

		list = new ArrayList<>();

		ArrayList<CSV> files = new ArrayList<>();

		for (File file : folder.listFiles()) {
			files.add(new CSV(file));
		}

		data = new HashMap<>();

		for (CSV csv : files) {

			for (ArrayList<String> list : csv.getData()) {
				String party = list.get(2);
				Text t = getWords(list.get(1));

				ArrayList<Text> textList = data.get(party);
				if (textList == null) {
					textList = new ArrayList<>();
					data.put(party, textList);
				}
				textList.add(t);
			}
		}

		for (String key : data.keySet()) {
			for (int i = 0; i < 25; i++) {
				Text t = data.get(key).get(i);
				list.add(new PartyText(key, t));
			}
		}

	}

	private Text getWords(String s) {
		Text text = new Text();
		String[] list = s.split("([!\\.,])?[\\s]+");
		for (String word : list) {
			text.add(word.toLowerCase());
		}
		return text;
	}

	@Override
	public Iterator<PartyText> iterator() {
		return list.iterator();
	}

}
