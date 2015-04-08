package classifier;

import java.util.HashMap;

import corpus.Text;

public class PartyTester {

	private String[] parties = new String[] { "C", "FP", "KD", "M", "MP", "S",
			"SD", "V" };

	private HashMap<String, Text> texts;

	public PartyTester(String postFix) {
		texts = new HashMap<>();

		for (String s : parties) {
			Text t = new Text(s + postFix);
			texts.put(s, t);
		}
	}

	public PartyList evaluate(Text t) {
		PartyList pl = new PartyList();
		for (String key : texts.keySet()) {
			double d = texts.get(key).distanceTo(t);
			Party p = new Party(key, d);
			pl.add(p);
		}

		return pl;
	}
}
