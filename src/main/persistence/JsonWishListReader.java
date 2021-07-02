package persistence;

import model.WishListWorkRoom;
import org.json.JSONObject;

import java.io.IOException;

// Represents a reader that reads workroom from JSON data stored in file

// TODO: REFERENCE: Json reader and writer and tests are quoted from JsonSerializationDemo

public class JsonWishListReader extends JsonReader {

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file

    public WishListWorkRoom read(String source) throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseWorkRoom(jsonObject);
    }

    // EFFECTS: parses workroom from JSON object and returns it
    public WishListWorkRoom parseWorkRoom(JSONObject jsonObject) {
        WishListWorkRoom wr = new WishListWorkRoom();
        addPlaces(wr, jsonObject);
        return wr;
    }


}