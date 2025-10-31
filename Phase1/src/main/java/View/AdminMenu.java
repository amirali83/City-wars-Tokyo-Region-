package View;

import Controller.AppData;

import java.util.ArrayList;
import java.util.Scanner;
import Module.*;

public class AdminMenu {
    private static Scanner sc;
    public static void start(Scanner sc1) {
        System.out.println("Admin Menu");
        AdminMenu.sc = sc1;
        String command = sc.nextLine();
        do {
            if (Inputs.ADMIN_ADD_CARD.getMatcher(command).matches())
                addCard();
            else if (Inputs.ADMIN_EDIT_CARD.getMatcher(command).matches())
                editCard();
            else if (Inputs.ADMIN_REMOVE_CARD.getMatcher(command).matches())
                removeCard();
            else if (Inputs.ADMIN_SEE_ALL_PLAYERS.getMatcher(command).matches())
                seeAllPlayers();
            else if (Inputs.HELP.getMatcher(command).matches())
                help();
            else
                System.out.println("invalid command");

            command = sc.nextLine();
        } while(!command.equals("back"));
        System.out.println("back");
    }

    private static void help() {
        System.out.println(Inputs.ADMIN_ADD_CARD.getString());
        System.out.println(Inputs.ADMIN_EDIT_CARD.getString());
        System.out.println(Inputs.ADMIN_REMOVE_CARD.getString());
        System.out.println(Inputs.ADMIN_SEE_ALL_PLAYERS.getString());
        System.out.println();
    }

    private static void seeAllPlayers() {
        for (User user : AppData.users) {
            System.out.println("username: " + user.getUsername());
            System.out.println("level: " + user.getLevel());
            System.out.println("coins: " + user.getCoins());
            System.out.println();
        }
    }

    private static void removeCard() {
        ArrayList<Card> index = showAllNormalCards();
        System.out.print("Enter card index: ");
        int cardIndex = Integer.parseInt(sc.nextLine());
        if (cardIndex > index.size() + 1) {
            System.out.println("Invalid card index");
            return;
        }
        System.out.println("Are you sure you want to remove card? (y/n)");
        String answer = sc.nextLine();
        if (!answer.equals("y")) {
            System.out.println("Card didnt remove");
            return;
        }
        System.out.println("Card removed");
        AppData.allCards.remove(index.get(cardIndex - 1));
        AppData.deleteCard((NormalCard) index.get(cardIndex - 1));
    }

    private static void showCardProperty(Card card) {
        System.out.println("Card name: " + card.getCardName());
        System.out.println("Attack/Defence: " + ((NormalCard) card).getCardAttack_Deffence());
        System.out.println("Duration: " + ((NormalCard) card).getDuration());
        System.out.println("Player damage: " + ((NormalCard) card).getPlayerDamage());
        System.out.println("Upgrade cost: " + ((NormalCard) card).getUpgradeCost());
        System.out.println("Upgrade level: " + ((NormalCard) card).getUpgradeLevel());
        System.out.println("Card value: " + card.getCardValue());
    }

    private static void editCard() {
        ArrayList<Card> index = showAllNormalCards();
        System.out.print("Enter card index: ");
        int cardIndex = Integer.parseInt(sc.nextLine());
        if (cardIndex > index.size() + 1) {
            System.out.println("Invalid card index");
            return;
        }
        editCardProperties(index.get(cardIndex - 1));
    }

    private static ArrayList<Card> showAllNormalCards() {
        ArrayList<Card> index = new ArrayList<>();
        for (int i = 0; i < AppData.allCards.size(); i++)
            if (AppData.allCards.get(i).getClass().equals(NormalCard.class)) {
                System.out.println("index: " + (index.size() + 1));
                showCardProperty(AppData.allCards.get(i));
                index.add(AppData.allCards.get(i));
                System.out.println();
            }
        return index;
    }

