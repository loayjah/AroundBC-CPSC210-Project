package ui;

// Represents a class for the wishlist window

import model.Place;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class WishlistWindow extends AbstractPlacesWindow {

    private JButton deleteFromWishlistBtn;

    public WishlistWindow() {
        super("My Wishlist", aroundBC.getWishList().getPlacesList());


    }

    // MODIFIES: this
    // EFFECTS: creates and returns top bar panel
    @Override
    public JPanel createTopPanel() {

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        panel.add(createWishlistBtn());

        return panel;
    }

    // MODIFIES: AroundBC
    // EFFECTS: button listener: add
    private JButton createWishlistBtn() {

        deleteFromWishlistBtn = new JButton("Delete from my wishlist");
        deleteFromWishlistBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteConformationPopUp();
            }
        });

        return deleteFromWishlistBtn;
    }

    private void deleteConformationPopUp() {
        String[] responses = {"Cancel", "Delete"};

        int confirmed = JOptionPane.showOptionDialog(getContentPane(),
                "Are you sure to delete the selected places from wishlist?",
                "Deleting from wishlist", JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null, responses, responses[0]);

        if (confirmed == 1) {
            for (Place p : renderer.getSelectedPlaces()) {
                if (aroundBC.getWishList().contains(p)) {
                    aroundBC.getWishList().remove(p);

                }
            }
            dispose();
            new WishlistWindow();
        }
    }
}
