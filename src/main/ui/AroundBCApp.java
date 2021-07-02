package ui;

// Represents a class that runs the user interface

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static ui.AbstractPlacesWindow.aroundBC;


public class AroundBCApp extends JFrame {

    Dimension frameDimensions;
    JPanel mainContainer;
    JMenuBar menuBar;


    // EFFECTS: constructs a new window
    public AroundBCApp() {
        frameDimensions = new Dimension(750, 550);
        frameSetup();

        mainContainer = createMainPanel();
        mainContainerSetup();
        add(mainContainer);

        menuBar = createMenuBar();
        setJMenuBar(menuBar);


        setVisible(true);

        wishlistLoadingPopUpMsg();

    }

    public static void main(String[] args) {

        new AroundBCApp();
        //new AroundBC();

    }

    // MODIFIES: this
    // EFFECTS: display the starter dialog of loading up the data
    private void wishlistLoadingPopUpMsg() {
        String[] responses = {"Load my wishlist", "Create new wishlist"};

        ImageIcon saveIcon = new ImageIcon("src/res/load.png");

        int confirmed = JOptionPane.showOptionDialog(getContentPane(),
                "Do you want to load your wishlist from memory?",
                "Loading wishlist", JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE, saveIcon, responses, responses[0]);

        if (confirmed == 0) {
            aroundBC.loadData(false, false, true);
        }

    }

    // MODIFIES: this
    // EFFECTS: creates the menu bar
    private JMenuBar createMenuBar() {

        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenu helpMenu = new JMenu("Help");

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);

        fileMenu.add(createLoadMenuItem());

        fileMenu.add(createSaveMenuItem());

        fileMenu.add(createExitMenuItem());

        return menuBar;
    }

    // MODIFIES: this
    // EFFECTS: creates the exit menu item and sets its listener to close when clicked
    private JMenuItem createExitMenuItem() {
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeApplication();
            }
        });
        exitMenuItem.setIcon(new ImageIcon("src/res/exit.png"));
        return exitMenuItem;
    }


    // MODIFIES: this
    // EFFECTS: creates the save menu item and sets its listener to save data when clicked
    private JMenuItem createSaveMenuItem() {
        JMenuItem saveMenuItem = new JMenuItem("Save");
        saveMenuItem.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aroundBC.saveData();
            }
        });
        saveMenuItem.setIcon(new ImageIcon("src/res/save2.png"));
        return saveMenuItem;
    }

    // MODIFIES: this
    // EFFECTS: creates the load menu item and sets its listener to load data when clicked
    private JMenuItem createLoadMenuItem() {
        JMenuItem loadMenuItem = new JMenuItem("Load");
        loadMenuItem.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aroundBC.loadData(true, true, true);
            }
        });
        loadMenuItem.setIcon(new ImageIcon("src/res/load.png"));
        return loadMenuItem;
    }


    // MODIFIES: this
    // EFFECTS: creates the main container that holds up the main buttons
    private void mainContainerSetup() {

        JLabel greetingLabel1 = new JLabel("Hey " + System.getProperty("user.name") + "!");
        JLabel greetingLabel2 = new JLabel("Up to something fun?");

        JPanel greetingPanel = new JPanel();
        greetingPanel.setLayout(new BorderLayout());

        greetingPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.MAGENTA, 1),
                BorderFactory.createEmptyBorder(6, 10, 5, 10)));

        greetingLabel1.setFont(new Font(Font.MONOSPACED, Font.BOLD, 25));
        greetingPanel.add(greetingLabel1, BorderLayout.NORTH);

        greetingLabel2.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        greetingPanel.add(greetingLabel2, BorderLayout.SOUTH);

        mainContainer.add(greetingPanel);

        mainContainer.add(createSoloBtn());

        mainContainer.add(createFamilyBtn());

        mainContainer.add(createWishListBtn());

        mainContainer.add(createQuitBtn());

    }

    // MODIFIES: this
    // EFFECTS: creates the quit button and sets its listener to close when clicked
    private JButton createQuitBtn() {
        JButton quitBtn = new JButton("Quit the application");
        quitBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeApplication();
            }
        });
        quitBtn.setFocusable(false);

        return quitBtn;
    }

    // MODIFIES: this
    // EFFECTS: creates the quit button and sets its listener to view wishlist when clicked
    private JButton createWishListBtn() {
        JButton showWishListBtn = new JButton("My Wishlist");
        showWishListBtn.setFocusable(false);
        showWishListBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new WishlistWindow();
            }
        });
        return showWishListBtn;
    }

    // MODIFIES: this
    // EFFECTS: creates the family button and sets its listener to view family places when clicked
    private JButton createFamilyBtn() {
        JButton showFamilyBtn = new JButton("Family Trip");
        showFamilyBtn.setFocusable(false);
        showFamilyBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FamilyPlacesWindow();
            }
        });
        return showFamilyBtn;
    }

    // MODIFIES: this
    // EFFECTS: creates the solo button and sets its listener to view solo places when clicked
    private JButton createSoloBtn() {
        JButton showSoloBtn = new JButton("Solo Trip");
        showSoloBtn.setFocusable(false);
        showSoloBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new SoloPlacesWindow();
            }
        });

        return showSoloBtn;
    }

    // MODIFIES: this
    // EFFECTS: creates the main panel of the program (mainly set the margins)
    private JPanel createMainPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1, 0, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(70, 150, 50, 150));

        return panel;
    }

    // MODIFIES: this
    // EFFECTS: sets up the frame
    private void frameSetup() {
        setTitle("AroundBC Trip Planner");
        setSize(frameDimensions.width, frameDimensions.height);
        setResizable(false);
        setIconImage(new ImageIcon("src/res/app_logo.png").getImage());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setOnClosePopUpMsg();

    }

    // MODIFIES: this
    // EFFECTS: listener for the exit program button
    private void setOnClosePopUpMsg() {
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                closeApplication();
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: display the confirmation dialog of quitting the program
    private void closeApplication() {
        String[] responses = {"Save and Quit", "Quit without saving"};
        ImageIcon saveIcon = new ImageIcon("src/res/save_data.png");

        int confirmed = JOptionPane.showOptionDialog(getContentPane(),
                "You have unsaved data",
                "Data aren't saved!", JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE, saveIcon, responses, responses[0]);

        if (confirmed == 0) {
            aroundBC.saveData();
            System.exit(0);
        } else if (confirmed == 1) {
            System.exit(0);
        }

    }


}

