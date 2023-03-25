package ui;

import model.Users;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ViewWon extends UiState {

    public ViewWon() {
        super();
    }

    protected JPanel initWin() {
        super.initWin();
        return loadViewWon();
    }

    private JPanel loadViewWon() {
        panel.setLayout(null);
        panel.setBackground(new java.awt.Color(15, 23, 42));
        getJButtons().forEach(button -> panel.add(button));
        return panel;
    }

    /**
     * Gets the list of JButtons for the MainMenu state
     * @return ArrayList of JButtons
     */
    private ArrayList<JComponent> getJButtons() {
        ArrayList<JComponent> buttons = new ArrayList<>();
        Font buttonFont = new Font("Roboto", Font.PLAIN, 12);
        JButton createListing = new JButton("Create Listing");
        createListing.addActionListener(e -> {
            cardLayout.show(cards, "createListing");
        });
        buttons.add(createListing);

        JButton viewListings = new JButton("View Listings");
        viewListings.addActionListener(e -> {
            cardLayout.show(cards, "viewListings");
        });
        buttons.add(viewListings);

        JButton viewYourListings = new JButton("View Your Listings");
        viewYourListings.addActionListener(e -> {
            cardLayout.show(cards, "viewYourListings");
        });
        buttons.add(viewYourListings);

        JButton viewCurrentBids = new JButton("View Current Bids");
        viewCurrentBids.addActionListener(e -> {
            cardLayout.show(cards, "viewBids");
        });
        buttons.add(viewCurrentBids);

        JButton viewWonCars = new JButton("View Won Cars");
        viewWonCars.setBackground(new java.awt.Color(30, 41, 59));
        viewWonCars.setFocusPainted(false);
        buttons.add(viewWonCars);

        JButton loadUpToDateData = new JButton("Load Up-to-Date Data");
        buttons.add(loadUpToDateData);

        JButton saveCurrentData = new JButton("Save Current Data");
        buttons.add(saveCurrentData);

        JButton logout = new JButton("Logout");
        logout.addActionListener(e -> {
            System.out.println("Logging out...");
            cardLayout.show(cards, "loginMenu");
        });
        buttons.add(logout);

        Border border = BorderFactory.createLineBorder(new java.awt.Color(15, 23, 42), 1);
        int i = 0;
        for (JComponent button : buttons) {
            button.setFont(buttonFont.deriveFont(10f));
            button.setBounds(0, (UiState.frame.getHeight() / 2) - 225 + (i * 50), 100, 40);
            button.setBorder(border);
            if ((JButton) button != viewWonCars) {
                toSetButtons.add((JButton) button);
            } else {
                viewWonCars.setForeground(Color.WHITE);
            }
            i++;
        }
        super.setAttrButtons(toSetButtons);
        return buttons;
    }
}
