package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class PlacesSetTest {

    SuggestedPlacesWorkRoom placesSet;
    Place place1 = new Place("Science World,Vancouver", "Vancouver", "", "7.8", "family", "0", "");
    Place place2 = new Place("Vancouver Aquarium", "Vancouver", "", "8.9", "family", "0", "");
    Place place3 = new Place("H. R. Macmillan Space Museum", "Vancouver", "", "9.6", "family", "0", "");

    @BeforeEach
    void setup() {
        placesSet = new SuggestedPlacesWorkRoom();
    }

    @Test
    void addNotExistBeforeTest() {
        assertEquals(0, placesSet.getSize());
        placesSet.add(place1);
        assertEquals(1, placesSet.getSize());
    }

    @Test
    void addExistBeforeTest() {
        assertEquals(0, placesSet.getSize());
        placesSet.add(place1);
        assertEquals(1, placesSet.getSize());
        placesSet.add(place1);
        assertEquals(1, placesSet.getSize());
    }

    @Test
    void removeNothingInSetTest() {
        assertEquals(0, placesSet.getSize());
        placesSet.remove(place1);
        assertEquals(0, placesSet.getSize());
    }

    @Test
    void removeExistsInSetTest() {
        placesSet.add(place1);
        assertEquals(1, placesSet.getSize());
        placesSet.remove(place1);
        assertEquals(0, placesSet.getSize());
    }

    @Test
    void containsExistBeforeTest() {
        placesSet.add(place1);
        assertTrue(placesSet.contains(place1));
    }


    @Test
    void containsNotExistBeforeTest() {
        placesSet.add(place2);
        assertFalse(placesSet.contains(place1));
    }

    @Test
    void containsNothingInSetTest() {
        assertFalse(placesSet.contains(place1));
    }

    @Test
    void getSizeTest() {
        assertEquals(0, placesSet.getSize());
        placesSet.add(place1);
        assertEquals(1, placesSet.getSize());
    }

    @Test
    void getPlacesSetNothingInSetTest() {
        assertEquals(0, placesSet.getPlacesSet().size());
    }

    @Test
    void getPlacesSetItemsInSetTest() {
        placesSet.add(place1);
        placesSet.add(place2);
        placesSet.add(place3);

        assertEquals(3, placesSet.getPlacesSet().size());
    }


    @Test
    void containsTest() {
        placesSet.add(place1);

        if (!placesSet.contains(place1)) {
            fail("p1 is contained in the set");
        }

        if (placesSet.contains(place2)) {
            fail("p2 is not contained in the set");
        }

    }

}
