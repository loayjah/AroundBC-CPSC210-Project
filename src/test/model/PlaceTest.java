package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PlaceTest {
    Place place;

    @BeforeEach
    void setup() {
        place = new Place("Science World", "Vancouver", "Address", "7.8", "family", "0", "0");
    }

    @Test
    void constructorTest() {
        assertEquals("Science World", place.getName());
        assertEquals("Vancouver", place.getLocation());
        assertEquals("Address", place.getAddress());
        assertEquals("7.8", place.getRating());
        assertEquals("family", place.getCategory());
        assertEquals("0", place.getPicture());
    }


    @Test
    void getNameTest() {
        assertEquals("Science World", place.getName());
    }

    @Test
    void getLocationTest() {
        assertEquals("Vancouver", place.getLocation());
    }

    @Test
    void getAddressTest() {
        assertEquals("Address", place.getAddress());
    }

    @Test
    void getRatingTest() {
        assertEquals("7.8", place.getRating());
    }

    @Test
    void setVisitTimeTest() {
        place.setVisitTime("03:05");
        assertEquals("03:05", place.getVisitTime());
    }

    @Test
    void toStringTest() {
        assertEquals("Place{name='Science World', location='Vancouver', address='Address', rating='7.8', category='family', price='0'}", place.toString());
    }

}
