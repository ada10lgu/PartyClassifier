package classifier;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import classifier.genetic.Point;
import classifier.genetic.Population;
import corpus.Text;

public class ClassifierWeighted {

	private static PartyTester pt1;
	private static PartyTester pt2;
	private static TextList tl;

	public static void main(String[] args) throws IOException {

		println("Loading party lists");
		pt1 = new PartyTester("");
		pt2 = new PartyTester("_p");
		println("Loading texts");
		tl = new TextList(new File("data/corpus"), 25);
		System.out.println("\t   Loaded " + tl.size() + " texts");
		println("Done!");

		int POPULATION_SIZE = 10;
		int GENERATIONS = 3;
		int BREEDERS = 4;
		double MUTATION_RISK = 0.04;
		int MUTATION_FREE = 1;
		int TOP_PLACING = 7;

		println("Genetic algoritm started");
		Population pop = new Population(POPULATION_SIZE);

		for (int i = 1; i <= GENERATIONS; i++) {
			println("Generation " + i);

			System.out.println("\t   Evaluating");
			for (Point p : pop.getPoints()) {
				double d = test(p.getA(), p.getB(), TOP_PLACING);
				p.setAns(d);
			}
			System.out.println("\t   Sorting");
			pop.sort();
			println("Top 3 after this round");
			for (int k = 0; k < 3 && k < pop.getPoints().length; k++) {
				Point p = pop.getPoints()[k];
				double val = p.getAns();
				double n = tl.size();
				double proc = 100 * val / n;

				System.out.printf("%f/%f = %f%%\n", val, n, proc);
			}
			System.out.println("\t   Breeding");
			pop.breed(BREEDERS);
			System.out.println("\t   Mutating");
			pop.mutate(MUTATION_RISK, MUTATION_FREE);
		}

		println("Done!");
		System.out.println(pop);

	}

	private static double test(double x, double y, int top) {
		println("Testing (" + x + "," + y + ")");
		int n = 0;

		int a = 0;
		int b = 0;
		int c = 0;
		for (PartyText pText : tl) {
			Text t = pText.getText();
			PartyList pl1 = pt1.evaluate(t);
			PartyList pl2 = pt2.evaluate(t.toPercent());

			pl1.multiply(x);
			pl2.multiply(y);

			PartyList sum = pl1.addLists(pl2);

			// System.out.printf("%s\t(%s/%s = %s)\n", pText.getParty(),
			// pl1.getBestParty(), pl2.getBestParty(),sum.getBestParty());

			for (int i = 0; i < top; i++) {
				String party = pl1.getSortedArray().get(i).getParty();
				if (party.equals(pText.getParty())) {
					a++;
					break;
				}
			}
			for (int i = 0; i < top; i++) {
				String party = pl2.getSortedArray().get(i).getParty();
				if (party.equals(pText.getParty())) {
					b++;
					break;
				}
			}
			for (int i = 0; i < top; i++) {
				String party = sum.getSortedArray().get(i).getParty();
				if (party.equals(pText.getParty())) {
					c++;
					break;
				}
			}

			n++;
		}
		// System.out.printf("%d\t(%d/%d = %d)\n", i, a, b, c);
		return c;
	}

	private static String time() {
		SimpleDateFormat printFormat = new SimpleDateFormat("[HH:mm:ss] ");
		return printFormat.format(new Date());
	}

	public static void println(String s) {
		System.out.println(time() + s);
	}
}
