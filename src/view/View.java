package view;

import view.controller.Controller;

public abstract class View {

    private Controller controller;

    protected Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public abstract void start();

}
