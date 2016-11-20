package launcher;

import domain.model.Game;
import domain.model.ModelFacade;
import view.GameView;
import view.controller.Controller;

public class Launcher {

    public static void main(String[] args) {

        GameView gameView = new GameView();
        ModelFacade modelFacade = new ModelFacade();
        Controller controller = new Controller(gameView, modelFacade);
        gameView.setController(controller);
        controller.startView();

    }

}
