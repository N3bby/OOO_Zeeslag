package view.controller;

import domain.model.*;
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
            result.put(template.toString(), template.formattedName() + " (" + template.getNbrOfCells() + ")");
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

    public void addPlayer(String name, boolean isAi) {
        getModel().addPlayer(name, isAi);
    }

    public void addBoardObserver(String playerName, BoardObserver boardObserver) {
        getModel().getPlayer(playerName).getBoard().addObserver(boardObserver);
    }

    public void placeRandomShipsIfAi(String name) {
        Player player = getModel().getPlayer(name);
        if(player instanceof AiPlayer) ((AiPlayer) player).placeRandomShips();
    }

}
