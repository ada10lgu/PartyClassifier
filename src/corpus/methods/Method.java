package corpus.methods;

import java.util.ArrayList;

import corpus.Text;

public interface Method {
	
	public Text calculate(ArrayList<Text> data);
	
	public Text modify(Text t);
	
	public String getPostfix();
}
