package view;

import view.controller.Controller;

public abstract class View {

    private Controller controller;

    public View(Controller controller) {
        this.controller = controller;
    }

    protected Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

}
