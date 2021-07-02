package persistence;

import model.Place;
import model.WorkRoom;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public abstract class JsonReader {

    // EFFECTS: reads source file as string and returns it
    protected String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // MODIFIES: wr
    // EFFECTS: parses places from JSON object and adds them to workroom
    protected void addPlaces(WorkRoom wr, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("places");
        for (Object json : jsonArray) {
            JSONObject nextPlace = (JSONObject) json;
            addPlace(wr, nextPlace);
        }
    }

    // MODIFIES: wr
    // EFFECTS: parses place from JSON object and adds it to workroom
    protected void addPlace(WorkRoom wr, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String location = jsonObject.getString("location");
        String address = jsonObject.getString("address");
        String rating = jsonObject.getString("rating");
        String category = jsonObject.getString("category");
        String price = jsonObject.getString("price");
        String picture = jsonObject.getString("picture");
        Place place = new Place(name, location, address, rating, category, price, picture);
        wr.add(place);
    }


}
