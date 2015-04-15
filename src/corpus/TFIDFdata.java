package corpus;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import csv.CSV;

public class TFIDFdata {

	private int documents;
	private HashMap<String, Integer> data;

	public TFIDFdata(File f) throws IOException {
		data = new HashMap<>();

		CSV csv = new CSV(f);

		ArrayList<ArrayList<String>> rows = csv.getData();

		String n = rows.get(0).get(0);
		documents = Integer.parseInt(n);

		for (int i = 1; i < rows.size(); i++) {
			ArrayList<String> row = rows.get(i);

			String word = row.get(0);
			int ammount = Integer.parseInt(row.get(1));
			data.put(word, ammount);
		}

	}

	public int numberOfPages() {
		return documents;
	}

	public int wordApparance(String word) {
		Integer i = data.get(word);
		if (i == null)
			return 0;
		return i;
	}

}
