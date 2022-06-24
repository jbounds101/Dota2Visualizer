import com.fasterxml.jackson.databind.JsonNode;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import java.io.IOException;

public class Dota2Drafter {

    public static void main(String[] args) {
        String URL = "https://api.steampowered.com/IDOTA2Match_570/GetMatchHistory/V001/?matches_requested=1&key=81FDC9775199C7A7D7F8AA581B62EA4D";
        JsonNode node = null;
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // Try with resources, makes sure to auto close httpClient
            HttpGet httpGet = new HttpGet(URL);

            try (CloseableHttpResponse httpResponse = httpClient.execute(httpGet)) {
                // Got a response, could be invalid though

                HttpEntity entity = httpResponse.getEntity();
                String response = EntityUtils.toString(entity, "UTF-8");
                //System.out.printf(response);
                try {
                    DotaMatch match = JsonParser.parseMatch(response);
                    node = JsonParser.parse(response);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                System.out.println("Error! No response was given.");
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println("Error! Connection could not be established.");
            e.printStackTrace();
        }
        assert node != null;

        // Response was valid, node now contains the Json date
        //System.out.println(node.get("city").asText());
        // asText() translates to literal characters



    }



}