package domain.model.observable.board;

import domain.model.Board;

public interface BoardObserver {
	
	void boardChanged(Board board);

}
