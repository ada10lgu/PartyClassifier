package classifier;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClassifierWeighted {

	public static void main(String[] args) throws IOException {

		println("Loading party lists");
		PartyTester pt1 = new PartyTester("");
		PartyTester pt2 = new PartyTester("_p");
		println("Loading all texts");
		TextList tl = new TextList(new File("data/corpus"));
		println("Done!");

		int i = 0;
		for (PartyText pText : tl) {
			i++;
		}
		System.out.println(i);
	}

	private static String time() {
		SimpleDateFormat printFormat = new SimpleDateFormat("[HH:mm:ss] ");
		return printFormat.format(new Date());
	}

	public static void println(String s) {
		System.out.println(time() + s);
	}
}
