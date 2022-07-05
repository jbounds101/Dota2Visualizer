package main.resources;

import dotaobject.Match;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

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




        return null;
    }




}
