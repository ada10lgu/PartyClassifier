package corpus;

import java.util.ArrayList;

public class TextTest {

	public static void main(String[] args) {
		Text t1 = new Text();
		t1.add("Hej");
		t1.add("Hallå");
		t1.add("Hallå");
		t1.add("Hellu");
		
		t1.saveToFile("test");
		Text t2 = new Text("test");
		System.out.println(t2.toString());
	}
	
	private void testMean(){
		Text t1 = new Text();
		t1.add("Hej");
		t1.add("Hallå");
		t1.add("Hallå");
		t1.add("Hellu");
		
		Text t2 = new Text();
		t2.add("Hej");
		t2.add("Hallå");
		t2.add("Hallå");
		t2.add("Hellu");
		
		Text t3 = new Text();
		t3.add("Hej");
		t3.add("Hallå");
		t3.add("Hallå");
		t3.add("Hellu");
		
		ArrayList<Text> l1 = new ArrayList<Text>();
		l1.add(t1);
		l1.add(t2);
		l1.add(t3);
		
		Text m = new Text(l1);
		System.out.println(m.toString());
	}

}
