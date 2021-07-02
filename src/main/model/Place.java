package model;

// This class represents the place in this program. A place could have name, location, address, rating, type,
// and picture. A place is the smallest abstraction of this program and could be added at sets and lists.

import org.json.JSONObject;
import persistence.Writable;


public class Place implements Writable {

    private String name;
    private String location;
    private String address;
    private String rating;
    private String category;
    private String price;
    private String visitTime;
    private String picture;

    // EFFECTS: initializes the fields to its values
    public Place(String name, String location, String address, String rating,
                 String category, String price, String picture) {
        this.name = name;
        this.location = location;
        this.address = address;
        this.rating = rating;
        this.category = category;
        this.price = price;
        this.picture = picture;
    }

    // EFFECTS: gets the name of this object
    public String getName() {
        return name;
    }

    // EFFECTS: gets the location of this object
    public String getLocation() {
        return location;
    }

    // EFFECTS: gets the address of this object
    public String getAddress() {
        return address;
    }

    // EFFECTS: gets the rating of this object
    public String getRating() {
        return rating;
    }

    public String getPicture() {
        return picture;
    }

    // EFFECTS: gets the visit time of this object
    public String getVisitTime() {
        return visitTime;
    }

    // MODIFIES: this
    // EFFECTS: sets the visit time of this object
    public void setVisitTime(String visitTime) {
        this.visitTime = visitTime;
    }

    public String getCategory() {
        return category;
    }

    // EFFECTS: returns the object information in String
    @Override
    public String toString() {
        return "Place{" + "name='" + name + '\'' + ", location='" + location + '\''
                + ", address='" + address + '\'' + ", rating='" + rating + '\''
                + ", category='" + category + '\'' + ", price='" + price + '\'' + '}';
    }


    // EFFECTS: writes a json object with the given data
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("location", location);
        json.put("address", address);
        json.put("rating", rating);
        json.put("category", category);
        json.put("price", price);
        json.put("picture", picture);
        return json;
    }

}
