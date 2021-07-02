package ui;

// Represents a class to setup solo places window

public class SoloPlacesWindow extends SuggestedPlacesWindow {

    public SoloPlacesWindow() {
        super("Solo Trip Suggested places", aroundBC.getPlaceRoom().getPlacesSet());
    }

}
