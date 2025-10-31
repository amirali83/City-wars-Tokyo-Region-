package example;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Controller.*;
import Module.*;
import View.*;

public class AppController {
    public static ArrayList<User> users = new ArrayList<>();
    public static User currentUser = null, opponentUser = null;
    public static long time;
    private static int timesIncorect = 0;
    private static String timeLeftToLogInAgain = "";
    private static String username, password, nickname, email;

    public static ArrayList<User> getUsers() {return users;}

    public static Outputs run(String command) {
        Scanner sc = new Scanner(System.in);
        if (Inputs.SIGN_UP_NOT_RANDOM.getMatcher(command).matches())
            return createUser(Inputs.SIGN_UP_NOT_RANDOM.getMatcher(command), sc);
        else if (Inputs.LOGIN.getMatcher(command).matches())
            return loginUser(Inputs.LOGIN.getMatcher(command));
        else if (Inputs.SECURITY_QUESTION.getMatcher(command).matches())
            return completeSecurityQuestion(Inputs.SECURITY_QUESTION.getMatcher(command));
        else if (Inputs.FORGOT_PASSWORD.getMatcher(command).matches())
            forgotPassword(Inputs.FORGOT_PASSWORD.getMatcher(command), sc);
        else if (Inputs.LOGOUT.getMatcher(command).matches())
            logout();
//        else if (Inputs.PROFILE_MENU.getMatcher(command).matches())
//            ProfileMenu(sc);
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
        return null;
    }

    private static boolean loginSecondPlayer(Scanner sc) {
        System.out.println("login your opponent");
        String playerName = sc.nextLine();
        opponentUser = new User();
        if (Inputs.LOGIN.getMatcher(playerName).matches()) {
            loginUser(Inputs.LOGIN.getMatcher(playerName));
            return true;
        }
        else {
            System.out.println("login failed");
            opponentUser = null;
            play(sc);
            return false;
        }
    }

