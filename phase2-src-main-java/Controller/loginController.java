package Controller;

import example.AppController;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import Module.*;
import View.*;
import javafx.scene.text.Text;

public class loginController {
    public TextField username;
    public PasswordField password;
    public Text countdown;
    String command = "";
    public boolean run = false;

    public void login(MouseEvent mouseEvent) {
        command = String.format("user login -u %s -p %s", username.getText(), password.getText());
        System.out.println(command);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        Outputs out = AppController.run(command);
        run = true;
        if (out.equals(Outputs.WRONG_PASSWORD)) {
            alert.setHeaderText("Wrong Password");
            alert.showAndWait();
            return;
        }
        if (out.equals(Outputs.USERNAME_DOESNT_EXIST)) {
            alert.setHeaderText("Username does not exist");
            alert.showAndWait();
            return;
        }
        if (out.equals(Outputs.WAIT_UNTIL_TIME_FOR_WRONG_PASSWORD_FINISH)) {
            alert.setHeaderText("Too many attempts");
            alert.setContentText(AppController.getTimeLeftToLogInAgain());
            alert.showAndWait();
            return;
        }
        if (out.equals(Outputs.CURRENT_USER_LOGGED_IN_SUCCESSFULY)) {
            if (!GraphicController.getUser().getRecievedStarterPack()) {
                GraphicController.getUser().setRecievedStarterPack(true);
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Starter Pack");
                alert.setHeaderText("Starter Pack Recieved");
                alert.showAndWait();
            }
            if (GraphicController.getOpponent() == null) {
                System.out.println("LOG IN AGAIN");
                mainMenuGraphic menu = new mainMenuGraphic();
                try {
                    menu.start(GraphicController.getStage());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            else {
                if (GraphicController.getGameType() == 1) {
                    System.out.println("GO TO GAME");
                    playMenuGraphic menu = new playMenuGraphic();
                    try {
                        menu.start(GraphicController.getStage());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    betMoneyMenuGraphic menu = new betMoneyMenuGraphic();
                    try {
                        menu.start(GraphicController.getStage());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    public void signup(MouseEvent mouseEvent) {
        System.out.println("12");
        signupMenuGraphic menu = new signupMenuGraphic();
        try {
            menu.start(GraphicController.getStage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void forgotPass(MouseEvent mouseEvent) {
        boolean flag = true;
        for (User user : AppData.users) {
            if (user.getUsername().equals(username.getText())) {
                flag = false;
                break;
            }
        }
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        if (username.getText().equals("") || username.getText().isEmpty() || username.getText() == null) {
            alert.setHeaderText("Enter your username");
            alert.showAndWait();
        }
        else if (flag) {
            alert.setHeaderText("Username doesnt exists");
            alert.showAndWait();
        }
        else {
            for (User user : AppData.users) {
                if (user.getUsername().equals(username.getText())) {
                    GraphicController.setUser(user);
                    break;
                }
            }
            forgotPasswordGraphicMenu menu = new forgotPasswordGraphicMenu();
            try {
                menu.start(GraphicController.getStage());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update(MouseEvent mouseEvent) {
        if (run) {
            Outputs out = AppController.run(command);
            System.out.println("121212");
            if (!AppController.getTimeLeftToLogInAgain().equals("Try again in 1 seconds")) {
                countdown.setText(AppController.getTimeLeftToLogInAgain());
                //command = "";
            }
            else {
                countdown.setText("");
                run = false;
            }
        }
    }
}
