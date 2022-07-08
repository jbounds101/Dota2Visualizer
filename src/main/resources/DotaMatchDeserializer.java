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
        int playerIndex = 0;
        for (JsonNode currentPlayerNode : playersNode) {
            String playerName = "Anonymous";
            try {
                playerName = currentPlayerNode.get("personaname").asText();
            } catch (NullPointerException ignored) {}
            int accountID = currentPlayerNode.get("account_id").asInt();
            int playerSlot = currentPlayerNode.get("player_slot").asInt();
            Hero hero = Heroes.getHero(currentPlayerNode.get("hero_id").asInt());
            int kills = currentPlayerNode.get("kills").asInt();
            int assists = currentPlayerNode.get("assists").asInt();
            int deaths = currentPlayerNode.get("deaths").asInt();
            final int itemSlots = 6;
            Item[] items = new Item[itemSlots];
            items[0] = Items.getItem(currentPlayerNode.get("item_0").asInt());
            items[1] = Items.getItem(currentPlayerNode.get("item_1").asInt());
            items[2] = Items.getItem(currentPlayerNode.get("item_2").asInt());
            items[3] = Items.getItem(currentPlayerNode.get("item_3").asInt());
            items[4] = Items.getItem(currentPlayerNode.get("item_4").asInt());
            items[5] = Items.getItem(currentPlayerNode.get("item_5").asInt());
            final int backpackSlots = 3;
            Item[] backpack = new Item[backpackSlots];
            backpack[0] = Items.getItem(currentPlayerNode.get("backpack_0").asInt());
            backpack[1] = Items.getItem(currentPlayerNode.get("backpack_1").asInt());
            backpack[2] = Items.getItem(currentPlayerNode.get("backpack_2").asInt());
            Item neutralItem = Items.getItem(currentPlayerNode.get("item_neutral").asInt());
            int gpm = currentPlayerNode.get("gold_per_min").asInt();
            int lastHits = currentPlayerNode.get("last_hits").asInt();
            int denies = currentPlayerNode.get("denies").asInt();
            int xpm = currentPlayerNode.get("xp_per_min").asInt();
            int netWorth = currentPlayerNode.get("net_worth").asInt();
            int level = currentPlayerNode.get("level").asInt();
            boolean radiant = currentPlayerNode.get("isRadiant").asBoolean();

            JsonNode purchaseLogNode = currentPlayerNode.get("purchase_log");
            PurchaseLogElement[] purchaseLog = new PurchaseLogElement[purchaseLogNode.size()];
            for (int i = 0; i < purchaseLogNode.size(); i++) {
                int time = purchaseLogNode.get(i).get("time").asInt();
                Item item = Items.getItem(purchaseLogNode.get(i).get("key").asText());
                purchaseLog[i] = new PurchaseLogElement(time, item);
            }

            JsonNode goldAtMinNode = currentPlayerNode.get("gold_t");
            JsonNode lastHitAtMinNode = currentPlayerNode.get("lh_t");
            JsonNode xpAtMinNode = currentPlayerNode.get("xp_t");
            int[] goldAtMin = new int[minutesPlayed];
            int[] lastHitAtMin = new int[minutesPlayed];
            int[] xpAtMin = new int[minutesPlayed];
            for (int minuteMark = 0; minuteMark < minutesPlayed; minuteMark++) {
                goldAtMin[minuteMark] = goldAtMinNode.get(minuteMark).asInt();
                lastHitAtMin[minuteMark] = lastHitAtMinNode.get(minuteMark).asInt();
                xpAtMin[minuteMark] = xpAtMinNode.get(minuteMark).asInt();
            }

            players[playerIndex++] = new Player(playerName, accountID, playerSlot, hero, kills, assists, deaths, items,
                    backpack, neutralItem, gpm, goldAtMin, lastHits, lastHitAtMin, denies, xpm, xpAtMin, netWorth,
                    level, purchaseLog, radiant);
        }

        return new Match(matchID, radiantTeamId, direTeamId, radiantScore, direScore, towerStatusRadiant,
                towerStatusDire, barracksStatusRadiant, barracksStatusDire, duration, minutesPlayed, firstBloodTime,
                gameMode, lobbyType, humanPlayers, matchSeqNum, radiantWin, radiantGoldAdvantage, radiantXPAdvantage,
                players);
    }

    public class PurchaseLogElement {

        public PurchaseLogElement(int time, Item item) {
            this.time = time;
            this.item = item;
        }
        private int time;
        private Item item;

        @Override
        public String toString() {
            return this.item.toString();
        }

        //private
    }




}
