package domain.model.observable.board;

public interface BoardObservable {
	
	void addBoardObserver(BoardObserver o);
	
	void removeBoardObserver(BoardObserver o);
	
	void notifyBoardChanged();

}
