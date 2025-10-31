package Controller;

import javafx.scene.input.MouseEvent;
import View.*;

public class chooseModController {
    public void gamblersMode(MouseEvent mouseEvent) {
        GraphicController.setGameType(2);
        secondPlayerLoginGraphic menu = new secondPlayerLoginGraphic();
        try {
            menu.start(GraphicController.getStage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void dualMode(MouseEvent mouseEvent) {
        GraphicController.setGameType(1);
        secondPlayerLoginGraphic menu = new secondPlayerLoginGraphic();
        try {
            menu.start(GraphicController.getStage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
