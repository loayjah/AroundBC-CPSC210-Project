package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class PlacesListTest {

    WishListWorkRoom placesList;
    Place place1 = new Place("Science World,Vancouver", "Vancouver", "", "7.8", "family", "0", "");
    Place place2 = new Place("Vancouver Aquarium", "Vancouver", "", "8.9", "family", "0", "");
    Place place3 = new Place("H. R. Macmillan Space Museum", "Vancouver", "", "9.6", "family", "0", "");

    @BeforeEach
    void setup() {
        placesList = new WishListWorkRoom();
    }

    @Test
    void addNotExistBeforeTest() {
        assertEquals(0, placesList.getSize());
        placesList.add(place1);
        assertEquals(1, placesList.getSize());
    }

    @Test
    void addExistBeforeTest() {
        assertEquals(0, placesList.getSize());
        placesList.add(place1);
        assertEquals(1, placesList.getSize());
        placesList.add(place1);
        assertEquals(1, placesList.getSize());
    }

    @Test
    void removeNothingInSetTest() {
        assertEquals(0, placesList.getSize());
        placesList.remove(place1);
        assertEquals(0, placesList.getSize());
    }

    @Test
    void removeExistsInSetTest() {
        placesList.add(place1);
        assertEquals(1, placesList.getSize());
        placesList.remove(place1);
        assertEquals(0, placesList.getSize());
    }

    @Test
    void containsExistBeforeTest() {
        placesList.add(place1);
        assertTrue(placesList.contains(place1));
    }


    @Test
    void containsNotExistBeforeTest() {
        placesList.add(place2);
        assertFalse(placesList.contains(place1));
    }

    @Test
    void containsNothingInSetTest() {
        assertFalse(placesList.contains(place1));
    }

    @Test
    void getSizeTest() {
        assertEquals(0, placesList.getSize());
        placesList.add(place1);
        assertEquals(1, placesList.getSize());
    }

    @Test
    void getPlacesSetNothingInSetTest() {
        assertEquals(0, placesList.getPlacesList().size());
    }

    @Test
    void getPlacesSetItemsInSetTest() {
        placesList.add(place1);
        placesList.add(place2);
        placesList.add(place3);

        assertEquals(3, placesList.getPlacesList().size());
    }

    @Test
    void checkEqualsTest() {
        Place place0 = new Place("Science World,Vancouver", "Vancouver", "", "7.8", "family", "0", "");
        assertTrue(placesList.checkEquals(place1, place0));
        assertFalse(placesList.checkEquals(place1, place2));
    }


}
