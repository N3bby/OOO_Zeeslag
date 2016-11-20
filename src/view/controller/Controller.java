package view.controller;

import domain.model.BoardObserver;
import domain.model.ModelFacade;
import domain.model.Orientation;
import domain.model.ShipTemplate;
import view.PlayerBoardPanel;
import view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Controller extends ControllerCommon {

    public Controller(View view, ModelFacade model) {
        super(view, model);
    }

    public void startView() {
        getView().start();
    }

    public Map<String, String> getTemplateShipMap() {

        Map<String, String> result = new HashMap<>();

        for (ShipTemplate template : ShipTemplate.values()) {
            result.put(template.toString(), ShipTemplate.formattedName(template) + " (" + template.getNbrOfCells() + ")");
        }
        return result;

    }

    public String[] getShipDirections() {

        List<String> result = new ArrayList<>();

        for (Orientation orientation : Orientation.values()) {
            result.add(orientation.toString().toUpperCase().substring(0, 1) + orientation.toString().toLowerCase().substring(1));
        }

        return result.toArray(new String[result.size()]);

    }

    public void addPlayer(String name) {
        getModel().addPlayer(name);
    }

    public void addBoardObserver(String playerName, BoardObserver boardObserver) {
        getModel().getPlayer(playerName).getBoard().addObserver(boardObserver);
    }
}
