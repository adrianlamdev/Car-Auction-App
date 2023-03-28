package ui;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * UiState class (including UI) for the Car Auction application
 */
public abstract class UiState {
    protected static JFrame frame;
    protected static CardLayout cardLayout;
    protected static ArrayList<Bid> bids;
    protected JPanel panel;
    protected static JPanel cards;
    protected static User currentUser = new User();
    protected static Cars listedCars;
    protected static Users users;
    protected static Font robotoFont;
    protected static HashMap<String, String> userMap;
    protected ArrayList<JButton> toSetButtons;
    protected static volatile Login loginUI;
    protected static volatile MainMenu mainMenuUI;
    protected static volatile CreateListing createListingUI;
    protected static volatile ViewListings viewListingsUI;
    protected static volatile ViewYourListings viewYourListingsUI;
    protected static ViewBids viewBidsUI;
    protected static ViewWon viewWonUI;
    protected static JPanel createAccountPanel;
    protected static JPanel loginPanel;
    protected static JPanel mainMenuPanel;
    protected static JPanel createListingPanel;
    protected static JPanel viewListingsPanel;
    protected static JPanel viewYourListingsPanel;
    protected static JPanel viewBidsPanel;
    protected static JPanel viewWonPanel;
    protected ArrayList<JComponent> buttons;
    protected static HashMap<String, Boolean> activeClass = new HashMap<>();
    protected static ArrayList<JComponent> timers = new ArrayList<>();
    protected ArrayList<JComponent> inputFields = new ArrayList<>();

    /**
     * Constructs a new UiState
     */
    public UiState() {
        toSetButtons = new ArrayList<>();
        buttons = new ArrayList<>();
        inputFields = new ArrayList<>();
        panel = new JPanel();

    }

    /**
     * Initializes the UI state
     * @return the JPanel of the UI state
     */
    protected JPanel initWin() {
        panel.setVisible(true);
        return panel;
    }

    /**
     * Sets the attributes of the buttons
     * @param buttons
     */
    protected void setAttrButtons(ArrayList<JButton> buttons) {
        for (JButton button : buttons) {
            button.setBackground(new Color(99, 102, 241));
            button.setForeground(Color.WHITE);
            button.setFocusPainted(false);
            button.setBorder(BorderFactory.createLineBorder(new Color(30, 41, 59), 2));
            button.setForeground(Color.WHITE);
            button.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent evt) {
                    button.setBackground(new Color(30,41,59));
                    button.setBorder(BorderFactory.createLineBorder(new Color(99, 102, 241), 2));
                }

                public void mouseExited(MouseEvent evt) {
                    button.setBackground(new Color(99,102,241));
                    button.setBorder(BorderFactory.createLineBorder(new Color(30, 41, 59), 2));
                }
            });
        }
    }

    protected static void loadFont() {
        try {
            File fontFile = new File("./data/Roboto/Roboto-Regular.ttf");
            robotoFont = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(robotoFont);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected abstract JPanel loadPanel();

    protected static void updateListingPanel() {
        viewListingsPanel = viewListingsUI.initWin();
    }

    protected static void save() {
        AuctionApp.save();
    }

    protected static void load() {
        AuctionApp.load();
    }

}