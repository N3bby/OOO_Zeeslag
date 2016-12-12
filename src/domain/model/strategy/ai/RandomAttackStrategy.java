package domain.model.strategy.ai;

import java.util.Random;

import domain.model.Board;

public class RandomAttackStrategy implements AttackStrategy {

	@Override
	public void attack(Board board) {
		
		Random random = new Random();
		boolean hit = false;
		
		while (!hit) {
			try {
				int x = random.nextInt(10);
				int y = random.nextInt(10);
				
				
				board.fire(x, y);
				hit = true;
			} catch (Exception ignored) {
				
			}
		}
		
	}

}
