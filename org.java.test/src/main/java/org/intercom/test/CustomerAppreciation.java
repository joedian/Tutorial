/**
 * 
 */
package org.intercom.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.TreeMap;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

/**
 * @author joedian
 *
 */
public class CustomerAppreciation {

	public static void main(String[] args) {
		String companyURL = "https://gist.githubusercontent.com/brianw/19896c50afa89ad4dec3/raw/6c11047887a03483c50017c1d451667fd62a53ca/gistfile1.txt";
		getCustomersForEvent(companyURL);

	}

	/**
	 * Using TreeMap as it allows for natural ordering of elements Normal
	 * sorting is not offered on a HashMap
	 * 
	 * @return
	 */
	public static TreeMap<String, String> getCustomersForEvent(String companyURL) {
		TreeMap<String, String> nearestCustomers = new TreeMap<String, String>();
		URL url = null;
		HttpURLConnection request = null;
		JsonReader jr = null;

		try {
			// absolute URL which offers content as plain text
			url = new URL(companyURL);

			// connect to source to pull data
			request = (HttpURLConnection) url.openConnection();
			request.connect();

			// initialize parser to retrieve data
			JsonParser jp = new JsonParser();
			jr = new JsonReader(new BufferedReader(new InputStreamReader(url.openStream())));

			// allows malformed text to be parsed also
			jr.setLenient(true);

			// iterate through and retrieve data
			while (jr.hasNext()) {
				JsonElement root = jp.parse(jr);
				JsonObject rootobj = root.getAsJsonObject();

				// check distance from office
				Double kilometresOfSeparation = checkDistance(rootobj.get("latitude").getAsDouble(),
						rootobj.get("longitude").getAsDouble());

				// if it is within range then add to map
				if (Math.abs(kilometresOfSeparation) <= 100) {
					nearestCustomers.put(rootobj.get("user_id").getAsString(), rootobj.get("name").getAsString());
				}
			}
			// clean up and terminate connections
			jr.endArray();
			jr.close();
			request.disconnect();

		} catch (IllegalArgumentException e) {
			// this is thrown at the end of the read of the JSON file,
			// cannot resolve and is unimportant to the functionality so it
			// being supressed
		} catch (FileNotFoundException e) {
			System.err.println("Please check your URl and try again.");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (request != null)
				request.disconnect();

			if (jr != null)
				try {
					jr.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		}

		return nearestCustomers;
	}

	/**
	 * Evaluate the distance between the customers homes and the office
	 * 
	 * @param lattitude
	 * @param longitude
	 * @return
	 */
	private static Double checkDistance(double lattitude, double longitude) {
		double theta = -6.2592576 - longitude;
		double distance = Math.sin(Math.toRadians(53.3381985)) * Math.sin(Math.toRadians(lattitude))
				+ Math.cos(Math.toRadians(53.3381985)) * Math.cos(Math.toRadians(lattitude))
						* Math.cos(Math.toRadians(theta));
		distance = Math.acos(distance);
		distance = Math.toDegrees(distance);
		distance = distance * 60 * 1.1515;
		distance = distance * 1.609344;

		return (distance);
	}

}
