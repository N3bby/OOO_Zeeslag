package view;

import view.controller.ControllerCommon;

public abstract class View {

    private ControllerCommon controller;

    protected ControllerCommon getController() {
        return controller;
    }

    public void setController(ControllerCommon controller) {
        this.controller = controller;
    }

    public abstract void start();

    public abstract void showException(Exception e);

}
