package domain.model;

import domain.model.state.game.GameState;
import domain.model.state.game.NamingGameState;

import java.util.Arrays;

public class Game {

	private GameState gameState;
    private Player[] players = new Player[2];
    private int currentTurn = 0;

    public void addPlayer(String name, boolean isAi) {
        if (!(gameState instanceof NamingGameState))
            throw new IllegalStateException("Cannot add players while not in the NamingGameState");
        Player player = new Player(name);
        if(isAi) player = new AiPlayer(name);
        if (players[0] == null) {
            players[0] = player;
        } else if (players[1] == null) {
            players[1] = player;
            gameState.proceed(this); //Go to the next state (Ship placement)
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
    }

}
