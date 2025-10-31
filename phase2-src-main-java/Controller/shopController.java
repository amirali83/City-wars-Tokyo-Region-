package Controller;

import View.mainMenuGraphic;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import Module.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class shopController {

    public Rectangle card1;
    public Rectangle card3;
    public Rectangle card2;
    public Rectangle card4;
    public Rectangle card5;
    public Rectangle card6;
    public Rectangle card6lock;
    public Rectangle card4lock;
    public Rectangle card2lock;
    public Rectangle card5lock;
    public Rectangle card1lock;
    public Rectangle card3lock;
    public Text card3details;
    public Text card1details;
    public Text card5details;
    public Text card2details;
    public Text card4details;
    public Text card6details;
    int cardSelected = -1;
    int pagenum = 1;

    public void pagebef(MouseEvent mouseEvent) {
        if (pagenum != 1) pagenum--;
        card1.setOpacity(1);
        card2.setOpacity(1);
        card3.setOpacity(1);
        card4.setOpacity(1);
        card5.setOpacity(1);
        card6.setOpacity(1);
        cardSelected = -1;
        update();
    }

    public void pageaf(MouseEvent mouseEvent) {
        if (!(pagenum * 6 > GraphicController.getAllCards().size())) pagenum++;
        card1.setOpacity(1);
        card2.setOpacity(1);
        card3.setOpacity(1);
        card4.setOpacity(1);
        card5.setOpacity(1);
        card6.setOpacity(1);
        card1lock.setOpacity(1);
        card2lock.setOpacity(1);
        card3lock.setOpacity(1);
        card4lock.setOpacity(1);
        card5lock.setOpacity(1);
        card6lock.setOpacity(1);
        cardSelected = -1;
        update();
    }

    public void back(MouseEvent mouseEvent) {
        mainMenuGraphic menu = new mainMenuGraphic();
        try {
            menu.start(GraphicController.getStage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void buy(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        if (cardSelected == -1) {
            alert.setHeaderText("Select a cart first");
            alert.showAndWait();
            return;
        }
        int cardIndex = (pagenum - 1 ) * 6 + cardSelected - 1;
        Card temp = GraphicController.getAllCards().get(cardIndex);
        if (temp.getClass().equals(EspecialCard.class)) {
            alert.setHeaderText("Error");
            alert.setContentText("This card can not be upgraded");
            alert.showAndWait();
        }
        if (checkUserhave(temp)) {
            if (GraphicController.getUser().getCoins() < temp.getCardValue()) {
                alert.setHeaderText("Error");
                alert.setHeaderText("You don't have enough money");
                alert.showAndWait();
                return;
            }
            if (GraphicController.getUser().getLevel() < ((NormalCard) temp).getUpgradeLevel()) {
                alert.setHeaderText("Error");
                alert.setHeaderText("Your level is not high enough");
                alert.showAndWait();
                return;
            }
            ButtonType ok = new ButtonType("OK");
            ButtonType cancel = new ButtonType("Cancel");
            alert = new Alert(Alert.AlertType.NONE, "", ok, cancel);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Are you sure you want to upgrade this card?");
            alert.setContentText(String.format("Card A/D = %d -> %d\nCard Damage = %d -> %d\n", ((NormalCard) temp).getCardAttack_Deffence(), (int) (((NormalCard) temp).getCardAttack_Deffence() * 1.15), ((NormalCard) temp).getPlayerDamage(), (int) (((NormalCard) temp).getCardAttack_Deffence() * 1.2)));
            alert.showAndWait().ifPresent(response -> {
                if (response == ok) {
                    cardUpgrade((NormalCard) temp);
                }
            });
        } else {
            if (GraphicController.getUser().getCoins() < ((NormalCard) temp).getUpgradeCost()) {
                alert.setHeaderText("Error");
                alert.setContentText("You don't have enough money");
                alert.showAndWait();
                return;
            }
            ButtonType ok = new ButtonType("OK");
            ButtonType cancel = new ButtonType("Cancel");
            alert = new Alert(Alert.AlertType.NONE, "", ok, cancel);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Are you sure you want to buy this card?");
            alert.showAndWait().ifPresent(response -> {
                if (response == ok) {
                    buyCard(temp);
                }
            });
        }
        update();
    }

    private void buyCard(Card temp) {
        GraphicController.getUser().getCards().add(temp);
    }

    public void cardUpgrade(NormalCard temp) {
        temp.setCardAttack_Deffence((int) (temp.getCardAttack_Deffence() * 1.15));
        temp.setPlayerDamage((int) (temp.getPlayerDamage() * 1.2));
    }

    public boolean checkUserhave(Card card1) {
        for (Card card: GraphicController.getUser().getCards())
            if (card1.equals(card)) return true;
        return false;
    }

    public String getDetail(Card card) {
        String command = "";
        if (card.getClass().equals(EspecialCard.class))
            command = String.format("Card name = %s\nCard explanation = \n%s\nCard Value = %d", card.getCardName(), ((EspecialCard) card).getCardExplanation(), card.getCardValue());
        if (card.getClass().equals(NormalCard.class))
            command = String.format("Card name = %s\nCard A/D = %d\nCard Damage = %d\nCard Value = %d\nCard Upgrade Cost = %d\nCard Upgrade Level = %d", card.getCardName(), ((NormalCard) card).getCardAttack_Deffence(), ((NormalCard) card).getPlayerDamage(), card.getCardValue(), ((NormalCard) card).getUpgradeCost(), ((NormalCard) card).getUpgradeLevel());
        return command;
    }

    public void update() {
        card1.setVisible(false);
        card2.setVisible(false);
        card3.setVisible(false);
        card4.setVisible(false);
        card5.setVisible(false);
        card6.setVisible(false);
        card1lock.setVisible(false);
        card3lock.setVisible(false);
        card4lock.setVisible(false);
        card5lock.setVisible(false);
        card6lock.setVisible(false);
        card2lock.setVisible(false);
        for (int i = (pagenum - 1 ) * 6; i < Math.min(GraphicController.getAllCards().size(), pagenum * 6); i++) {
            if (i % 6 == 0) {
                card1.setFill(new ImagePattern(new Image(getClass().getResource(GraphicController.getAllCards().get(i).getImageLink()).toExternalForm())));
                card1.setVisible(true);
                card1lock.setFill(new ImagePattern(new Image(getClass().getResource("/CardImage/lock.png").toExternalForm())));
                if (!checkUserhave(GraphicController.getAllCards().get(i)))
                    card1lock.setVisible(true);
                card1details.setText(getDetail(GraphicController.getAllCards().get(i)));
                card1details.setWrappingWidth(130);
                card1details.setFont(Font.font(10));
                card1details.setTextAlignment(TextAlignment.CENTER);
            }
            else if (i % 6 == 1) {
                card2.setFill(new ImagePattern(new Image(getClass().getResource(GraphicController.getAllCards().get(i).getImageLink()).toExternalForm())));
                card2.setVisible(true);
                card2lock.setFill(new ImagePattern(new Image(getClass().getResource("/CardImage/lock.png").toExternalForm())));
                if (!checkUserhave(GraphicController.getAllCards().get(i)))
                    card2lock.setVisible(true);
                card2details.setText(getDetail(GraphicController.getAllCards().get(i)));
                card2details.setWrappingWidth(130);
                card2details.setFont(Font.font(10));
                card2details.setTextAlignment(TextAlignment.CENTER);
            }
            else if (i % 6 == 2) {
                card3.setFill(new ImagePattern(new Image(getClass().getResource(GraphicController.getAllCards().get(i).getImageLink()).toExternalForm())));
                card3.setVisible(true);
                card3lock.setFill(new ImagePattern(new Image(getClass().getResource("/CardImage/lock.png").toExternalForm())));
                if (!checkUserhave(GraphicController.getAllCards().get(i)))
                    card3lock.setVisible(true);
                card3details.setText(getDetail(GraphicController.getAllCards().get(i)));
                card3details.setWrappingWidth(130);
                card3details.setFont(Font.font(10));
                card3details.setTextAlignment(TextAlignment.CENTER);
            }
            else if (i % 6 == 3) {
                card4.setFill(new ImagePattern(new Image(getClass().getResource(GraphicController.getAllCards().get(i).getImageLink()).toExternalForm())));
                card4.setVisible(true);
                card4lock.setFill(new ImagePattern(new Image(getClass().getResource("/CardImage/lock.png").toExternalForm())));
                if (!checkUserhave(GraphicController.getAllCards().get(i)))
                    card4lock.setVisible(true);
                card4details.setText(getDetail(GraphicController.getAllCards().get(i)));
                card4details.setWrappingWidth(80);
                card4details.setWrappingWidth(130);
                card4details.setFont(Font.font(10));
                card4details.setTextAlignment(TextAlignment.CENTER);
            }
            else if (i % 6 == 4) {
                card5.setFill(new ImagePattern(new Image(getClass().getResource(GraphicController.getAllCards().get(i).getImageLink()).toExternalForm())));
                card5.setVisible(true);
                card5lock.setFill(new ImagePattern(new Image(getClass().getResource("/CardImage/lock.png").toExternalForm())));
                if (!checkUserhave(GraphicController.getAllCards().get(i)))
                    card5lock.setVisible(true);
                card5details.setText(getDetail(GraphicController.getAllCards().get(i)));
                card5details.setWrappingWidth(130);
                card5details.setFont(Font.font(10));
                card5details.setTextAlignment(TextAlignment.CENTER);
            }
            else if (i % 6 == 5) {
                card6.setFill(new ImagePattern(new Image(getClass().getResource(GraphicController.getAllCards().get(i).getImageLink()).toExternalForm())));
                card6.setVisible(true);
                card6lock.setFill(new ImagePattern(new Image(getClass().getResource("/CardImage/lock.png").toExternalForm())));
                if (!checkUserhave(GraphicController.getAllCards().get(i)))
                    card6lock.setVisible(true);
                card6details.setText(getDetail(GraphicController.getAllCards().get(i)));
                card6details.setWrappingWidth(130);
                card6details.setFont(Font.font(10));
                card6details.setTextAlignment(TextAlignment.CENTER);
            }
        }
    }

    public void selectCard1(MouseEvent mouseEvent) {
        cardSelected = 1;
        System.out.println("12212");
        card1.setOpacity(0.5);
        card1lock.setOpacity(0.5);

        card2.setOpacity(1);
        card2lock.setOpacity(1);
        card3.setOpacity(1);
        card3lock.setOpacity(1);
        card4.setOpacity(1);
        card4lock.setOpacity(1);
        card5.setOpacity(1);
        card5lock.setOpacity(1);
        card6.setOpacity(1);
        card6lock.setOpacity(1);
    }

    public void selectCard2(MouseEvent mouseEvent) {
        cardSelected = 2;
        card2.setOpacity(0.5);
        card2lock.setOpacity(0.5);

        card1.setOpacity(1);
        card1lock.setOpacity(1);
        card3.setOpacity(1);
        card3lock.setOpacity(1);
        card4.setOpacity(1);
        card4lock.setOpacity(1);
        card5.setOpacity(1);
        card5lock.setOpacity(1);
        card6.setOpacity(1);
        card6lock.setOpacity(1);
    }

    public void selectCard3(MouseEvent mouseEvent) {
        cardSelected = 3;
        card3.setOpacity(0.5);
        card3lock.setOpacity(0.5);

        card2.setOpacity(1);
        card2lock.setOpacity(1);
        card1.setOpacity(1);
        card1lock.setOpacity(1);
        card4.setOpacity(1);
        card4lock.setOpacity(1);
        card5.setOpacity(1);
        card5lock.setOpacity(1);
        card6.setOpacity(1);
        card6lock.setOpacity(1);
    }

    public void selectCard4(MouseEvent mouseEvent) {
        cardSelected = 4;
        card4.setOpacity(0.5);
        card4lock.setOpacity(0.5);

        card2.setOpacity(1);
        card2lock.setOpacity(1);
        card3.setOpacity(1);
        card3lock.setOpacity(1);
        card1.setOpacity(1);
        card1lock.setOpacity(1);
        card5.setOpacity(1);
        card5lock.setOpacity(1);
        card6.setOpacity(1);
        card6lock.setOpacity(1);
    }

    public void selectCard5(MouseEvent mouseEvent) {
        cardSelected = 5;
        card5.setOpacity(0.5);
        card5lock.setOpacity(0.5);

        card2.setOpacity(1);
        card2lock.setOpacity(1);
        card3.setOpacity(1);
        card3lock.setOpacity(1);
        card4.setOpacity(1);
        card4lock.setOpacity(1);
        card1.setOpacity(1);
        card1lock.setOpacity(1);
        card6.setOpacity(1);
        card6lock.setOpacity(1);
    }

    public void selectCard6(MouseEvent mouseEvent) {
        cardSelected = 6;
        card6.setOpacity(0.5);
        card6lock.setOpacity(0.5);

        card2.setOpacity(1);
        card2lock.setOpacity(1);
        card3.setOpacity(1);
        card3lock.setOpacity(1);
        card4.setOpacity(1);
        card4lock.setOpacity(1);
        card5.setOpacity(1);
        card5lock.setOpacity(1);
        card1.setOpacity(1);
        card1lock.setOpacity(1);
    }
}
