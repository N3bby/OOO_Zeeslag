package domain.model.state.cell;

public class ShipCellState implements CellState{

	@Override
	public CellState hit() {
		return new DamagedCellState();
	}

    @Override
    public boolean isVisibleByOpponent() {
        return false;
    }

}
