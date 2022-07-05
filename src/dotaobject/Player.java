package dotaobject;

import java.util.Map;

public class Player {
    private int accountID;
    private int playerSlot;
    private Hero hero;
    private int kills;
    private int assists;
    private int deaths;
    private Item[] items; // Of size six
    private Item[] backpack; // Of size three
    private Item neutralItem;
    private int gpm;
    private int[] goldAtMin; // Amount at each minute mark
    private int lastHits;
    private int[] lastHitAtMin;
    private int xpm;
    private int[] xpAtMin;
    private int netWorth;
    private int level;
    private Map<Integer, Item> purchaseLog;
    private boolean radiant;
}
