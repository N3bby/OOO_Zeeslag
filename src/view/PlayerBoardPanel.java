package view;

import domain.model.Board;
import domain.model.observable.board.BoardObserver;
import view.controller.GameController;
import view.controller.PlayerBoardController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerBoardPanel extends JPanel implements BoardObserver {

    private JLabel lblPlayerName;
    private List<BoardCell> boardCells = new ArrayList<>();
    private PlayerBoardController boardController;

    public PlayerBoardPanel(GameController gameController, Dimension buttonPanelSize) {
        init(gameController, buttonPanelSize);
    }

    private void init(GameController gameController, Dimension buttonPanelSize) {

        this.setLayout(new BorderLayout(0, 5));

        //Add name to main panel
        lblPlayerName = new JLabel("?");
        this.add(lblPlayerName, BorderLayout.NORTH);

        //Add buttonPanel
        JPanel buttonPanel = new JPanel(new GridLayout(10, 10));
        buttonPanel.setPreferredSize(buttonPanelSize);
        buttonPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        //Create playerBoardController and add this board as a listener in the domain
        boardController = new PlayerBoardController(gameController.getGameView(), gameController.getModel());

        //Add all the buttons
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
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

    //Sets the board's player and adds this board as an observer in the domainModel
    public void setPlayerName(String name) {
        lblPlayerName.setText(name);
        boardController.addBoardPanelAsDomainBoardObserver(this);
    }

    public String getPlayerName() {
        return lblPlayerName.getText();
    }

    //Sets the color of a cell with given coordinates
    public void setBoardCellColor(int cellX, int cellY, Color color) {
        boardCells.stream()
                .filter(c -> c.getCellX() == cellX && c.getCellY() == cellY)
                .findFirst()
                .orElse(null).setBackground(color);
    }

    public PlayerBoardController getBoardController() {
        return boardController;
    }

    @Override
    public void boardChanged(Board board) {
        boardController.processBoardChanged(board);
    }

}
