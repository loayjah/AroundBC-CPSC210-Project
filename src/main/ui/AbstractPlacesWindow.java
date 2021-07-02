package ui;


// Represents a window for the JList renderer


import model.Place;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Collection;

public abstract class AbstractPlacesWindow extends JFrame {

    public static AroundBC aroundBC = new AroundBC();
    protected PlacesJListRenderer renderer;
    private JPanel mainPanel;
    private Collection<Place> placesCollection;
    private Dimension frameSize;

    // EFFECTS: constructs a new window
    public AbstractPlacesWindow(String frameTitle, Collection<Place> list) {

        frameSize = new Dimension(750, 550);

        placesCollection = list;

        renderer = new PlacesJListRenderer();
        frameSetup(frameTitle);

        mainPanel = createMainPanel();
        mainPanel.add(createTopPanel(), BorderLayout.NORTH);
        mainPanel.add(createCenterPanel(), BorderLayout.CENTER);

        add(mainPanel);

        setVisible(true);

    }

    // EFFECTS: creates the top panel
    public JPanel createTopPanel() {
        return null;
    }

    // MODIFIES: this
    // EFFECTS: sets up the window frame
    private void frameSetup(String frameTitle) {
        setTitle(frameTitle);
        setSize(frameSize);
        setResizable(false);
        setIconImage(new ImageIcon("src/res/app_logo.png").getImage());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    // EFFECTS: creates and returns the wrapper container
    private JPanel createMainPanel() {
        return new JPanel(new BorderLayout());
    }

    // EFFECTS: creates and returns the panel at the center of the page
    private JPanel createCenterPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.add(new JScrollPane(createPlacesJList(placesCollection)),
                BorderLayout.CENTER);

        return panel;
    }


    // EFFECTS: creates and returns a JList of places using the custom renderer
    private JList<Place> createPlacesJList(Collection<Place> list) {

        DefaultListModel<Place> model = new DefaultListModel<>();

        for (Place p : list) {
            model.addElement(p);
        }

        JList<Place> placesJList = new JList<>(model);

        placesJList.setCellRenderer(renderer);

        return placesJList;

    }


}
