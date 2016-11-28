package domain.model;

public class Player {

	private String name;
	private Board board;
	
	public Player(String name) {
		if(name.equals(null) || name.trim().isEmpty()) throw new IllegalArgumentException("Name is invalid");
		this.name = name;
		board = new Board(this);
	}
	
	public String getName() {
		return name;
	}
	
	public Board getBoard() {
		return board;
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
