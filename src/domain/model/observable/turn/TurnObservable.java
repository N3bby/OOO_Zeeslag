package domain.model.observable.turn;

public interface TurnObservable {

    void addTurnObserver(TurnObserver turnObserver);

    void removeTurnObserver(TurnObserver turnObserver);

    void notifyTurnChanged();

}
