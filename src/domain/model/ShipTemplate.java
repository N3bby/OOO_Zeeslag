package domain.model;

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

	public static String formattedName(ShipTemplate shipTemplate){

		String str = shipTemplate.toString().toLowerCase().replace("_", " ");

		//Zet de eerste letter achter de spatie in hoofdletter (voor de tweedelige namen)
		if(str.contains(" ")){
			for (int i=0; i<str.length(); i++){
				if(str.charAt(i) == ' '){
					char chr = str.charAt(i+1);
					chr = Character.toUpperCase(chr);
					str = str.substring(0, i+1) + chr + str.substring(i+2);
					break;
				}

			}
		}

		//Zet de eerste letter in hoofdletter
		return Character.toUpperCase(str.charAt(0)) + str.substring(1);

	}

}
