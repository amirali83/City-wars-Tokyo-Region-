package View;

import Module.*;

import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class twoPlayerGame {
    private static User []users = new User[2];
    private static GameCh []chars = new GameCh[2];
    private static Scanner sc;
    private static Card [][]timeLines = new Card[2][21];
    private static Card [][]playersdeck = new Card[2][5];
    private static int []playersDamage = new int[2];
    private static int []playersRound = new int[2];
    private static int inCharge;

    public static void start(User user1, User user2, Scanner sc) {
        twoPlayerGame.sc = sc;
        twoPlayerGame.users[0] = user1;
        twoPlayerGame.users[1] = user2;

        System.out.println("Select your characters");
        String command = sc.nextLine();
        if (Inputs.SELECT_CHARACTER.getMatcher(command).matches())
            chars[0] = selectCh(Inputs.SELECT_CHARACTER.getMatcher(command));
        else {
            System.out.println("Invalid input");
            twoPlayerGame.start(user1, user2, sc);
            return;
        }
        command = sc.nextLine();
        if (Inputs.SELECT_CHARACTER.getMatcher(command).matches())
            chars[1] = selectCh(Inputs.SELECT_CHARACTER.getMatcher(command));
        else {
            System.out.println("Invalid input");
            twoPlayerGame.start(user1, user2, sc);
            return;
        }
        Random random = new Random();
        int f = Math.abs(random.nextInt()) % 21, s = Math.abs(random.nextInt()) % 21;
        for (int i = 0; i < 21; i++) {
            if (i != f)
                timeLines[0][i] = new Card();
            if (i != s)
                timeLines[1][i] = new Card();
        }
        for (int i = 0; i < 5; i++) {
            f = Math.abs(random.nextInt()) % user1.getCards().size();
            s = Math.abs(random.nextInt()) % user2.getCards().size();
            playersdeck[0][i] = user1.getCards().get(f);
            playersdeck[1][i] = user2.getCards().get(s);
        }
        playersDamage[0] = playersDamage[1] = 0;
        playersRound[0] = playersRound[1] = 4;
        drawGrphic();
        startGame();
    }

    private static void startGame() {
        Random r = new Random();
        inCharge = Math.abs(r.nextInt()) % 2;
        String command = sc.nextLine();
        while (true) {
            if (Inputs.SHOW_CARD_INFORMATION.getMatcher(command).matches()) {
                showCardInformation(Inputs.SHOW_CARD_INFORMATION.getMatcher(command));
            } else if (Inputs.PLACE_CARD_NUMBER.getMatcher(command).matches()) {
                placeCard(Inputs.PLACE_CARD_NUMBER.getMatcher(command));
            } else if (Inputs.HELP.getMatcher(command).matches()){

            } else
                System.out.println("invalid input");
            command = sc.nextLine();
        }
    }

    private static void placeCard(Matcher matcher) {
        
    }

    private static void showCardInformation(Matcher matcher) {
        matcher.find();
        int player = -1, index = -1;
        if (matcher.group("player").equals(users[0].getUsername())) player = 0;
        else if (matcher.group("player").equals(users[1].getUsername())) player = 1;
        else {
            System.out.println("Wrong player");
            System.out.println("try again");
            return;
        }
        if (!(matcher.group("number").equals("1") || matcher.group("number").equals("2") ||
                matcher.group("number").equals("3") || matcher.group("number").equals("4") ||
                matcher.group("number").equals("5"))) {
            System.out.println("Wrong index");
            System.out.println("try again");
            return;
        }
        index = Integer.parseInt(matcher.group("number")) - 1;
        if (playersdeck[player][index].getClass().equals(EspecialCard.class)) {
            System.out.println("Card name: " + playersdeck[player][index].getCardName());
            System.out.println("Card duration: " + ((EspecialCard) playersdeck[player][index]).getDuration());
            System.out.println("Card explanation: " + ((EspecialCard) playersdeck[player][index]).getCardExplanation());
        } else {
            System.out.println("Card name: " + playersdeck[player][index].getCardName());
            System.out.println("Attack/Defence: " + ((NormalCard) playersdeck[player][index]).getCardAttack_Deffence());
            System.out.println("Duration: " + ((NormalCard) playersdeck[player][index]).getDuration());
            System.out.println("Player damage: " + ((NormalCard) playersdeck[player][index]).getPlayerDamage());
        }
        System.out.println();
    }

    private static void drawGrphic() {
        for (int i = 0; i < 2; i++) {
            System.out.println("Player " + (i + 1) + ": ");
            System.out.println("Health: " + users[i].getHP());
            System.out.print(playersRound[i]);
            for (int j = 0; j < 21; j++) {
                if (timeLines[i][j] != null)
                    System.out.print(timeLines[i][j].getCardName());
                else
                    System.out.print("-1");
                System.out.print(" ");
            }
            System.out.println(playersDamage[i]);
            System.out.println();
        }
    }

    private static GameCh selectCh(Matcher matcher) {
        return new GameCh();
    }

}
