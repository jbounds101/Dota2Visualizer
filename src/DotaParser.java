import Dota.resources.Match;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import java.io.IOException;

public class DotaParser {

    // A constructor is used for objectMapper settings
    private static final ObjectMapper objectMapper = createDefaultObjectMapper();
    private static ObjectMapper createDefaultObjectMapper() {
        ObjectMapper defaultObjectMapper = new ObjectMapper();

        // Add Dota.resources.DotaMatch deserializer to the ObjectMapper
        SimpleModule DotaMatchDeserializer =
            new SimpleModule("DotaMatchDeserializer", new Version(1, 0, 0,
                    null, null, null));

        DotaMatchDeserializer.addDeserializer(Match[].class, new DotaMatchDeserializer());
        defaultObjectMapper.registerModule(DotaMatchDeserializer);

        return defaultObjectMapper;
    }

    public static JsonNode parse(String url) throws IOException {
        String response = getJsonResponse(url);
        return objectMapper.readTree(response);
    }

    public static Match[] parseMatch(String source) throws IOException {
        return objectMapper.readValue(source, Match[].class);
    }

    private static String getJsonResponse(String url) {
        String response = null;
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // Try with resources, makes sure to auto close httpClient
            HttpGet httpGet = new HttpGet(url);
            try (CloseableHttpResponse httpResponse = httpClient.execute(httpGet)) {
                // Got a response, could be invalid though
                HttpEntity entity = httpResponse.getEntity();
                response = EntityUtils.toString(entity, "UTF-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert (response != null);
        // Response was valid, response now contains the Json date as a string
        return response;
    }
}


