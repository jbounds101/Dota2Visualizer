package main.resources.dotaobject;
import com.fasterxml.jackson.databind.JsonNode;
import main.resources.DotaParser;

import java.util.HashMap;
import java.util.Map;

public class Heroes {
    private static final Hero[] heroesList;
    private static final Map<Integer, Integer> heroIdIndices; // Of type <id, arrayIndex>
    private static final Map<String, Integer> heroNameIndices; // Of type <localizedName, arrayIndex>

    static {
        JsonNode heroes = DotaParser.parse("https://api.opendota.com/api/heroes");
        heroIdIndices = new HashMap<>();
        heroNameIndices = new HashMap<>();
        assert heroes != null;
        heroesList = new Hero[heroes.size()];
        for (int i = 0; i < heroes.size(); i++) {
            JsonNode current = heroes.get(i);
            int id = current.get("id").asInt();
            String name = current.get("name").asText();
            String localizedName = current.get("localized_name").asText();
            Hero.AttributeType primaryAttribute = Hero.AttributeType.convert(current.get("primary_attr").asText());
            String attackType = current.get("attack_type").asText();
            JsonNode rolesNode = current.get("roles");
            String[] roles = new String[rolesNode.size()];
            for (int j = 0; j < rolesNode.size(); j++) {
                roles[j] = rolesNode.get(j).asText();
            }
            int legs = current.get("legs").asInt();
            Hero hero = new Hero(id, name, localizedName, primaryAttribute, attackType, roles, legs);
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
