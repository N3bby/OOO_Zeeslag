package domain.model;

import domain.model.observable.gamestate.GameStateObservable;
import domain.model.observable.gamestate.GameStateObserver;
import domain.model.observable.turn.TurnObservable;
import domain.model.observable.turn.TurnObserver;
import domain.model.state.game.GameState;
import domain.model.state.game.NewGameState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game implements GameStateObservable, TurnObservable {

	private GameState gameState = new NewGameState();
    private Player[] players = new Player[2];
    private int currentTurn = 0;
    private List<GameStateObserver> gameStateObservers = new ArrayList<>();
    private List<TurnObserver> turnObservers = new ArrayList<>();

    public void addPlayer(String name, boolean isAi) {
        if (!(gameState instanceof NewGameState))
            throw new IllegalStateException("Cannot add players while not in the NamingGameState");
        Player player = new Player(this, name);
        if(isAi) {
            player = new AiPlayer(this, name);
            addTurnObserver((AiPlayer)player);
        }
        if (players[0] == null) {
            players[0] = player;
        } else if (players[1] == null) {
            players[1] = player;
        } else {
            throw new IndexOutOfBoundsException("There are aleady 2 players");
        }
    }

    public Player getPlayer(String name) {
        for (Player player : players) {
            if(player != null) {
                if(player.getName().equals(name)) return player;
            }
        }
        throw new RuntimeException("No player with name '" + name + "' found!");
    }
    
    public Player[] getPlayers() {
    	return Arrays.copyOf(players, 2);
    }

    public Player getCurrentTurnPlayer() {
        return players[currentTurn];
    }

    public void nextTurn() {
        if(currentTurn == 0) {
        	currentTurn = 1;
        }
        else if(currentTurn == 1) {
        	currentTurn = 0;
        }
        Arrays.stream(players).forEach(p -> p.getBoard().notifyBoardChanged());
        notifyTurnChanged();
    }
    
    public GameState getGameState() {
    	return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
        notifyGameStateChanged();
    }

    @Override
    public void addGameStateObserver(GameStateObserver gameStateObserver) {
        gameStateObservers.add(gameStateObserver);
        if(gameStateObservers.size() == 1) notifyGameStateChanged(); //Do this because the NewGameState event won't be caught otherwise
    }

    @Override
    public void removeGameStateObsserver(GameStateObserver gameStateObserver) {
        gameStateObservers.remove(gameStateObserver);
    }

    @Override
    public void notifyGameStateChanged() {
        gameStateObservers.forEach(o -> o.gameStateChanged(gameState));
    }

    @Override
    public void addTurnObserver(TurnObserver turnObserver) {
        turnObservers.add(turnObserver);
    }

    @Override
    public void removeTurnObserver(TurnObserver turnObserver) {
        turnObservers.remove(turnObserver);
    }

    @Override
    public void notifyTurnChanged() {
        turnObservers.forEach(o -> o.turnChanged(this));
    }

}
