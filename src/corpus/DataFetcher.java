package corpus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataFetcher {

	StringBuilder sb = new StringBuilder();

	public DataFetcher(String url) throws IOException {
		URL connection = new URL(url);
		URLConnection u = connection.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(
				u.getInputStream(), "UTF-8"));
		String inputLine;
		while ((inputLine = in.readLine()) != null)
			sb.append(inputLine).append("\n");
		in.close();
	}

	@Override
	public String toString() {
		return sb.toString();
	}

	public ArrayList<String[]> parsedData() {
		String s = toString().replace('\n', (char) 2);

		Matcher m1 = find(s, "<body.*>.*</body>");
		String a = "";
		if (m1.find()) {
			a = m1.group();
		}

		Matcher m2 = find(a, "<div.*?id=\"panel(.*?)\".*?>(.*?)</div>");
		String b = "";
		while (m2.find()) {
			if (m2.group(1).equals("1"))
				b += m2.group(2);
		}

		Matcher m3 = find(b, "<p.*</p>");
		String c = "";
		if (m3.find()) {
			c = m3.group();
		}
		c.trim();

		Matcher m4 = find(c, "^(<p><strong>.*?besvaras av </strong></p>)?(.*)");
		String d = "";
		if (m4.find()) {
			d = m4.group(2);
		}

		Matcher m5 = find(d, "(.*)<p>Överläggningen var härmed avslutad");
		String e = "";
		if (m5.find()) {
			e = m5.group(1);
		}

		ArrayList<String> rows = new ArrayList<>();

		Matcher m6 = find(e, "<p>(.*?)</p>");
		while (m6.find()) {
			rows.add(m6.group(1));
		}

		ArrayList<String[]> data = new ArrayList<>();

		StringBuilder current = null;
		String speaker = null;
		for (String row : rows) {
			if (row.trim().equals(""))
				continue;
			if (row.startsWith("<strong>")) {
				if (current != null) {
					data.add(new String[] { speaker, current.toString() });
				}
				current = new StringBuilder();
				speaker = getName(row);

			} else {
				current.append(row);
				current.append(" ");
			}
		}
		if (current != null) {
			data.add(new String[] { speaker, current.toString() });
		}
		
		return data;
	}
	
	public String parsedText() {
		
		ArrayList<String[]> data = parsedData();

		StringBuilder sb = new StringBuilder();

		for (String[] row : data) {
			sb.append(row[0]).append("\n");
			sb.append(row[1]);
			sb.append("\n");
		}

		return sb.toString();

	}

	private String getName(String s) {

		Matcher m = find(s,
				"anf.(\\d+)[\\s]*(Försvarsminister|Arbetsmarknadsminister|Kultur- och demokratiminister|Statsrådet|Finansminister|Utrikesminister|Klimat- och miljöminister|Socialförsäkringsminister|Justitie- och migrationsmin.)?[\\s]*(.*?)[\\s]*\\(.*?\\)");
		if (m.find()) {
			return m.group(3);
		} else {
			throw new RuntimeException(s);
		}
	}

	private Matcher find(String data, String regex) {
		Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE
				| Pattern.MULTILINE);
		return p.matcher(data);
	}

	public String getTitle() {
		String s = toString().replace('\n', (char) 2);

		Matcher m1 = find(s, "<title.*>(.*)</title>");
		if (m1.find()) {
			return m1.group(1).trim();
		}
		return "ERROR";
	}

}
