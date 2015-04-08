package classifier;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import corpus.Text;

public class ClassifierWeighted {

	private static PartyTester pt1;
	private static PartyTester pt2;
	private static TextList tl;
	
	public static void main(String[] args) throws IOException {

		println("Loading party lists");
		pt1 = new PartyTester("");
		pt2 = new PartyTester("_p");

		Random r = new Random();
		
		for(int i = 0; i < 50; i++) {
		
		println("Loading all texts");
		tl = new TextList(new File("data/corpus"));
		println("Done!");

		
		test(r.nextDouble(),100+r.nextInt(200));
		}
	}
	
	private static void test(double x, double y) {
		println("Testing ("+x+","+y+")");
		int i = 0;

		int a = 0;
		int b = 0;
		int c = 0;
		for (PartyText pText : tl) {
			Text t = pText.getText();
			PartyList pl1 = pt1.evaluate(t);
			t.toPercent();
			PartyList pl2 = pt2.evaluate(t);
		
			pl1.multiply(x);
			pl2.multiply(y);
			
			
			PartyList sum = pl1.addLists(pl2);

			// System.out.printf("%s\t(%s/%s = %s)\n", pText.getParty(),
			// pl1.getBestParty(), pl2.getBestParty(),sum.getBestParty());

			if (pText.isParty(pl1.getBestParty()))
				a++;
			if (pText.isParty(pl2.getBestParty()))
				b++;
			if (pText.isParty(sum.getBestParty()))
				c++;

			i++;
			if (i == 2500)
				break;
		}
		System.out.printf("%d\t(%d/%d = %d)\n", i, a, b, c);
	}
	

	private static String time() {
		SimpleDateFormat printFormat = new SimpleDateFormat("[HH:mm:ss] ");
		return printFormat.format(new Date());
	}

	public static void println(String s) {
		System.out.println(time() + s);
	}
}
