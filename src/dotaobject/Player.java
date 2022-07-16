package dotaobject;

import main.DotaMatchDeserializer;

public class Player {
    public Player(String playerName, int accountID, int playerSlot, Hero hero, int kills, int assists, int deaths,
                  Item[] items, Item[] backpack, Item neutralItem, int gpm, int[] goldAtMin, int lastHits,
                  int[] lastHitAtMin, int denies, int xpm, int[] xpAtMin, int netWorth, int level,
                  DotaMatchDeserializer.PurchaseLogElement[] purchaseLog, boolean radiant) {
        this.playerName = playerName;
        this.accountID = accountID;
        this.playerSlot = playerSlot;
        this.hero = hero;
        this.kills = kills;
        this.assists = assists;
        this.deaths = deaths;
        this.items = items;
        this.backpack = backpack;
        this.neutralItem = neutralItem;
        this.gpm = gpm;
        this.goldAtMin = goldAtMin;
        this.lastHits = lastHits;
        this.lastHitAtMin = lastHitAtMin;
        this.denies = denies;
        this.xpm = xpm;
        this.xpAtMin = xpAtMin;
        this.netWorth = netWorth;
        this.level = level;
        this.purchaseLog = purchaseLog;
        this.radiant = radiant;
    }

    private String playerName;
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
    private int denies;
    private int xpm;
    private int[] xpAtMin;
    private int netWorth;
    private int level;
    private DotaMatchDeserializer.PurchaseLogElement[] purchaseLog;
    private boolean radiant;

    // TODO should likely add benchmarks


    @Override
    public String toString() {
        String isRadiant = "Radiant";
        if (!this.radiant) {
            isRadiant  = "Dire";
        }
        return this.playerName + " | (" + this.hero.toString() + ") | " + isRadiant;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getAccountID() {
        return accountID;
    }

    public int getPlayerSlot() {
        return playerSlot;
    }

    public Hero getHero() {
        return hero;
    }

    public int getKills() {
        return kills;
    }

    public int getAssists() {
        return assists;
    }

    public int getDeaths() {
        return deaths;
    }

    public Item[] getItems() {
        return items;
    }

    public Item[] getBackpack() {
        return backpack;
    }

    public Item getNeutralItem() {
        return neutralItem;
    }

    public int getGpm() {
        return gpm;
    }

    public int[] getGoldAtMin() {
        return goldAtMin;
    }

    public int getLastHits() {
        return lastHits;
    }

    public int[] getLastHitAtMin() {
        return lastHitAtMin;
    }

    public int getDenies() {
        return denies;
    }

    public int getXpm() {
        return xpm;
    }

    public int[] getXpAtMin() {
        return xpAtMin;
    }

    public int getNetWorth() {
        return netWorth;
    }

    public int getLevel() {
        return level;
    }

    public DotaMatchDeserializer.PurchaseLogElement[] getPurchaseLog() {
        return purchaseLog;
    }

    public boolean isRadiant() {
        return radiant;
    }
}
