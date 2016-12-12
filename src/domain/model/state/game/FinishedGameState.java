package domain.model.state.game;

import domain.model.Game;

public class FinishedGameState implements GameState {

	@Override
	public void proceed(Game g) {
		throw new RuntimeException("This is the last gamestate you dummy!");
	}

}
