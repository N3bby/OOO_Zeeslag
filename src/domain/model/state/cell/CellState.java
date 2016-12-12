package domain.model.state.cell;

public interface CellState {

	CellState hit();

    boolean isVisibleByOpponent();
	
}
