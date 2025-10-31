package Controller;

import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import View.*;

public class endGameMenuController {
    public Label winner;
    public Label trophy;
    boolean bringUp = false;

    public void bringUp(MouseEvent mouseEvent) {
        if (!bringUp) {
            winner.setText(String.format("%s won", GraphicController.getWinner()));
            if (GraphicController.getGameType() == 1) {
                trophy.setText("coins +50\nXP +100");
            }
            else {
                trophy.setText(String.format("coins +%d\nXP +100", 50 + GraphicController.getBet()));
            }
            bringUp = true;
        }
    }

    public void playAgain(MouseEvent mouseEvent) {
        playMenuGraphic menu = new playMenuGraphic();
        try {
            menu.start(GraphicController.getStage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void quit(MouseEvent mouseEvent) {
        mainMenuGraphic menu = new mainMenuGraphic();
        try {
            menu.start(GraphicController.getStage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
