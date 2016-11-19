package model;


public class ShipFactory {
	
	public ShipFactory(){
	}
	
	public Ship createShipFromTemplate(ShipTemplate type, int x, int y
			,Orientation orientation){
		
		String name = type.name();
		
		Ship ship = new Ship(name, x, y, orientation);
		
		return ship;
		
		
	}

}
