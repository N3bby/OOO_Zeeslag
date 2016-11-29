package view.controller;

import domain.model.ModelFacade;
import view.GameView;

public abstract class ControllerCommon {

    private GameView gameView;
    private ModelFacade model;

    public ControllerCommon(ControllerCommon controllerCommon) {
        this(controllerCommon.getGameView(), controllerCommon.getModel());
    }

    public ControllerCommon(GameView gameView, ModelFacade model) {
        this.gameView = gameView;
        this.model = model;
    }

    public GameView getGameView() {
        return gameView;
    }

    public ModelFacade getModel() {
        return model;
    }

}
