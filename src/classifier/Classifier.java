package classifier;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

import corpus.Text;

public class Classifier {

	public static void main(String[] args) throws FileNotFoundException {

		File folder = new File("data/text/");
		HashMap<String,Text> map = new HashMap<>();
		
		for (File file : folder.listFiles()) {
			String name = file.getName();
			String party = name.substring(0,name.length()-4);
			Text t =  new Text(party);
			map.put(party,t);
		}
		
		Text test = loadText();
		
		
		for (String party : map.keySet()) {
			System.out.print(party);
			System.out.print("\t");
			System.out.println(map.get(party).distanceTo(test));
		}

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

}
