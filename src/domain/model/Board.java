package domain.model;

import domain.model.state.CellState;
import domain.model.state.EmptyCellState;
import domain.model.state.ShipCellState;

import java.util.ArrayList;
import java.util.List;

public class Board implements BoardObservable {

    private final int WIDTH_HEIGHT = 10;

	private CellState[][] cells;
    private Player player;

	private List<BoardObserver> boardObservers = new ArrayList<>();

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
	
	public void applyShip(Ship ship) {
        System.out.println(ship);
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
		notifyBoardChanged();
	}

    public Player getPlayer() {
        return player;
    }

    @Override
	public void addObserver(BoardObserver o) {
		boardObservers.add(o);
	}

	@Override
	public void removeObserver(BoardObserver o) {
		boardObservers.remove(o);
	}

	@Override
	public void notifyBoardChanged() {
        boardObservers.forEach(o -> o.boardChanged(this));
	}

}
