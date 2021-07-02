package model;

// This class represents a list of places in the program. This list can be used in presenting the wishlist
// because it is sorted in indices

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WishListWorkRoom implements Writable, WorkRoom {

    private ArrayList<Place> placesList;

    // EFFECTS: constructs workroom with an empty list of places
    public WishListWorkRoom() {
        placesList = new ArrayList<>();

    }

    // MODIFIES: this
    // EFFECTS: add place to this workroom if it's not already in there
    public void add(Place p) {
        if (!contains(p)) {
            placesList.add(p);
        }
    }

    // MODIFIES: this
    // EFFECTS: remove place from this workroom if it's already in there
    public void remove(Place p) {
        if (contains(p)) {
            placesList.remove(p);
        }
    }

    // EFFECTS: returns true if it contains the place
    // and false otherwise
    public boolean contains(Place p) {
        for (Place m : placesList) {
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

    // EFFECTS: returns the number of places in this workroom
    public int getSize() {
        return placesList.size();
    }

    // EFFECTS: returns the list of places in this workroom
    public ArrayList<Place> getPlacesList() {
        return placesList;
    }

    // EFFECTS: returns an unmodifiable list of places in this workroom
    public List<Place> getPlaces() {
        return Collections.unmodifiableList(placesList);
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

        for (Place p : placesList) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }

}
