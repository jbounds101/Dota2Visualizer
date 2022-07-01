package main.resources.dotaobject;

import com.fasterxml.jackson.databind.JsonNode;
import main.resources.DotaJsonParser;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Hero {
    // https://liquipedia.net/dota2/MediaWiki:Dota2webapi-heroes.json


    public Hero(int id, String name, String localizedName, String primaryAttribute, String attackType,
                String[] roles, boolean captainsMode, int[] picks, int[] wins, int proBans) {
        this.id = id;
        this.name = name;
        this.localizedName = localizedName;
        this.primaryAttribute = primaryAttribute;
        this.attackType = attackType;
        this.roles = roles;
        this.captainsMode = captainsMode;
        this.picks = picks;
        this.wins = wins;
        this.proBans = proBans;
    }

    private final int id;
    private final String name;
    private final String localizedName;
    public String primaryAttribute;
    private final String attackType;
    private final String[] roles;
    /*private final String img;
    private final String icon; TODO need to fix this, these should be images */
    private boolean captainsMode;
    private int[] picks;
    private int[] wins;
    private int proBans;
    // herald, guardian, crusader, archon, legend, ancient, divine, immortal, pro
    private Map<LaneRoles, Float> lanePresence;
    // A map of LaneRoles, with the corresponding percentage of games they are in that lane


    enum LaneRoles {
        SAFELANE,
        MIDLANE,
        OFFLANE
    }

    @Override
    public String toString() {
        return this.localizedName;
    }

    public float winPercentage(Player.PlayerRank rank) {
        // Hero win percentage in a certain skill bracket
        int picks_ = picks[rank.ordinal()];
        int wins_ = wins[rank.ordinal()];
        return ((float) wins_ / (float) picks_);
    }
    public float winPercentage() {
        // General hero win percentage, excludes pro data because of small sample size and skewed data
        int picks_ = 0;
        int wins_ = 0;
        for (int i = 0; i < Player.PlayerRank.values().length - 1; i++) {
            picks_ += picks[i];
            wins_ += wins[i];
        }
        return ((float) wins_ / (float) picks_);
    }

    public Map<Hero, Float> getProMatchUps() {
        // Gets the hero match-ups based on professional matches
        String url = "https://api.opendota.com/api/heroes/" + this.id + "/matchups";
        JsonNode response = DotaJsonParser.parse(url);
        assert (response != null);
        Map<Hero, Float> matchUps = new HashMap<>();
        for (int i = 0; i < response.size(); i++) {
            JsonNode current = response.get(i);
            int heroId = current.get("hero_id").asInt();
            int gamesPlayed = current.get("games_played").asInt();
            int wins = current.get("wins").asInt();
            float winRate = ((float) wins / (float) gamesPlayed);
            matchUps.put(Heroes.getHero(heroId), winRate);
        }
        return matchUps;
    }

    public Map<Hero, Float> getPublicMatchUps() {
        // Gets hero match-ups based on public matches
        String dotaBuffName = this.localizedName.toLowerCase().replace(' ', '-');
        if (dotaBuffName.equals("outworld-devourer")) dotaBuffName = "outworld-destroyer";
        String url = "https://www.dotabuff.com/heroes/" + dotaBuffName + "/counters";
        return DotaJsonParser.scrapePublicMatchUps(url);
    }
}