package view.controller;

import domain.model.*;
import domain.model.state.cell.CellState;
import domain.model.state.cell.EmptyCellState;
import domain.model.state.cell.HiddenCellState;
import domain.model.state.cell.ShipCellState;
import domain.model.state.game.NewGameState;
import view.BoardCell;
import view.GameView;
import view.View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;

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

        try {
            getModel().applyShip(ship, player);
        } catch (Exception ignored) { }

    }

    @Override
    public void boardChanged(Board board) {

        GameView view = (GameView) getView();
        List<BoardCell> boardCells = view.getPlayerBoardPanel(board.getPlayer().getName()).getBoardCells();
        
        CellState[][] newCells;
        if (board.getPlayer().equals(getModel().getCurrentTurnPlayer())) {
        	newCells = board.getCells();
        }
        else {
        	newCells = board.getCellsFilteredForOpponent();
        }
        for (BoardCell c : boardCells) {
            c.setBackground(cellStateToColor(newCells[c.getCellY()][c.getCellX()]));
        }
        
        if (getModel().getGameState() instanceof NewGameState) {
        	Player[] players = getModel().getPlayers();
        	boolean full = true;
        	for (Player player : players) {
				if(player.getBoard().getShipCount() < 5) full = false;
			}
        	if(full) {
        		JButton startButton = ((GameView)getView()).getShipPlacementPanel().getStartButton();
        		startButton.setEnabled(true);
        	}
        }
 
    }

    private Color cellStateToColor(CellState cellState) {
        if(cellState instanceof EmptyCellState) {
            return Color.WHITE;
        } else if (cellState instanceof ShipCellState) {
            return Color.darkGray;
        } else if (cellState instanceof HiddenCellState){
            return Color.LIGHT_GRAY;
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


