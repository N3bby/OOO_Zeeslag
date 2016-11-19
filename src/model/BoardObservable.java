package model;

/**
 * 
 * @author Sander Willems
 *
 */


public interface BoardObservable {
	
	public void addObserver(BoardObserver o);
	
	public void removeObserver(BoardObserver o);
	
	public void notifyBoardChanged();

}
