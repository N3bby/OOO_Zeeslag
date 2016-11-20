package domain.model;

/**
 * 
 * @author Sander Willems
 *
 */


public interface BoardObservable {
	
	void addObserver(BoardObserver o);
	
	void removeObserver(BoardObserver o);
	
	void notifyBoardChanged();

}
