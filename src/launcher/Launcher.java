package launcher;

import view.GameView;
import view.controller.Controller;

public class Launcher {

    public static void main(String[] args) {

        new GameView(new Controller() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        }).start();

    }

}
