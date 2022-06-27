package main.resources.dotaobject;

import java.util.HashMap;
import java.util.Map;

public class Hero {
    // https://liquipedia.net/dota2/MediaWiki:Dota2webapi-heroes.json


    public Hero(int id, String name, String localizedName, AttributeType primaryAttribute, String attackType,
                String[] roles, boolean captainsMode, int proBans, int proPicks, int proWins,
                int heraldPicks, int heraldWins, int guardianPicks, int guardianWins,
                int crusaderPicks, int crusaderWins, int archonPicks, int archonWins,
                int legendPicks, int legendWins, int ancientPicks, int ancientWins, int divinePicks,
                int divineWins, int immortalPicks, int immortalWins) {
        this.id = id;
        this.name = name;
        this.localizedName = localizedName;
        this.primaryAttribute = primaryAttribute;
        this.attackType = attackType;
        this.roles = roles;
        this.captainsMode = captainsMode;
        this.proBans = proBans;
        this.proWins = proWins;
        this.proPicks = proPicks;
        this.heraldPicks = heraldPicks;
        this.heraldWins = heraldWins;
        this.guardianPicks = guardianPicks;
        this.guardianWins = guardianWins;
        this.crusaderPicks = crusaderPicks;
        this.crusaderWins = crusaderWins;
        this.archonPicks = archonPicks;
        this.archonWins = archonWins;
        this.legendPicks = legendPicks;
        this.legendWins = legendWins;
        this.ancientPicks = ancientPicks;
        this.ancientWins = ancientWins;
        this.divinePicks = divinePicks;
        this.divineWins = divineWins;
        this.immortalPicks = immortalPicks;
        this.immortalWins = immortalWins;
    }

    private final int id;
    private final String name;
    private final String localizedName;
    private final AttributeType primaryAttribute;
    public enum AttributeType {
        STRENGTH("str"),
        AGILITY("agi"),
        INTELLIGENCE("int");
        private final String value;
        AttributeType(String value) {
            this.value = value;
        }
        private static final Map<String, Hero.AttributeType> map = new HashMap<>();
        static {
            for (Hero.AttributeType attributeType : Hero.AttributeType.values()) {
                map.put(attributeType.value, attributeType);
            }
        }
        public static Hero.AttributeType convert(String value) {
            return (Hero.AttributeType) map.get(value);
        }
    }
    private final String attackType;
    private final String[] roles;
    /*private final String img;
    private final String icon; TODO need to fix this, these should be images */
    private boolean captainsMode;
    private int proBans;
    private int proPicks;
    private int proWins;
    private int heraldPicks;
    private int heraldWins;
    private int guardianPicks;
    private int guardianWins;
    private int crusaderPicks;
    private int crusaderWins;
    private int archonPicks;
    private int archonWins;
    private int legendPicks;
    private int legendWins;
    private int ancientPicks;
    private int ancientWins;
    private int divinePicks;
    private int divineWins;
    private int immortalPicks;
    private int immortalWins;

    @Override
    public String toString() {
        return this.localizedName;
    }
    
    public float getOverallWinPercentage() {
        int totalPicks = proPicks + heraldPicks + guardianPicks + crusaderPicks + archonPicks + legendPicks + 
                ancientPicks + divinePicks + immortalPicks;
        int totalWins = proWins + heraldWins + guardianWins + crusaderWins + archonWins + legendWins + 
                ancientWins + divineWins + immortalWins;
        return (((float) totalWins) / ((float) totalPicks));
    }
    public float getProWinPercentage() {
         return (((float) proWins) / ((float) proPicks));
    }
    public float getHeraldWinPercentage() {
         return (((float) heraldWins) / ((float) heraldPicks));
    }
    public float getGuardianWinPercentage() {
         return (((float) guardianWins) / ((float) guardianPicks));
    }
    public float getCrusaderWinPercentage() {
         return (((float) crusaderWins) / ((float) crusaderPicks));
    }
    public float getArchonWinPercentage() {
         return (((float) archonWins) / ((float) archonPicks));
    }
    public float getLegendWinPercentage() {
         return (((float) legendWins) / ((float) legendPicks));
    }
    public float getAncientWinPercentage() {
         return (((float) ancientWins) / ((float) ancientPicks));
    }
    public float getDivineWinPercentage() {
         return (((float) divineWins) / ((float) divinePicks));
    }
    public float getImmortalWinPercentage() {
         return (((float) immortalWins) / ((float) immortalPicks));
    }

    
}
