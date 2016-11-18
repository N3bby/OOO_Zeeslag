package view;

import view.controller.Controller;

import javax.swing.*;
import java.awt.*;

public class PlayerBoardPanelFactory extends View {

    public PlayerBoardPanelFactory(Controller controller) {
        super(controller);
    }

    protected JPanel getPanel(String name, Dimension buttonPanelSize) {

        JPanel panel = new JPanel(new BorderLayout(0,5));

        //Add name to main panel
        panel.add(new JLabel(name + ":"), BorderLayout.NORTH);

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
        panel.add(buttonPanel, BorderLayout.CENTER);

        //Return main panel
        return panel;

    }

}
