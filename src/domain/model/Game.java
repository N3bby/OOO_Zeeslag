package domain.model;

public class Game {

    private Player[] players = new Player[2];

    public void addPlayer(String player) {
        if (players[0] == null) {
            players[0] = new Player(player);
        } else if (players[1] == null) {
            players[1] = new Player(player);
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
