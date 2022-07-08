package dotaobject;

import com.fasterxml.jackson.databind.JsonNode;
import main.resources.DotaJsonParser;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;

public class Items {
    private static final Item[] itemsList;
    private static final Map<Integer, Integer> itemIDIndices; // Of type <id, arrayIndex>
    private static final Map<String, Integer> itemNameIndices; // Of type <name (NOT LOCALIZED), arrayIndex>



    static {
        JsonNode items = DotaJsonParser.parse("https://api.opendota.com/api/constants/items");
        assert items != null;

        itemIDIndices = new HashMap<>();
        itemNameIndices = new HashMap<>();
        itemsList = new Item[items.size()];

        int i = 0;
        Iterator<JsonNode> nodeIterator = items.elements();
        Iterator<String> nameIterator = items.fieldNames();
        while (nodeIterator.hasNext()) {
            String name = nameIterator.next();
            JsonNode current = nodeIterator.next();
            String localizedName = null;
            int cost = -1;
            try {
                localizedName = current.get("dname").asText();
                cost = current.get("cost").asInt();
            } catch (NullPointerException ignored) {} // Item is unused, likely some sort of event item


            int id = current.get("id").asInt();
            String imageURL = "https://api.opendota.com" + current.get("img").asText();
            /*BufferedImage img = DotaJsonParser.findImage("src/item_images", id + "img.png",
                    "https://api.opendota.com" + current.get("img").asText());*/

            Item item = new Item(name, localizedName, id, cost, imageURL);

            itemsList[i] = item;
            itemIDIndices.put(id, i);
            itemNameIndices.put(name, i);
            i++;
        }
    }

    public static Item[] getItemsList() {
        return itemsList;
    }

    public static Item getItem(int itemID) {
        try {
            return itemsList[itemIDIndices.get(itemID)];
        } catch (NullPointerException e) {
            return null;
        }
    }
    public static Item getItem(String name) {
        try {
            return itemsList[itemNameIndices.get(name)];
        } catch (NullPointerException e) {
            return null;
        }
    }
}
