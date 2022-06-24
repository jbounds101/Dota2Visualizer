import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.ArrayList;

public class DotaMatchDeserializer extends StdDeserializer<DotaMatch> {
   public DotaMatchDeserializer() {
       this(null);
   }
   public DotaMatchDeserializer(Class<DotaMatch> a) {
       super(a);
   }

    @Override
    public DotaMatch deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {
        DotaMatch match = new DotaMatch();
        ObjectCodec codec = jsonParser.getCodec();
        JsonNode outsideNode = codec.readTree(jsonParser);
        JsonNode resultNode = outsideNode.get("result");
        outsideNode = null;

        int status = resultNode.get("status").asInt();
        assert (status == 1); // Status must be 1, or an error occurred during match collection
        int numMatches = resultNode.get("num_results").asInt();
        assert (numMatches > 0); // Can't deserialize 0 matches

        ArrayList<DotaMatch> matches = new ArrayList<>();


        return null;
    }




}
