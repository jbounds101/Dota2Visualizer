package dotaobject;

public class Match {

    public Match(Long matchID, Long radiantTeamId, Long direTeamId, int radiantScore, int direScore,
                 int towerStatusRadiant, int towerStatusDire, int barracksStatusRadiant, int barracksStatusDire,
                 int duration, int minutesPlayed, int firstBloodTime, int gameMode, int lobbyType, int humanPlayers,
                 int matchSeqNum, boolean radiantWin, int[] radiantGoldAdvantage, int[] radiantXPAdvantage,
                 Player[] players) {
        this.matchID = matchID;
        this.radiantTeamId = radiantTeamId;
        this.direTeamId = direTeamId;
        this.radiantScore = radiantScore;
        this.direScore = direScore;
        this.towerStatusRadiant = towerStatusRadiant;
        this.towerStatusDire = towerStatusDire;
        this.barracksStatusRadiant = barracksStatusRadiant;
        this.barracksStatusDire = barracksStatusDire;
        this.duration = duration;
        this.minutesPlayed = minutesPlayed;
        this.firstBloodTime = firstBloodTime;
        this.gameMode = gameMode;
        this.lobbyType = lobbyType;
        this.humanPlayers = humanPlayers;
        this.matchSeqNum = matchSeqNum;
        this.radiantWin = radiantWin;
        this.radiantGoldAdvantage = radiantGoldAdvantage;
        this.radiantXPAdvantage = radiantXPAdvantage;
        this.players = players;
    }

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

    @Override
    public String toString() {
        return "Match(" +this.matchID.toString()+ ")";
    }

    public Long getMatchID() {
        return matchID;
    }

    public Long getRadiantTeamId() {
        return radiantTeamId;
    }

    public Long getDireTeamId() {
        return direTeamId;
    }

    public int getRadiantScore() {
        return radiantScore;
    }

    public int getDireScore() {
        return direScore;
    }

    public int getTowerStatusRadiant() {
        return towerStatusRadiant;
    }

    public int getTowerStatusDire() {
        return towerStatusDire;
    }

    public int getBarracksStatusRadiant() {
        return barracksStatusRadiant;
    }

    public int getBarracksStatusDire() {
        return barracksStatusDire;
    }

    public int getDuration() {
        return duration;
    }

    public int getMinutesPlayed() {
        return minutesPlayed;
    }

    public int getFirstBloodTime() {
        return firstBloodTime;
    }

    public int getGameMode() {
        return gameMode;
    }

    public int getLobbyType() {
        return lobbyType;
    }

    public int getHumanPlayers() {
        return humanPlayers;
    }

    public int getMatchSeqNum() {
        return matchSeqNum;
    }

    public boolean isRadiantWin() {
        return radiantWin;
    }

    public int[] getRadiantGoldAdvantage() {
        return radiantGoldAdvantage;
    }

    public int[] getRadiantXPAdvantage() {
        return radiantXPAdvantage;
    }

    public Player[] getPlayers() {
        return players;
    }
}