    private static void editCardProperties(Card card) {
        System.out.println("1 Card name: " + card.getCardName());
        System.out.println("2 Attack/Defence: " + ((NormalCard) card).getCardAttack_Deffence());
        System.out.println("3 Duration: " + ((NormalCard) card).getDuration());
        System.out.println("4 Player damage: " + ((NormalCard) card).getPlayerDamage());
        System.out.println("5 Upgrade cost: " + ((NormalCard) card).getUpgradeCost());
        System.out.println("6 Upgrade level: " + ((NormalCard) card).getUpgradeLevel());
        System.out.println("7 Card value: " + card.getCardValue());

        System.out.println("Enter index of the filed changing");
        String command = sc.nextLine();
        String cardName = null;
        int AD = -1, duration = -1, PD = -1, UC = -1, UL = -1, CV = -1;
        while (true) {
            if (command.equals("1")) cardName = getName();
            else if (command.equals("2")) AD = getAttack();
            else if (command.equals("3")) duration = getDuration();
            else if (command.equals("4")) PD = getDamage();
            else if (command.equals("5")) UC = getUpgradeCost();
            else if (command.equals("6")) UL = getUpgradeLevel();
            else if (command.equals("7")) CV = getValue();
            else if (command.equals("back")) return;
            else if (command.equals("finish")) break;
            else System.out.println("invalid command");
            System.out.println("Enter index of the filed changing");
            command = sc.nextLine();
        }
        System.out.println("are you sure you want to edit this card? (y/n)");
        command = sc.nextLine();
        if (!command.equals("y"))  {
            System.out.println("Card edit unsuccessful");
            return;
        }
        AppData.deleteCard((NormalCard) card);
        if (cardName != null) ((NormalCard) card).setCardName(cardName);
        if (CV != -1) ((NormalCard) card).setCardValue(CV);
        if (duration != -1) ((NormalCard) card).setDuration(duration);
        if (UC != -1) ((NormalCard) card).setUpgradeCost(UC);
        if (UL != -1) ((NormalCard) card).setUpgradeLevel(UL);
        if (AD != -1) ((NormalCard) card).setCardAttack_Deffence(AD);
        if (PD != -1) ((NormalCard) card).setPlayerDamage(PD);
        AppData.addCard((NormalCard) card);
        System.out.println("Card edited successfuly");
    }

    private static void addCard() {
        String name = getName();
        int cardValue = getValue();
        int cardAttack_Defence = getAttack();
        int duration = getDuration();
        int playerDamage = getDamage();
        int upgradeLevel = getUpgradeLevel();
        int upgradeCost = getUpgradeCost();

        System.out.println("Card added successfuly");
        AppData.allCards.add(new NormalCard(name, cardValue, cardAttack_Defence, duration, playerDamage, upgradeLevel, upgradeCost));
        AppData.addCard((NormalCard) AppData.allCards.getLast());
    }

    private static int getValue() {
        System.out.print("Enter card value cost: ");
        int value = Integer.parseInt(sc.nextLine());
        return value;
    }

    private static int getUpgradeCost() {
        System.out.print("Enter card upgrade cost: ");
        int cost = Integer.parseInt(sc.nextLine());
        return cost;
    }

    private static int getUpgradeLevel() {
        System.out.print("Enter card upgrade level: ");
        int level = Integer.parseInt(sc.nextLine());
        return level;
    }

    private static int getDamage() {
        System.out.print("Enter card damage: ");
        int damage = Integer.parseInt(sc.nextLine());
        if (damage < 10 || damage > 50) {
            System.out.println("invalid damage\n Try again");
            return getDamage();
        }
        return damage;
    }

    private static int getDuration() {
        System.out.print("Enter card duration: ");
        int duration = Integer.parseInt(sc.nextLine());
        if (duration < 1 || duration > 5) {
            System.out.println("invalid duration\n Try again");
            return getDuration();
        }
        return duration;
    }

    private static int getAttack() {
        System.out.print("Enter the card defences/attack: ");
        int attack = Integer.parseInt(sc.nextLine());
        if (attack < 10 || attack > 100) {
            System.out.println("Invalid attack\n Try again");
            return getAttack();
        }
        return attack;
    }

    private static String getName() {
        System.out.print("Enter the name of the card: ");
        String name = sc.nextLine();
        for (Card card: AppData.allCards)
            if (card.getCardName().equals(name)) {
                System.out.println("Name in use\n Try again");
                return getName();
            }
        return name;
    }
}
