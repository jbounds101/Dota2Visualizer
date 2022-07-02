package main.resources;
import main.resources.dotaobject.Hero;
import main.resources.dotaobject.Heroes;
import main.resources.dotaobject.Player;

import java.lang.reflect.Method;
import java.util.*;

public class Dota2Drafter {
    static List<Hero> heroes = Arrays.asList(Heroes.getHeroesList());
    static final int numHeroes = heroes.size();
    /*static Comparator<Hero> nameComparatorLEx =
                (l, r) -> Float.compare(l.getWinPercentage(Player.PlayerRank.ANCIENT), (r.getWinPercentage(Player.PlayerRank.ANCIENT)));
    static Comparator<Hero> aaa =
                (l, r) -> Float.compare(l.getAvgLastHitsAtTen(), (r.getAvgLastHitsAtTen()));*/

    public static void main(String[] args) {
        allPickGetFirstPick(Player.PlayerRank.IMMORTAL);
        System.out.println("test");
        System.out.println("test2");

    }

    public static Hero allPickGetFirstPick(Player.PlayerRank rank) {
        ArrayList<Hero> heroesByWinrate = new ArrayList<>(heroes);
        Collections.sort(heroesByWinrate, createWinRateComparator(rank));
        return null;
    }

    private static Comparator<Hero> createWinRateComparator(Player.PlayerRank rank) {
        return (l, r) -> Float.compare(l.getWinPercentage(rank), r.getWinPercentage(rank));
    }


    public class Draft {

        private Hero[][] picks = new Hero[2][5];
        private Hero[][] bans = new Hero[2][5];



    }

}
