package Controller;

import View.loginMenuGraphic;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class forgotPasswordController {
    public Label question;
    public TextField answer;
    public TextField password;
    public PasswordField repeatPassword;
    boolean flag = false;

    public void createQuestion(MouseEvent mouseEvent) {
        if (!flag) {
            if (GraphicController.getUser().getPasswordRecoveryA().equals("1"))
                question.setText("• 1-What is your father’s name ?");
            else if (GraphicController.getUser().getPasswordRecoveryA().equals("2"))
                question.setText("• 2-What is your favourite color ?");
            else question.setText("• 3-What was the name of your first pet ?");
            flag = true;
        }
    }

    private static boolean checkPasswordStrong(String password) {
        boolean strong = password.length() >= 8;
        if (!strong) {
            System.out.println("Password length less than 8!");
            System.out.println("Please try again");
            return true;
        }
        Matcher m = Pattern.compile("[a-z]").matcher(password);
        strong = m.find();
        if (!strong) {
            System.out.println("Password lack some characters!");
            System.out.println("Please try again");
            return true;
        }
        m = Pattern.compile("[A-Z]").matcher(password);
        strong = m.find();
        if (!strong) {
            System.out.println("Password lack some characters!");
            System.out.println("Please try again");
            return true;
        }
        m = Pattern.compile("[^a-zA-Z0-9]").matcher(password);
        strong = m.find();
        if (!strong) {
            System.out.println("Password lack some characters!");
            System.out.println("Please try again");
            return true;
        }
        return false;
    }

    public void resetPassword(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        if (answer.getText().equals("") || answer.getText().isEmpty() || answer.getText() == null) {
            alert.setHeaderText("Enter you answer");
            alert.showAndWait();
        }
        else if (password.getText().equals("") || password.getText().isEmpty() || password.getText() == null) {
            alert.setHeaderText("Enter your password");
            alert.showAndWait();
        }
        else if (repeatPassword.getText().equals("") || repeatPassword.getText().isEmpty() || repeatPassword.getText() == null) {
            alert.setHeaderText("Repeat your password");
            alert.showAndWait();
        }
        else if (!answer.getText().equals(GraphicController.getUser().getPasswordRecoveryA())) {
            alert.setHeaderText("Your answer in incorrect");
            alert.showAndWait();
        }
        else if (checkPasswordStrong(password.getText())) {
            alert.setHeaderText("Password not strong enough");
            alert.showAndWait();
        }
        else if (!password.getText().equals(repeatPassword.getText())) {
            alert.setHeaderText("your password does not match");
            alert.showAndWait();
        }
        else {
            AppData.saveDataOtherThanUsername(GraphicController.getUser());
            GraphicController.getUser().setPassword(password.getText());
            GraphicController.setUser(null);
            loginMenuGraphic menu = new loginMenuGraphic();
            try {
                menu.start(GraphicController.getStage());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
