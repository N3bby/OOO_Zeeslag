package domain.model;

/**
 * 
 * @author Sander Willems
 *
 */


public class ShipFactory {
	
	public ShipFactory(){
	}
	
	public Ship createShipFromTemplate(ShipTemplate type, int x, int y ,Orientation orientation){
		
		String name = type.name();
		int length = type.getNbrOfCells();
		
		name = name.toLowerCase();
		name = name.replace('_', ' ');
		name = this.capitalize(name);
		
		
		Ship ship = new Ship(name, length, x, y, orientation);
		
		return ship;
		
		
	}
	
	
	private String capitalize(String str){
		
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
