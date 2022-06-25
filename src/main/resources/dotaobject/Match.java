package main.resources.dotaobject;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;


public class Match {

    private BigInteger matchID;
    private int barracksStatusDire;
    private int barracksStatusRadiant;
    // https://dev.dota2.com/forum/dota-2/spectating/replays/webapi/59075-this-is-how-to-interpret-the-barracks_status-and-tower_status-results-from-the-api#post948450
    // chat?
    // cluster?
    // cosmetics?
    private int direScore; // Number of kills
    private BigInteger direTeamId; // Usually zero; if playing on a team, will give the ID
    // drafting timings TODO: probably needed for captains mode
    private int duration; // In seconds
    // engine?
    private int firstBloodTime;
    private int gameMode;
    private int humanPlayers; // CHECK: probably the number of non-bots
    private BigInteger leagueID;
    private LobbyType lobbyType;
    public enum LobbyType {
        INVALID(-1),
        PUBLIC_MATCHMAKING(0),
        PRACTICE(1),
        TOURNAMENT(2),
        TUTORIAL(3),
        COOP_WITH_BOTS(4),
        TEAM_MATCH(5);
        private final int value;
        LobbyType(int value) {
            this.value = value;
        }
        private static final Map<Integer, LobbyType> map = new HashMap<>();
        static {
            for (LobbyType lobbyType : LobbyType.values()) {
                map.put(lobbyType.value, lobbyType);
            }
        }
        public static LobbyType convert(int value) {
            return (LobbyType) map.get(value);
        }
    }
    private int matchSeqNum;
    private int negativeVotes;
    // objectives?


}
