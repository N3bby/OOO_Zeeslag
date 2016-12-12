package domain.model;

import java.util.ArrayList;
import java.util.List;

public class Ship {

    private ShipTemplate shipTemplate;
	private int x;
	private int y;
	private Orientation orientation;
	
	public Ship(ShipTemplate shipTemplate, int x, int y, Orientation orientation) {
		this.shipTemplate = shipTemplate;
        setX(x);
		setY(y);
		setOrientation(orientation);
	}

    public ShipTemplate getShipTemplate() {
        return shipTemplate;
    }

    public String getName() {
		return shipTemplate.formattedName();
	}

	public int getLength() {
		return shipTemplate.getNbrOfCells();
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		if(x < 0 || x > 10) throw new IllegalArgumentException();
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		if(y < 0 || y > 10) throw new IllegalArgumentException();
		this.y = y;
	}
	
	public List<Cell> getCells() {
		List<Cell> cells = new ArrayList<Cell>();
		for (int i = 0; i<shipTemplate.getNbrOfCells();i++) {
			if(orientation == Orientation.VERTICAL) {
				cells.add(new Cell(x, y+i));
			}
			else if(orientation == Orientation.HORIZONTAL) {
				cells.add(new Cell(x+i, y));
			}
		}
		return cells;
	}

	public Orientation getOrientation() {
		return orientation;
	}

	public void setOrientation(Orientation orientation) {
		if(orientation == null) throw new IllegalArgumentException();
		this.orientation = orientation;
	}

}
