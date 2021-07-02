package ui;

// This class deals with keep the program running, and it represents the underlying
// operations for the user interface

import model.Place;
import model.SuggestedPlacesWorkRoom;
import model.WishListWorkRoom;
import model.WorkRoom;
import persistence.JsonSuggestedPlacesReader;
import persistence.JsonWishListReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

public class AroundBC {

    public static final String JSON_WISHLIST_PATH = "./data/wishList2.json";
    public static final String JSON_INDIVIDUAL_LIST_PATH = "./data/individualSuggestedList.json";
    public static final String JSON_FAMILY_LIST_PATH = "./data/familySuggestedList.json";
    public static final String FAMILY_CATEGORY = "family";
    public static final String INDIVIDUAL_CATEGORY = "individual";

    private Scanner in;
    private SuggestedPlacesWorkRoom individualPlaces;
    private SuggestedPlacesWorkRoom familyPlaces;
    private WishListWorkRoom wishList;
    private JsonWriter jsonWriter;
    private JsonWishListReader jsonWishListReader;
    private JsonSuggestedPlacesReader jsonSuggestedListReader;

    // MODIFIES: this, places
    // EFFECTS: constructs the json reader and writer, initializes the workroom,
    // load previous data, and runs the application
    public AroundBC() {
        in = new Scanner(System.in);
        wishList = new WishListWorkRoom();
        individualPlaces = new SuggestedPlacesWorkRoom();
        familyPlaces = new SuggestedPlacesWorkRoom();
        jsonWriter = new JsonWriter();
        jsonWishListReader = new JsonWishListReader();
        jsonSuggestedListReader = new JsonSuggestedPlacesReader();
        loadData(true, true, false);
        //runAroundBC();
    }

    // MODIFIES: places, wishList
    // EFFECTS: prompts the main menu and keep the program runs as long as the input not "q"
    private void runAroundBC() {
        System.out.println("Welcome to AroundBC application.");
        System.out.println("\nUp to something fun today?");

        do {
            displayMainMenu();
            processMainCommand(receiveInputs());
            System.out.println("\nEnter any character to display the main menu, or \"q\" to quit");
        } while (!in.next().equals("q"));
    }

    // EFFECTS: prompts the main menu to select from
    private void displayMainMenu() {
        System.out.println("\nPlease select from: ");
        System.out.println("\td -> to display suggested places to go");
        System.out.println("\tw -> to display your wishlist");
        System.out.println("\ts -> to suggest a place to be added to the list");
        System.out.println("\tq -> quit");
    }

