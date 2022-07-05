package dotaobject;

public class Match {

    private Long matchID;
    private int barracksStatusDire;
    private int barracksStatusRadiant;
    // https://dev.dota2.com/forum/dota-2/spectating/replays/webapi/59075-this-is-how-to-interpret-the-barracks_status-and-tower_status-results-from-the-api#post948450
    private int direScore; // Number of kills
    private Long direTeamId; // Usually zero; if playing on a team, will give the ID
    private int duration; // In seconds
    private int firstBloodTime;
    private int gameMode;
    private int humanPlayers;
    private Long leagueID;
    private int lobbyType;
    private int matchSeqNum;
    // objectives?
    private int[] radiantGoldAdvantage; // This is equal to the difference in net-worth each minute
    private int radiantScore;
    private Long radiantTeamId;
    private boolean radiantWin;
    private int[] radiantXpAdvantage;
    // teamfights? TODO add this
    private int towerStatusDire;
    private int towerStatusRadiant;
    private Player[] players;
}
