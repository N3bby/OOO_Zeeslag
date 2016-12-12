package domain.model.state.game;

import domain.model.Game;

public class StartedGameState implements GameState {

	@Override
	public void proceed(Game g) {
		
		g.setGameState(new FinishedGameState());
		
	}

}
