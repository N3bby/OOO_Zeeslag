package view;

import view.controller.GameController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;

public class ShipPlacementPanel extends JPanel {

    private GameController gameController;
    private PlayerBoardPanel playerBoardPanel;
    private Map<String, String> availableShips;

    private JComboBox<String> availableShipsComboBox;
    private ButtonGroup directionButtonGroup;
    private JButton startButton;

    public ShipPlacementPanel(GameController gameController, PlayerBoardPanel playerBoardPanel) {
        this.gameController = gameController;
        this.playerBoardPanel = playerBoardPanel;
        init();
    }

    //Swing sucks...
    private void init() {

        this.setLayout(new BorderLayout());
        JPanel contentPanel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(contentPanel, BoxLayout.Y_AXIS);
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPanel.setLayout(boxLayout);
        this.add(contentPanel, BorderLayout.NORTH);

        // Available ships -----------------------------------------------------------------------------------------------------

        JLabel availableShipsLabel = new JLabel("Available ships:");
        availableShipsLabel.setAlignmentX(LEFT_ALIGNMENT);
        contentPanel.add(availableShipsLabel);

        contentPanel.add(Box.createRigidArea(new Dimension(5, 5))); //Spacing

        availableShips = gameController.getTemplateShipMap();
        availableShipsComboBox =
                new JComboBox<>(availableShips.values().toArray(new String[availableShips.size()]));

        availableShipsComboBox.setAlignmentX(LEFT_ALIGNMENT);
        contentPanel.add(availableShipsComboBox);

        contentPanel.add(Box.createRigidArea(new Dimension(5, 100))); //Spacing

        // Directions ----------------------------------------------------------------------------------------------------------

        JLabel directionLabel = new JLabel("Direction:");
        directionLabel.setAlignmentX(LEFT_ALIGNMENT);
        contentPanel.add(directionLabel);

        contentPanel.add(Box.createRigidArea(new Dimension(5, 5))); //Spacing

        String[] shipDirections = gameController.getShipDirections();
        directionButtonGroup = new ButtonGroup();
        for (String direction : shipDirections) {
            JRadioButton radioButton = new JRadioButton(direction);
            if (Arrays.asList(shipDirections).indexOf(direction) == 0) radioButton.setSelected(true);
            radioButton.setAlignmentX(LEFT_ALIGNMENT);
            directionButtonGroup.add(radioButton);
            contentPanel.add(radioButton);
        }

        Dimension preferredSize = this.getPreferredSize();
        preferredSize.width = 200;
        this.setPreferredSize(preferredSize);

        contentPanel.add(Box.createRigidArea(new Dimension(5, 79)));
        
        startButton = new JButton("Start");
        startButton.setMaximumSize(new Dimension(200, 10));
        startButton.setEnabled(false);
        contentPanel.add(startButton);
    }
    
    public JButton getStartButton() {
    	return startButton;
    }

    public String getSelectedShipTemplateName() {
        for (Map.Entry<String, String> entry : availableShips.entrySet()) {
            if (entry.getValue().equals(availableShipsComboBox.getSelectedItem())) return entry.getKey();
        }
        throw new RuntimeException("Selected ship not found in internal template map");
    }

    public String getSelectedDirectionName() {
        for (Enumeration<AbstractButton> buttons = directionButtonGroup.getElements(); buttons.hasMoreElements(); ) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                return button.getText();
            }
        }
        throw new RuntimeException("No direction selected");
    }

}
