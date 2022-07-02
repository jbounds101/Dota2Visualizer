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
import java.util.Random;

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

    public static Map<Hero, float[]> scrapePublicMatchUps(String url) {
        // Used for accessing data on non-API website
        Map<Hero, float[]> counters = new HashMap<>();
        try {
            System.setProperty("http.proxyHost", "192.168.5.1");
            System.setProperty("http.proxyPort", "1080");
            final Document document = Jsoup.connect(url).get();
            Random random = new Random();
            //Thread.sleep(500 + random.nextInt(50));
            int index = -1;
            for (Element row: document.select("table.sortable tr")) {
                index++;
                if (index == 0) continue;
                String localizedHeroName = row.child(0).attributes().get("data-value");
                Hero hero = Heroes.getHero(localizedHeroName);
                float winRate = Float.parseFloat(row.child(3).attributes().get("data-value")) / 100;
                float disadvantage = Float.parseFloat(row.child(2).attributes().get("data-value")) / 100;
                counters.put(hero, new float[]{winRate, disadvantage});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return counters;
    }

    public static Map<String, Float> scrapeHeroEconomy() {
        // Can't be of type <Hero, Float> because this is used during the creation of the Heroes module
        Map<String, Float> economy = new HashMap<>();
        String url = "https://www.dotabuff.com/heroes/farm";
        try {
            final Document document = Jsoup.connect(url).get();
            int index = -1;
            for (Element row : document.select("table.sortable tr")) {
                index++;
                if (index == 0) continue;
                String localizedName = row.child(0).attributes().get("data-value");
                if (localizedName.equals("Outworld Destroyer")) localizedName = "Outworld Devourer";
                float lastHits = Float.parseFloat(row.child(2).attributes().get("data-value"));
                economy.put(localizedName, lastHits);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return economy;
    }
}


