package domain.model;

public class ShipFactory {

    public ShipFactory() {

    }

    public Ship createShipFromTemplate(ShipTemplate type, int x, int y, Orientation orientation) {

        String name = ShipTemplate.formattedName(type);
        int length = type.getNbrOfCells();

        return new Ship(name, length, x, y, orientation);

    }

}
