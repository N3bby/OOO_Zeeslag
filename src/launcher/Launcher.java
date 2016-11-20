package launcher;

import domain.model.Game;
import domain.model.ModelFacade;
import view.GameView;
import view.controller.Controller;

public class Launcher {

    public static void main(String[] args) {

        GameView gameView = new GameView();
        Game game = new Game();
        ModelFacade modelFacade = new ModelFacade(game);
        Controller controller = new Controller(gameView, modelFacade);
        controller.startView();

    }

}
