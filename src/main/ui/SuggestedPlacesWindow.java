package ui;

// Represents a class to setup places window

import model.Place;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Collection;

public class SuggestedPlacesWindow extends AbstractPlacesWindow {


    private JButton addToWishlistBtn;
    private JButton addNewPlaceBtn;
    private String category;

    // EFFECTS: constructs a new window
    public SuggestedPlacesWindow(String frameTitle, Collection<Place> list) {
        super(frameTitle, list);

        addToWishlistBtnListener();
        addNewPlaceBtnListener();

        category = checkCategory();

    }


    // EFFECTS: checks whether the list if for individual or family trip
    private String checkCategory() {
        if (getTitle().equals("Solo Trip Suggested places")) {
            category = "individual";
        } else {
            category = "family";
        }

        return category;
    }


    // MODIFIES: this
    // EFFECTS: creates and returns top bar panel
    @Override
    public JPanel createTopPanel() {

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        addToWishlistBtn = new JButton("Add to my wishlist");
        panel.add(addToWishlistBtn);

        addNewPlaceBtn = new JButton("Suggest a place");
        panel.add(addNewPlaceBtn);

        return panel;
    }

    // MODIFIES: AroundBC
    // EFFECTS: button listener: add
    private void addToWishlistBtnListener() {
        addToWishlistBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                for (Place p : renderer.getSelectedPlaces()) {
                    if (!aroundBC.getWishList().contains(p)) {
                        aroundBC.getWishList().add(p);
                    }
                }

            }
        });
    }

    // MODIFIES: this
    // EFFECTS: disposes this window when the add button is clicked (no need to duplicate windows)
    private void addNewPlaceBtnListener() {
        addNewPlaceBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddPlaceWindow(category);
                dispose();
            }
        }
        );

    }


}
