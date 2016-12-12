package view.controller;

import domain.model.*;
import domain.model.state.game.FinishedGameState;
import domain.model.state.game.GameState;
import domain.model.state.game.NewGameState;
import domain.model.state.game.StartedGameState;
import view.GameView;

import java.util.*;
import java.util.stream.Collectors;

public class GameController extends ControllerCommon {

    public final boolean PLAYS_AGAINST_AI = true;
    public final String AI_NAME = "Computer";

    public GameController(GameView gameView, ModelFacade model) {
        super(gameView, model);
    }

    public void startGameView() {

        getGameView().start();
        addGameStateObserver(getGameView());

    }

    //Returns a map where
    //  key = enum as string (e.g. 'BATTLESHIP')
    //  value = formatted string (e.g. 'Battleship (4)')
    public Map<String, String> getTemplateShipMap() {

        Map<String, String> result = new HashMap<>();

        for (ShipTemplate template : ShipTemplate.values()) {
            result.put(template.toString(), template.formattedName() + " (" + template.getNbrOfCells() + ")");
        }
        return result;

    }

    //Returns an array of all possible directions (formatted - capitalized)
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

    public void placeRandomShipsIfAi(String name) {
        Player player = getModel().getPlayer(name);
        if(player instanceof AiPlayer) ((AiPlayer) player).placeRandomShips();
    }

    public void handleGameStateChanged(GameState gameState) {

        if(gameState instanceof NewGameState) {
            processNewGameState();
        } else if (gameState instanceof StartedGameState) {
            processStartedGameState();
        } else if(gameState instanceof FinishedGameState) {
        	processFinishedGameState();
        }

    }

    private void processFinishedGameState() {
    	
    	Player p = getModel().getPlayers()[0];
    	if(getModel().getPlayers()[1].getScore() > p.getScore()) {
    		p = getModel().getPlayers()[1];
    	}
    	
		getGameView().showScoreDialog(p.getName(), p.getScore());
	}

	private void processNewGameState() {

        //Get player names and add them to the game
        List<String> playerNames = getGameView().acquirePlayerNames(PLAYS_AGAINST_AI ? 1 : 2);
        if(PLAYS_AGAINST_AI) playerNames.add(AI_NAME);
        playerNames.forEach(s -> getModel().addPlayer(s, playerNames.indexOf(s) == 1 && PLAYS_AGAINST_AI));

        getGameView().setPlayerBoardPanelNames(getPlayerNames());

        //Show player overview
        getGameView().showPlayerOverView();

        //Open the placement panel
        getGameView().openPlacementPanel();

        //If there is an AI, give the first turn to him (so it can place boats)
        if(PLAYS_AGAINST_AI) {
            getModel().nextTurn(); //AI is 2nd player so calling nextTurn should work for this
        }

    }

    private void processStartedGameState() {

        getGameView().getShipPlacementPanel().getStartButton().setEnabled(false);

    }

    public void addGameStateObserver(GameView gameView) {
        getModel().getGame().addGameStateObserver(gameView);
    }

    public List<String> getPlayerNames() {

        return Arrays.stream(getModel().getPlayers())
                .map(Player::getName)
                .collect(Collectors.toList());

    }

	public int getScore(String player) {
		return getModel().getPlayer(player).getScore();
	}
}
