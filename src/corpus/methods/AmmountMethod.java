package corpus.methods;

import java.util.ArrayList;

import corpus.Text;

public class AmmountMethod implements Method {

	@Override
	public Text calculate(ArrayList<Text> data) {
		return new Text(data);
	}

	@Override
	public String toString() {
		return "Ammount base";
	}
}
