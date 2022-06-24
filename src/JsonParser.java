import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;

public class JsonParser {

    // A constructor is used for objectMapper settings
    private static final ObjectMapper objectMapper = createDefaultObjectMapper();
    private static ObjectMapper createDefaultObjectMapper() {
        ObjectMapper defaultObjectMapper = new ObjectMapper();

        // Add DotaMatch deserializer to the ObjectMapper
        SimpleModule DotaMatchDeserializer =
            new SimpleModule("DotaMatchDeserializer", new Version(1, 0, 0,
                    null, null, null));

        DotaMatchDeserializer.addDeserializer(DotaMatch[].class, new DotaMatchDeserializer());
        defaultObjectMapper.registerModule(DotaMatchDeserializer);

        return defaultObjectMapper;
    }

    public static JsonNode parse(String source) throws IOException {
        return objectMapper.readTree(source);
    }

    public static DotaMatch[] parseMatch(String source) throws IOException {
        return objectMapper.readValue(source, DotaMatch[].class);
    }

}


