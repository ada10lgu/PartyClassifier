package corpus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InterpellationsFetcher {

	private static HashSet<String> urls = new HashSet<>();

	public static void main(String[] args) throws IOException {

		
		
		
		String mainURL = "http://www.riksdagen.se/sv/Start/Sok/?typ=ip&rm=2014/15&searchtype=webbtv&facets=2&a=s&sortorder=desc&sort=debattdagtid&webbtv=1&p=";
		
		
		
		int start = 1;
		int stop = 16;

		System.out.println("Fetching links");
		for (int i = start; i <= stop; i++) {
			String url = mainURL + i;

			StringBuilder sb = new StringBuilder();

			URL connection = new URL(url);
			URLConnection u = connection.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					u.getInputStream(), "UTF-8"));
			String inputLine;
			while ((inputLine = in.readLine()) != null)
				sb.append(inputLine).append(" ");
			in.close();
			fetchURLs(sb.toString());

			System.out.println(i + "/" + stop);
		}


		
		System.out.println("Saving files");
		Model m = new Model();

		int i = 1;
		for (String url : urls) {

			System.out.print(i + "/" + urls.size() + " ");
			m.setURL(url);
			if (m.getParsedData().length() > 10)
				System.out.println("yay");
			else {
				System.out.println("ney (" + m.getTitle() + ")");
			}
			m.save();
			i++;
		}

	}

	private static void fetchURLs(String html) {

		String find = "href=\"(/sv/Debatter--beslut/Interpellationsdebatter1/Debatt/\\?did=(.*?))\"";
		Pattern p = Pattern.compile(find);
		Matcher m = p.matcher(html);

		while (m.find()) {
			urls.add("http://www.riksdagen.se" + m.group(1));
		}

	}

}
