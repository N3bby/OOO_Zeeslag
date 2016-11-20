package view;

import view.controller.Controller;
import view.controller.PlayerBoardController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerBoardPanel extends JPanel {

    private JLabel lblPlayerName;
    private List<BoardCell> boardCells = new ArrayList<>();
    private PlayerBoardController boardController;

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

        boardController = new PlayerBoardController(controller.getView(), controller.getModel());

        //Add all the buttons
        for (int y = 0; y < 10; y++) {
            for(int x = 0; x < 10 ; x++) {
                BoardCell boardCell = new BoardCell(this, x, y);
                boardCell.setBackground(Color.white);
                boardCell.setOpaque(true);
                boardCell.addActionListener(boardController);
                buttonPanel.add(boardCell);
                boardCells.add(boardCell);
            }
        }

        //Add buttonPanel to main panel
        this.add(buttonPanel, BorderLayout.CENTER);

    }

    public void setPlayerName(String name) {
        lblPlayerName.setText(name);
        boardController.addToBoardObservable(name);
    }

    public String getPlayerName() {
        return lblPlayerName.getText();
    }

    public List<BoardCell> getBoardCells() {
        return boardCells;
    }

    public PlayerBoardController getBoardController() {
        return boardController;
    }
}
