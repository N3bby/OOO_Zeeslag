package domain.model;

public interface BoardObservable {
	
	void addObserver(BoardObserver o);
	
	void removeObserver(BoardObserver o);
	
	void notifyBoardChanged();

}
