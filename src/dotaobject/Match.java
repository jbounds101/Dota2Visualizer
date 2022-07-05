package dotaobject;

public class Match {

    private Long matchID;
    private Long radiantTeamId;
    private Long direTeamId; // Usually zero; if playing on a team, will give the ID
    private int radiantScore;
    private int direScore;

    private int towerStatusRadiant;
    private int towerStatusDire;
    private int barracksStatusRadiant;
    private int barracksStatusDire;
    // https://dev.dota2.com/forum/dota-2/spectating/replays/webapi/59075-this-is-how-to-interpret-the-barracks_status-and-tower_status-results-from-the-api#post948450

    private int duration;
    private int minutesPlayed; // Rounded up
    private int firstBloodTime;
    private int gameMode;
    private int lobbyType;
    private int humanPlayers;
    private int matchSeqNum;

    private boolean radiantWin;
    private int[] radiantGoldAdvantage; // This is equal to the difference in net-worth each minute
    private int[] radiantXPAdvantage;
    // teamfights? TODO add this

    private Player[] players;
}
