package domain.model.state.cell;

public class EmptyCellState implements CellState {

	@Override
	public CellState hit() {
		return new MissedCellState();
	}

    @Override
    public boolean isVisibleByOpponent() {
        return false;
    }

}
