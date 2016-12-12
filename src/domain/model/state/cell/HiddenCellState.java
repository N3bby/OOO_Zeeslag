package domain.model.state.cell;

public class HiddenCellState implements CellState {

	@Override
	public CellState hit() {
		throw new RuntimeException("How is this even possible???");
	}

    @Override
    public boolean isVisibleByOpponent() {
        return true;
    }

}
