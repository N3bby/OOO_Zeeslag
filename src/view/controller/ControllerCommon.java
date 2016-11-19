package view.controller;

import model.ModelFacade;
import view.View;

public abstract class ControllerCommon {

    private View view;
    private ModelFacade model;

    public ControllerCommon(View view, ModelFacade model) {
        this.view = view;
        this.model = model;
    }

    protected View getView() {
        return view;
    }

    protected ModelFacade getModel() {
        return model;
    }

}
