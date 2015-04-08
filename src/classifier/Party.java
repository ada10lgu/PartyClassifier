package classifier;

public class Party implements Comparable<Party> {

	String party;
	double value;
	
	public Party(String party, double value) {
		this.party = party;
		this.value = value;
	}
	
	@Override
	public int compareTo(Party p) {
		return (int) (100000*(value-p.value));
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Party)
			return ((Party) o).compareTo(this) == 0;
		return false;
	}
	
	public String getParty() {
		return party;
	}
	
	public double getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		
		return party + "["+value+"]";
	}

}
