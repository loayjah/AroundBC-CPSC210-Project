package ui;


// Represents this window of the "adding place" form

import model.Place;
import model.SuggestedPlacesWorkRoom;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AddPlaceWindow extends JFrame {

    Dimension frameDimensions;
    JPanel mainPanel;
    String category;
    String picturePath;
    PropertyPanelClass name;
    PropertyPanelClass address;
    PropertyPanelClass rating;
    PropertyPanelClass price;
    JFileChooser imageFileChooser;
    int imageApproved = -2;
    Place place;

    // EFFECTS: constructs a new window
    public AddPlaceWindow(String category) {

        picturePath = "null.png";

        this.category = category;

        frameDimensions = new Dimension(750, 550);
        frameSetup();

        mainPanel = createMainPanel();

        add(mainPanel);

        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: setup the frame of the window
    private void frameSetup() {
        setTitle("Add a place");
        setSize(frameDimensions.width, frameDimensions.height);
        setResizable(false);
        setIconImage(new ImageIcon("src/res/app_logo.png").getImage());
        setLocationRelativeTo(null);
        setOnClosePopUpMsg();
    }

    // EFFECTS: listener to refresh the parent window when close this window
    private void setOnClosePopUpMsg() {
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                refreshTheList();
            }
        });
    }

    // EFFECTS: show up an updated window of solo trip or the family trip
    private void refreshTheList() {
        if (category.equals("individual")) {
            new SoloPlacesWindow();
        } else if (category.equals("family")) {
            new FamilyPlacesWindow();
        }
    }

    // MODIFIES: this
    // EFFECTS: creates the main panel where adding the entry fields
    private JPanel createMainPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1, 0, 10));

        name = new PropertyPanelClass("Name");
        JPanel namePanel = name.getPropertyPanel();

        address = new PropertyPanelClass("Address");
        JPanel addressPanel = address.getPropertyPanel();

        rating = new PropertyPanelClass("Rating");
        JPanel ratingPanel = rating.getPropertyPanel();

        price = new PropertyPanelClass("Price");
        JPanel pricePanel = price.getPropertyPanel();


        panel.add(namePanel);
        panel.add(addressPanel);
        panel.add(ratingPanel);
        panel.add(pricePanel);
        panel.add(createPicturePanel());

        panel.add(createBottomBar());

        return panel;
    }

    // MODIFIES: this
    // EFFECTS: creates the panel of picture field
    private JPanel createPicturePanel() {

        JPanel picturePanel = new JPanel(new BorderLayout());

        picturePanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(12, 12, 12, 12),
                BorderFactory.createLineBorder(new Color(204, 204, 204))));

        JLabel pictureLabel = new JLabel("Image");
        pictureLabel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));

        pictureLabel.setPreferredSize(new Dimension(75, 25));


        JButton chooseImageBtn = new JButton("Choose an image...");
        chooseImageBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                imageFileChooser = new JFileChooser();
                imageFileChooser.setFileFilter(
                        new FileNameExtensionFilter("jpg, jpeg, png, images", "jpg", "jpeg", "png"));

                imageApproved = imageFileChooser.showOpenDialog(null);

            }
        });

        picturePanel.add(pictureLabel, BorderLayout.WEST);
        picturePanel.add(chooseImageBtn, BorderLayout.CENTER);

        return picturePanel;
    }

    // MODIFIES: this
    // EFFECTS: creates the bottom panel and its buttons
    private JPanel createBottomBar() {
        JPanel panel = new JPanel(new FlowLayout());

        panel.add(createAddButton());
        panel.add(createClearButton());

        return panel;
    }

    // MODIFIES: this
    // EFFECTS: create the clear button and sets up its listener to clear
    // all fields when button is clicked
    private JButton createClearButton() {
        JButton clearBtn = new JButton("Clear");
        clearBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                name.getPropertyTextField().setText("");
                address.getPropertyTextField().setText("");
                rating.getPropertyTextField().setText("");
                price.getPropertyTextField().setText("");
                imageFileChooser = new JFileChooser();
                picturePath = "null.png";
            }
        });
        return clearBtn;
    }

    // MODIFIES: this
    // EFFECTS: create the add button and sets up its listener to add
    // the place to the appropriate list
    private JButton createAddButton() {

        JButton addBtn = new JButton("Add");
        addBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dealWithFileChooser();

                place = new Place(name.getPropertyTextField().getText(),
                        address.getPropertyTextField().getText(), "", rating.getPropertyTextField().getText(), category,
                        price.getPropertyTextField().getText(), picturePath);

                addPlaceToAppropriate();
                dispose();
                refreshTheList();
            }

        });

        return addBtn;
    }

    // EFFECTS: add the place to the appropriate list of individual or family
    private void addPlaceToAppropriate() {
        if (category.equals(AroundBC.INDIVIDUAL_CATEGORY)) {

            SuggestedPlacesWorkRoom col = AbstractPlacesWindow.aroundBC.getPlaceRoom();

            if (!col.contains(place)) {
                col.getPlacesSet().add(place);
                JOptionPane.showMessageDialog(getContentPane(), "Added Successfully!");

            } else {
                JOptionPane.showMessageDialog(getContentPane(), "Place already exists!");
            }
        } else if (category.equals(AroundBC.FAMILY_CATEGORY)) {

            SuggestedPlacesWorkRoom col = AbstractPlacesWindow.aroundBC.getFamilyPlacesWorkroom();
            if (!col.contains(place)) {
                col.getPlacesSet().add(place);
                JOptionPane.showMessageDialog(getContentPane(), "Added Successfully!");
            } else {
                JOptionPane.showMessageDialog(getContentPane(), "Place already exists!");
            }

        }
    }

    // EFFECTS: copy the selected picture to the program's directory res
    // and sets its name based on the text in the name field
    private void dealWithFileChooser() {
        if (imageApproved == JFileChooser.APPROVE_OPTION) {


            try {
                String newPictureName = name.getPropertyTextField().getText().replace(" ", "_").toLowerCase();
                String newPictureExtension = imageFileChooser.getSelectedFile().getName().substring(
                        imageFileChooser.getSelectedFile().getName().indexOf("."));
                Files.copy(Paths.get(imageFileChooser.getSelectedFile().getPath()),
                        Paths.get("/Users/loay/Desktop/CPSC210/lecture_lab/"
                                + "project_n8l6b/src/res/places pictures/"
                                + newPictureName
                                + newPictureExtension));


                picturePath = newPictureName + newPictureExtension;

            } catch (IOException ioException) {
                System.out.println("already exists picture");
            } catch (NullPointerException ignored) {
                System.out.println("ERROR UP HERE");
            }
        }

    }

    // Represents class of to each property panel field
    private class PropertyPanelClass {

        private JPanel propertyPanel;
        private JTextField propertyTextField;

        // EFFECTS: creates a panel for the specific property
        private PropertyPanelClass(String propertyName) {

            propertyPanel = new JPanel(new BorderLayout());

            propertyPanel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createEmptyBorder(12, 12, 12, 12),
                    BorderFactory.createLineBorder(new Color(204, 204, 204))));

            JLabel propertyLabel = new JLabel(propertyName);

            propertyLabel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));

            propertyLabel.setPreferredSize(new Dimension(75, 25));

            propertyTextField = new JTextField();

            propertyTextField.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 10));

            propertyPanel.add(propertyLabel, BorderLayout.WEST);
            propertyPanel.add(propertyTextField, BorderLayout.CENTER);
        }


        private JPanel getPropertyPanel() {
            return propertyPanel;
        }

        private JTextField getPropertyTextField() {
            return propertyTextField;
        }

    }


}



