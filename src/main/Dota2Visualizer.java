package main;
import dotaobject.*;


public class Dota2Visualizer {
    static Hero[] heroes = Heroes.getHeroesList();

    public static void main(String[] args) {
        System.out.println("test");
        //DotaJsonParser.sendParseRequest(6643139986L);

        Match match = DotaJsonParser.readMatch(6643139986L);

        System.out.println("test2");

    }
}
