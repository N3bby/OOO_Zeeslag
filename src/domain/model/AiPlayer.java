package domain.model;

import java.util.Random;

public class AiPlayer extends Player {

    public AiPlayer(String name) {
        super(name);
    }

    public void placeRandomShips() {

        Random r = new Random();
        ShipFactory shipFactory = new ShipFactory();

        int succeededCount = 0;
        while (succeededCount < 5) {

            int shipTemplateIndex = r.nextInt(ShipTemplate.values().length);
            int xCoord = r.nextInt(10);
            int yCoord = r.nextInt(10);
            int orientationIndex = r.nextInt(Orientation.values().length);

            Ship ship = shipFactory.createShipFromTemplate(
                    ShipTemplate.values()[shipTemplateIndex],
                    xCoord,
                    yCoord,
                    Orientation.values()[orientationIndex]);

            try {
                getBoard().applyShip(ship);
                succeededCount++;
            } catch (Exception ignored) { }

        }

    }


}
