package domain.model;

/**
 * 
 * @author Sander Willems
 *
 */

public enum ShipTemplate {
	AIRCRAFT_CARRIER(5),
	BATTLESHIP(4),
	SUBMARINE(3),
	DESTROYER(3),
	PATROL_BOAT(2);
	
	private int nbrOfCells;
	
	private ShipTemplate(int nbrOfCells){
		this.nbrOfCells = nbrOfCells;
	}
	
	public int getNbrOfCells(){
		return this.nbrOfCells;
	}

}
