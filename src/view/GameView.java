package view;

import javafx.scene.canvas.GraphicsContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class GameView extends View {

    private final String CONST_TITLE = "Zeeslag";
    private final int CONST_SIZE_X = 645;
    private final int CONST_SIZE_Y = 375;

    private JFrame frame;

    private PlayerBoardPanel p1Board;
    private PlayerBoardPanel p2Board;

    @Override
    public void start() {

        initJFrame();

        //Get and set player names
        String p1Name = getPlayerName(frame);
        String p2Name = "Computer";

        p1Board.setPlayerName(p1Name);
        p2Board.setPlayerName(p2Name);

        JOptionPane.showMessageDialog(frame, "Players:\n\u2022    " + p1Name + "\n\u2022    " + p2Name);

    }

    private void initJFrame() {

        //Close existing frame if it exists
        if(frame != null)
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));

        //Set some info
        frame = new JFrame(CONST_TITLE);
        frame.setResizable(false);
        frame.setSize(CONST_SIZE_X, CONST_SIZE_Y);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout(FlowLayout.LEFT));

        //Add content
        initPlayerBoardPanels();

        //Set frame to center
        Point centerPoint = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        centerPoint.setLocation(centerPoint.getX() - frame.getWidth()/2.0, centerPoint.getY() - frame.getHeight()/2.0);
        frame.setLocation(centerPoint);

        //Display frame
        frame.setVisible(true);

    }

    private void initPlayerBoardPanels() {

        //Add content
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));

        p1Board = new PlayerBoardPanel(getController(), new Dimension(300,300));
        p2Board = new PlayerBoardPanel(getController(), new Dimension(300,300));
        panel.add(p1Board);
        panel.add(p2Board);

        frame.add(panel);

    }

    private String getPlayerName(JFrame frame) {
        String name = null;
        while(name == null || name.isEmpty()) {
            name = JOptionPane.showInputDialog(frame, "Enter your name (p1)");
            if(name == null || name.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Name cannot be empty!");
            }
        }
        return name;
    }

}
