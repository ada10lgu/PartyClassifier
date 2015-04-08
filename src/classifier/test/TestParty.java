package classifier.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import classifier.Party;
import classifier.PartyList;
import classifier.PartyTester;
import corpus.Text;

public class TestParty {

	@Test
	public void test() {
		Party p1 = new Party("SD", 0.2);
		Party p2 = new Party("S", 0.3);
		Party p3 = new Party("M", 0.2);

		assertFalse(p1.equals(p2));
		assertTrue(p1.equals(p1));
		assertTrue(p1.equals(p3));
		assertTrue(p1.compareTo(p2) < 0);
		assertTrue(p3.compareTo(p2) < 0);
		assertTrue(p2.compareTo(p3) > 0);
		assertTrue(p3.compareTo(p1) == 0);
	}

	
	@Test
	public void test2() {
		Text t = new Text("SD");
		
		
		PartyTester pt = new PartyTester("");
		PartyList pl = pt.evaluate(t);
		System.out.println(pl);
	}
}


