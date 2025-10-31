package Controller;

import View.playMenuGraphic;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import View.*;

public class betMoneyMenuController {
    public TextField bet;

    public void next(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        if (bet.getText().isEmpty()) {
            alert.setContentText("Please enter a bet");
            alert.showAndWait();
            return;
        }
        if (Integer.parseInt(bet.getText()) > GraphicController.getUser().getCoins() || Integer.parseInt(bet.getText()) > GraphicController.getOpponent().getCoins()) {
            alert.setContentText("You don't have enough money to bet");
            alert.showAndWait();
            mainMenuGraphic menu = new mainMenuGraphic();
            try {
                menu.start(GraphicController.getStage());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        GraphicController.setBet(Integer.parseInt(bet.getText()));
        playMenuGraphic menu = new playMenuGraphic();
        try {
            menu.start(GraphicController.getStage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
