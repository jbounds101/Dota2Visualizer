package dotaobject;

import com.fasterxml.jackson.databind.JsonNode;
import main.DotaJsonParser;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class Heroes {
    private static final Hero[] heroesList;
    private static final Map<Integer, Integer> heroIDIndices; // Of type <id, arrayIndex>
    private static final Map<String, Integer> heroNameIndices; // Of type <localizedName, arrayIndex>

    static {
        JsonNode heroes = null;
        try {
            heroes = DotaJsonParser.parse("https://api.opendota.com/api/heroStats");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assert heroes != null;
        heroIDIndices = new HashMap<>();
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
            BufferedImage img = DotaJsonParser.findImage("src/hero_images", id + "img.png",
                    "https://api.opendota.com" + current.get("img").asText());
            BufferedImage icon = DotaJsonParser.findImage("src/hero_images", id + "icon.png",
                    "https://api.opendota.com" + current.get("icon").asText());

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
            heroIDIndices.put(id, i);
            heroNameIndices.put(localizedName, i);
        }
    }
    public static Hero[] getHeroesList() {
        return heroesList;
    }
    public static Hero getHero(int heroID) {
       try {
            return heroesList[heroIDIndices.get(heroID)];
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
