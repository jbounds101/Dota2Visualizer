import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonParser {

    // A constructor is used for objectMapper settings
    private static ObjectMapper objectMapper = createDefaultObjectMapper();

    private static ObjectMapper createDefaultObjectMapper() {
        ObjectMapper defaultObjectMapper = new ObjectMapper();
        return defaultObjectMapper;
    }

    public static JsonNode parse(String source) throws IOException {
        return objectMapper.readTree(source);
    }

    public static DotaMatch parseMatch(String source) throws IOException {
        return objectMapper.readValue(source, DotaMatch.class);
    }

}
