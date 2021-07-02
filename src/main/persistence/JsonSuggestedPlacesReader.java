package persistence;

// Represents a reader that reads workroom from JSON data stored in file

// TODO: REFERENCE: Json reader and writer and tests are quoted from JsonSerializationDemo

import model.SuggestedPlacesWorkRoom;
import org.json.JSONObject;

import java.io.IOException;

public class JsonSuggestedPlacesReader extends JsonReader {


    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public SuggestedPlacesWorkRoom read(String source) throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseWorkRoom(jsonObject);
    }

    // EFFECTS: parses workroom from JSON object and returns it
    protected SuggestedPlacesWorkRoom parseWorkRoom(JSONObject jsonObject) {
        SuggestedPlacesWorkRoom wr = new SuggestedPlacesWorkRoom();
        addPlaces(wr, jsonObject);
        return wr;
    }


}
