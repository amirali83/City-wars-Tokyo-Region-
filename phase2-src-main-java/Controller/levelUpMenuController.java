package Controller;

import com.sun.tools.javac.Main;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import View.*;

public class levelUpMenuController {
    public Label trophy;

    public void mainMenu(MouseEvent mouseEvent) {
        mainMenuGraphic menu = new mainMenuGraphic();
        try {
            menu.start(GraphicController.getStage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void update(MouseEvent mouseEvent) {
        trophy.setText(String.format("coins +%d", GraphicController.getUser().getLevel() * 100));
        GraphicController.getUser().setCoins(GraphicController.getUser().getCoins() + GraphicController.getUser().getLevel() * 100);
    }
}
