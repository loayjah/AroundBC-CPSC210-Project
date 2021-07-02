package persistence;

import model.Place;
import model.WishListWorkRoom;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonWishListWriterTest {

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter();
            writer.open("./data/my\0illegal:fileName.json");
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            WishListWorkRoom wr = new WishListWorkRoom();
            JsonWriter writer = new JsonWriter();
            writer.open("./data/testWriterEmptyWorkroom.json");
            writer.write(wr);
            writer.close();

            JsonWishListReader reader = new JsonWishListReader();
            wr = reader.read( "./data/testWriterEmptyWorkroom.json");
            assertEquals(0, wr.getSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            WishListWorkRoom wr = new WishListWorkRoom();
            wr.add(new Place("Science World,Vancouver", "Vancouver", "", "7.8", "family", "0", ""));
            wr.add(new Place("Vancouver Aquarium", "Vancouver", "", "8.9", "family", "0", ""));
            JsonWriter writer = new JsonWriter();
            writer.open("./data/testWriterGeneralWorkroom.json");
            writer.write(wr);
            writer.close();

            JsonWishListReader reader = new JsonWishListReader();
            wr = reader.read("./data/testWriterGeneralWorkroom.json");
            List<Place> places = wr.getPlacesList();
            assertEquals(2, places.size());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
