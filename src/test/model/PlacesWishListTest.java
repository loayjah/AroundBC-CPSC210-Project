package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class PlacesWishListTest {

    WishListWorkRoom placesWishList;

    @BeforeEach
    void setup() {
        placesWishList = new WishListWorkRoom();
    }

    @Test
    void instructorTest() {
        assertEquals(placesWishList, placesWishList);
    }

    @Test
    void getPlacesTest() {
        assertEquals(0, placesWishList.getPlaces().size());
        placesWishList.add(new Place("Science World", "Vancouver", "Address", "7.8", "family", "0", "0"));
        assertEquals(1, placesWishList.getPlaces().size());
    }


    @Test
    void containsTest() {
        Place p1 = new Place("Science World", "Vancouver", "Address", "7.8",
                "family", "0", "0");
        Place p2 = new Place("Science", "Vancouver", "Address", "7.8",
                "family", "0", "0");
        placesWishList.add(p1);

        if (placesWishList.contains(p2)) {
            fail("p2 is not contained in wishlist");
        }

        if (!placesWishList.contains(p1)) {
            fail("p1 is contained in the wishlist");
        }
    }


}
