package domain.model;

import domain.model.state.CellState;

public class ModelFacade {

    private Game game;

    public ModelFacade() {
        this.game = new Game();
    }

    public void addPlayer(String name) {
        game.addPlayer(name);
    }

    public Player getPlayer(String name) {
        return game.getPlayer(name);
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

}
