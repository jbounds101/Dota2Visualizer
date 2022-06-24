import java.util.ArrayList;

public class DotaMatch {
    public DotaMatch(int matchId, int matchSeqNum, int startTime, int lobbyType, int radiantTeamId, int direTeamId, ArrayList<DotaPlayer> players) {
        this.matchId = matchId;
        this.matchSeqNum = matchSeqNum;
        this.startTime = startTime;
        this.lobbyType = lobbyType;
        this.radiantTeamId = radiantTeamId;
        this.direTeamId = direTeamId;
        this.players = players;
    }
    public DotaMatch() {
        this.matchId = -1;
        this.matchSeqNum = -1;
        this.startTime = -1;
        this.lobbyType = -1;
        this.radiantTeamId = -1;
        this.direTeamId = -1;
        this.players = null;
    }

    private int matchId;
    private int matchSeqNum;
    private int startTime;
    private int lobbyType;
    private int radiantTeamId;
    private int direTeamId;
    ArrayList<DotaPlayer> players;

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public int getMatchSeqNum() {
        return matchSeqNum;
    }

    public void setMatchSeqNum(int matchSeqNum) {
        this.matchSeqNum = matchSeqNum;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getLobbyType() {
        return lobbyType;
    }

    public void setLobbyType(int lobbyType) {
        this.lobbyType = lobbyType;
    }

    public int getRadiantTeamId() {
        return radiantTeamId;
    }

    public void setRadiantTeamId(int radiantTeamId) {
        this.radiantTeamId = radiantTeamId;
    }

    public int getDireTeamId() {
        return direTeamId;
    }

    public void setDireTeamId(int direTeamId) {
        this.direTeamId = direTeamId;
    }

    public ArrayList<DotaPlayer> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<DotaPlayer> players) {
        this.players = players;
    }
}
