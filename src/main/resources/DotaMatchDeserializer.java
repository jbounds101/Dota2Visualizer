package main.resources;

import main.resources.dotaobject.Match;
import main.resources.dotaobject.Player;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;

public class DotaMatchDeserializer extends StdDeserializer<Match[]> {
   public DotaMatchDeserializer() {
       this(null);
   }
   public DotaMatchDeserializer(Class<Match> a) {
       super(a);
   }

    @Override
    public Match[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {
        //main.resources.resources.DotaMatch match = new main.resources.resources.DotaMatch();
        ObjectCodec codec = jsonParser.getCodec();
        JsonNode outsideNode = codec.readTree(jsonParser);
        JsonNode resultNode = outsideNode.get("result");
        outsideNode = null;

        int status = resultNode.get("status").asInt();
        assert (status == 1); // Status must be 1, or an error occurred during match collection
        int numMatches = resultNode.get("num_results").asInt();
        assert (numMatches == 1); // Can only deserialize once at a time

        JsonNode matchesToParse = resultNode.get("matches");
        Match[] matches = new Match[numMatches];

        ArrayList<Player> players = null;

        for (int i = 0; i < numMatches; i++) {
            JsonNode currentParse = matchesToParse.get(i);
            BigInteger matchId = new BigInteger(currentParse.get("match_id").asText());
            BigInteger matchSeqNum = new BigInteger(currentParse.get("match_seq_num").asText());
            BigInteger startTime = new BigInteger(currentParse.get("start_time").asText());
            int lobbyType = currentParse.get("lobby_type").asInt();
            int radiantTeamId = currentParse.get("radiant_team_id").asInt();
            int direTeamId = currentParse.get("dire_team_id").asInt();
           /* main.resources.resources.DotaMatch match = new main.resources.resources.DotaMatch(matchId, matchSeqNum, startTime, lobbyType, radiantTeamId,
                    direTeamId, players);
            matches[i] = match;*/
        }



        return matches;
    }




}
