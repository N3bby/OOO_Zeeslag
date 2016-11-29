package domain.model.observable.gamestate;

public interface GameStateObservable {

    void addGameStateObserver(GameStateObserver gameStateObserver);

    void removeGameStateObsserver(GameStateObserver gameStateObserver);

    void notifyGameStateChanged();

}
