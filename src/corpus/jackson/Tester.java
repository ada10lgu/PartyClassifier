package corpus.jackson;

import java.awt.List;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.*;
public class Tester {

	public static void main(String[] args) {

		ObjectMapper mapper = new ObjectMapper();

		try {

			// read from file, convert it to user class
			User user = mapper.readValue(new File("c:\\user.json"), User.class);

			// display to console
			System.out.println(user);

		} catch (JsonGenerationException e) {

			e.printStackTrace();

		} catch (JsonMappingException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
	}

	
	private static class User {
		 
		private int age = 29;
		private String name = "mkyong";
		private ArrayList<String> messages = new ArrayList<String>() {
			{
				add("msg 1");
				add("msg 2");
				add("msg 3");
			}
		};
	 
		//getter and setter methods
	 
		@Override
		public String toString() {
			return "User [age=" + age + ", name=" + name + ", " +
					"messages=" + messages + "]";
		}
	}
}
