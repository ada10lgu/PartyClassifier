package corpus;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.JOptionPane;

import csv.CSV;

public class Model extends Observable {

	private DataFetcher df;

	public synchronized void setURL(String url) {
		try {
			df = new DataFetcher(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		setChanged();
		notifyObservers();
	}

	public synchronized String getHTML() {
		if (df == null)
			return "";
		return df.toString();
	}

	public String getParsedData() {
		if (df == null)
			return "No Data";
		return df.parsedText();
	}

	public String getTitle() {
		return df.getTitle();
	}

	public void save() {
		save("");
	}
	
	public void save(String extra) {
		if (df == null)
			return;
		File f = new File("data/corpus"+extra+"/" + getTitle().replace(' ', '_')
				+ ".csv");

		try {
			CSV csv = new CSV(f);
			ArrayList<ArrayList<String>> data = csv.getData();
			data.clear();
			for (String[] row : df.parsedData()) {
				ArrayList<String> temp = new ArrayList<>();
				for (String s : row)
					temp.add(s);
				data.add(temp);
			}

			csv.save();

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return;
		}
	}

}