    private static void play(Scanner sc) {
        int result;
        if (currentUser == null) {
            System.out.println("you are not logged in!");
            return;
        }
        System.out.println("Chose the mode");
        System.out.println("1- Two player game");
        System.out.println("2- gambler game");
        String mod = sc.nextLine();
        opponentUser = new User();
        if (mod.equals("1")) {
            if (!loginSecondPlayer(sc))
                return;
            result = twoPlayerGame.start(currentUser, opponentUser);
        } else if (mod.equals("2")) {
            if (!loginSecondPlayer(sc))
                return;
            System.out.print("Place your bets: ");
            int bets = Integer.parseInt(sc.nextLine());
            if (currentUser.getCoins() < bets || opponentUser.getCoins() < bets) {
                System.out.println("you do not have enough money");
                play(sc);
                return;
            }
            result = twoPlayerGame.start(currentUser, opponentUser);
            if (result == 0) {
                currentUser.setCoins(currentUser.getCoins() + bets);
                opponentUser.setCoins(opponentUser.getCoins() - bets);
            }
            else if (result == 1) {
                opponentUser.setCoins(opponentUser.getCoins() - bets);
                currentUser.setCoins(currentUser.getCoins() + bets);
            }
        } else {
            System.out.println("Invalid mode");
            play(sc);
            return;
        }
        AppData.saveDataOtherThanUsername(opponentUser);
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        String time = localDate.getYear() + "%" + localDate.getMonthValue() + "%" + localDate.getDayOfMonth() + "%"
                + localTime.getHour() + "%" + localTime.getMinute() + "%" + localTime.getSecond();
        if (result == 1) {
            currentUser.getGameHistory().add(time + ":1:" + opponentUser.getUsername() + ":" + opponentUser.getLevel() + ":" + "10");
            opponentUser.getGameHistory().add(time + ":0:" + currentUser.getUsername() + ":" + currentUser.getLevel() + ":" + "10");
        } else if (result == 2) {
            currentUser.getGameHistory().add(time + ":0:" + opponentUser.getUsername() + ":" + opponentUser.getLevel() + ":" + "10");
            opponentUser.getGameHistory().add(time + ":1:" + currentUser.getUsername() + ":" + currentUser.getLevel() + ":" + "10");
        } else {
            currentUser.getGameHistory().add(time + ":-1:" + opponentUser.getUsername() + ":" + opponentUser.getLevel() + ":" + "10");
            opponentUser.getGameHistory().add(time + ":-1:" + currentUser.getUsername() + ":" + currentUser.getLevel() + ":" + "10");
        }
        opponentUser = null;
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
        System.out.println(Inputs.START_GAME.getString());
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

    public static void startGame() {
        buildEspecialCards();
        AppData.loadData();
        for (User user : AppData.users)
            users.add(user);
        //System.out.println("At any point by typing help you can get the list of the commands that you can use");
    }

    private static void buildEspecialCards() {
        AppData.allCards.add(new EspecialCard ("shield", "it breaks any card with any damage", 100, 1, "/CardImage/drainer.png"));
        AppData.allCards.add(new EspecialCard ("heal", "give some hp to the player (no card can break this)", 100, 1, "/CardImage/thermo.png"));
        AppData.allCards.add(new EspecialCard ("power buffer", "one random card will be buff (no space will be taken)", 100, 0, "/CardImage/ghost1.png"));
        AppData.allCards.add(new EspecialCard ("hole changer", "change both players holes (block with card wont be replaced)", 100, 0, "/CardImage/ghost1.png"));
        AppData.allCards.add(new EspecialCard ("fixer", "fix the hole (no space will be taken)", 100, 0, "/CardImage/ghost1.png"));
        AppData.allCards.add(new EspecialCard ("reducer", "it will reduce one round of the game (no space will be taken)", 100, 0, "/CardImage/ghost1.png"));
        AppData.allCards.add(new EspecialCard ("thief", "it will get one random card from opponent and add it to your hand (no space will be taken)", 100, 0, "/CardImage/shock.png"));
        AppData.allCards.add(new EspecialCard ("changer", "it will get two random cards from opponent and reduce ones damage and anothers power (no space will be taken)", 100, 0, "/CardImage/ghost1.png"));
        AppData.allCards.add(new EspecialCard ("copy", "it will copy one of your own cards (no space will be taken)", 100, 0, "/CardImage/ghost1.png"));
        //emtiazi
        AppData.allCards.add(new EspecialCard ("hider", "for the next round opponent will not be able to see his/her cards (no space will be taken)", 100, 0, "/CardImage/ghost1.png"));
    }

    //"/CardImage/acid evaporation.png"


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

//    private static void ProfileMenu(Scanner sc) {
//        if (currentUser == null) {
//            System.out.println("No one is logged in");
//            return;
//        }
//
//        ProfileMenu profileMenu = new ProfileMenu(currentUser);
//        profileMenu.start();
//    }

    private static void logout() {
        if (currentUser != null) {
            System.out.println("You have logged out!");
            AppData.saveDataOtherThanUsername(currentUser);
            currentUser = null;
            GraphicController.setUser(null);
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

    public static String getTimeLeftToLogInAgain() {return timeLeftToLogInAgain;}

    private static Outputs loginUser(Matcher matcher) {
//        if (currentUser != null && opponentUser == null) {
//            System.out.println("You are already logged in!");
//            return;
//        }

        if ((System.nanoTime() - time) / 1000000000 < timesIncorect * 5L) {
            timeLeftToLogInAgain = "Try again in " + (timesIncorect * 5L - (System.nanoTime() - time) / 1000000000) + " seconds";
            System.out.println("Try again in " + (timesIncorect * 5L - (System.nanoTime() - time) / 1000000000) + " seconds");
            return Outputs.WAIT_UNTIL_TIME_FOR_WRONG_PASSWORD_FINISH;
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
            return Outputs.USERNAME_DOESNT_EXIST;
        }
        if (!tempUser.getPassword().equals(matcher.group("password"))) {
            time = System.nanoTime();
            timesIncorect++;
            System.out.println("Password and Username don’t match!");
            return Outputs.WRONG_PASSWORD;
        }
        timesIncorect = 0;
        if (GraphicController.getUser() == null) {
            currentUser = tempUser;
            GraphicController.setUser(currentUser);
        }
        else {
            opponentUser = tempUser;
            GraphicController.setOpponent(opponentUser);
        }
        System.out.println("user logged in successfully!");

        if (!GraphicController.getUser().getRecievedStarterPack())
            getStarterPack(GraphicController.getUser());
        if (GraphicController.getOpponent() != null)
            if (!GraphicController.getOpponent().getRecievedStarterPack())
                getStarterPack(GraphicController.getOpponent());
        return Outputs.CURRENT_USER_LOGGED_IN_SUCCESSFULY;
    }

    private static void getStarterPack(User user) {
        System.out.println("starter pack received successfully!");
        Random rand = new Random();
        for (int i = 0; i < 20; i++)
            user.getCards().add(AppData.allCards.get((Math.abs(rand.nextInt())) % (AppData.allCards.size())));

    }

    private static Outputs createUser(Matcher matcher, Scanner sc) {
//        if (currentUser != null) {
//            System.out.println("Some is logged in please try again at another time");
//            return;
//        }

        matcher.find();

//        if (checkEmpty(matcher))
//            return;
        if (checkSyntaxUsername(matcher.group("username")))
            return Outputs.USERNAME_SYNTAX_INVALID;
        if (checkUsernameExist(matcher.group("username")))
            return Outputs.USERNAME_ALREADY_EXIST;
        if (checkPasswordStrong(matcher.group("password")))
            return Outputs.PASSWORD_NOT_STRONG_ENOUGH;
        if (!matcher.group("password").equals(matcher.group("passwordConfirmation")))
            return Outputs.CONFIRMATION_PASSWORD_DOESNT_MATCH;
        if (checkEmail(matcher.group("email"))) {
            System.out.println("There is an issue with your email address");
            System.out.println("Please try again");
            return Outputs.EMAIL_NOT_ACCEPTABLE;
        }

        username = matcher.group("username");
        password = matcher.group("password");
        email = matcher.group("email");
        nickname = matcher.group("nickname");

        return Outputs.GONE_FOR_SECURITY_QUESTION;


//        System.out.println("676875768");
//        System.out.println(GraphicController.getCommand());
//
//        String securityQuestionAnswer = completeSecurityQuestion();
//
//        //createCaptcha(sc);
//
//        User user = new User(matcher.group("username"), matcher.group("password"), matcher.group("nickname"),
//                matcher.group("email"), securityQuestionAnswer.substring(0, securityQuestionAnswer.length() - 1),
//                securityQuestionAnswer.substring(securityQuestionAnswer.length() - 1));
//        users.add(user);
//        AppData.users.add(user);
//        AppData.addUser(user);
//        return Outputs.USER_CREATED_SUCCESSFULLY;
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

    private static Outputs completeSecurityQuestion(Matcher m) {
        m.find();
        String securityQuestionAnswer = m.group("answer") + m.group("questionNumber");
        User user = new User(username, password, nickname,
            email, securityQuestionAnswer.substring(0, securityQuestionAnswer.length() - 1),
            securityQuestionAnswer.substring(securityQuestionAnswer.length() - 1));
        users.add(user);
        AppData.users.add(user);
        AppData.addUser(user);
        return Outputs.USER_CREATED_SUCCESSFULLY;
    }

}