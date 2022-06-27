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
        JsonNode heroes = DotaParser.parse("https://api.opendota.com/api/heroStats");
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
            boolean captainsMode = current.get("cm_enabled").asBoolean();
            int proBans = current.get("pro_ban").asInt();
            int proPicks = current.get("pro_pick").asInt();
            int proWins = current.get("pro_win").asInt();
            int heraldPicks = current.get("1_pick").asInt();
            int heraldWins = current.get("1_win").asInt();
            int guardianPicks = current.get("2_pick").asInt();
            int guardianWins = current.get("2_win").asInt();
            int crusaderPicks = current.get("3_pick").asInt();
            int crusaderWins = current.get("3_win").asInt();
            int archonPicks = current.get("4_pick").asInt();
            int archonWins = current.get("4_win").asInt();
            int legendPicks = current.get("5_pick").asInt();
            int legendWins = current.get("5_win").asInt();
            int ancientPicks = current.get("6_pick").asInt();
            int ancientWins = current.get("6_win").asInt();
            int divinePicks = current.get("7_pick").asInt();
            int divineWins = current.get("7_win").asInt();
            int immortalPicks = current.get("8_pick").asInt();
            int immortalWins = current.get("8_win").asInt();

            Hero hero = new Hero(id, name, localizedName, primaryAttribute, attackType, roles, captainsMode, proBans,
                    proPicks, proWins, heraldPicks, heraldWins, guardianPicks, guardianWins, crusaderPicks,
                    crusaderWins, archonPicks, archonWins, legendPicks, legendWins, ancientPicks, ancientWins,
                    divinePicks, divineWins, immortalPicks, immortalWins);
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
