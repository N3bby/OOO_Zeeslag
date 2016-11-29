package launcher;

import domain.model.ModelFacade;
import view.GameView;
import view.controller.GameController;

public class Launcher {

    public static void main(String[] args) {

        GameView gameView = new GameView();
        ModelFacade modelFacade = new ModelFacade();
        GameController gameController = new GameController(gameView, modelFacade);
        gameView.setController(gameController);
        gameController.startView();

    }

}
