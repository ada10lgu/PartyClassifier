package classifier;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import corpus.Text;
import csv.CSV;

public class Classifier {

	public static void main(String[] args) throws IOException {

		File folder = new File("data/text/");
		HashMap<String, Text> map = new HashMap<>();

		System.out.println("Loading data");
		for (File file : folder.listFiles()) {
			String name = file.getName();
			String party = name.substring(0, name.length() - 4);
			Text t = new Text(party);
			map.put(party, t);
		}
		System.out.println("All data loaded");

		HashMap<String, ArrayList<Text>> data = getAllTexts();

		int sum = 0;
		int correct = 0;

		for (String key : data.keySet()) {
			System.out.print(key+":\t");
			ArrayList<Text> texts = data.get(key);
			int partySum = 0;
			int partyCorrect = 0;
			for (Text t : texts) {
				if (classify(t, map).equals(key)) {
					correct++;
					partyCorrect++;
				}
				sum++;
				partySum++;
			}
			System.out.print(partyCorrect + "/" + partySum);
			double proc = (100.0 * partyCorrect) / partySum;
			if (!key.equals("S"))
				System.out.print("\t");
			System.out.println("\t"+proc + "%");

		}
		System.out.println();
		System.out.println("Total:");
		System.out.print("\t"+correct + "/" + sum);
		double proc = (100.0 * correct) / sum;
		System.out.println("\t"+proc + "%");

	}

	private static String classify(Text t, HashMap<String, Text> parties) {
		double best = Double.POSITIVE_INFINITY;
		String party = "?";

		for (String key : parties.keySet()) {
			double test = parties.get(key).distanceTo(t);
			if (test < best) {
				best = test;
				party = key;
			}

		}

		return party;
	}

	private static Text loadText() throws FileNotFoundException {
		Scanner s = new Scanner(new File("data/test/V"));
		StringBuilder sb = new StringBuilder();
		while (s.hasNext()) {
			sb.append(s.nextLine()).append(" ");
		}

		Text t = new Text();
		String[] list = sb.toString().split("([!\\.,])?[\\s]+");
		for (String word : list) {
			t.add(word.toLowerCase());
		}

		return t;
	}

	private static HashMap<String, ArrayList<Text>> getAllTexts()
			throws IOException {

		ArrayList<CSV> files = new ArrayList<>();

		File folder = new File("data/corpus");

		for (File file : folder.listFiles()) {
			files.add(new CSV(file));
		}

		HashMap<String, ArrayList<Text>> data = new HashMap<>();

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
