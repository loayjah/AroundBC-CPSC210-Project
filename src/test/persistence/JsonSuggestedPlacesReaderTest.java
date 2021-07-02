package persistence;

import model.Place;
import model.SuggestedPlacesWorkRoom;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonSuggestedPlacesReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonSuggestedPlacesReader reader = new JsonSuggestedPlacesReader() {
        };
        try {
            SuggestedPlacesWorkRoom wr = reader.read( "./data/noSuchFile.json");
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonSuggestedPlacesReader reader = new JsonSuggestedPlacesReader();
        try {
            SuggestedPlacesWorkRoom wr = reader.read("./data/testReaderEmptyWorkRoom.json");
            assertEquals(0, wr.getSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonSuggestedPlacesReader reader = new JsonSuggestedPlacesReader();
        try {
            SuggestedPlacesWorkRoom wr = reader.read( "./data/testReaderGeneralWorkRoom.json");
            HashSet<Place> places = wr.getPlacesSet();
            assertEquals(1, places.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}