package View;

import Controller.AppData;

import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import Module.*;

public class ProfileMenu {
    private User currentUser;
    private Scanner sc;

    public ProfileMenu(Scanner sc, User currentUser) {
        this.sc = sc;
        this.currentUser = currentUser;
    }

    public void start() {
        System.out.println("Profile Menu");
        String command = sc.nextLine();
        do {
            if (Inputs.SHOW_INFORMATION.getMatcher(command).matches())
                showAllInformation();
            else if (Inputs.CHANGE_USERNAME.getMatcher(command).matches())
                changeUsername(Inputs.CHANGE_USERNAME.getMatcher(command));
            else if (Inputs.CHANGE_NICKNAME.getMatcher(command).matches())
                changeNickname(Inputs.CHANGE_NICKNAME.getMatcher(command));
            else if (Inputs.CHANGE_PASSWORD.getMatcher(command).matches())
                changePassword(Inputs.CHANGE_PASSWORD.getMatcher(command), sc);
            else if (Inputs.CHANGE_EMAIL.getMatcher(command).matches())
                changeEmail(Inputs.CHANGE_EMAIL.getMatcher(command));
            else if (Inputs.HELP.getMatcher(command).matches())
                help();
            else
                System.out.println("Invalid command");

            command = sc.nextLine();
        } while (!command.equals("back"));
        System.out.println("back");
    }

    private void help() {
        System.out.println(Inputs.SHOW_INFORMATION.getString());
        System.out.println(Inputs.CHANGE_USERNAME.getString());
        System.out.println(Inputs.CHANGE_NICKNAME.getString());
        System.out.println(Inputs.CHANGE_PASSWORD.getString());
        System.out.println(Inputs.CHANGE_EMAIL.getString());
        System.out.println();
    }

    private void changeEmail(Matcher matcher) {
        matcher.find();
        if (checkEmail(matcher.group("email"))) {
            System.out.println("There is an issue with your email address");
            System.out.println("Please try again");
            return;
        }

        currentUser.setEmail(matcher.group("email"));
        System.out.println("Email changed successfully");
        AppData.saveDataOtherThanUsername(currentUser);
    }

    private static boolean checkEmail(String email) {
        String em = "", domain = "";
        boolean found = false;
        for (int i = 0; i < email.length() - 4; i++) {
            if (email.charAt(i) == '@') {
                if (found) return true;
                found = true;
            }
            if (found) domain += email.charAt(i);
            else em += email.charAt(i);
        }
        if (em.isEmpty() || domain.isEmpty() || !email.endsWith(".com")) return true;
        return false;
    }

    private static void createCaptcha(Scanner sc) {
        Random r = new Random();
        String captcha = "";
        for (int i = 0; i < 5 + Math.abs(r.nextInt()) % 3; i++)
            captcha += Integer.toString(Math.abs(r.nextInt()) % 10);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < captcha.length() * 4; j++) {
                int k = j / 4;
                char t = NumbersInCaptcha.numbers[captcha.charAt(k) - '0'][i][j % 4];
                if (t == '8')
                    System.out.print(NumbersInCaptcha.numbers[captcha.charAt(k) - '0'][i][j % 4]);
                else
                    System.out.print(" ");
            }
            System.out.println();
        }
        String captchaAnswer = sc.nextLine();
        if (!captcha.equals(captchaAnswer)) {
            System.out.println("Your captcha doesn't match");
            System.out.println("Please try again");
            createCaptcha(sc);
        }
        else System.out.println("correct captcha");
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

    private void changePassword(Matcher matcher, Scanner sc) {
        matcher.find();
        if (!currentUser.getPassword().equals(matcher.group("oldPassword"))) {
            System.out.println("Current password is incorrect");
            return;
        }
        if (matcher.group("newPassword").equals(currentUser.getPassword())) {
            System.out.println("Please enter a new password");
            return;
        }
        if (checkPasswordStrong(matcher.group("newPassword")))
            return;

        createCaptcha(sc);

        currentUser.setPassword(matcher.group("newPassword"));
        System.out.println("Password changed successfully");
        AppData.saveDataOtherThanUsername(currentUser);
    }

    private void changeNickname(Matcher matcher) {
        matcher.find();
        if (checkSyntaxUsername(matcher.group("nickname")))
            return;
        if (checkNicknameExist(matcher.group("nickname")))
            return;
        System.out.println("username changed successfully");
        currentUser.setNickname(matcher.group("nickname"));
        AppData.saveDataOtherThanUsername(currentUser);
    }

    private boolean checkNicknameExist(String nickname) {
        for (User user: AppData.users) {
            if (user.getNickname().equals(nickname)) {
                System.out.println("Nickname already exists");
                System.out.println("Please try again");
                return true;
            }
        }
        return false;
    }

    private static boolean checkSyntaxUsername(String username) {
        Pattern pattern = Pattern.compile("[^a-zA-Z_]");
        Matcher matcher = pattern.matcher(username);
        if (matcher.find()) {
            System.out.println("Your input is invalid!");
            System.out.println("please try again");
            return true;
        }
        return false;
    }

    private static boolean checkUsernameExist(String username) {
        for (User user: AppData.users) {
            if (user.getUsername().equals(username)) {
                System.out.println("Username already exists");
                System.out.println("Please try again");
                return true;
            }
        }
        return false;
    }

    private void changeUsername(Matcher matcher) {
        matcher.find();
        if (checkSyntaxUsername(matcher.group("username")))
            return;
        if (checkUsernameExist(matcher.group("username")))
            return;
        System.out.println("username changed successfully");
        currentUser.setUsername(matcher.group("username"));
        AppData.saveDataUsername(currentUser);
    }

    private void showAllInformation() {
        String []question = {"1-What is your father’s name ?", "2-What is your favourite color ?", "3-What was the name of your first pet?"};
        System.out.println("username: " + currentUser.getUsername());
        System.out.println("nickname: " + currentUser.getNickname());
        System.out.println("email: " + currentUser.getEmail());
        //System.out.println("password: " + currentUser.getPassword());
        //System.out.println("Security question: " + question[Integer.parseInt(currentUser.getPasswordRecoveryQ()) - 1]);
        //System.out.println("Security answer: " + currentUser.getPasswordRecoveryA());
        System.out.println("Level: " + currentUser.getLevel());
        System.out.println("HP: " + currentUser.getHP());
        System.out.println("XP: " + currentUser.getXP());
        System.out.println("Coins: " + currentUser.getCoins());
    }
}
