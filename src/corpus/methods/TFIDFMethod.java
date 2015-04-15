package corpus.methods;

import java.util.ArrayList;

import corpus.Text;

public class TFIDFMethod implements Method {

	@Override
	public Text calculate(ArrayList<Text> data) {
		return new Text();
	}
	
	
	@Override
	public String toString() {
		return "TFIDF";
	}


	@Override
	public Text modify(Text t) {
		return new Text();
	}


	@Override
	public String getPostfix() {
		return "_t";
	}
	
	
}
