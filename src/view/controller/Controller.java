package view.controller;

import domain.model.ModelFacade;
import view.View;

public class Controller extends ControllerCommon {

    public Controller(View view, ModelFacade model) {
        super(view, model);
    }

    public void startView() {
        getView().start();
    }

}
