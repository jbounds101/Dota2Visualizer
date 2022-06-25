package Dota.resources;

public class Heroes {
    private static Hero[] heroesList;
    static {
        DotaParser.parse("https://api.opendota.com/api/heroes");
    }
}
