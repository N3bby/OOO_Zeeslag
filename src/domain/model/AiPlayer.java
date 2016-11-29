package domain.model;

import domain.model.observable.turn.TurnObserver;
import domain.model.state.game.GameState;
import domain.model.state.game.NewGameState;
import domain.model.state.game.StartedGameState;

import java.util.Random;

public class AiPlayer extends Player implements TurnObserver {

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
            } catch (Exception ignored) {
            }

        }

    }

    @Override
    public void turnChanged(Game game) {

        if (game.getGameState() instanceof NewGameState) {
            processNewGameStateTurnChanged(game);
        } else if (game.getGameState() instanceof StartedGameState) {
            processStartedGameStateTurnChanged(game);
        }

    }

    private void processNewGameStateTurnChanged(Game game) {

        placeRandomShips();
        game.nextTurn();

    }

    private void processStartedGameStateTurnChanged(Game game) {

    }

}
