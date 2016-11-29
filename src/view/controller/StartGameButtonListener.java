package view.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartGameButtonListener extends ControllerCommon implements ActionListener {

    public StartGameButtonListener(ControllerCommon controllerCommon) {
        super(controllerCommon);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        getModel().getGameState().proceed(getModel().getGame());

    }

}
