package domain.model.state.cell;

public class MissedCellState implements CellState {

	@Override
	public CellState hit() {
		throw new RuntimeException("This cell has already been hit");
	}

	@Override
	public boolean isVisibleByOpponent() {
		return true;
	}

}
