package domain.model.state.game;

import domain.model.Game;

public interface GameState {
	
	void proceed(Game g);

}
