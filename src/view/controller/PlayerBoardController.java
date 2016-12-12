package view.controller;

import domain.model.*;
import domain.model.state.cell.CellState;
import domain.model.state.cell.EmptyCellState;
import domain.model.state.cell.HiddenCellState;
import domain.model.state.cell.DamagedCellState;
import domain.model.state.cell.DestroyedCellState;
import domain.model.state.cell.MissedCellState;
import domain.model.state.cell.ShipCellState;
import domain.model.state.game.GameState;
import domain.model.state.game.NewGameState;
import domain.model.state.game.StartedGameState;
import view.BoardCell;
import view.GameView;
import view.PlayerBoardPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class PlayerBoardController extends ControllerCommon implements ActionListener {

	public PlayerBoardController(GameView game, ModelFacade model) {
		super(game, model);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		GameState gameState = getModel().getGameState();

		if (gameState instanceof NewGameState) {
			processPlacement(e);
		} else if (gameState instanceof StartedGameState) {
			processAttack(e);
		}

	}

	private void processPlacement(ActionEvent e) {

		String playerName = ((BoardCell) e.getSource()).getPlayerBoardPanel().getPlayerName();

		if (!getModel().getCurrentPlayer().getName().equals(playerName)) {
			// Only do something if it is this player's turn
			return;
		}

		BoardCell boardCell = (BoardCell) e.getSource();
		GameView view = (GameView) getGameView();

		// Get information and create required objects
		ShipTemplate template = ShipTemplate.valueOf(view.getShipPlacementPanel().getSelectedShipTemplateName());
		Orientation orientation = Orientation
				.valueOf(view.getShipPlacementPanel().getSelectedDirectionName().toUpperCase());

		Ship ship = getModel().getShip(template, boardCell.getCellX(), boardCell.getCellY(), orientation);
		Player player = getModel().getPlayer(boardCell.getPlayerBoardPanel().getPlayerName());

		// Apply the ship. Exceptions are ignored (trying to place more than 5
		// ships or in an invalid location for example)
		try {
			getModel().applyShip(ship, player);
		} catch (Exception ignored) {
		}

		// Check whether to enable the start button (both players have placed
		// their ships)
		boolean canStart = Arrays.stream(getModel().getPlayers()).allMatch(p -> p.getBoard().getShipCount() >= 5);

		if (canStart) { // If we can start, enable the start button

			getGameView().getShipPlacementPanel().getStartButton().setEnabled(true);

		} else {

			// Else turn switches to other player
			if (player.getBoard().getShipCount() == Board.MAX_SHIP_COUNT) {
				getModel().nextTurn();
			}

		}

	}

	private void processAttack(ActionEvent e) {

		BoardCell boardCell = (BoardCell) e.getSource();
		String playerName = boardCell.getPlayerBoardPanel().getPlayerName();

		// Check if player is not attacking self
		if (getModel().getPlayer(playerName).equals(getModel().getCurrentPlayer())) {
			return;
		}

		Board board = getModel().getPlayer(playerName).getBoard();
		try {
			board.fire(boardCell.getCellX(), boardCell.getCellY());
			// getModel().nextTurn();
		} catch (Exception ignored) {

		}

	}

	// Merely a delegation method
	public void processBoardChanged(Board board) {

		GameView view = (GameView) getGameView();

		// Get newCells based on whether it's this player's turn or not
		CellState[][] newCells;
		if (board.getPlayer().equals(getModel().getCurrentPlayer())) {
			newCells = board.getCells();
		} else {
			newCells = board.getCellsFilteredForOpponent();
		}

		// Get player board panel and set the colors correctly
		PlayerBoardPanel playerBoardPanel = getGameView().getPlayerBoardPanel(board.getPlayer().getName());
		for (int y = 0; y < board.WIDTH_HEIGHT; y++) {
			for (int x = 0; x < board.WIDTH_HEIGHT; x++) {
				playerBoardPanel.setBoardCellColor(x, y, cellStateToColor(newCells[y][x]));
			}
		}

	}

	// Translates CellState to matching Color
	private Color cellStateToColor(CellState cellState) {
		if (cellState instanceof EmptyCellState) {
			return Color.WHITE;
		} else if (cellState instanceof ShipCellState) {
			return Color.darkGray;
		} else if (cellState instanceof HiddenCellState) {
			return Color.LIGHT_GRAY;
		} else if (cellState instanceof DamagedCellState) {
			return Color.YELLOW;
		} else if (cellState instanceof DestroyedCellState) {
			return Color.RED;
		}else if (cellState instanceof MissedCellState) {
			return Color.BLUE;
		}else {
			return Color.MAGENTA;
		}
	}

	// Adds the given PlayerBoardPanel to the observer list of the correct board
	public void addBoardPanelAsDomainBoardObserver(PlayerBoardPanel playerBoardPanel) {
		getModel().getPlayer(playerBoardPanel.getPlayerName()).getBoard().addBoardObserver(playerBoardPanel);
	}

}
