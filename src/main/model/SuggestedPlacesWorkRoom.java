package model;

// This class represents a set of places in the program. This set can be used in presenting the suggested places
// to visit because it is easier and faster to search through

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.HashSet;


public class SuggestedPlacesWorkRoom implements Writable, WorkRoom {

    private HashSet<Place> placesSet;

    // EFFECTS: constructs workroom with an empty set of places
    public SuggestedPlacesWorkRoom() {
        placesSet = new HashSet<Place>();

    }

    // MODIFIES: this
    // EFFECTS: add place to this set workroom
    public void add(Place p) {
        placesSet.add(p);
    }

    // MODIFIES: this
    // EFFECTS: remove place from this set workroom
    public void remove(Place p) {
        placesSet.remove(p);
    }

    // EFFECTS: returns true if it contains the place
    // and false otherwise
    public boolean contains(Place p) {
        for (Place m : placesSet) {
            if (checkEquals(m, p)) {
                return true;
            }
        }
        return false;
    }

    // EFFECTS: checks if two place objects are the same
    public boolean checkEquals(Place first, Place second) {
        return first.getName().equals(second.getName())
                && first.getAddress().equals(second.getAddress())
                && first.getCategory().equals(second.getCategory())
                && first.getLocation().equals(second.getLocation())
                && first.getRating().equals(second.getRating());
    }

    // EFFECTS: returns the number of places in the Set
    public int getSize() {
        return placesSet.size();
    }

    // EFFECTS: returns the set of places in this workroom
    public HashSet<Place> getPlacesSet() {
        return placesSet;
    }


    // EFFECTS: writes a json object with the given data
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("places", placesToJson());
        return json;
    }

    // EFFECTS: returns places in this workroom as a JSON array
    private JSONArray placesToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Place p : placesSet) {
            jsonArray.put(p.toJson());
        }
        return jsonArray;
    }

}
