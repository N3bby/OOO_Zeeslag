package view;

import view.controller.GameController;

public abstract class View {

    private GameController gameController;

    protected GameController getGameController() {
        return gameController;
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public abstract void start();

    public abstract void showException(Exception e);

}
