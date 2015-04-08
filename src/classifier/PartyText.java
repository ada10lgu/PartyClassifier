package classifier;

import corpus.Text;

public class PartyText {
	private String party;
	private Text t;
	
	public PartyText(String party, Text t) {
		this.party = party;
		this.t = t;
	}
	
	public Text getText() {
		return t;
	}
	
	public boolean isParty(String party) {
		return this.party.equals(party);
	}
}
