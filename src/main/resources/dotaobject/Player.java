package main.resources.dotaobject;

public class Player {
    private int accountId;
    private int playerSlot;
    private int heroId;

    public enum PlayerRank {
        HERALD,
        GUARDIAN,
        CRUSADER,
        ARCHON,
        LEGEND,
        ANCIENT,
        DIVINE,
        IMMORTAL,
        PRO
    }
}
