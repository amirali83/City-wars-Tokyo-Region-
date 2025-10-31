package Controller;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import View.*;
import Module.*;

public class pofrileController {
    public TextField newUsername;
    public TextField newNickname;
    public TextField newPassword;
    public TextField newEmail;
    String command;

    public void changeUsername(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        if (newUsername.getText().equals("") || newUsername.getText().isEmpty()) {
            alert.setHeaderText("Enter Username");
            alert.showAndWait();
            return;
        }
        command = "Profile change -u " + newUsername.getText();
        ProfileMenu menu = new ProfileMenu(GraphicController.getUser());
        Outputs out = menu.start(command);
        if (out.equals(Outputs.USERNAME_SYNTAX_INVALID)) {
            alert.setHeaderText("Invalid Username");
            alert.showAndWait();
        }
        else if (out.equals(Outputs.USERNAME_ALREADY_EXIST)) {
            alert.setHeaderText("Username Already Exist");
            alert.showAndWait();
        }
        else if (out.equals(Outputs.USERNAME_CHANGED_SUCCESSFULLY)) {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Username Changed");
            alert.setHeaderText("Username Changed Successfully");
            alert.showAndWait();
        }
    }

    public void changeNickname(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        if (newNickname.getText().equals("") || newNickname.getText().isEmpty()) {
            alert.setHeaderText("Enter Nickname");
            alert.showAndWait();
            return;
        }
        command = "Profile change -n " + newNickname.getText();
        ProfileMenu menu = new ProfileMenu(GraphicController.getUser());
        Outputs out = menu.start(command);
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Nickname Changed");
        alert.setHeaderText("Nickname Changed Successfully");
        alert.showAndWait();
    }

    public void changePassword(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        if (newPassword.getText().equals("") || newPassword.getText().isEmpty()) {
            alert.setHeaderText("Enter Password");
            alert.showAndWait();
            return;
        }
        command = String.format("Profile change password -o %s -n %s", GraphicController.getUser().getPassword(), newPassword.getText());
        ProfileMenu menu = new ProfileMenu(GraphicController.getUser());
        Outputs out = menu.start(command);
        if (out.equals(Outputs.NEW_PASSWORD_IS_THE_SAME)) {
            alert.setHeaderText("Password is the same");
            alert.showAndWait();
        } else if (out.equals(Outputs.PASSWORD_NOT_STRONG_ENOUGH)) {
            alert.setHeaderText("Password is strong enough");
            alert.showAndWait();
        } else if (out.equals(Outputs.PASSWORD_CHANGED_SUCCESSFULLY)) {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Password Changed");
            alert.setHeaderText("Password Changed Successfully");
            alert.showAndWait();
        }
    }

    public void changeEmail(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        if (newEmail.getText().equals("") || newEmail.getText().isEmpty()) {
            alert.setHeaderText("Enter Email");
            alert.showAndWait();
            return;
        }
        command = "Profile change -e " + newEmail.getText();
        ProfileMenu menu = new ProfileMenu(GraphicController.getUser());
        Outputs out = menu.start(command);
        if (out.equals(Outputs.EMAIL_NOT_ACCEPTABLE)) {
            alert.setHeaderText("Email Not Acceptable");
            alert.showAndWait();
        }
        else if (out.equals(Outputs.EMAIL_CHANGED_SUCCESSFULLY)) {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Email Changed");
            alert.setHeaderText("Email Changed Successfully");
            alert.showAndWait();
        }
    }

    public void back(MouseEvent mouseEvent) {
        mainMenuGraphic menu = new mainMenuGraphic();
        try {
            menu.start(GraphicController.getStage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
