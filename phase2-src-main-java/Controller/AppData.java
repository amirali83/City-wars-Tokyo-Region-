package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

import Module.*;

public class AppData {
    public static ArrayList<User> users = new ArrayList<>();
    public static ArrayList<Card> allCards = new ArrayList<>();
    public static Connection c;
    public static Statement s;
    public static String sql;
    public static ResultSet rs;

    private static GameCh selectCh(String s) {
        if (s.equals("1"))
            return GameCh.CHARACTER1;
        else if (s.equals("2"))
            return GameCh.CHARACTER2;
        else if (s.equals("3"))
            return GameCh.CHARACTER3;
        else if (s.equals("4"))
            return GameCh.CHARACTER4;
        return null;
    }

    public static void loadData() {
        try {
            c = null;
            s = null;
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Amirali9363?");
            s = c.createStatement();
            sql = "SELECT * FROM NORMALCARD";
            rs = s.executeQuery(sql);
            String cardName, cardCh, link;
            int cardValue, cartAD, cartDuration, playerDamage, upgradeLevel, upgradeCost, level;
            GameCh ch;
            while (rs.next()) {
                cardName = rs.getString("cardName");
                cardValue = rs.getInt("cardValue");
                cartAD = rs.getInt("cardAD");
                cartDuration = rs.getInt("cardDuration");
                playerDamage = rs.getInt("playerDamage");
                upgradeLevel = rs.getInt("upgradeLevel");
                upgradeCost = rs.getInt("upgradeCost");
                cardCh = rs.getString("Ch");
                link = rs.getString("ImageLink");
                ch = selectCh(cardCh);

                AppData.allCards.add(new NormalCard(cardName, cardValue, cartAD, cartDuration, playerDamage, upgradeLevel, upgradeCost, ch, link));
            }
            GraphicController.setAllCards(AppData.allCards);
            //System.out.println("cards loaded successfully");

            sql = "SELECT * FROM USER";
            rs = s.executeQuery(sql);
            String username, password, nickname, email, passwordRecoveryA, passwordRecoveryQ;
            int XP, HP, coins;
            boolean recievedStarterPack;
            String cards;
            String gameHistory;
            while (rs.next()) {
                username = rs.getString("username");
                password = rs.getString("password");
                nickname = rs.getString("nickname");
                email = rs.getString("email");
                passwordRecoveryA = rs.getString("passwordRecoveryA");
                passwordRecoveryQ = rs.getString("passwordRecoveryQ");
                level = rs.getInt("level");
                HP = rs.getInt("HP");
                XP = rs.getInt("XP");
                coins = rs.getInt("coins");
                recievedStarterPack = rs.getBoolean("recievedStarterPack");
                cards = rs.getString("cards");
                gameHistory = rs.getString("gameHistory");
                ArrayList<Card> cardList = new ArrayList<>();
                String []cardNames = cards.split(",");
                for (int i = 0; i < cardNames.length; i++) {
                    for (Card card: AppData.allCards) {
                        if (card.getCardName().equals(cardNames[i])) {
                            cardList.add(card);
                            break;
                        }
                    }
                }
                ArrayList<String> gameHistoryList = new ArrayList<>();
                String []gameHistoryNames = gameHistory.split(",");
                gameHistoryList.addAll(Arrays.asList(gameHistoryNames));
                AppData.users.add(new User(username, password, nickname, email, passwordRecoveryA, passwordRecoveryQ, level, HP,
                        XP, coins, recievedStarterPack, cardList, gameHistoryList));

            }
            //System.out.println("users added successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addCard(NormalCard card) {
        try {
            c = null;
            s = null;
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Amirali9363?");
            s = c.createStatement();
            sql = "INSERT INTO normalcard (cardName, cardValue, cardAD, cardDuration, playerDamage, upgradeLevel, upgradeCost, ImageLink) Values ('"
                    + card.getCardName() + "', " + card.getCardValue() + ", " + card.getCardAttack_Deffence() + ", " +
                    card.getDuration() + ", " + card.getPlayerDamage() + ", " + card.getUpgradeLevel() + ", " + card.getUpgradeCost() + ", " + card.getLevel()
                    + ")";
            s.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteCard(NormalCard card) {
        try {
            c = null;
            s = null;
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Amirali9363?");
            s = c.createStatement();
            sql = "DELETE FROM normalcard WHERE cardName = '" + card.getCardName() + "'";
            s.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addUser(User user) {
        try {
            c = null;
            s = null;
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Amirali9363?");
            s = c.createStatement();
            String cardsName = "";
            String gameHistory = "";
            for (Card card: user.getCards())
                cardsName += card.getCardName() + ",";
            for (String s: user.getGameHistory())
                gameHistory += s + ",";
            sql = String.format("INSERT INTO user (username, password, nickname, email, passwordRecoveryA, passwordRecoveryQ, level, HP, XP" +
                    ", coins, recievedStarterPack, cards, gameHistory) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', %d, %d, %d, %d, %b, '%s', '%s')",
                    user.getUsername(), user.getPassword(), user.getNickname(), user.getEmail(), user.getPasswordRecoveryA(),
                    user.getPasswordRecoveryQ(), user.getLevel(), user.getHP(), user.getXP(), user.getCoins(), user.getRecievedStarterPack(),
                    cardsName.substring(0, Math.max(0, cardsName.length() - 1)), gameHistory.substring(0, Math.max(0, gameHistory.length() - 1)));

            s.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveDataOtherThanUsername(User currentUser) {
        String cardsName = "";
        String gameHistory = "";
        for (Card card: currentUser.getCards())
            cardsName += card.getCardName() + ",";
        for (String s: currentUser.getGameHistory())
            gameHistory += s + ",";
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Amirali9363?");
            s = c.createStatement();
            sql = String.format("UPDATE USER SET nickname = '%s' WHERE username = '%s'", currentUser.getNickname(), currentUser.getUsername());
            s.executeUpdate(sql);
            sql = String.format("UPDATE USER SET password = '%s' WHERE username = '%s'", currentUser.getPassword(), currentUser.getUsername());
            s.executeUpdate(sql);
            sql = String.format("UPDATE USER SET email = '%s' WHERE username = '%s'", currentUser.getEmail(), currentUser.getUsername());
            s.executeUpdate(sql);
            sql = String.format("UPDATE USER SET level = %d WHERE username = '%s'", currentUser.getLevel(), currentUser.getUsername());
            s.executeUpdate(sql);
            sql = String.format("UPDATE USER SET HP = '%d' WHERE username = '%s'", currentUser.getHP(), currentUser.getUsername());
            s.executeUpdate(sql);
            sql = String.format("UPDATE USER SET XP = '%d' WHERE username = '%s'", currentUser.getXP(), currentUser.getUsername());
            s.executeUpdate(sql);
            sql = String.format("UPDATE USER SET coins = '%d' WHERE username = '%s'", currentUser.getCoins(), currentUser.getUsername());
            s.executeUpdate(sql);
            sql = String.format("UPDATE USER SET recievedStarterPack = %b WHERE username = '%s'", currentUser.getRecievedStarterPack(), currentUser.getUsername());
            s.executeUpdate(sql);
            sql = String.format("UPDATE USER SET cards = '%s' WHERE username = '%s'", cardsName.substring(0, Math.max(0, cardsName.length() - 1)), currentUser.getUsername());
            s.executeUpdate(sql);
            sql = String.format("UPDATE USER SET gameHistory = '%s' WHERE username = '%s'", gameHistory.substring(0, Math.max(0, gameHistory.length() - 1)), currentUser.getUsername());
            s.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveDataUsername(User currentUser) {
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Amirali9363?");
            s = c.createStatement();
            sql = String.format("UPDATE USER SET username = '%s' WHERE nickname = '%s' AND password = '%s'", currentUser.getUsername(), currentUser.getNickname(), currentUser.getPassword());
            s.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
