package optional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * This class is for the optional part
 * https://restcountries.eu/#api-endpoints-capital-city
 */
public class GETRequest {

    /**
     * This method invokes a REST Web Service for more details about a country
     * @param country
     * @throws IOException
     */
    public static void getRequest(String country) throws IOException {
        System.out.println(country);
        URL urlForGetRequest = new URL("https://restcountries.eu/rest/v2/alpha/" + country.toLowerCase());
        String readLine = null;
        HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
        conection.setRequestMethod("GET");
        int responseCode = conection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(conection.getInputStream()));
            StringBuffer response = new StringBuffer();
            while ((readLine = in.readLine()) != null) {
                response.append(readLine);
            }
            in.close();
            displayMoreDetails(response.toString());
        } else {
            System.out.println("GET NOT WORKED");
        }
    }

    /**
     * This method displays the details for a specific country
     * @param json
     */
    private static void displayMoreDetails(String json) {
        int index = json.lastIndexOf("callingCodes");
        System.out.println("CallingCodes: " + json.substring(index + 15, index + 19));
        index = json.lastIndexOf("area");
        System.out.println("Area: " + json.substring(index + 6, index + 14));
        index = json.lastIndexOf("timezones");
        System.out.println("Area: " + json.substring(index + 12, index + 23));
    }
}
