package classifier;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import corpus.Text;
import csv.CSV;

public class Classifier {

	public static void main(String[] args) throws IOException {
		classify(true);
		classify(false);

	}

	private static void classify(boolean procent) throws IOException {

		if (procent)
			System.out.println("Using procentage based space");
		else
			System.out.println("Using ammount based space");

		HashMap<String, Text> map = new HashMap<>();

		String[] parties = new String[] { "C", "FP", "KD", "M", "MP", "S",
				"SD", "V" };

		System.out.println("Loading data");
		for (String party : parties) {
			String postfix = "";
			if (procent)
				postfix = "_p";
			Text t = new Text(party + postfix);
			map.put(party, t);
		}
		System.out.println("All data loaded");

		HashMap<String, ArrayList<Text>> data = getAllTexts();

		int sum = 0;
		int correct = 0;

		for (String key : data.keySet()) {
			System.out.print(key + ":\t");
			ArrayList<Text> texts = data.get(key);
			int partySum = 0;
			int partyCorrect = 0;
			for (Text t : texts) {
				if (procent)
					t.toPercent();
				if (classify(t, map).equals(key)) {
					correct++;
					partyCorrect++;
				}
				sum++;
				partySum++;
				if (partySum == 25)
					break;
			}
			System.out.print(partyCorrect + "/" + partySum);
			double proc = (100.0 * partyCorrect) / partySum;
			System.out.println("\t" + proc + "%");

		}
		System.out.println();
		System.out.println("Total:");
		System.out.print("\t" + correct + "/" + sum);
		double proc = (100.0 * correct) / sum;
		System.out.println("\t" + proc + "%");

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
