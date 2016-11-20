package view;

import view.controller.Controller;

import javax.swing.*;
import java.awt.*;

public class PlayerBoardPanel extends JPanel {

    private JLabel lblPlayerName;

    public PlayerBoardPanel(Controller controller, Dimension buttonPanelSize) {
        init(controller, buttonPanelSize);
    }

    private void init(Controller controller, Dimension buttonPanelSize) {

        this.setLayout(new BorderLayout(0,5));

        //Add name to main panel
        lblPlayerName = new JLabel("?");
        this.add(lblPlayerName, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(10, 10));
        buttonPanel.setPreferredSize(buttonPanelSize);
        buttonPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        //Add all the buttons
        for (int x = 0; x < 10; x++) {
            for(int y = 0; y < 10 ; y++) {
                JButton button = new JButton();
                button.setBackground(Color.white);
                button.setOpaque(true);
                buttonPanel.add(button, y, x); //First row, then column
            }
        }

        //Add buttonPanel to main panel
        this.add(buttonPanel, BorderLayout.CENTER);

    }

    public void setPlayerName(String name) {
        lblPlayerName.setText(name);
    }

}
