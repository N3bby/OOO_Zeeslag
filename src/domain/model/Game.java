package domain.model;

import domain.model.state.game.GameState;

public class Game {
	
	private GameState gameState;
    private Player[] players = new Player[2];

    public void addPlayer(String name, boolean isAi) {
        Player player = new Player(name);
        if(isAi) player = new AiPlayer(name);
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

}
