package setup;

import java.io.File;
import java.io.IOException;

import csv.CSV;

public class PeopleLoader {
	public static void main(String[] args) throws IOException {

//		File f = new File("data/ledarmoter.csv");
//
//		Scanner s = new Scanner(f);
//		
//		ArrayList<String> data = new ArrayList<>();
//		
//		while (s.hasNext())
//			data.add(s.nextLine());
//		
//
//		FileWriter fw = new FileWriter(f);
//		for (String row : data) {
//			if (!row.startsWith("\""))
//				fw.write("\"");
//			fw.write(row);
//			if (!row.endsWith("\""))
//				fw.write("\"");
//			fw.write("\n");
//			fw.flush();
//		}
		
		CSV csv = new CSV(new File("data/corpus/It_på_lärarutbildningen_-_Interpellationsdebatt_tisdag_24__mars_2015_-_riksdagen.se.csv"));
		System.out.println(csv);
		
	}
}
