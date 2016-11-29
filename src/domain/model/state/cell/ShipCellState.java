package domain.model.state.cell;

public class ShipCellState implements CellState{

	@Override
	public void hit() {
		
	}

    @Override
    public boolean isVisibleByOpponent() {
        return false;
    }

}
