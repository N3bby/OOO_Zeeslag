package view.controller;

import domain.model.ModelFacade;
import view.View;

public abstract class ControllerCommon {

    private View view;
    private ModelFacade model;

    public ControllerCommon(View view, ModelFacade model) {
        this.view = view;
        this.model = model;
    }

    public View getView() {
        return view;
    }

    public ModelFacade getModel() {
        return model;
    }

}
