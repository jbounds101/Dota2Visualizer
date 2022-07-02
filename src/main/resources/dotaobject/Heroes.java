package main.resources.dotaobject;
import com.fasterxml.jackson.databind.JsonNode;
import main.resources.DotaJsonParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Heroes {
    private static final Hero[] heroesList;
    private static final Map<Integer, Integer> heroIdIndices; // Of type <id, arrayIndex>
    private static final Map<String, Integer> heroNameIndices; // Of type <localizedName, arrayIndex>
    private static float maximumLastHitsAtTen = 0;
    private static float minimumLastHitsAtTen = 999;
    private static float maximumCounterability = 0;
    private static float minumumCounterability = 999;
    private static ArrayList<Hero> supports = new ArrayList<>();
    private static ArrayList<Hero> carrys = new ArrayList<>();

    static {
        JsonNode heroes = DotaJsonParser.parse("https://api.opendota.com/api/heroStats");
        JsonNode laneScenariosSafe =
                DotaJsonParser.parse("https://api.opendota.com/api/scenarios/laneRoles?lane_role=1");
        JsonNode laneScenariosMid =
                DotaJsonParser.parse("https://api.opendota.com/api/scenarios/laneRoles?lane_role=2");
        JsonNode laneScenariosOff =
                DotaJsonParser.parse("https://api.opendota.com/api/scenarios/laneRoles?lane_role=3");
        assert (laneScenariosSafe != null);
        assert (laneScenariosMid != null);
        assert (laneScenariosOff != null);

        Map<String, Float> lastHitsAtTenList = DotaJsonParser.scrapeHeroEconomy();
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
            String primaryAttribute = (current.get("primary_attr").asText());
            String attackType = current.get("attack_type").asText();
            JsonNode rolesNode = current.get("roles");
            boolean support = false;
            boolean carry = false;
            String[] roles = new String[rolesNode.size()];
            for (int j = 0; j < rolesNode.size(); j++) {
                roles[j] = rolesNode.get(j).asText();
                if (roles[j].equals("Support")) {
                    support = true;
                }
                if (roles[j].equals("Carry")) {
                    carry = true;
                }
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

            // Get lane presence percentages
            Map<Hero.LaneRoles, Float> lanePresence = new HashMap<>();
            int totalGames = 0;
            int safeLaneGames = 0;
            int midLaneGames = 0;
            int offLaneGames = 0;
            JsonNode currentLane = null;

            for (int role = 0; role < Hero.LaneRoles.values().length; role++) {
                switch (role) {
                    case 0:
                        currentLane = laneScenariosSafe;
                        break;
                    case 1:
                        currentLane = laneScenariosMid;
                        break;
                    case 2:
                        currentLane = laneScenariosOff;
                        break;
                }
                for (int node = 0; node < currentLane.size(); node++) {
                    JsonNode currentLaneScenario = currentLane.get(node);
                    int laneScenarioHeroId = currentLaneScenario.get("hero_id").asInt();
                    if (laneScenarioHeroId != id) continue; // Isn't the hero we are currently creating, by parsing
                    // the whole page it is possible to request much less API calls
                    int games = currentLaneScenario.get("games").asInt();
                    switch (role) {
                    case 0:
                        safeLaneGames += games;
                        break;
                    case 1:
                        midLaneGames += games;
                        break;
                    case 2:
                        offLaneGames += games;
                        break;
                }
                    totalGames += games;
                }
            }
            lanePresence.put(Hero.LaneRoles.SAFELANE, (float) safeLaneGames / (float) totalGames);
            lanePresence.put(Hero.LaneRoles.MIDLANE, (float) midLaneGames / (float) totalGames);
            lanePresence.put(Hero.LaneRoles.OFFLANE, (float) offLaneGames / (float) totalGames);

            float avgLastHitsAtTen = lastHitsAtTenList.get(localizedName);

            Hero hero = new Hero(id, name, localizedName, primaryAttribute, attackType, roles, captainsMode,
                    picks, wins, proBans, lanePresence, avgLastHitsAtTen, support, carry);
            heroesList[i] = hero;
            heroIdIndices.put(id, i);
            heroNameIndices.put(localizedName, i);
        }

        // This is for uninitialized values that rely on the heroes list
        for (Hero hero : getHeroesList()) {
            hero.getCoreLikelihood();
            hero.getPublicMatchUps();
            if (hero.isSupport()) {
                supports.add(hero);
            }
            if (hero.isCarry()) {
                carrys.add(hero);
            }
        }
        for (Hero hero : getHeroesList()) {
            hero.getCounterability();
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
        if (localizedHeroName.equals("Outworld Destroyer")) {
            localizedHeroName = "Outworld Devourer"; // Name change happened in game, but not on many clients
        }
        try {
            return heroesList[heroNameIndices.get(localizedHeroName)];
        } catch (NullPointerException e) {
            return null;
        }
    }
    public static float getMaximumLastHitsAtTen() {
        if (maximumLastHitsAtTen != 0) return maximumLastHitsAtTen;
        float currentMax = 0;
        for (Hero hero : heroesList) {
            float currentLastHits = hero.getAvgLastHitsAtTen();
            if (currentLastHits > currentMax) {
                currentMax = currentLastHits;
            }
        }
        maximumLastHitsAtTen = currentMax;
        return maximumLastHitsAtTen;
    }
    public static float getMinimumLastHitsAtTen() {
        if (minimumLastHitsAtTen != 999) return minimumLastHitsAtTen;
        float currentMin = 999;
        for (Hero hero : heroesList) {
            float currentLastHits = hero.getAvgLastHitsAtTen();
            if (currentLastHits < currentMin) {
                currentMin = currentLastHits;
            }
        }
        minimumLastHitsAtTen = currentMin;
        return minimumLastHitsAtTen;
    }
    public static float getMaximumCounterability() {
        if (maximumCounterability != 0) return maximumCounterability;
        float currentMax = 0;
        for (Hero hero : heroesList) {
            float currentCounterability = hero.getCounterabilityIndex();
            if (currentCounterability > currentMax) {
                currentMax = currentCounterability;
            }
        }
        maximumCounterability = currentMax;
        return maximumCounterability;
    }
    public static float getMinumumCounterability() {
        if (minumumCounterability != 999) return minumumCounterability;
        float currentMin = 999;
        for (Hero hero : heroesList) {
            float currentCounterability = hero.getCounterabilityIndex();
            if (currentCounterability < currentMin) {
                currentMin = currentCounterability;
            }
        }
        minimumLastHitsAtTen = currentMin;
        return minimumLastHitsAtTen;
    }

    public static ArrayList<Hero> getSupports() {
        return supports;
    }

    public static ArrayList<Hero> getCarrys() {
        return carrys;
    }
}
