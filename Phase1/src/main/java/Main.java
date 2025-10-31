import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Controller.AppData;
import Module.*;
import View.*;

public class Main {
    public static ArrayList<User> users = new ArrayList<>();
    public static User currentUser = null, opponentUser = null;
    public static long time;
    private static int timesIncorect = 0;

    public static ArrayList<User> getUsers() {return users;}

    public static void main(String[] args) {
        startGame();
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        do {
            if (Inputs.SIGN_UP_RANDOM.getMatcher(command).matches())
                creatRandomPassword(Inputs.SIGN_UP_RANDOM.getMatcher(command), sc);
            else if (Inputs.SIGN_UP_NOT_RANDOM.getMatcher(command).matches())
                createUser(Inputs.SIGN_UP_NOT_RANDOM.getMatcher(command), sc);
            else if (Inputs.LOGIN.getMatcher(command).matches())
                loginUser(Inputs.LOGIN.getMatcher(command));
            else if (Inputs.FORGOT_PASSWORD.getMatcher(command).matches())
                forgotPassword(Inputs.FORGOT_PASSWORD.getMatcher(command), sc);
            else if (Inputs.LOGOUT.getMatcher(command).matches())
                logout();
            else if (Inputs.PROFILE_MENU.getMatcher(command).matches())
                ProfileMenu(sc);
            else if (Inputs.GAME_HISTORY_MENU.getMatcher(command).matches())
                gameHistoryMenu(sc);
            else if (Inputs.SHOP_MENU.getMatcher(command).matches())
                shopMenu(sc);
            else if (Inputs.ADMIN_LOGIN.getMatcher(command).matches())
                adminMenu(Inputs.ADMIN_LOGIN.getMatcher(command), sc);
            else if (Inputs.START_GAME.getMatcher(command).matches())
                play(sc);
            else if (Inputs.HELP.getMatcher(command).matches())
                help();
            else
                System.out.println("Invalid command");

            command = sc.nextLine();
        } while (!command.equals("exit"));
    }

    private static void play(Scanner sc) {
        System.out.println("Chose the mode");
        System.out.println("1- Two player game");
        System.out.println("2- gambler game");
        String mod = sc.nextLine();
        if (mod.equals("1")) {
            String playerName = sc.nextLine();
            opponentUser = new User();
            if (Inputs.LOGIN.getMatcher(playerName).matches())
                loginUser(Inputs.LOGIN.getMatcher(playerName));
            else {
                System.out.println("login failed");
                opponentUser = null;
                play(sc);
                return;
            }
            twoPlayerGame.start(currentUser, opponentUser, sc);

        } else if (mod.equals("2")) {

        } else
            System.out.println("Invalid mode");
    }

    private static void help() {
        System.out.println(Inputs.SIGN_UP_NOT_RANDOM.getString());
        System.out.println(Inputs.SIGN_UP_RANDOM.getString());
        System.out.println(Inputs.LOGIN.getString());
        System.out.println(Inputs.FORGOT_PASSWORD.getString());
        System.out.println(Inputs.LOGOUT.getString());
        System.out.println(Inputs.PROFILE_MENU.getString());
        System.out.println(Inputs.GAME_HISTORY_MENU.getString());
        System.out.println(Inputs.SHOP_MENU.getString());
        System.out.println(Inputs.ADMIN_LOGIN.getString());
        System.out.println();
    }

    private static void adminMenu(Matcher matcher, Scanner sc) {
        matcher.find();
        if (!matcher.group("pass").equals("1234")) {
            System.out.println("Wrong Pass");
            return;
        }
        AdminMenu.start(sc);
    }

    private static void startGame() {
        buildEspecialCards();
        AppData.loadData();
        for (User user : AppData.users)
            users.add(user);
        System.out.println("At any point by typing help you can get the list of the commands that you can use");
    }

