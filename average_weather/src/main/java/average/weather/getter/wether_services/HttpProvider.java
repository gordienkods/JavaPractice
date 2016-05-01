package average.weather.getter.wether_services;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Димон on 30.04.2016.
 */
public class HttpProvider {

    public static String sendApiRequest (String request) {
        StringBuilder responseBuilder = new StringBuilder();
        try {
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpGet httpGetRequest = new HttpGet(request);
            HttpResponse httpResponse = httpClient.execute(httpGetRequest);
            BufferedReader br = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
            String line;
            while ((line = br.readLine()) != null) {
                responseBuilder.append(line + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return responseBuilder.toString();
    }


}
