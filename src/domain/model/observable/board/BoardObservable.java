package domain.model.observable.board;

public interface BoardObservable {
	
	void addObserver(BoardObserver o);
	
	void removeObserver(BoardObserver o);
	
	void notifyBoardChanged();

}
