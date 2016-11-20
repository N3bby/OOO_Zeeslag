package model;

/**
 * 
 * @author Sander Willems
 *
 */

public class ModelFacade {

    private Game game;

    public ModelFacade(Game game) {
        this.setGame(game);
    }

    public Game getGame() {
        return this.game;
    }

    public void setGame(Game game) {
        this.game = game;
    }


    public void addPlayer(String name) {
        this.getGame().addPlayer(name);
    }


    public Player[] getPlayers() {
        return this.getGame().getPlayers();
    }

    public void applyShip(Ship ship, Player player) {
        player.getBoard.applyShip(ship);
    }

    public Ship getShip(ShipTemplate type, int x, int y, Orientation orientation) {
        return new ShipFactory().createShipFromTemplate(type, x, y, orientation);
    }

    public CellState[][] getCells(Player player) {
        return player.getBoard().getCells();
    }

}
