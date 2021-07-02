package ui;

// Represents a class to setup family places window

public class FamilyPlacesWindow extends SuggestedPlacesWindow {
    public FamilyPlacesWindow() {
        super("Family Trip Suggested places", aroundBC.getFamilyPlacesWorkroom().getPlacesSet());
    }
}
