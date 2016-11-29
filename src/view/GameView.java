package view;

import view.controller.PlayerBoardController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class GameView extends View {

    private final String CONST_TITLE = "Zeeslag";

    private JFrame frame;

    private PlayerBoardPanel p1Board;
    private PlayerBoardPanel p2Board;

    private ShipPlacementPanel shipPlacementPanel;

    @Override
    public void start() {

        initJFrame();

        //Get and set player names
        String p1Name = promptPlayerName(frame);
        String p2Name = "Computer";

        //Add players to game
        getGameController().addPlayer(p1Name, false);
        getGameController().addPlayer(p2Name, true);

        //Set board names
        p1Board.setPlayerName(p1Name);
        p2Board.setPlayerName(p2Name);

        JOptionPane.showMessageDialog(frame, "Players:\n\u2022    " + p1Name + "\n\u2022    " + p2Name);

        //Add ship placement panel
        initShipPlacementPanel(p1Board);

        //Set it so p1 can place stuff
        p1Board.getBoardController().setState(PlayerBoardController.PlayerBoardState.PLACE);

        getGameController().placeRandomShipsIfAi(p2Name);

    }

    @Override
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

        //Add content
        initPlayerBoardPanels();
        packAndAlign();

        //Display frame
        frame.setVisible(true);

    }

    private void initPlayerBoardPanels() {

        //Add content
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));

        p1Board = new PlayerBoardPanel(getGameController(), new Dimension(300, 300));
        p2Board = new PlayerBoardPanel(getGameController(), new Dimension(300, 300));
        panel.add(p1Board);
        panel.add(p2Board);

        frame.add(panel, BorderLayout.CENTER);

    }

    private void initShipPlacementPanel(PlayerBoardPanel playerBoardPanel) {

        shipPlacementPanel = new ShipPlacementPanel(getGameController(), playerBoardPanel);
        frame.add(shipPlacementPanel, BorderLayout.WEST);
        packAndAlign();

    }

    private String promptPlayerName(JFrame frame) {
        String name = null;
        while (name == null || name.isEmpty()) {
            name = JOptionPane.showInputDialog(frame, "Enter your name (p1)");
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
        if(p1Board.getPlayerName().equals(playerName)) return p1Board;
        if(p2Board.getPlayerName().equals(playerName)) return p2Board;
        throw new RuntimeException("No player board with name '" + playerName + "' found!");
    }

    public ShipPlacementPanel getShipPlacementPanel() {
        return shipPlacementPanel;
    }

}
