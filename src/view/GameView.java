package view;

import view.controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class GameView extends View implements ExecutableView {

    private final String CONST_TITLE = "Zeeslag";
    private final int CONST_SIZE_X = 645;
    private final int CONST_SIZE_Y = 375;


    private JFrame frame;

    public GameView(Controller controller) {
        super(controller);
    }

    @Override
    public void start() {

        //Close existing frame if it exists
        if(frame != null)
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));

        //Get player name
        String p1Name = JOptionPane.showInputDialog(null, "Enter name for player 1");
        JOptionPane.showMessageDialog(null, "Player 2 is played by the computer");
        String p2Name = "Computer";

        //TODO Add players through controller function

        //Set some info
        frame = new JFrame(CONST_TITLE);
        frame.setResizable(false);
        frame.setSize(CONST_SIZE_X, CONST_SIZE_Y);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout(FlowLayout.LEFT));

        //Add content
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panel.add(new PlayerBoardPanelFactory(getController()).getPanel(p1Name, new Dimension(300,300)));
        panel.add(new PlayerBoardPanelFactory(getController()).getPanel(p2Name, new Dimension(300,300)));

        frame.add(panel);

        //Display frame
        frame.setVisible(true);

    }

}
