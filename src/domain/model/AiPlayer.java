package domain.model;

import domain.model.factory.strategy.AttackStrategyFactory;
import domain.model.observable.turn.TurnObserver;
import domain.model.state.game.GameState;
import domain.model.state.game.NewGameState;
import domain.model.state.game.StartedGameState;
import domain.model.strategy.ai.AttackStrategy;
import domain.model.strategy.ai.RandomAttackStrategy;

import java.util.Arrays;
import java.util.Random;

public class AiPlayer extends Player implements TurnObserver {

	private AttackStrategy attackStrategy; 
	
    public AiPlayer(Game game, String name) {
        super(game, name);
        String method = GlobalProperties.getInstance().getProperty("method");
        setAttackStrategy(new AttackStrategyFactory().createAttackStrategy(method));
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
    
    public void setAttackStrategy(AttackStrategy attackStrategy) {
		this.attackStrategy = attackStrategy;
	}

    private void processNewGameStateTurnChanged(Game game) {

        placeRandomShips();
        game.nextTurn();

    }

    private void processStartedGameStateTurnChanged(Game game) {
    	Player me = this;
    	Board opponentBoard = Arrays.stream(game.getPlayers())
    		.filter(player -> player != me)
    		.findFirst()
    		.get()
    		.getBoard();
    	attackStrategy.attack(opponentBoard);
    	game.nextTurn();
    }
    
    @Override
    public void turnChanged(Game game) {

    	if(game.getCurrentTurnPlayer() == this) {
    	
	        if (game.getGameState() instanceof NewGameState) {
	            processNewGameStateTurnChanged(game);
	        } else if (game.getGameState() instanceof StartedGameState) {
	            processStartedGameStateTurnChanged(game);
	        }
	        
    	}

    }

}
