package corpus.methods;

import java.util.ArrayList;

import corpus.Text;

public class TFIDFMethod implements Method {

	@Override
	public Text calculate(ArrayList<Text> data) {
		ArrayList<Text> tData = new ArrayList<>();

		for (Text t : data)
			tData.add(t.toTfidf());

		return new Text(tData);
	}

	@Override
	public String toString() {
		return "TFIDF";
	}

	@Override
	public Text modify(Text t) {
		return t.toTfidf();
	}

	@Override
	public String getPostfix() {
		return "_t";
	}

}
