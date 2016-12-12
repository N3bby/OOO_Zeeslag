package domain.model.state.cell;

public class DamagedCellState implements CellState {

	@Override
	public CellState hit() {
		throw new RuntimeException("This cell is already hit");
	}

	@Override
	public boolean isVisibleByOpponent() {
		return true;
	}

}
