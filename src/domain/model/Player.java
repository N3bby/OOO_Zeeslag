package domain.model;

public class Player {

	private String name;
	private Board board;
	private Game game;
	
	public Player(Game game, String name) {
		if(name.equals(null) || name.trim().isEmpty()) throw new IllegalArgumentException("Name is invalid");
		this.name = name;
		this.game = game;
		board = new Board(this);
	}
	
	public String getName() {
		return name;
	}
	
	public Board getBoard() {
		return board;
	}
	
	public Game getGame() {
		return game;
	}
	
	public int getScore() {
		return 19 - board.getHits();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
	
}
