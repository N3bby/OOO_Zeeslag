package domain.model;

import domain.model.state.CellState;
import domain.model.state.EmptyCellState;
import domain.model.state.ShipCellState;

//Deni Askhabov

public class Board {
	
	private CellState[][] cells;
	private BoardObserver[] boardObservers;
	
	public Board() {
		cells = new CellState[getWidth()][getHeight()];
		for (int i = 0; i < getWidth(); i++) {
			for (int j = 0; i < getHeight(); j++) {
				cells[i][j] = new EmptyCellState();
			}
		}
	}
	
	public int getWidth() {
		return 10;
	}
	
	public int getHeight() {
		return 10;
	}
	
	public CellState[][] getCells() {
		return cells;
	}
	
	public void applyShip(Ship ship) throws Exception {
		if(ship.getOrientation() == Orientation.VERTICAL) {
			for (int i = ship.getY(); i < ship.getLength(); i++) {
				if(cells[ship.getX()][i].equals(EmptyCellState.class)) {
					cells[ship.getX()][i] = new ShipCellState();
				}
				else throw new Exception("There is already something here");
			}
		}
		else {
			for (int i = ship.getX(); i < ship.getLength(); i++) {
				if(cells[ship.getY()][i].equals(EmptyCellState.class)) {
					cells[ship.getY()][i] = new ShipCellState();
				}
				else throw new Exception("There is already something here");
			}
		}
	}
	
}
