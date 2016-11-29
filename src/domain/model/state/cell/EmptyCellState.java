package domain.model.state.cell;

public class EmptyCellState implements CellState {

	@Override
	public void hit() {
		
	}

    @Override
    public boolean isVisibleByOpponent() {
        return false;
    }

}
