package main.resources;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import main.resources.dotaobject.Hero;
import main.resources.dotaobject.Heroes;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DotaJsonParser {

    // A constructor is used for objectMapper settings
    private static final ObjectMapper objectMapper = createDefaultObjectMapper();
    private static ObjectMapper createDefaultObjectMapper() {
        ObjectMapper defaultObjectMapper = new ObjectMapper();

        // Add main.resources.resources.DotaMatch deserializer to the ObjectMapper
        /*SimpleModule DotaMatchDeserializer =
            new SimpleModule("main.resources.DotaMatchDeserializer", new Version(1, 0, 0,
                    null, null, null));

        DotaMatchDeserializer.addDeserializer(Match[].class, new DotaMatchDeserializer());
        defaultObjectMapper.registerModule(DotaMatchDeserializer);*/

        return defaultObjectMapper;
    }

    public static JsonNode parse(String url) {
        String response = getJsonResponse(url);
        try {
            return objectMapper.readTree(response);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
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

    public static Map<Hero, Float> scrapePublicMatchUps(String url) {
        // Used for accessing data on non-API website
        Map<Hero, Float> counters = new HashMap<>();
        try {
            final Document document = Jsoup.connect(url).get();
            int index = -1;
            for (Element row: document.select("table.sortable tr")) {
                index++;
                if (index == 0) continue;
                String localizedName = row.child(0).attributes().get("data-value");
                Hero hero = Heroes.getHero(localizedName);
                assert (hero != null);
                float winRate = Float.parseFloat(row.child(3).attributes().get("data-value"));
                counters.put(hero, winRate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return counters;
    }
}


