package main.resources.dotaobject;
import com.fasterxml.jackson.databind.JsonNode;
import main.resources.DotaJsonParser;

import java.util.HashMap;
import java.util.Map;

public class Heroes {
    private static final Hero[] heroesList;
    private static final Map<Integer, Integer> heroIdIndices; // Of type <id, arrayIndex>
    private static final Map<String, Integer> heroNameIndices; // Of type <localizedName, arrayIndex>

    static {
        JsonNode heroes = DotaJsonParser.parse("https://api.opendota.com/api/heroStats");
        heroIdIndices = new HashMap<>();
        heroNameIndices = new HashMap<>();
        assert heroes != null;
        heroesList = new Hero[heroes.size()];
        for (int i = 0; i < heroes.size(); i++) {
            JsonNode current = heroes.get(i);
            assert (current != null);
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
            int[] picks = new int[Player.PlayerRank.values().length];
            int[] wins = new int[Player.PlayerRank.values().length];
            picks[Player.PlayerRank.HERALD.ordinal()] = current.get("1_pick").asInt();
            wins[Player.PlayerRank.HERALD.ordinal()] = current.get("1_win").asInt();
            picks[Player.PlayerRank.GUARDIAN.ordinal()] = current.get("2_pick").asInt();
            wins[Player.PlayerRank.GUARDIAN.ordinal()] = current.get("2_win").asInt();
            picks[Player.PlayerRank.CRUSADER.ordinal()] = current.get("3_pick").asInt();
            wins[Player.PlayerRank.CRUSADER.ordinal()] = current.get("3_win").asInt();
            picks[Player.PlayerRank.ARCHON.ordinal()] = current.get("4_pick").asInt();
            wins[Player.PlayerRank.ARCHON.ordinal()] = current.get("4_win").asInt();
            picks[Player.PlayerRank.LEGEND.ordinal()] = current.get("5_pick").asInt();
            wins[Player.PlayerRank.LEGEND.ordinal()] = current.get("5_win").asInt();
            picks[Player.PlayerRank.ANCIENT.ordinal()] = current.get("6_pick").asInt();
            wins[Player.PlayerRank.ANCIENT.ordinal()] = current.get("6_win").asInt();
            picks[Player.PlayerRank.DIVINE.ordinal()] = current.get("7_pick").asInt();
            wins[Player.PlayerRank.DIVINE.ordinal()] = current.get("7_win").asInt();
            picks[Player.PlayerRank.IMMORTAL.ordinal()] = current.get("8_pick").asInt();
            wins[Player.PlayerRank.IMMORTAL.ordinal()] = current.get("8_win").asInt();
            picks[Player.PlayerRank.PRO.ordinal()] = current.get("pro_pick").asInt();
            wins[Player.PlayerRank.PRO.ordinal()] = current.get("pro_win").asInt();
            int proBans = current.get("pro_ban").asInt();

            Hero hero = new Hero(id, name, localizedName, primaryAttribute, attackType, roles, captainsMode,
                    picks, wins, proBans);
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
