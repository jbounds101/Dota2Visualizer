package main.resources.dotaobject;

import java.util.HashMap;
import java.util.Map;

public class Hero {
    // https://liquipedia.net/dota2/MediaWiki:Dota2webapi-heroes.json

    public Hero(int id, String name, String localizedName, AttributeType primaryAttribute, String attackType, String[] roles, int legs) {
        this.id = id;
        this.name = name;
        this.localizedName = localizedName;
        this.primaryAttribute = primaryAttribute;
        this.attackType = attackType;
        this.roles = roles;
        this.legs = legs;
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
    private final int legs;

    @Override
    public String toString() {
        return this.localizedName;
    }
}
