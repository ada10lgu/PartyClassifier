package corpus;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import corpus.methods.AmmountMethod;
import corpus.methods.Method;
import csv.CSV;

public class WordCounter {

	public static void main(String[] args) throws IOException {

		HashMap<String, ArrayList<Text>> text = getAllPartyTexts();

		for (String key : text.keySet()) {
			System.out.println(key + ":\t" + text.get(key).size());
		}

		HashMap<String, Method> methods = new HashMap<>();
		methods.put("", new AmmountMethod());
		methods.put("_p", new AmmountMethod());
		

		for (String key : text.keySet()) {
			ArrayList<Text> data = text.get(key);
			System.out.println(key);
			for (String methodKey : methods.keySet()) {
				Method m = methods.get(methodKey);
				System.out.println("\t"+m);

				Text t = m.calculate(data);

				t.saveToFile(key + methodKey);
			}
		}

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

	private static void old() throws IOException {
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
				Text t = getWords(list.get(1));
				if (procent)
					t = t.toPercent();

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

}
