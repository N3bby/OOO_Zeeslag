package domain.model;

public class ShipFactory {

    public ShipFactory() {

    }

    public Ship createShipFromTemplate(ShipTemplate type, int x, int y, Orientation orientation) {

        return new Ship(type, x, y, orientation);

    }

}
