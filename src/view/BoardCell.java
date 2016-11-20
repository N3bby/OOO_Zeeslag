package view;

import javax.swing.*;

public class BoardCell extends JButton {

    private PlayerBoardPanel playerBoardPanel;
    private int cellX;
    private int cellY;

    public BoardCell(PlayerBoardPanel playerBoardPanel, int cellX, int cellY) {
        super();
        this.playerBoardPanel = playerBoardPanel;
        this.cellX = cellX;
        this.cellY = cellY;
    }

    public PlayerBoardPanel getPlayerBoardPanel() {
        return playerBoardPanel;
    }

    public int getCellX() {
        return cellX;
    }

    public int getCellY() {
        return cellY;
    }



}
