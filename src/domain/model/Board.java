package domain.model;

import java.util.ArrayList;
import java.util.List;

import domain.model.observable.board.BoardObservable;
import domain.model.observable.board.BoardObserver;
import domain.model.state.cell.CellState;
import domain.model.state.cell.DamagedCellState;
import domain.model.state.cell.DestroyedCellState;
import domain.model.state.cell.EmptyCellState;
import domain.model.state.cell.HiddenCellState;
import domain.model.state.cell.MissedCellState;
import domain.model.state.cell.ShipCellState;

public class Board implements BoardObservable {

    public static final int WIDTH_HEIGHT = 10;
    public static final int MAX_SHIP_COUNT = 5;

    private int hits = 0;
	private CellState[][] cells;
    private Player player;

	private List<BoardObserver> boardObservers = new ArrayList<>();

    private List<Ship> placedShips = new ArrayList<>();

    public Board(Player player) {
        this.player = player;
        cells = new CellState[WIDTH_HEIGHT][WIDTH_HEIGHT];
		for (int i = 0; i < WIDTH_HEIGHT; i++) {
			for (int j = 0; j < WIDTH_HEIGHT; j++) {
				cells[i][j] = new EmptyCellState();
			}
		}
	}

	public CellState[][] getCells() {
		return cells;
	}

	public CellState[][] getCellsFilteredForOpponent() {
		CellState[][] hiddenCells = new CellState[WIDTH_HEIGHT][WIDTH_HEIGHT];
		for (int y = 0; y < WIDTH_HEIGHT; y++) {
			for (int x = 0; x < WIDTH_HEIGHT; x++) {
                if(!cells[y][x].isVisibleByOpponent()) {
                    hiddenCells[y][x] = new HiddenCellState();
                } else {
                    hiddenCells[y][x] = cells[y][x];
                }

			}
		}
		return hiddenCells;
    }
	
	public void applyShip(Ship ship) {

        if(placedShips.size() >= 5) throw new RuntimeException("Can only place 5 ships");
        if(!checkShipCountValid(ship)) throw new RuntimeException("Can't place any more of this ship");
        if(!checkShipPositionValid(ship)) throw new RuntimeException("Invalid placement of ship");
        if(!checkShipDoesNotOverlap(ship)) throw new RuntimeException("Ship overlaps");

        if(ship.getOrientation() == Orientation.VERTICAL) {
			for(int i = ship.getY(); i < ship.getY() + ship.getLength() && i < WIDTH_HEIGHT; i++) {
                cells[i][ship.getX()] = new ShipCellState();
            }
		}
		else {
            for(int i = ship.getX(); i < ship.getX() + ship.getLength() && i < WIDTH_HEIGHT; i++) {
                cells[ship.getY()][i] = new ShipCellState();
            }
		}
		placedShips.add(ship);
		notifyBoardChanged();

	}
	
	public int getShipCount() {
		return placedShips.size();
	}

    private boolean checkShipDoesNotOverlap(Ship ship) {

        int xMin = ship.getX() - 1;
        int xMax = -1;
        int yMin = ship.getY() - 1;
        int yMax = -1;

        if(ship.getOrientation() == Orientation.HORIZONTAL) {

            xMax = ship.getX() + ship.getLength();
            yMax = ship.getY() + 1;

        } else if(ship.getOrientation() == Orientation.VERTICAL) {

            xMax = ship.getX() + 1;
            yMax = ship.getY() + ship.getLength();

        }

        if(xMin < 0) xMin = 0;
        if(yMin < 0) yMin = 0;
        if(xMax > 9) xMax = 9;
        if(yMax > 9) yMax = 9;

        for (int x = xMin; x <= xMax; x++) {
            for (int y = yMin; y <= yMax; y++) {
                if(!(cells[y][x] instanceof EmptyCellState)) return false;
            }
        }

        return true;

    }

    private boolean checkShipPositionValid(Ship ship) {

        if(ship.getOrientation() == Orientation.HORIZONTAL) {

            if(ship.getX() < 0 || ship.getX() > 9) return false;
            if(ship.getY() < 0 || ship.getY() > 9) return false;
            if(ship.getX() + ship.getLength() < 0 || ship.getX() + ship.getLength() > 10) return false;

        } else if (ship.getOrientation() == Orientation.VERTICAL) {

            if(ship.getX() < 0 || ship.getX() > 9) return false;
            if(ship.getY() < 0 || ship.getY() > 9) return false;
            if(ship.getY() + ship.getLength() < 0 || ship.getY() + ship.getLength() > 10) return false;

        }

        return true;

    }

    private boolean checkShipCountValid(Ship ship) {

        if(placedShips.size() >= MAX_SHIP_COUNT) return false;

        ArrayList<Ship> newPlacedShips = new ArrayList<>(placedShips);
        newPlacedShips.add(ship);

        return !ShipTemplate.containsShipsOverMaxCount(newPlacedShips);

    }

    public Player getPlayer() {
        return player;
    }
    
    public void fire(int x, int y) {
        cells[y][x] = cells[y][x].hit();
        for (Ship ship : placedShips) {
        	boolean isDestroyed = true;
        	for (Cell cell : ship.getCells()) {
            	if(!(cells[cell.getY()][cell.getX()] instanceof DamagedCellState)) {
            		isDestroyed = false;
            	}
            }
        	if(isDestroyed) {
        		for (Cell cell : ship.getCells()) {
                	cells[cell.getY()][cell.getX()] = new DestroyedCellState();
                }
        	}
        }
        if(!(cells[y][x] instanceof MissedCellState)) hits++;
        boolean notFinished = false;
        for (int x2 = 0 ; x2 < WIDTH_HEIGHT; x2++) {
        	for (int y2 = 0 ; y2 < WIDTH_HEIGHT; y2++) {
            	if(cells[y2][x2] instanceof ShipCellState) {
            		notFinished = true;
            	}
            }
        }
        notifyBoardChanged();
        if(!notFinished) {
        	getPlayer().getGame().getGameState().proceed(getPlayer().getGame());
        }
	}
    
    public int getHits() {
		return hits;
	}

	@Override
	public void addBoardObserver(BoardObserver o) {
		boardObservers.add(o);
	}

	@Override
	public void removeBoardObserver(BoardObserver o) {
		boardObservers.remove(o);
	}

	@Override
	public void notifyBoardChanged() {
        boardObservers.forEach(o -> o.boardChanged(this));
	}

}
