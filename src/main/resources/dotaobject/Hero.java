package main.resources.dotaobject;

import com.fasterxml.jackson.databind.JsonNode;
import main.resources.DotaJsonParser;

import java.util.HashMap;
import java.util.Map;

public class Hero {
    // https://liquipedia.net/dota2/MediaWiki:Dota2webapi-heroes.json


    public Hero(int id, String name, String localizedName, String primaryAttribute, String attackType,
                String[] roles, boolean captainsMode, int[] picks, int[] wins, int proBans,
                Map<LaneRoles, Float> lanePresence, float avgLastHitsAtTen, boolean support, boolean carry) {
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
        this.lanePresence = lanePresence;
        this.avgLastHitsAtTen = avgLastHitsAtTen;
        this.support = support;
        this.carry = carry;
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
    private float avgLastHitsAtTen;
    private float coreLikelihood = -1;
    private Map<Hero, Float> publicMatchUps = null;
    private float counterabilityIndex = 999; // This is an arbitrary number which represents the combined
    // maximum match up win and lose percentage difference between this hero's regular win rate
    private float counterability = -1;
    private boolean support;
    private boolean carry;

    enum LaneRoles {
        SAFELANE,
        MIDLANE,
        OFFLANE
    }

    @Override
    public String toString() {
        return this.localizedName;
    }

    public float getWinPercentage(Player.PlayerRank rank) {
        // Hero win percentage in a certain skill bracket (combines divine and immortal)
        int picks_ = picks[rank.ordinal()];
        int wins_ = wins[rank.ordinal()];
        if (rank == Player.PlayerRank.DIVINE) {
            picks_ += picks[Player.PlayerRank.IMMORTAL.ordinal()];
            wins_ += wins[Player.PlayerRank.IMMORTAL.ordinal()];
        } else if (rank == Player.PlayerRank.IMMORTAL) {
             picks_ += picks[Player.PlayerRank.DIVINE.ordinal()];
            wins_ += wins[Player.PlayerRank.DIVINE.ordinal()];
        }
        return ((float) wins_ / (float) picks_);
    }
    public float getWinPercentage() {
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
        if (publicMatchUps != null) return publicMatchUps;
        // Gets hero match-ups based on public matches
        String dotaBuffName = this.localizedName.toLowerCase().replace(' ', '-');
        if (dotaBuffName.equals("outworld-devourer")) dotaBuffName = "outworld-destroyer";
        if (dotaBuffName.equals("nature's-prophet")) dotaBuffName = "natures-prophet";
        String url = "https://www.dotabuff.com/heroes/" + dotaBuffName + "/counters";
        this.publicMatchUps = DotaJsonParser.scrapePublicMatchUps(url);
        return this.publicMatchUps;
    }

    public float getCoreLikelihood() {
        if (this.coreLikelihood != -1) return this.coreLikelihood;
        float maximumLastHits = Heroes.getMaximumLastHitsAtTen();
        float minimumLastHits = Heroes.getMinimumLastHitsAtTen();
        this.coreLikelihood = (this.avgLastHitsAtTen - minimumLastHits) / (maximumLastHits - minimumLastHits);
        return this.coreLikelihood;
    }

    public float getCounterability() {
        if (this.counterability != -1) return this.counterability;
        float maximumCounterability = Heroes.getMaximumCounterability();
        float minimumCounterability = Heroes.getMinumumCounterability();
        this.counterability = (this.counterabilityIndex - minimumCounterability) /
                (maximumCounterability - minimumCounterability);
        return this.counterability;
    }

    public float getAvgLastHitsAtTen() {
        return avgLastHitsAtTen;
    }

    public float getCounterabilityIndex() {
        if (this.counterabilityIndex != 999) {
            return this.counterabilityIndex;
        }
        float currentMin = 100;
        float currentMax = 0;
        Map<Hero, Float> matchUps = this.getPublicMatchUps();
        for (Hero hero : Heroes.getHeroesList()) {
            try {
                float checkWinRate = matchUps.get(hero);
                if (checkWinRate > currentMax) {
                    currentMax = checkWinRate;
                }
                if (checkWinRate < currentMin) {
                    currentMin = checkWinRate;
                }
            } catch (NullPointerException ignored) {}
        }
        float regularWinPercentage = this.getWinPercentage();
        this.counterabilityIndex = (currentMax - regularWinPercentage) + (regularWinPercentage - currentMin);
        return this.counterabilityIndex;
    }
}
