package dotaobject;

import com.fasterxml.jackson.databind.JsonNode;
import main.resources.DotaJsonParser;

import java.awt.image.BufferedImage;
import java.util.*;

public class Items {
    private static final Item[] itemsList;
    private static final Map<Integer, Integer> itemIDIndices; // Of type <id, arrayIndex>
    private static final Map<String, Integer> itemNameIndices; // Of type <localizedName, arrayIndex>

    static {
        JsonNode items = DotaJsonParser.parse("https://api.opendota.com/api/constants/items");
        assert items != null;

        itemIDIndices = new HashMap<>();
        itemNameIndices = new HashMap<>();
        itemsList = new Item[items.size()];

        Iterator<JsonNode> nodeIterator = items.elements();
        while (nodeIterator.hasNext()) {
            JsonNode current = nodeIterator.next();
            String name = current.get("dname").asText();
            int id = current.get("id").asInt();
            BufferedImage img = DotaJsonParser.findImage("src/item_images", id + "img.png",
                    "https://api.opendota.com" + current.get("img").asText());
            int cost = current.get("cost").asInt();
        }
    }

    public static Item[] getItemsList() {
        return itemsList;
    }
}
