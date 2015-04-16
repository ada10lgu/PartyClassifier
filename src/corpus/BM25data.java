package corpus;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import csv.CSV;

public class BM25data {
	private String party;
	private int documentCount;
	private Double wordCount;
	private Text data;
	
	public BM25data(File f) throws IOException{
		data = new Text();
		wordCount = 0.0;
		CSV csv = new CSV(f);
		ArrayList<ArrayList<String>> rows = csv.getData();
		party = rows.get(0).get(1);
		String c = rows.get(1).get(1);
		documentCount = Integer.parseInt(c);
		
		for (int i = 2; i < rows.size(); i++) {
			ArrayList<String> row = rows.get(i);

			String word = row.get(0);
			Double ammount = Double.parseDouble(row.get(1));
			wordCount += ammount;
			data.getData().put(word, ammount);
		}
	}
	
	public int getDocumentCount(){
		return documentCount;
	}
	
	public Double getAvgdl(){
		return (double)wordCount/documentCount;
	}
	
	public Text getData(){
		return data;
	}
	
	public String getParty(){
		return party;
	}
	
}
