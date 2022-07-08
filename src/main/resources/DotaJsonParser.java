package main.resources;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import dotaobject.Match;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.BasicNameValuePair;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;



public class DotaJsonParser {

    // A constructor is used for objectMapper settings
    private static final ObjectMapper objectMapper = createDefaultObjectMapper();

    private static ObjectMapper createDefaultObjectMapper() {
        ObjectMapper defaultObjectMapper = new ObjectMapper();

        // Add main.resources.resources.DotaMatch deserializer to the ObjectMapper
        SimpleModule DotaMatchDeserializer =
            new SimpleModule("main.resources.DotaMatchDeserializer", new Version(1, 0, 0,
                    null, null, null));

        DotaMatchDeserializer.addDeserializer(Match.class, new DotaMatchDeserializer());
        defaultObjectMapper.registerModule(DotaMatchDeserializer);

        return defaultObjectMapper;
    }

    public static JsonNode parse(String url) {
        // This uses the Jackson library to create JsonNode objects
        String response = getJsonResponse(url);
        try {
            return objectMapper.readTree(response);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Match readMatch(Long matchID) {
        String response = getJsonResponse("https://api.opendota.com/api/matches/" + Long.toString(matchID));
        try {
            Match match = objectMapper.readValue(response, Match.class);
            return match;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean sendParseRequest(Long matchID) {
        // Returns true if the post was successful, false if it fails
        antiSpam();
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost("https://api.opendota.com/api/request/");
            List<NameValuePair> parameters = new ArrayList<>();
            parameters.add(new BasicNameValuePair("match_id", Long.toString(matchID)));
            httpPost.setEntity(new UrlEncodedFormEntity(parameters));
            try (CloseableHttpResponse httpResponse = httpClient.execute(httpPost)) {
                // Received a response, see if it was 200 (okay)
                // TODO this doesn't work
                System.out.println("see");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private static String getJsonResponse(String url) {
        antiSpam();
        // Apache gets the JSON response
        String response = null;
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // Try with resources, makes sure to auto close httpClient
            HttpGet httpGet = new HttpGet(url);
            try (CloseableHttpResponse httpResponse = httpClient.execute(httpGet)) {
                // Got a response, could be invalid though
                if (httpResponse.getCode() != 200) {
                    throw new RuntimeException("Could not parse the request. The server is likely down!");
                }
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

    public static BufferedImage findImage(String pathName, String fileName, String urlDownloadOnFail) {
        // Attempts to find a local copy of the image in the listed directory, otherwise,
        // downloads from the url into that directory with the file-name given
        BufferedImage img;
        File dir = new File(pathName);
        if (!dir.exists()) dir.mkdir();
        try {
            File imgFolderInput = new File(dir, fileName);
            img = ImageIO.read(imgFolderInput);
            return img;
        } catch (IOException e) {
            // Image wasn't found locally
            antiSpam();
            try {
                URL imgURL = new URL(urlDownloadOnFail);
                img = ImageIO.read(imgURL);
                ImageIO.write(img, "png", new File(dir, fileName));
                return img;
            } catch (IOException ee) {
                return null;
            }
        }
    }

    private static void antiSpam() {
        try {
            Thread.sleep(1050);
        } catch (InterruptedException e) {
            throw new RuntimeException("Thread was interrupted");
        }
    }
}


