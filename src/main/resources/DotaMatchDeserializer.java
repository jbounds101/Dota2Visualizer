package main.resources;

import dotaobject.*;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.Map;

public class DotaMatchDeserializer extends StdDeserializer<Match> {
   public DotaMatchDeserializer() {
       this(null);
   }
   public DotaMatchDeserializer(Class<Match> a) {
       super(a);
   }

    @Override
    public Match deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {
        //main.resources.resources.DotaMatch match = new main.resources.resources.DotaMatch();
        ObjectCodec codec = jsonParser.getCodec();
        JsonNode node = codec.readTree(jsonParser);

        Long matchID = node.get("match_id").asLong();
        Long radiantTeamId = node.get("radiant_team_id").asLong();
        Long direTeamId = node.get("dire_team_id").asLong();
        int radiantScore = node.get("radiant_score").asInt();
        int direScore = node.get("dire_score").asInt();

        int towerStatusRadiant = node.get("tower_status_radiant").asInt();
        int towerStatusDire = node.get("tower_status_dire").asInt();
        int barracksStatusRadiant = node.get("barracks_status_radiant").asInt();
        int barracksStatusDire = node.get("barracks_status_radiant").asInt();

        int duration = node.get("duration").asInt();
        int firstBloodTime = node.get("first_blood_time").asInt();
        int gameMode = node.get("game_mode").asInt();
        int lobbyType = node.get("lobby_type").asInt();
        int humanPlayers = node.get("human_players").asInt();
        int matchSeqNum = node.get("match_seq_num").asInt();

        boolean radiantWin = node.get("radiant_win").asBoolean();

        JsonNode goldAdvantageNode = node.get("radiant_gold_adv");
        JsonNode xpAdvantageNode = node.get("radiant_xp_adv");
        int minutesPlayed = goldAdvantageNode.size();
        int[] radiantGoldAdvantage = new int[minutesPlayed];
        int[] radiantXPAdvantage = new int[minutesPlayed];
        for (int minuteMark = 0; minuteMark < minutesPlayed; minuteMark++) {
            radiantGoldAdvantage[minuteMark] = goldAdvantageNode.get(minuteMark).asInt();
            radiantXPAdvantage[minuteMark] = xpAdvantageNode.get(minuteMark).asInt();
        }

        JsonNode playersNode = node.get("players");
        Player[] players = new Player[playersNode.size()];
        for (JsonNode currentPlayerNode : playersNode) {
            int accountID = currentPlayerNode.get("account_id").asInt();
            int playerSlot = currentPlayerNode.get("player_slot").asInt();
            Hero hero = Heroes.getHero(currentPlayerNode.get("hero_id").asInt());
            int kills = currentPlayerNode.get("kills").asInt();
            int assists = currentPlayerNode.get("assists").asInt();
            int deaths = currentPlayerNode.get("deaths").asInt();
            Item[] items; // Of size six
            Item[] backpack; // Of size three
            Item neutralItem;
            int gpm = currentPlayerNode.get("gold_per_min").asInt();
            int lastHits = currentPlayerNode.get("last_hits").asInt();
            int denies = currentPlayerNode.get("denies").asInt();
            int xpm = currentPlayerNode.get("xp_per_min").asInt();
            int netWorth = currentPlayerNode.get("net_worth").asInt();
            int level = currentPlayerNode.get("level").asInt();
            boolean radiant = currentPlayerNode.get("isRadiant").asBoolean();
            Map<Integer, Item> purchaseLog;

            int[] goldAtMin; // Amount at each minute mark
            int[] lastHitAtMin;
            int[] xpAtMin;

        }


        return null;
    }




}
