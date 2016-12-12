package domain.model.factory.strategy;

import domain.model.strategy.ai.AttackStrategy;
import domain.model.strategy.ai.RandomAttackStrategy;

public class AttackStrategyFactory {

	public AttackStrategy createAttackStrategy(String name) {

		switch (name) {

		case "random":
			return new RandomAttackStrategy();
		default:
			throw new RuntimeException("Unknown attack strategy: " + name);

		}

	}

}
