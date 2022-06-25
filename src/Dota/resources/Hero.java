package Dota.resources;

import java.util.HashMap;
import java.util.Map;

class Hero {
    // https://liquipedia.net/dota2/MediaWiki:Dota2webapi-heroes.json
    private int id;
    private String name;
    private String localizedName;
    private AttributeType primaryAttribute;
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

    private String attackType;
    private String[] roles;
    private int legs;


}
