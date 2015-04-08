package classifier;

import java.util.HashMap;



public class PartyList {
	private HashMap<String, Party> map;
	private Party best;
	
	public PartyList(){
		map = new HashMap<String, Party>();
	}
	
	public void add(Party p) {
		map.put(p.party, p);
		if(best == null || best.wheigh > p.wheigh){
			best = p;
		}
	}
	
	public void multiply(double d) {
		
	}
	
	public PartyList addLists(PartyList list2) {
		return null;
	}
	
	public String getBestParty() {
		return null;
	}
	
	@Override
	public String toString() {
		return null;
	}
}
