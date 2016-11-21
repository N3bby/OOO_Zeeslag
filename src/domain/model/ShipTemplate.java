package domain.model;

import java.util.List;
import java.util.stream.Collectors;

public enum ShipTemplate {

	AIRCRAFT_CARRIER(5, 1),
	BATTLESHIP(4, 2),
	SUBMARINE(3, 3),
	DESTROYER(3, 3),
	PATROL_BOAT(2, 4);
	
	private int nbrOfCells;
    private int maxShipCount;

    ShipTemplate(int nbrOfCells, int maxShipCount){
		this.nbrOfCells = nbrOfCells;
        this.maxShipCount = maxShipCount;
    }

    public static boolean containsShipsOverMaxCount(List<Ship> ships) {

        for (ShipTemplate template : values()) {
            //Grabs all ships of current template and puts them into a list
            List<Ship> shipsOfTemplate = ships.stream()
                    .filter(ship -> ship.getShipTemplate() == template)
                    .collect(Collectors.toList());
            if(shipsOfTemplate.size() > template.getMaxShipCount()) return true;
        }

        return false;

    }

    public int getMaxShipCount() {
        return maxShipCount;
    }

    public int getNbrOfCells(){
		return this.nbrOfCells;
	}

	public String formattedName(){

		String str = this.toString().toLowerCase().replace("_", " ");

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
