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
		if(best == null || best.value > p.value){
			best = p;
		}
	}
	
	public void multiply(double d) {
		for(String key : map.keySet()){
			Party p = map.get(key);
			p.value *= d;
			map.put(p.party, p);
		}
	}
	
	public PartyList addLists(PartyList list2) {
		return null;
	}
	
	public String getBestParty() {
		return best.party;
	}
	
	@Override
	public String toString() {
		return null;
	}
}
