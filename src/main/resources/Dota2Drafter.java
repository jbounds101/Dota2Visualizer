package main.resources;
import main.resources.dotaobject.Hero;
import main.resources.dotaobject.Heroes;
import main.resources.dotaobject.Player;

import java.util.Arrays;
import java.util.List;

public class Dota2Drafter {
    static List<Hero> heroes = Arrays.asList(Heroes.getHeroesList());
    static final int numHeroes = heroes.size();
    public static void main(String[] args) {
        System.out.println("test");
        System.out.println("test2");
    }

    public static Hero allPickGetFirstPick(Player.PlayerRank rank) {
        int[] heroesByWinRate = new int[numHeroes];
        if ((rank == Player.PlayerRank.DIVINE) || (rank == Player.PlayerRank.IMMORTAL)) {
            // Combine the two ranks due to low sample size
            for (Hero hero : heroes) {

            }
        }
        return null;
    }


    public class Draft {

        private Hero[][] picks = new Hero[2][5];
        private Hero[][] bans = new Hero[2][5];



    }

}
