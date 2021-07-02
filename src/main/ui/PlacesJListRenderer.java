package ui;


import model.Place;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PlacesJListRenderer implements ListCellRenderer<Place> {

    private JPanel mainPanel;
    private JPanel textPanel;
    private JLabel placeName;
    private JLabel placeAddress;
    private JLabel placeRating;
    private JLabel placeImage;
    private Dimension imageDimensions;
    private ArrayList<Place> selectedPlaces;
    private Place value;
    private JList<? extends Place> list;

    // EFFECTS: construct the renderer
    public PlacesJListRenderer() {

        mainPanel = new JPanel(new BorderLayout(5, 5));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        placeName = new JLabel();
        placeAddress = new JLabel();
        placeRating = new JLabel();
        placeImage = new JLabel();

        textPanel = new JPanel(new GridLayout(0, 1));
        textPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 10));
        textPanel.add(placeName);
        textPanel.add(placeAddress);
        textPanel.add(placeRating);

        mainPanel.add(textPanel, BorderLayout.CENTER);
        mainPanel.add(placeImage, BorderLayout.WEST);

        selectedPlaces = new ArrayList<>();

    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Place> list, Place value, int index,
                                                  boolean isSelected, boolean cellHasFocus) {

        imageDimensions = new Dimension(150, 125);
        this.value = value;
        this.list = list;
        dataSetup();

        placeName.setOpaque(true);
        placeAddress.setOpaque(true);
        placeRating.setOpaque(true);
        placeImage.setOpaque(true);

        if (isSelected) {
            itemSelected();

        } else {
            itemReleased();
        }

        return mainPanel;
    }

    // MODIFIES: this
    // EFFECTS: the actions happen when items are no longer selected
    private void itemReleased() {
        textPanel.setBorder(BorderFactory.createLineBorder(list.getBackground(), 10));
        mainPanel.setBackground(list.getBackground());
        placeName.setBackground(list.getBackground());
        placeAddress.setBackground(list.getBackground());
        placeRating.setBackground(list.getBackground());
        placeImage.setBackground(list.getBackground());
        if (selectedPlaces.contains(value)) {
            selectedPlaces.remove(value);
        }
    }


    // MODIFIES: this
    // EFFECTS: the actions happen when items are being selected
    private void itemSelected() {
        textPanel.setBorder(BorderFactory.createLineBorder(list.getSelectionBackground(), 10));
        mainPanel.setBackground(list.getSelectionBackground());
        placeName.setBackground(list.getSelectionBackground());
        placeAddress.setBackground(list.getSelectionBackground());
        placeRating.setBackground(list.getSelectionBackground());
        placeImage.setBackground(list.getSelectionBackground());

        if (!selectedPlaces.contains(value)) {
            selectedPlaces.add(value);
        }
    }

    // MODIFIES: this
    // EFFECTS: assigns the data to its appropriate field in the list
    private void dataSetup() {
        placeName.setText(value.getName());
        placeAddress.setText(value.getLocation());
        placeRating.setText(value.getRating());
        placeImage.setPreferredSize(imageDimensions);
        placeImage.setBorder(BorderFactory.createLineBorder(Color.red, 2));
        String iconPath;

        if (value.getPicture() != null) {
            iconPath = "src/res/places pictures/" + value.getPicture();
        } else {
            iconPath = "src/res/places pictures/null.png";
        }

        ImageIcon imageIcon = new ImageIcon(
                new ImageIcon(iconPath).getImage().getScaledInstance(
                        imageDimensions.width, imageDimensions.height, Image.SCALE_DEFAULT));
        placeImage.setIcon(imageIcon);
    }

    public ArrayList<Place> getSelectedPlaces() {
        return selectedPlaces;
    }

}