    private static void buildEspecialCards() {
        AppData.allCards.add(new EspecialCard ("shield", "it breaks any card with any damage", 100, 1));
        AppData.allCards.add(new EspecialCard ("heal", "give some hp to the player (no card can break this)", 100, 1));
        AppData.allCards.add(new EspecialCard ("power buffer", "one random card will be buff (no space will be taken)", 100, 0));
        AppData.allCards.add(new EspecialCard ("hole changer", "change both players holes (block with card wont be replaced)", 100, 1));
        AppData.allCards.add(new EspecialCard ("fixer", "fix the hole (no space will be taken)", 100, 0));
        AppData.allCards.add(new EspecialCard ("reducer", "it will reduce one round of the game (no space will be taken)", 100, 0));
        AppData.allCards.add(new EspecialCard ("thief", "it will get one random card from opponent and add it to your hand (no space will be taken)", 100, 0));
        AppData.allCards.add(new EspecialCard ("changer", "it will get two random cards from opponent and reduce ones damage and anothers power (no space will be taken)", 100, 0));
        AppData.allCards.add(new EspecialCard ("copy", "it will copy one of your own cards (no space will be taken)", 100, 0));
        //emtiazi
        AppData.allCards.add(new EspecialCard ("hider", "for the next round opponent will not be able to see his/her cards (no space will be taken)", 100, 0));
    }

    private static void shopMenu(Scanner sc) {
        if (currentUser == null) {
            System.out.println("No one is logged in");
            return;
        }

        ShopMenu shopMenu = new ShopMenu(sc, currentUser);
        shopMenu.start();
    }

    private static void gameHistoryMenu(Scanner sc) {
        if (currentUser == null) {
            System.out.println("No one is logged in");
            return;
        }

        GameHistoryMenu gameHistoryMenu = new GameHistoryMenu(sc, currentUser);
        gameHistoryMenu.start();
    }

    private static void ProfileMenu(Scanner sc) {
        if (currentUser == null) {
            System.out.println("No one is logged in");
            return;
        }

        ProfileMenu profileMenu = new ProfileMenu(sc, currentUser);
        profileMenu.start();
    }

    private static void logout() {
        if (currentUser != null) {
            System.out.println("You have logged out!");
            AppData.saveDataOtherThanUsername(currentUser);
            currentUser = null;
        }
        else System.out.println("There are no users logged in!");
    }

    private static void forgotPassword(Matcher matcher, Scanner sc) {
        matcher.find();
        User tempUser = null;
        for (User user : users)
            if (user.getUsername().equals(matcher.group("username"))) {
                tempUser = user;
                break;
            }
        if (tempUser == null) {
            System.out.println("Username doesn’t exist!");
            return;
        }
        String []question = {"1-What is your father’s name ?", "2-What is your favourite color ?", "3-What was the name of your first pet?"};
        System.out.println(question[Integer.parseInt(tempUser.getPasswordRecoveryQ()) - 1]);
        System.out.println("Enter your answer to recovery question");
        String answer = sc.nextLine();
        if (!tempUser.getPasswordRecoveryA().equals(answer)) {
            System.out.println("Wrong password recovery!");
            System.out.println("try again");
            forgotPassword(matcher, sc);
        }
        else
            tempUser.setPassword(setNewPassword(sc));
    }

    private static String setNewPassword(Scanner sc) {
        System.out.println("Enter your new password");
        String answer = sc.nextLine();
        if (checkPasswordStrong(answer))
            return setNewPassword(sc);
        else {
            System.out.println("Password changed successfully!");
            return answer;
        }
    }

    private static void loginUser(Matcher matcher) {
        if (currentUser != null && opponentUser == null) {
            System.out.println("You are already logged in!");
            return;
        }

        if ((System.nanoTime() - time) / 1000000000 < timesIncorect * 5L) {
            System.out.println("Try again in " + (timesIncorect * 5L - (System.nanoTime() - time) / 1000000000) + " seconds");
            return;
        }

        matcher.find();
        User tempUser = null;
        for (User user : users)
            if (user.getUsername().equals(matcher.group("username"))) {
                tempUser = user;
                break;
            }
        if (tempUser == null) {
            System.out.println("Username doesn’t exist!");
            return;
        }
        if (!tempUser.getPassword().equals(matcher.group("password"))) {
            time = System.nanoTime();
            timesIncorect++;
            System.out.println("Password and Username don’t match!");
            return;
        }
        timesIncorect = 0;
        System.out.println("user logged in successfully!");
        if (opponentUser == null)
            currentUser = tempUser;
        else
            opponentUser = tempUser;

        if (!currentUser.getRecievedStarterPack())
            getStarterPack();
        if (opponentUser != null)
            if (!opponentUser.getRecievedStarterPack())
                getStarterPack();
    }

