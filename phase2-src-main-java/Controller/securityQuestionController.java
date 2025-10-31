package Controller;

import example.AppController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import View.*;
import Module.*;
import javafx.scene.layout.Pane;

import java.util.Random;
import java.util.Scanner;

public class securityQuestionController {
    public CheckBox box1;
    public CheckBox box2;
    public CheckBox box3;
    public TextField answer;
    public Label captcha;
    public boolean captchaCreated = false;
    public TextField check;
    public String command;
    public String answerCaptcha;

    private String createCaptcha() {
        Random r = new Random();
        String captcha = "";
        for (int i = 0; i < 5 + Math.abs(r.nextInt()) % 3; i++)
            captcha += Integer.toString(Math.abs(r.nextInt()) % 10);
        answerCaptcha = captcha;
        String ans = "";
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < captcha.length() * 4; j++) {
                int k = j / 4;
                char t = NumbersInCaptcha.numbers[captcha.charAt(k) - '0'][i][j % 4];
                if (t == '8')
                    ans += "8";
                else
                    ans += "-";
            }
            ans += "\n";
        }
        System.out.println(ans);
        return ans.substring(0, ans.length() - 1);
    }

    public void signup(MouseEvent mouseEvent) {
        String securityQuestion = "";
        if (box1.isSelected()) securityQuestion = "1";
        if (box2.isSelected()) securityQuestion = "2";
        if (box3.isSelected()) securityQuestion = "3";
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        if (securityQuestion.equals("")) {
            alert.setHeaderText("choose your question");
            alert.showAndWait();
        }
        else if (answer.getText().equals("") || answer.getText().isEmpty() || answer.getText() == null) {
            alert.setHeaderText("choose your answer");
            alert.showAndWait();
        }
        else if (!answerCaptcha.equals(check.getText())) {
            alert.setHeaderText("captcha didn't match");
            alert.showAndWait();
            captchaCreated = false;
            updateLable();
        }
        else {
            command = String.format("question pick -q %s -a %s -c %s", securityQuestion, answer.getText(), answer.getText());
            if (AppController.run(command).equals(Outputs.USER_CREATED_SUCCESSFULLY)) {
                loginMenuGraphic menu = new loginMenuGraphic();
                try {
                    menu.start(GraphicController.getStage());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void updateLable() {
        if (!captchaCreated) {
            captcha.setText(createCaptcha());
            captchaCreated = true;
        }
    }

    public void updateLableA(MouseEvent mouseEvent) {
        captchaCreated = false;
        updateLable();
    }
}
