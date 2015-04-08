package corpus;

public class TextTest {

	public static void main(String[] args) {
		Text t1 = new Text();
		t1.add("Hej");
		t1.add("Hallå");
		t1.add("Hallå");
		t1.add("Hellu");
		
		Text t2 = new Text();
		t2.add("Hopp");
		
		System.out.println("Distance: " + t1.distanceTo(t2));
	}

}
