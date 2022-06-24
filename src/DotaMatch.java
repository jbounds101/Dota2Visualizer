import java.math.BigInteger;
import java.util.ArrayList;

public class DotaMatch {
    public DotaMatch(BigInteger matchId, BigInteger matchSeqNum, BigInteger startTime, int lobbyType, int radiantTeamId, int direTeamId, ArrayList<DotaPlayer> players) {
        this.matchId = matchId;
        this.matchSeqNum = matchSeqNum;
        this.startTime = startTime;
        this.lobbyType = lobbyType;
        this.radiantTeamId = radiantTeamId;
        this.direTeamId = direTeamId;
        this.players = players;
    }
    public DotaMatch() {
        this.matchId = null;
        this.matchSeqNum = null;
        this.startTime = null;
        this.lobbyType = -1;
        this.radiantTeamId = -1;
        this.direTeamId = -1;
        this.players = null;
    }

    private BigInteger matchId;
    private BigInteger matchSeqNum;
    private BigInteger startTime;
    private int lobbyType;
    private int radiantTeamId;
    private int direTeamId;
    ArrayList<DotaPlayer> players;

    public BigInteger getMatchId() {
        return matchId;
    }

    public void setMatchId(BigInteger matchId) {
        this.matchId = matchId;
    }

    public BigInteger getMatchSeqNum() {
        return matchSeqNum;
    }

    public void setMatchSeqNum(BigInteger matchSeqNum) {
        this.matchSeqNum = matchSeqNum;
    }

    public BigInteger getStartTime() {
        return startTime;
    }

    public void setStartTime(BigInteger startTime) {
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
