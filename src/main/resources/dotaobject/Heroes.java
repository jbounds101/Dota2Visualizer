package main.resources.dotaobject;

import com.fasterxml.jackson.databind.JsonNode;
import main.resources.DotaJsonParser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Heroes {
    private static final Hero[] heroesList;
    private static final Map<Integer, Integer> heroIdIndices; // Of type <id, arrayIndex>
    private static final Map<String, Integer> heroNameIndices; // Of type <localizedName, arrayIndex>

    static {
        JsonNode heroes = DotaJsonParser.parse("https://api.opendota.com/api/heroStats");
        assert heroes != null;
        heroIdIndices = new HashMap<>();
        heroNameIndices = new HashMap<>();
        heroesList = new Hero[heroes.size()];
        for (int i = 0; i < heroes.size(); i++) {
            JsonNode current = heroes.get(i);
            assert (current != null);
            int id = current.get("id").asInt();
            String localizedName = current.get("localized_name").asText();
            String primaryAttribute = (current.get("primary_attr").asText());
            String attackType = current.get("attack_type").asText();

            JsonNode rolesNode = current.get("roles");
            String[] roles = new String[rolesNode.size()];
            for (int j = 0; j < rolesNode.size(); j++) {
                roles[j] = rolesNode.get(j).asText();
            }

            // Download the img and icon files, or simply open them if they are already downloaded
            File dir = new File("src/images");
            if (!dir.exists()) dir.mkdir();
            BufferedImage img = null;
            BufferedImage icon = null;
            try {
                File imgFolderInput = new File(dir, id + "img.png");
                img = ImageIO.read(imgFolderInput);
                File iconFolderInput = new File(dir, id + "icon.png");
                icon = ImageIO.read(iconFolderInput);
            } catch (IOException e) {
                // Images don't exist yet
                try {
                    URL imgURL = new URL("https://api.opendota.com" + current.get("img").asText());
                    URL iconURL = new URL("https://api.opendota.com" + current.get("icon").asText());
                    try {
                        img = ImageIO.read(imgURL);
                        ImageIO.write(img, "png", new File(dir, id + "img.png"));
                        icon = ImageIO.read(iconURL);
                        ImageIO.write(icon, "png", new File(dir, id + "icon.png"));
                    } catch (IOException ee) {
                        // Read error
                        e.printStackTrace();
                    }
                } catch (MalformedURLException ee) {
                    e.printStackTrace();
                }
            }


            int baseHealth = current.get("base_health").asInt();
            double baseHealthRegen = current.get("base_health_regen").asDouble();
            int baseMana = current.get("base_mana").asInt();
            double baseManaRegen = current.get("base_mana_regen").asDouble();
            int baseArmor = current.get("base_armor").asInt();
            int baseStr = current.get("base_str").asInt();
            int baseAgi = current.get("base_agi").asInt();
            int baseInt = current.get("base_int").asInt();
            double strGain = current.get("str_gain").asDouble();
            double agiGain = current.get("agi_gain").asDouble();
            double intGain = current.get("int_gain").asDouble();
            int attackRange = current.get("attack_range").asInt();
            double attackRate = current.get("attack_rate").asDouble();
            int moveSpeed = current.get("move_speed").asInt();
            boolean captainsMode = current.get("cm_enabled").asBoolean();

            Hero hero = new Hero(id,  localizedName,  primaryAttribute,  attackType,
                    roles, img, icon, baseHealth, baseHealthRegen, baseMana,
                    baseManaRegen, baseArmor, baseStr, baseAgi, baseInt, strGain,
                    agiGain, intGain, attackRange, attackRate, moveSpeed, captainsMode);
            heroesList[i] = hero;
            heroIdIndices.put(id, i);
            heroNameIndices.put(localizedName, i);
        }
    }
    public static Hero[] getHeroesList() {
        return heroesList;
    }
    public static Hero getHero(int heroId) {
       try {
            return heroesList[heroIdIndices.get(heroId)];
        } catch (NullPointerException e) {
            return null;
        }
    }
    public static Hero getHero(String localizedHeroName) {
        try {
            return heroesList[heroNameIndices.get(localizedHeroName)];
        } catch (NullPointerException e) {
            return null;
        }
    }
}
