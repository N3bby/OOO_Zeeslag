package domain.model.state.cell;

public class DestroyedCellState implements CellState {

	@Override
	public CellState hit() {
		throw new RuntimeException("You wanna destroy a destroyed ship? What a badass, it's never enough isn't it?");
	}

	@Override
	public boolean isVisibleByOpponent() {
		return true;
	}

}
