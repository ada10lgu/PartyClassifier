package csv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

public class CSV {

	private File f;
	private ArrayList<ArrayList<String>> data;

	private final static char COLUMN_SEPERATOR = ',';
	private final static char STRING_CONTAINER = '"';
	private static final char ESCAPE_CARACTER = '\\';

	public CSV(File f) throws IOException {
		this.f = f;

		if (!f.exists())
			f.createNewFile();

		data = new ArrayList<>();

		BufferedReader in = new BufferedReader(new InputStreamReader(
				new FileInputStream(f), "UTF8"));

		String str;
		while ((str = in.readLine()) != null) {
			data.add(parseRow(str));
		}

		in.close();
	}

	private ArrayList<String> parseRow(String row) {
		ArrayList<String> data = new ArrayList<>();

		String s = "";
		char expect = STRING_CONTAINER;
		for (int i = 0; i < row.length(); i++) {
			char c = row.charAt(i);
			switch (expect) {
			case STRING_CONTAINER:
				if (c != STRING_CONTAINER) {
					System.out.println(data);
					throw new RuntimeException("Illegal character (" + c + ")");
				}
				s = "";
				expect = (char) -1;
				break;
			case COLUMN_SEPERATOR:
				data.add(s);
				expect = STRING_CONTAINER;
				break;

			default:
				if (c != STRING_CONTAINER) {
					if (c == ESCAPE_CARACTER)
						s += row.charAt(++i);
					else
						s += c;
				} else
					expect = COLUMN_SEPERATOR;
				break;
			}
		}
		data.add(s);
		return data;
	}

	public void save() throws IOException {

		Writer out = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(f), "UTF-8"));

		for (ArrayList<String> row : data) {
			if (row.isEmpty())
				continue;
			out.write(STRING_CONTAINER);
			out.write(row.get(0));
			out.write(STRING_CONTAINER);
			for (int i = 1; i < row.size(); i++) {
				String s = row.get(i);
				s = s.replaceAll("\\\\", "\\\\\\\\");
				s = s.replaceAll("\"", "\\\\\"");
				out.write(COLUMN_SEPERATOR);
				out.write(STRING_CONTAINER);
				out.write(s);
				out.write(STRING_CONTAINER);
			}
			out.write("\n");
		}
		out.flush();
		out.close();
	}

	public ArrayList<ArrayList<String>> getData() {
		return data;
	}

	@Override
	public String toString() {
		return data.toString();
	}
}
