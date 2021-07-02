package persistence;


import model.Place;
import model.WishListWorkRoom;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonWishListReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonWishListReader reader = new JsonWishListReader() {
        };
        try {
            WishListWorkRoom wr = reader.read( "./data/noSuchFile.json");
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonWishListReader reader = new JsonWishListReader();
        try {
            WishListWorkRoom wr = reader.read("./data/testReaderEmptyWorkRoom.json");
            assertEquals(0, wr.getSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonWishListReader reader = new JsonWishListReader();
        try {
            WishListWorkRoom wr = reader.read( "./data/testReaderGeneralWorkRoom.json");
            List<Place> places = wr.getPlaces();
            assertEquals(1, places.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
