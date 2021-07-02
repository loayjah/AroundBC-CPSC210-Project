package model;

import org.json.JSONObject;

public interface WorkRoom {

    // EFFECTS: returns this as JSON object
    JSONObject toJson();

    void add(Place p);
}
