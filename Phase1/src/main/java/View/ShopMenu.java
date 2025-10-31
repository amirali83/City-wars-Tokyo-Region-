package View;

import Controller.AppData;

import java.util.Scanner;
import Module.*;

public class ShopMenu {
    private User currentUser;
    private Scanner sc;

    public ShopMenu(Scanner sc, User currentUser) {
        this.sc = sc;
        this.currentUser = currentUser;
    }

    public void start() {
        System.out.println("shop menu");
        String command = sc.nextLine();
        do {
            if (Inputs.BUY_NEW_CARD.getMatcher(command).matches())
                buyNewCard();
            else if (Inputs.UPDATE_CARD.getMatcher(command).matches())
                updateCard();
            else if (Inputs.HELP.getMatcher(command).matches())
                help();
            else
                System.out.println("invalid command");

            command = sc.nextLine();
        } while(!command.equals("back"));
        System.out.println("back");
    }

    private void help() {
        System.out.println(Inputs.BUY_NEW_CARD.getString());
        System.out.println(Inputs.UPDATE_CARD.getString());
        System.out.println();
    }

    private void updateCard() {
        for (Card card: currentUser.getCards())
            if (card.getClass().equals(NormalCard.class))
                showCardProperty(card); /////////also show properties after update
        System.out.print("Enter the card name: ");
        String cardName = sc.nextLine();
        NormalCard tempCard = null;
        for (Card card : AppData.allCards)
            if (card.getCardName().equals(cardName)) {
                tempCard = (NormalCard) card;
                break;
            }
        if (tempCard == null) {
            System.out.println("Card not found");
            return;
        }
        if (tempCard.getUpgradeLevel() > currentUser.getLevel()) {
            System.out.println("Your level is not high enough");
            return;
        }
        if (tempCard.getUpgradeCost() > currentUser.getCoins()) {
            System.out.println("Your coins are not enough");
            return;
        }
        //card upgrade what happens to it?
//        ###############################################
        currentUser.setCoins(currentUser.getCoins() - tempCard.getUpgradeCost() * (int) Math.pow(1.25, tempCard.getLevel() - 1));
        tempCard.setLevel(tempCard.getLevel() + 1);
    }

    private void buyNewCard() {
        for (Card card : AppData.allCards) {
            if (!currentUser.hasCard(card))
                showCardProperty(card);
        }
        System.out.print("Enter the card name: ");
        String cardName = sc.nextLine();
        Card tempCard = null;
        for (Card card : AppData.allCards)
            if (card.getCardName().equals(cardName)) {
                tempCard = card;
                break;
            }
        if (tempCard == null) {
            System.out.println("Card not found");
            return;
        }
        if (tempCard.getCardValue() > currentUser.getCoins()) {
            System.out.println("You dont have enough money");
            return;
        }
        System.out.println("Card bought successfully");
        currentUser.addCard(tempCard);
        currentUser.setCoins(currentUser.getCoins() - tempCard.getCardValue());
    }

    private void showCardProperty(Card card) {
        if (card.getClass().equals(EspecialCard.class)) {
            System.out.println("Card name: " + card.getCardName());
            System.out.println("Card explanation: " + ((EspecialCard) card).getCardExplanation());
            System.out.println("Card value: " + card.getCardValue());
        }
        else if (card.getClass().equals(NormalCard.class)) {
            System.out.println("Card name: " + card.getCardName());
            System.out.println("Attack/Defence: " + ((NormalCard) card).getCardAttack_Deffence());
            System.out.println("Duration: " + ((NormalCard) card).getDuration());
            System.out.println("Player damage: " + ((NormalCard) card).getPlayerDamage());
            System.out.println("Upgrade cost: " + ((NormalCard) card).getUpgradeCost());
            System.out.println("Upgrade level: " + ((NormalCard) card).getUpgradeLevel());
            System.out.println("Card value: " + card.getCardValue());
        }
        System.out.println();
    }

}
