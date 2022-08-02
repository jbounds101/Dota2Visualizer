package test;

import dotaobject.Match;
import dotaobject.MatchNotFoundException;
import main.DotaJsonParser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

public class MatchTests {

    @Test
    void matchFoundOnValidMatchID() {
        assertDoesNotThrow(() -> {
            DotaJsonParser.readMatch(6634785915L);
        });
    }

    @Test
    void matchNotFoundOnInvalidMatchID() {
        assertThrows(MatchNotFoundException.class, () -> DotaJsonParser.readMatch(10L));
    }

    @Test
    void matchShouldContainTenPlayers() {
        Match match = null;
        try {
            match = DotaJsonParser.readMatch(6634785915L);
        } catch (MatchNotFoundException e) {
            fail("Match wasn't found");
        }
        assertEquals(10, match.getPlayers().length);
    }

    @Test
    void matchDurationShouldNotBeZero() {
        Match match = null;
        try {
            match = DotaJsonParser.readMatch(6634785915L);
        } catch (MatchNotFoundException e) {
            fail("Match wasn't found");
        }
        assertNotEquals(0, match.getDuration());
    }
}
