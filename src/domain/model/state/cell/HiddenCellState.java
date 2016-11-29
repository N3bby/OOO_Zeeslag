package domain.model.state.cell;

public class HiddenCellState implements CellState {

	@Override
	public void hit() {

	}

    @Override
    public boolean isVisibleByOpponent() {
        return true;
    }

}