    // EFFECTS: prompts the type of suggested places to display
    private void displaySuggestedPlacesMenu() {
        System.out.println("\nPlease select from: ");
        System.out.println("\ti -> individual");
        System.out.println("\tf -> family");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: individualPlaces, familyPlaces, wishList
    // EFFECTS: processing the received command - "d" for suggesting places, "w" to display wishlist
    // "s" to suggest a place, and "q" to quit the program
    private void processMainCommand(String command) {
        switch (command) {
            case "d":
                System.out.println("\nYou selected displaying suggested places");
                displaySuggestedPlacesMenu();
                processSuggestedPlacesCommand(receiveInputs());
                break;
            case "w":
                loadWishListWorkRoom();
                System.out.println("\nYou selected displaying your wish list");
                outputOneWishListItemAtOnce();
                break;

            case "s":
                System.out.println("\nYou selected suggesting a place for other visitor");
                addSuggestedPlace();

                System.out.println("\nPlace is added successfully!");
                break;

            case "q":
                quitProgram();
                break;

            default:
                System.out.println("\nYou entered the wrong command. Please try again\n");
                displayMainMenu();
        }

    }

    // EFFECTS: save changes and quit the program
    private void quitProgram() {
        System.exit(0);
    }

    // MODIFIES: places
    // EFFECTS: adds a place to the list of places, and write it down to the file
    private void addSuggestedPlace() {

        Place place = getAddedPlaceInfo();

        if (place.getCategory().equals(INDIVIDUAL_CATEGORY)) {
            loadSuggestedPlacesWorkRoom(INDIVIDUAL_CATEGORY, JSON_INDIVIDUAL_LIST_PATH);
            individualPlaces.add(place);
            saveWorkRoom(JSON_INDIVIDUAL_LIST_PATH, individualPlaces);
        } else {
            loadSuggestedPlacesWorkRoom(FAMILY_CATEGORY, JSON_FAMILY_LIST_PATH);
            familyPlaces.add(place);
            saveWorkRoom(JSON_FAMILY_LIST_PATH, familyPlaces);
        }


    }

    // EFFECTS: receive the information of the new place
    private Place getAddedPlaceInfo() {
        System.out.print("\nEnter the name of the place: ");
        String name = in.next();
        System.out.print("Enter the location of the place: ");
        String location = in.next();
        System.out.print("Enter the address of the place: ");
        String address = in.next();
        System.out.print("Enter the rating of the place: ");
        String rating = in.next();
        System.out.print("Enter the category of the place (" + INDIVIDUAL_CATEGORY + " or " + FAMILY_CATEGORY + "): ");
        String category = in.next();
        category = category.toLowerCase();
        while (!category.equals(INDIVIDUAL_CATEGORY) && !category.equals(FAMILY_CATEGORY)) {
            System.out.print("Please enter a valid value for the category of the place ("
                    + INDIVIDUAL_CATEGORY + " or " + FAMILY_CATEGORY + "): ");
            category = in.next();
            category = category.toLowerCase();
        }
        System.out.print("Enter the price of the place: ");
        String price = in.next();

        return new Place(name, location, address, rating, category, price, "");
    }


    // EFFECTS: saves the workroom to file
    public void saveWorkRoom(String filePath, WorkRoom workRoom) {
        try {
            jsonWriter.open(filePath);
            jsonWriter.write(workRoom);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("\nUnable to write to file: " + filePath);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadWishListWorkRoom() {

        try {
            wishList = jsonWishListReader.read(JSON_WISHLIST_PATH);
        } catch (IOException e) {
            System.out.println("\nUnable to read from file: " + jsonWishListReader);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadSuggestedPlacesWorkRoom(String category, String source) {
        try {
            if (category.equals(INDIVIDUAL_CATEGORY)) {
                individualPlaces = jsonSuggestedListReader.read(source);

            } else if (category.equals(FAMILY_CATEGORY)) {
                familyPlaces = jsonSuggestedListReader.read(source);
            }

        } catch (IOException e) {
            System.out.println("\nUnable to read from file: " + source);
        }
    }

    // MODIFIES: places
    // EFFECTS: processing the received command of the type of trip - "i" for individual or solo trip,
    // "f" for family trip, and "q" to quit the program
    private void processSuggestedPlacesCommand(String command) {
        switch (command) {
            case "i":
                loadSuggestedPlacesWorkRoom(INDIVIDUAL_CATEGORY, JSON_INDIVIDUAL_LIST_PATH);
                outputOneSuggestedPlaceAtOnce(individualPlaces.getPlacesSet());
                break;
            case "f":
                loadSuggestedPlacesWorkRoom(FAMILY_CATEGORY, JSON_FAMILY_LIST_PATH);
                outputOneSuggestedPlaceAtOnce(familyPlaces.getPlacesSet());
                break;

            case "q":
                quitProgram();
                break;

            default:
                System.out.println("\nYou entered the wrong command. Please try again");
                displaySuggestedPlacesMenu();
        }

    }

    // MODIFIES: wishList
    // EFFECTS: display one suggested places at once, with the ability to save them in wishlist
    private void outputOneSuggestedPlaceAtOnce(HashSet<Place> placesList) {
        for (Place p : placesList) {
            System.out.println();
            System.out.println(p);
            System.out.println("\nEnter \"next\" to see another suggestion,"
                    + "\"save\" to save it to your wishlist, or any other character to go back to menu\n");

            String receivedInput = receiveInputs();

            if (receivedInput.equals("save")) {
                if (wishList.contains(p)) {
                    System.out.println("\nThis place is already in your wish list!");
                } else {
                    loadWishListWorkRoom();
                    wishList.add(p);
                    saveWorkRoom(JSON_WISHLIST_PATH, wishList);
                    System.out.println("\nPlace is saved to your wish list successfully!");
                }
            } else if (!receivedInput.equals("next")) {
                return;
            }
        }
    }

    // MODIFIES: wishList
    // EFFECTS: display one wishlist item at once, with the ability to delete them from wishlist
    private void outputOneWishListItemAtOnce() {
        if (wishList.getSize() == 0) {
            System.out.println("\nThe list is empty! Display some places and add them to wishlist");
        }

        for (int j = wishList.getSize() - 1; j >= 0; j--) {
            System.out.println();
            System.out.println(wishList.getPlacesList().get(j));
            System.out.println("\nEnter \"next\" to see another item,"
                    + "\"delete\" to delete it from your wishlist, or any other character to go back to menu\n");

            String receivedInput = receiveInputs();
            if (receivedInput.equals("delete")) {
                wishList.remove(wishList.getPlacesList().get(j));
                saveWorkRoom(JSON_WISHLIST_PATH, wishList);
                System.out.println("\nPlace is deleted from your wish list successfully!");

            } else if (!receivedInput.equals("next")) {
                return;
            }
        }
    }

    public SuggestedPlacesWorkRoom getPlaceRoom() {
        return individualPlaces;
    }

    public SuggestedPlacesWorkRoom getFamilyPlacesWorkroom() {
        return familyPlaces;
    }

    public WishListWorkRoom getWishList() {
        return wishList;
    }

    // EFFECTS: receives command from the user and returns it
    private String receiveInputs() {
        String command = in.next();
        command = command.toLowerCase();
        if (command != null) {
            return command;
        }
        return "";
    }


    // EFFECTS: load the data from the memory based on the passed in values
    public void loadData(boolean loadSolo, boolean loadFamily, boolean loadWishList) {
        if (loadSolo) {
            loadSuggestedPlacesWorkRoom(INDIVIDUAL_CATEGORY, JSON_INDIVIDUAL_LIST_PATH);
        }
        if (loadFamily) {

            loadSuggestedPlacesWorkRoom(FAMILY_CATEGORY, JSON_FAMILY_LIST_PATH);
        }
        if (loadWishList) {
            loadWishListWorkRoom();
        }
    }

    // EFFECTS: saved all data to memory
    public void saveData() {
        saveWorkRoom(JSON_INDIVIDUAL_LIST_PATH, individualPlaces);
        saveWorkRoom(JSON_FAMILY_LIST_PATH, familyPlaces);
        saveWorkRoom(JSON_WISHLIST_PATH, wishList);

    }

}