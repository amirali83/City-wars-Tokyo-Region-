package Controller;

import View.ProfileMenuGraphic;
import View.loginMenuGraphic;
import example.AppController;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import Module.*;
import View.*;

public class mainController {
    public Label HP;
    public Label XP;
    public Label Coins;
    public Label Level;
    User user = GraphicController.getUser();

    public void startGame(MouseEvent mouseEvent) {
        choseGameModMenuGraphic menu = new choseGameModMenuGraphic();
        try {
            menu.start(GraphicController.getStage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void GameHistory(MouseEvent mouseEvent) {
        GameHistoryMenuGraphic menu = new GameHistoryMenuGraphic();
        try {
            menu.start(GraphicController.getStage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void Shop(MouseEvent mouseEvent) {
        shopMenuGraphic menu = new shopMenuGraphic();
        try {
            menu.start(GraphicController.getStage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void Profile(MouseEvent mouseEvent) {
        ProfileMenuGraphic menu = new ProfileMenuGraphic();
        try {
            menu.start(GraphicController.getStage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void Setting(MouseEvent mouseEvent) {
        settingMenuGraphic menu = new settingMenuGraphic();
        try {
            menu.start(GraphicController.getStage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void Logout(MouseEvent mouseEvent) {
        AppController.run("log out");
        loginMenuGraphic menu = new loginMenuGraphic();
        try {
            menu.start(GraphicController.getStage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void update(MouseEvent mouseEvent) {
        HP.setText("HP: " + Integer.toString(user.getHP()));
        XP.setText("XP: " + Integer.toString(user.getXP()));
        Coins.setText("Coins: " + Integer.toString(user.getCoins()));
        Level.setText("Level: " + Integer.toString(user.getLevel()));
        if (user.getXP() >= user.getLevel() * 1000) {
            user.setXP(user.getXP() - user.getLevel() * 1000);
            user.setLevel(user.getLevel() + 1);
            LevelUpMenuGraphic menu = new LevelUpMenuGraphic();
            try {
                menu.start(GraphicController.getStage());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