    private static void getStarterPack() {
        currentUser.setRecievedStarterPack(true);
        //get 20 new cards
    }

    private static void creatRandomPassword(Matcher matcher, Scanner sc) {
        if (currentUser != null) {
            System.out.println("Some is logged in please try again at another time");
            return;
        }

        matcher.find();

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
        System.out.println("Your random password: " + password);
        System.out.print("Please enter your password: ");
        String typedPassword = sc.nextLine();
        if (!typedPassword.equals(password)) {
            System.out.println("Passwords do not match");
            System.out.println("Please try again");
            creatRandomPassword(matcher, sc);
            return;
        }
        String s = "user create -u " + matcher.group("username") + " -p " + password + " " + password + " -email " +
                matcher.group("email") + " -n " + matcher.group("nickname");
        if (Inputs.SIGN_UP_NOT_RANDOM.getMatcher(s).matches())
            createUser(Inputs.SIGN_UP_NOT_RANDOM.getMatcher(s), sc);
    }

    private static void createUser(Matcher matcher, Scanner sc) {
        if (currentUser != null) {
            System.out.println("Some is logged in please try again at another time");
            return;
        }

        matcher.find();

        if (checkEmpty(matcher))
            return;
        if (checkSyntaxUsername(matcher.group("username")))
            return;
        if (checkUsernameExist(matcher.group("username")))
            return;
        if (checkPasswordStrong(matcher.group("password")))
            return;
        if (checkEmail(matcher.group("email"))) {
            System.out.println("There is an issue with your email address");
            System.out.println("Please try again");
            return;
        }

        String securityQuestionAnswer = completeSecurityQuestion(sc);

        createCaptcha(sc);

        User user = new User(matcher.group("username"), matcher.group("password"), matcher.group("nickname"),
                matcher.group("email"), securityQuestionAnswer.substring(0, securityQuestionAnswer.length() - 1),
                securityQuestionAnswer.substring(securityQuestionAnswer.length() - 1));
        users.add(user);
        AppData.users.add(user);
        AppData.addUser(user);
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

    private static boolean checkUsernameExist(String username) {
        for (User user: users)
            if (user.getUsername().equals(username)) {
                System.out.println("Username already exists");
                System.out.println("Please try again");
                return true;
            }
        return false;
    }

    private static boolean checkSyntaxUsername(String username) {
        Pattern pattern = Pattern.compile("[^a-zA-Z_]");
        Matcher matcher = pattern.matcher(username);
        if (matcher.find()) {
            System.out.println("Your username is invalid!");
            System.out.println("please try again");
            return true;
        }
        return false;
    }

    private static boolean checkEmpty(Matcher matcher) {
        if (matcher.group("username").isEmpty() || matcher.group("password").isEmpty()
                || matcher.group("email").isEmpty() || matcher.group("nickname").isEmpty()) {
            System.out.println("You have left some fields empty");
            System.out.println("please try again");
            return true;
        }
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

    private static String completeSecurityQuestion(Scanner sc) {
        System.out.println("User created successfully. Please choose a security question :");
        System.out.println("\t • 1-What is your father’s name ?");
        System.out.println("\t • 2-What is your favourite color ?");
        System.out.println("\t • 3-What was the name of your first pet?");
        String answer = sc.nextLine();
        if (!Inputs.SECURITY_QUESTION.getMatcher(answer).matches()) {
            System.out.println("Security question not found\n Please try again");
            return completeSecurityQuestion(sc);
        }
        Matcher m = Inputs.SECURITY_QUESTION.getMatcher(answer);
        m.find();
        return (m.group("answer") + m.group("questionNumber"));
    }
}