package corpus.methods;

import java.util.ArrayList;

import corpus.Text;

public class FrequencyMethod implements Method {

	@Override
	public Text calculate(ArrayList<Text> data) {
		ArrayList<Text> fData = new ArrayList<>();
		
		for (Text t : data)
			fData.add(t.toPercent());
		
		return new Text(fData);
	}

	
	@Override
	public String toString() {
		return "Frequenzy";
	}


	@Override
	public Text modify(Text t) {
		return t.toPercent();
	}


	@Override
	public String getPostfix() {
		return "_p";
	}
}
