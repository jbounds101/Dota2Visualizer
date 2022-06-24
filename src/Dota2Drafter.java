import org.apache.commons.codec.Charsets;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.entity.mime.Header;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Dota2Drafter {
    public static void main(String[] args) {
        String URL = "https://random-data-api.com/api/address/random_address";
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // Try with resources, makes sure to auto close httpClient
            HttpGet httpGet = new HttpGet(URL);

            try (CloseableHttpResponse httpResponse = httpClient.execute(httpGet)) {
                // Got a response, could be invalid though

                //String encodingHeader = httpResponse.getEntity().getContentEncoding();
                //Charset encoding = encodingHeader == null ? StandardCharsets.UTF_8 :
                //Charsets.toCharset(encodingHeader);



            } catch (Exception e) {
            System.out.println("Error! No response was given.");
        }
        } catch (Exception e) {
            System.out.println("Error! Connection could not be established.");
        }
    }
}