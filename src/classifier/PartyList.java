package classifier;

import java.util.ArrayList;
import java.util.HashMap;



public class PartyList {
	private HashMap<String, Party> list;
	private Party best;
	
	public PartyList(){
		list = new HashMap<String, Party>();
	}
	
	public void add(Party p) {
		list.put(p.party, p);
		if(best == null || best.value > p.value){
			best = p;
		}
	}
	
	public Party get(String party){
		return list.get(party);
	}
	
	public void multiply(double d) {
		for(String key : list.keySet()){
			Party p = list.get(key);
			p.value *= d;
			list.put(p.party, p);
		}
	}
	
	public PartyList addLists(PartyList list2) {
		PartyList mergedList = new PartyList();
		for(String key : list.keySet()){
			Double mergedValue = list.get(key).value + list2.get(key).value;
			mergedList.add(new Party(key, mergedValue));
		}
		return mergedList;
	}
	
	public String getBestParty() {
		return best.party;
	}
	
	private ArrayList<Party> getSortedArray(){
		ArrayList<Party> sortedList = new ArrayList<Party>();
		for(String key : list.keySet()){
			sortedList.add(list.get(key));
		}
		sortedList.sort(null);
		return sortedList;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		ArrayList<Party> sortedList = getSortedArray();
		for (int i = sortedList.size() -1; i >= 0; i--){
			sb.append(sortedList.get(i).toString());
			sb.append("\n");
		}
		return sb.toString();
	}
}
