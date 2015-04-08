package data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import csv.CSV;

public class RiksdagMembers {

	private HashMap<String, String> people;

	public RiksdagMembers(File f) throws IOException {
		people = new HashMap<>();

		CSV csv = new CSV(f);

		for (ArrayList<String> person : csv.getData()) {
			String name = person.get(1) + " " + person.get(0);
			people.put(name, person.get(2));
		}

	}

	public String getParty(String name) {
		return people.get(name);
	}

	@Override
	public String toString() {
		return people.toString();
	}


}
