package main.resources;

import main.resources.dotaobject.Hero;
import main.resources.dotaobject.Heroes;

public class Dota2Stats {
    public static void main(String[] args) {
        Hero[] heroes = Heroes.getHeroesList();
        Hero lina = Heroes.getHero(25);
        Hero primalBeast = Heroes.getHero("Primal Beast");
        Hero swagMancer = Heroes.getHero("Swag"); // Test nullptr

        DotaJsonParser.scrapeCounters("https://www.dotabuff.com/heroes/meepo/counters");

        System.out.println("test");
    }
}
