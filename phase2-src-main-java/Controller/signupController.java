package Controller;

import example.AppController;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import Module.*;
import View.*;

import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;

public class signupController {
    public TextField username;
    public PasswordField confirmPassword;
    public TextField nickname;
    public TextField email;
    public TextField passwordr;

    public void signup(MouseEvent mouseEvent) {
        String command = String.format("user create -u %s -p %s %s -email %s -n %s",
                username.getText(), passwordr.getText(), confirmPassword.getText(), email.getText(), nickname.getText());
        System.out.println(command);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        Outputs out = AppController.run(command);
        if (out.equals(Outputs.USERNAME_SYNTAX_INVALID)) {
            alert.setHeaderText("Username syntax incorrect");
            alert.showAndWait();
            return;
        }
        if (out.equals(Outputs.USERNAME_ALREADY_EXIST)) {
            alert.setHeaderText("Username already exist");
            alert.showAndWait();
            return;
        }
        if (out.equals(Outputs.PASSWORD_NOT_STRONG_ENOUGH)) {
            alert.setHeaderText("Password not strong enough");
            alert.showAndWait();
            return;
        }
        if (out.equals(Outputs.CONFIRMATION_PASSWORD_DOESNT_MATCH)) {
            alert.setHeaderText("Confirmation password does not match");
            alert.showAndWait();
            return;
        }
        if (out.equals(Outputs.EMAIL_NOT_ACCEPTABLE)) {
            alert.setHeaderText("Email not acceptable");
            alert.showAndWait();
            return;
        }
        if (out.equals(Outputs.GONE_FOR_SECURITY_QUESTION)) {
            securityQuestionMenuGraphic menu = new securityQuestionMenuGraphic();
            try {
                menu.start(GraphicController.getStage());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private String creatRandomPassword() {
        String password = "";
        Random r = new Random();
        for (int i = 0; i < 4; i++) {
            char c = (char) ((Math.abs(r.nextInt()) % 26) + 65);
            password += Character.toString(c);
        }
        for (int i = 0; i < 2; i++) {
            char c = (char) ((Math.abs(r.nextInt()) % 26) + 97);
            password += Character.toString(c);
        }
        for (int i = 0; i < 2; i++) {
            char c = (char) ((Math.abs(r.nextInt()) % 10) + 48);
            password += Character.toString(c);
        }
        for (int i = 0; i < 2; i++) {
            char c = (char) ((Math.abs(r.nextInt()) % 4) + 35);
            password += Character.toString(c);
        }
        return password;
    }

    public void generateRandom(MouseEvent mouseEvent) {
        String r = creatRandomPassword();
        passwordr.setText(r);
    }

    public void back(MouseEvent mouseEvent) {
        loginMenuGraphic menu = new loginMenuGraphic();
        try {
            menu.start(GraphicController.getStage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
