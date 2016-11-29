package domain.model.observable.turn;

import domain.model.Game;

public interface TurnObserver {

    void turnChanged(Game game);

}
