package view;

import domain.model.observable.gamestate.GameStateObserver;
import domain.model.state.game.GameState;
import view.controller.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class GameView implements GameStateObserver {

    private final String CONST_TITLE = "Zeeslag";

    private JFrame frame;

    private List<PlayerBoardPanel> playerBoardPanels = new ArrayList<>();

    private ShipPlacementPanel shipPlacementPanel;
    private GameController gameController;

    public void start() {

        initJFrame();

    }

    public void showException(Exception e) {

        JOptionPane.showMessageDialog(frame, e.toString());

    }

    private void initJFrame() {

        //Close existing frame if it exists
        if (frame != null)
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));

        //Set some info
        frame = new JFrame(CONST_TITLE);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        //Add player board panels
        initPlayerBoardPanels();

        //Pack and display frame
        packAndAlign();
        frame.setVisible(true);

    }

    private void initPlayerBoardPanels() {

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));

        for (int i = 0; i < 2; i++) {
            PlayerBoardPanel playerBoardPanel = new PlayerBoardPanel(getGameController(), new Dimension(300, 300));
            playerBoardPanels.add(playerBoardPanel);
            panel.add(playerBoardPanel);
        }

        frame.add(panel, BorderLayout.CENTER);

    }

    private String promptPlayerName(JFrame frame, int playerNumber) {
        String name = null;
        while (name == null || name.isEmpty()) {
            name = JOptionPane.showInputDialog(frame, "Enter your name (player " + playerNumber + ")");
            if (name == null || name.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Name cannot be empty!");
            }
        }
        return name;
    }

    private void packAndAlign() {

        frame.pack();

        //Set frame to center
        Point centerPoint = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        centerPoint.setLocation(centerPoint.getX() - frame.getWidth() / 2.0, centerPoint.getY() - frame.getHeight() / 2.0);
        frame.setLocation(centerPoint);

    }

    public PlayerBoardPanel getPlayerBoardPanel(String playerName) {

        return playerBoardPanels.stream()
                .filter(p -> p.getPlayerName().equals(playerName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No player board with player name '" + playerName + "' found!"));

    }

    public ShipPlacementPanel getShipPlacementPanel() {
        return shipPlacementPanel;
    }

    public GameController getGameController() {
        return gameController;
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    @Override
    public void gameStateChanged(GameState gameState) {
        gameController.handleGameStateChanged(gameState);
    }

    public List<String> acquirePlayerNames(int amountOfPlayers) {
        List<String> playerNames = new ArrayList<>();
        for (int i = 0; i < amountOfPlayers; i++) playerNames.add(promptPlayerName(frame, i));
        return playerNames;
    }

    public void showPlayerOverView() {

        String msg = "Players:";
        for (String playerName : gameController.getPlayerNames()) {
            msg += "\n\u2022    " + playerName;
        }
        JOptionPane.showMessageDialog(frame, msg);

    }

    public void openPlacementPanel() {

        shipPlacementPanel = new ShipPlacementPanel(getGameController());
        frame.add(shipPlacementPanel, BorderLayout.WEST);
        packAndAlign();

    }

    public void closePlacementPanel() {

        frame.remove(shipPlacementPanel);
        packAndAlign();

    }

    public void setPlayerBoardPanelNames(List<String> playerNames) {

        if (playerNames.size() != playerBoardPanels.size())
            throw new RuntimeException("Player name count doesn't match available board count (" + playerBoardPanels.size() + ")");

        for (int i = 0; i < playerBoardPanels.size(); i++) {
            playerBoardPanels.get(i).setPlayerName(playerNames.get(i));
        }

    }

	public void showScoreDialog(String name, int score) {
		
		JOptionPane.showMessageDialog(frame, "Game over!\n" + name + " won with " + score + " points..."); 
		
	}

}
