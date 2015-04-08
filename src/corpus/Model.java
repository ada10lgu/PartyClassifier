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
		if (df == null)
			return;
		File f = new File("data/corpus/" + getTitle().replace(' ', '_')
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

	public static void main(String[] args) {
		String[] list = {"http://www.riksdagen.se/sv/Debatter--beslut/Interpellationsdebatter1/Debatt/?did=H210348",
				"http://www.riksdagen.se/sv/Debatter--beslut/Interpellationsdebatter1/Debatt/?did=H210405",
				"http://www.riksdagen.se/sv/Debatter--beslut/Interpellationsdebatter1/Debatt/?did=H210355",
				"http://www.riksdagen.se/sv/Debatter--beslut/Interpellationsdebatter1/Debatt/?did=H210351",
				"http://www.riksdagen.se/sv/Debatter--beslut/Interpellationsdebatter1/Debatt/?did=H210359",
				"http://www.riksdagen.se/sv/Debatter--beslut/Interpellationsdebatter1/Debatt/?did=H210380",
				"http://www.riksdagen.se/sv/Debatter--beslut/Interpellationsdebatter1/Debatt/?did=H210345",
				"http://www.riksdagen.se/sv/Debatter--beslut/Interpellationsdebatter1/Debatt/?did=H210340",
				"http://www.riksdagen.se/sv/Debatter--beslut/Interpellationsdebatter1/Debatt/?did=H210293",
				"http://www.riksdagen.se/sv/Debatter--beslut/Interpellationsdebatter1/Debatt/?did=H210399",
				"http://www.riksdagen.se/sv/Debatter--beslut/Interpellationsdebatter1/Debatt/?did=H210370",
				"http://www.riksdagen.se/sv/Debatter--beslut/Interpellationsdebatter1/Debatt/?did=H210354",
				"http://www.riksdagen.se/sv/Debatter--beslut/Interpellationsdebatter1/Debatt/?did=H210362",
				"http://www.riksdagen.se/sv/Debatter--beslut/Interpellationsdebatter1/Debatt/?did=H210347",
				"http://www.riksdagen.se/sv/Debatter--beslut/Interpellationsdebatter1/Debatt/?did=H210379",
				"http://www.riksdagen.se/sv/Debatter--beslut/Interpellationsdebatter1/Debatt/?did=H210379",
				"http://www.riksdagen.se/sv/Debatter--beslut/Interpellationsdebatter1/Debatt/?did=H210407",
				"http://www.riksdagen.se/sv/Debatter--beslut/Interpellationsdebatter1/Debatt/?did=H210309",
				"http://www.riksdagen.se/sv/Debatter--beslut/Interpellationsdebatter1/Debatt/?did=H210382",
				"http://www.riksdagen.se/sv/Debatter--beslut/Interpellationsdebatter1/Debatt/?did=H210392",
				"http://www.riksdagen.se/sv/Debatter--beslut/Interpellationsdebatter1/Debatt/?did=H210390",
				"http://www.riksdagen.se/sv/Debatter--beslut/Interpellationsdebatter1/Debatt/?did=H210313",
				"http://www.riksdagen.se/sv/Debatter--beslut/Interpellationsdebatter1/Debatt/?did=H210311",
				"http://www.riksdagen.se/sv/Debatter--beslut/Interpellationsdebatter1/Debatt/?did=H210307",
				"http://www.riksdagen.se/sv/Debatter--beslut/Interpellationsdebatter1/Debatt/?did=H210289",
				"http://www.riksdagen.se/sv/Debatter--beslut/Interpellationsdebatter1/Debatt/?did=H210276",
				"http://www.riksdagen.se/sv/Debatter--beslut/Interpellationsdebatter1/Debatt/?did=H210372",
				"http://www.riksdagen.se/sv/Debatter--beslut/Interpellationsdebatter1/Debatt/?did=H210367",
				"http://www.riksdagen.se/sv/Debatter--beslut/Interpellationsdebatter1/Debatt/?did=H210368",
				"http://www.riksdagen.se/sv/Debatter--beslut/Interpellationsdebatter1/Debatt/?did=H210353",
				"http://www.riksdagen.se/sv/Debatter--beslut/Interpellationsdebatter1/Debatt/?did=H210371",
				"http://www.riksdagen.se/sv/Debatter--beslut/Interpellationsdebatter1/Debatt/?did=H210357",
				"http://www.riksdagen.se/sv/Debatter--beslut/Interpellationsdebatter1/Debatt/?did=H210377",
				"http://www.riksdagen.se/sv/Debatter--beslut/Interpellationsdebatter1/Debatt/?did=H210365",
				"http://www.riksdagen.se/sv/Debatter--beslut/Interpellationsdebatter1/Debatt/?did=H210299",
				"http://www.riksdagen.se/sv/Debatter--beslut/Interpellationsdebatter1/Debatt/?did=H210298",
				"http://www.riksdagen.se/sv/Debatter--beslut/Interpellationsdebatter1/Debatt/?did=H210291",
				"http://www.riksdagen.se/sv/Debatter--beslut/Interpellationsdebatter1/Debatt/?did=H210322",
				"http://www.riksdagen.se/sv/Debatter--beslut/Interpellationsdebatter1/Debatt/?did=H210287",
				"http://www.riksdagen.se/sv/Debatter--beslut/Interpellationsdebatter1/Debatt/?did=H210281",
				"http://www.riksdagen.se/sv/Debatter--beslut/Interpellationsdebatter1/Debatt/?did=H210280",
				"http://www.riksdagen.se/sv/Debatter--beslut/Interpellationsdebatter1/Debatt/?did=H210277",
				"http://www.riksdagen.se/sv/Debatter--beslut/Interpellationsdebatter1/Debatt/?did=H210363",
				"http://www.riksdagen.se/sv/Debatter--beslut/Interpellationsdebatter1/Debatt/?did=H210327",
				"http://www.riksdagen.se/sv/Debatter--beslut/Interpellationsdebatter1/Debatt/?did=H210314",
				"http://www.riksdagen.se/sv/Debatter--beslut/Interpellationsdebatter1/Debatt/?did=H210297",
				"http://www.riksdagen.se/sv/Debatter--beslut/Interpellationsdebatter1/Debatt/?did=H210238",
				"http://www.riksdagen.se/sv/Debatter--beslut/Interpellationsdebatter1/Debatt/?did=H210220",
				"http://www.riksdagen.se/sv/Debatter--beslut/Interpellationsdebatter1/Debatt/?did=H210250",
				"http://www.riksdagen.se/sv/Debatter--beslut/Interpellationsdebatter1/Debatt/?did=H210364",
				"http://www.riksdagen.se/sv/Debatter--beslut/Interpellationsdebatter1/Debatt/?did=H210358",
				"http://www.riksdagen.se/sv/Debatter--beslut/Interpellationsdebatter1/Debatt/?did=H210352",
				"http://www.riksdagen.se/sv/Debatter--beslut/Interpellationsdebatter1/Debatt/?did=H210339",
				"http://www.riksdagen.se/sv/Debatter--beslut/Interpellationsdebatter1/Debatt/?did=H210329",
				"http://www.riksdagen.se/sv/Debatter--beslut/Interpellationsdebatter1/Debatt/?did=H210349",
				"http://www.riksdagen.se/sv/Debatter--beslut/Interpellationsdebatter1/Debatt/?did=H210335",
				"http://www.riksdagen.se/sv/Debatter--beslut/Interpellationsdebatter1/Debatt/?did=H210330",
				"http://www.riksdagen.se/sv/Debatter--beslut/Interpellationsdebatter1/Debatt/?did=H210321",
				"http://www.riksdagen.se/sv/Debatter--beslut/Interpellationsdebatter1/Debatt/?did=H210328"};

		Model m = new Model();

		for (int i = 0; i < list.length; i++) {
			String s = list[i];
			System.out.print((i + 1) + "/" + list.length + " ");
			m.setURL(s);
			if (m.getParsedData().length() > 10)
				System.out.println("yay");
			else {
				System.out.println("ney (" + m.getTitle() + ")");
			}
			m.save();
		}

	}
}
