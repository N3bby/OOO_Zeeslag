package view.controller;

import domain.model.*;
import domain.model.state.CellState;
import domain.model.state.EmptyCellState;
import domain.model.state.ShipCellState;
import view.BoardCell;
import view.GameView;
import view.PlayerBoardPanel;
import view.View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PlayerBoardController extends ControllerCommon implements ActionListener, BoardObserver {

    private PlayerBoardState state = PlayerBoardState.NONE;

    public PlayerBoardController(View view, ModelFacade model) {
        super(view, model);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (state) {
            case PLACE:
                processPlacement(e);
                break;
        }

    }

    private void processPlacement(ActionEvent e) {

        BoardCell boardCell = (BoardCell) e.getSource();
        GameView view = (GameView) getView();

        ShipTemplate template = ShipTemplate.valueOf(view.getShipPlacementPanel().getSelectedShipTemplateName());
        Orientation orientation = Orientation.valueOf(view.getShipPlacementPanel().getSelectedDirectionName().toUpperCase());

        Ship ship = getModel().getShip(template, boardCell.getCellX(), boardCell.getCellY(), orientation);
        Player player = getModel().getPlayer(boardCell.getPlayerBoardPanel().getPlayerName());

        getModel().applyShip(ship, player);

    }

    @Override
    public void boardChanged(Board board) {

        GameView view = (GameView) getView();
        List<BoardCell> boardCells = view.getPlayerBoardPanel(board.getPlayer().getName()).getBoardCells();

        CellState[][] newCells = board.getCells();
        for (BoardCell c : boardCells) {
            c.setBackground(cellStateToColor(newCells[c.getCellY()][c.getCellX()]));
        }

    }

    private Color cellStateToColor(CellState cellState) {
        if(cellState instanceof EmptyCellState) {
            return Color.WHITE;
        } else if (cellState instanceof ShipCellState) {
            return Color.darkGray;
        } else {
            return Color.MAGENTA;
        }
    }

    public void addToBoardObservable(String playerName) {
        getModel().getPlayer(playerName).getBoard().addObserver(this);
    }

    public void setState(PlayerBoardState state) {
        this.state = state;
    }

    public enum PlayerBoardState {
        NONE,
        PLACE
    }

}


