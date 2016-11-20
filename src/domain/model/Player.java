package domain.model;

public class Player {

	private String name;
	private Board board;
	
	public Player(String name) {
		if(name.equals(null) || name.trim().isEmpty()) throw new IllegalArgumentException("Name is invalid");
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public Board getBoard() {
		return board;
	}
	
}
