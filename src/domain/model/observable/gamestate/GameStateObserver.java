package domain.model.observable.gamestate;

import domain.model.state.game.GameState;

public interface GameStateObserver {

    void gameStateChanged(GameState gameState);

}
