package domain.model;

import domain.model.state.cell.CellState;
import domain.model.state.game.GameState;

public class ModelFacade {

    private Game game;

    public ModelFacade() {
        this.game = new Game();
    }

    public void addPlayer(String name, boolean isAi) {
        game.addPlayer(name, isAi);
    }

    public Player getPlayer(String name) {
        return game.getPlayer(name);
    }
    
    public Player[] getPlayers() {
        return game.getPlayers();
    }

    public void applyShip(Ship ship, Player player) {
        player.getBoard().applyShip(ship);
    }

    public Ship getShip(ShipTemplate type, int x, int y, Orientation orientation) {
        return new ShipFactory().createShipFromTemplate(type, x, y, orientation);
    }

    public CellState[][] getCells(Player player) {
        return player.getBoard().getCells();
    }

    public Player getCurrentPlayer() {
        return game.getCurrentTurnPlayer();
    }

    public void nextTurn() {
        game.nextTurn();
    }

	public GameState getGameState() {
		return game.getGameState();
	}

    public Game getGame() {
        return game;
    }
}
