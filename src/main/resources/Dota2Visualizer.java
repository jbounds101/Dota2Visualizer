package main.resources;
import com.fasterxml.jackson.core.JsonParser;
import dotaobject.Hero;
import dotaobject.Heroes;
import dotaobject.Match;

import java.math.BigInteger;

public class Dota2Visualizer {
    static Hero[] heroes = Heroes.getHeroesList();

    public static void main(String[] args) {
        System.out.println("test");
        System.out.println("test2");
       // DotaJsonParser.sendParseRequest(6643139986L);
        Match match = DotaJsonParser.readMatch(6643139986L);

    }
}
