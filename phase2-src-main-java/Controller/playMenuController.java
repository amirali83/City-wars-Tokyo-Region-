package Controller;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import View.*;
import Module.*;
import javafx.scene.text.Text;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;

public class playMenuController {
    public Rectangle timeline00, timeline01, timeline02, timeline03, timeline04, timeline05, timeline06, timeline07, timeline08, timeline09, timeline010, timeline011, timeline012, timeline013, timeline014, timeline015, timeline016, timeline017, timeline018, timeline019, timeline020, timeline021, timeline022, timeline023;
    public Rectangle timeline10, timeline11, timeline12, timeline13, timeline14, timeline15, timeline16, timeline17, timeline18, timeline19, timeline110, timeline111, timeline112, timeline113, timeline114, timeline115, timeline116, timeline117, timeline118, timeline119, timeline120, timeline121, timeline122, timeline123;
    public Rectangle player10, player11, player12, player13, player14, player15;
    public Rectangle player20, player21, player22, player23, player24, player25;

    public Rectangle [][]timelines = new Rectangle[2][24];
    public Rectangle [][]playersdeck = new Rectangle[2][6];
    public Label roundLeftP2;
    public Label roundLeftP1;
    public Label DamageP2;
    public Label DamageP1;
    public Label player1name;
    public Label player1live;
    public Label player2live;
    public Label player2name;
    public Text ShowCardInformation;

    boolean bringup = false;

    int chosedPlayerCardIndex = -1;
    int chosedTimeLineIndex = -1;
    int cardIndexToShow;
    int playerIndexToShowCard;
    private int cardType1, cardType2;
    private int cardAD1, cardAD2;
    private int cardDamage1, cardDamage2;
    private Card [][]timeLines = new Card[2][21];

    public void bringUp(MouseEvent mouseEvent) {
        if (!bringup) {
            timelines[0][0] = timeline00; timelines[0][1] = timeline01; timelines[0][2] = timeline02; timelines[0][3] = timeline03; timelines[0][4] = timeline04; timelines[0][5] = timeline05; timelines[0][6] = timeline06; timelines[0][7] = timeline07; timelines[0][8] = timeline08; timelines[0][9] = timeline09; timelines[0][10] = timeline010; timelines[0][11] = timeline011; timelines[0][12] = timeline012; timelines[0][13] = timeline013; timelines[0][14] = timeline014; timelines[0][15] = timeline015; timelines[0][16] = timeline016; timelines[0][17] = timeline017; timelines[0][18] = timeline018; timelines[0][19] = timeline019; timelines[0][20] = timeline020; timelines[0][21] = timeline021; timelines[0][22] = timeline022; timelines[0][23] = timeline023;
            timelines[1][0] = timeline10; timelines[1][1] = timeline11; timelines[1][2] = timeline12; timelines[1][3] = timeline13; timelines[1][4] = timeline14; timelines[1][5] = timeline15; timelines[1][6] = timeline16; timelines[1][7] = timeline17; timelines[1][8] = timeline18; timelines[1][9] = timeline19; timelines[1][10] = timeline110; timelines[1][11] = timeline111; timelines[1][12] = timeline112; timelines[1][13] = timeline113; timelines[1][14] = timeline114; timelines[1][15] = timeline115; timelines[1][16] = timeline116; timelines[1][17] = timeline117; timelines[1][18] = timeline118; timelines[1][19] = timeline119; timelines[1][20] = timeline120; timelines[1][21] = timeline121; timelines[1][22] = timeline122; timelines[1][23] = timeline123;
            playersdeck[0][0] = player10; playersdeck[0][1] = player11; playersdeck[0][2] = player12; playersdeck[0][3] = player13; playersdeck[0][4] = player14; playersdeck[0][5] = player15;
            playersdeck[1][0] = player20; playersdeck[1][1] = player21; playersdeck[1][2] = player22; playersdeck[1][3] = player23; playersdeck[1][4] = player24; playersdeck[1][5] = player25;
            bringup = true;
            GraphicController.setTimelines(timelines);
            GraphicController.setPlayersDeck(playersdeck);
            timelines[0][0].setVisible(false);
            timelines[1][0].setVisible(false);
            timelines[0][23].setVisible(false);
            timelines[1][23].setVisible(false);
            twoPlayerGame.start(GraphicController.getUser(), GraphicController.getOpponent());
            roundLeftP2.setText(Integer.toString(GraphicController.getPlayersRound()[1]));
            roundLeftP1.setText(Integer.toString(GraphicController.getPlayersRound()[0]));
            DamageP2.setText(Integer.toString(GraphicController.getPlayersDamage()[1]));
            DamageP1.setText(Integer.toString(GraphicController.getPlayersDamage()[0]));
            if (GraphicController.getInCharge() == 0) {roundLeftP1.setTextFill(Color.RED); roundLeftP2.setTextFill(Color.BLACK);}
            if (GraphicController.getInCharge() == 1) {roundLeftP2.setTextFill(Color.RED); roundLeftP1.setTextFill(Color.BLACK);}
            player1name.setText(GraphicController.getUser().getUsername());
            player2name.setText(GraphicController.getOpponent().getUsername());
            player1live.setText(Integer.toString(GraphicController.getLives()[0]));
            player2live.setText(Integer.toString(GraphicController.getLives()[1]));
        }
    }

    public void reset(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ESCAPE)) {
            chosedPlayerCardIndex = -1;
            chosedTimeLineIndex = -1;
            resetSelect();
        }
    }

    public void reset() {
        chosedPlayerCardIndex = -1;
        chosedTimeLineIndex = -1;
        resetSelect();
        roundLeftP2.setText(Integer.toString(GraphicController.getPlayersRound()[1]));
        roundLeftP1.setText(Integer.toString(GraphicController.getPlayersRound()[0]));
        DamageP2.setText(Integer.toString(GraphicController.getPlayersDamage()[1]));
        DamageP1.setText(Integer.toString(GraphicController.getPlayersDamage()[0]));
        player1live.setText(Integer.toString(GraphicController.getLives()[0]));
        player2live.setText(Integer.toString(GraphicController.getLives()[1]));
        if (GraphicController.getInCharge() == 0) {roundLeftP1.setTextFill(Color.RED); roundLeftP2.setTextFill(Color.BLACK);}
        if (GraphicController.getInCharge() == 1) {roundLeftP2.setTextFill(Color.RED); roundLeftP1.setTextFill(Color.BLACK);}
        player1live.setText(Integer.toString(GraphicController.getLives()[0]));
        player2live.setText(Integer.toString(GraphicController.getLives()[1]));
    }

    public void resetSelect() {
        for (int i = 0; i < 6; i++) {
            playersdeck[0][i].setOpacity(1);
            playersdeck[1][i].setOpacity(1);
        }
    }

    public int giveAlertForNotTurn(int pl) {
        if (pl != GraphicController.getInCharge()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("It is not your turn");
            alert.showAndWait();
            return 1;
        }
        resetSelect();
        return -1;
    }

    private int giveAlertForInvalidPlace(int i) {
        if (i != GraphicController.getInCharge()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("you can not place your card here");
            alert.showAndWait();
            return 1;
        }
        return -1;
    }

    private int giveAlertForNotChoosedCard(int chosedPlayerCardIndex) {
        if (chosedPlayerCardIndex == -1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Select a card first");
            alert.showAndWait();
            return 1;
        }
        return -1;
    }

    private void placeCard(){
        String command = String.format("-Placing card number %s in block %s", chosedPlayerCardIndex + 1, chosedTimeLineIndex + 1);
        Outputs out = twoPlayerGame.startGame(command);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        if (out.equals(Outputs.CANT_PLACE_CARD_HERE)) {
            alert.setHeaderText("Can't place card here");
            alert.showAndWait();
        }
//        else if (out.equals(Outputs.PLAYER1_WON) || out.equals(Outputs.PLAYER2_WON)) {
//            AppData.saveDataOtherThanUsername(GraphicController.getOpponent());
//            LocalDate localDate = LocalDate.now();
//            LocalTime localTime = LocalTime.now();
//            String time = localDate.getYear() + "%" + localDate.getMonthValue() + "%" + localDate.getDayOfMonth() + "%"
//                    + localTime.getHour() + "%" + localTime.getMinute() + "%" + localTime.getSecond();
//            if (out.equals(Outputs.PLAYER1_WON)) {
//                GraphicController.getUser().getGameHistory().add(time + ":1:" + GraphicController.getOpponent().getUsername() + ":" + GraphicController.getOpponent().getLevel() + ":" + "10");
//                GraphicController.getOpponent().getGameHistory().add(time + ":0:" + GraphicController.getUser().getUsername() + ":" + GraphicController.getUser().getLevel() + ":" + "10");
//            } else if (out.equals(Outputs.PLAYER2_WON)) {
//                GraphicController.getUser().getGameHistory().add(time + ":0:" + GraphicController.getOpponent().getUsername() + ":" + GraphicController.getOpponent().getLevel() + ":" + "10");
//                GraphicController.getOpponent().getGameHistory().add(time + ":1:" + GraphicController.getUser().getUsername() + ":" + GraphicController.getUser().getLevel() + ":" + "10");
//            } else {
//                GraphicController.getUser().getGameHistory().add(time + ":-1:" + GraphicController.getOpponent().getUsername() + ":" + GraphicController.getOpponent().getLevel() + ":" + "10");
//                GraphicController.getOpponent().getGameHistory().add(time + ":-1:" + GraphicController.getUser().getUsername() + ":" + GraphicController.getUser().getLevel() + ":" + "10");
//            }
//            AppData.saveDataOtherThanUsername(GraphicController.getOpponent());
//            AppData.saveDataOtherThanUsername(GraphicController.getUser());
//            GraphicController.setOpponent(null);
//            endGameMenuGraphic menu = new endGameMenuGraphic();
//            try {
//                menu.start(GraphicController.getStage());
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        }
        else if (out.equals(Outputs.IN_TIME_LINE)) {
            int result = playTimeLine();
            if (result == -1) {
                resetTimeline();
            }
        }
        reset();
    }

    public static void wait(int ms) {
        try
        {
            Thread.sleep(ms);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }

    private int playTimeLine() {
        for (int i = 0; i < 21; i++) {
            timeLines[0][i] = GraphicController.getTimelinesCard()[0][i];
            timeLines[1][i] = GraphicController.getTimelinesCard()[1][i];
        }
        for (int i = 0; i < 21; i++) {
            checkDamage(i);
            if (cardAD1 > cardAD2) GraphicController.getLives()[1] -= cardDamage1;
            else if (cardAD1 < cardAD2) GraphicController.getLives()[0] -= cardDamage2;
            DamageP2.setText(Integer.toString(GraphicController.getPlayersDamage()[1]));
            DamageP1.setText(Integer.toString(GraphicController.getPlayersDamage()[0]));
            player1live.setText(Integer.toString(GraphicController.getLives()[0]));
            player2live.setText(Integer.toString(GraphicController.getLives()[1]));
            wait(3000);
            System.out.println(i);
            System.out.println(cardDamage1);
            System.out.println(cardDamage2);
            if (GraphicController.getLives()[0] <= 0 && GraphicController.getLives()[1] <= 0) return 2;
            if (GraphicController.getLives()[0] <= 0 && GraphicController.getLives()[1] > 0) return 1;
            if (GraphicController.getLives()[0] > 0 && GraphicController.getLives()[1] <= 0) return 0;
        }
        return -1;
    }

    private void checkDamage(int i) {
        if (timeLines[0][i].getCardName().equals("null"))
            cardType1 = -1;
        else if (!timeLines[0][i].getCardName().equals("null") && timeLines[0][i].getClass().equals(NormalCard.class))
            cardType1 = 0;
        else
            cardType1 = 1;

        if (timeLines[1][i].getCardName().equals("null"))
            cardType2 = -1;
        else if (!timeLines[1][i].getCardName().equals("null") && timeLines[1][i].getClass().equals(NormalCard.class))
            cardType2 = 0;
        else
            cardType2 = 1;

        cardDamage1 = cardDamage2 = 0;

        if (cardType1 == 0) {cardAD1 = ((NormalCard) timeLines[0][i]).getCardAttack_Deffence(); cardDamage1 = ((NormalCard) timeLines[0][i]).getPlayerDamage();}
        if (cardType2 == 0) {cardAD2 = ((NormalCard) timeLines[1][i]).getCardAttack_Deffence(); cardDamage2 = ((NormalCard) timeLines[1][i]).getPlayerDamage();}

        if (cardType1 == 1 || cardType1 == -1) cardAD1 = 0;
        if (cardType2 == 1 || cardType2 == -1) cardAD2 = 0;

        handleSpecial(i);
    }

    private void handleSpecial(int i) {
        if (timeLines[0][i].getCardName().equals("shield"))
            cardAD2 = 0;
        if (timeLines[1][i].getCardName().equals("shield"))
            cardAD1 = 0;

        if (timeLines[0][i].getCardName().equals("heal"))
            GraphicController.getLives()[0] += 50;
        if (timeLines[1][i].getCardName().equals("heal"))
            GraphicController.getLives()[1] += 50;
    }

    private void resetTimeline() {
        Random random = new Random();
        int f = Math.abs(random.nextInt()) % 21, s = Math.abs(random.nextInt()) % 21;
        for (int i = 0; i < 21; i++) {
            timeLines[0][i] = new Card();
            timeLines[1][i] = new Card();
            if (i == f) {
                timeLines[0][i].setCardName("null");
                timeLines[0][i].setImageLink("/CardImage/null.png");
            }
            if (i == s) {
                timeLines[1][i].setCardName("null");
                timeLines[0][i].setImageLink("/CardImage/null.png");
            }
            GraphicController.getTimelinesCard()[1][i] = timeLines[0][i];
            GraphicController.getTimelinesCard()[0][i] = timeLines[1][i];
            GraphicController.getTimlines()[1][i + 1].setFill(new ImagePattern(new Image(twoPlayerGame.class.getResource(GraphicController.getTimelinesCard()[1][i].getImageLink()).toExternalForm())));
            GraphicController.getTimlines()[0][i + 1].setFill(new ImagePattern(new Image(twoPlayerGame.class.getResource(GraphicController.getTimelinesCard()[0][i].getImageLink()).toExternalForm())));
            int []it = {0, 0};
            GraphicController.setPlayersDamage(it);
        }
        GraphicController.getPlayersRound()[0] = GraphicController.getPlayersRound()[1] = 4;
    }

    private void showCardProperty(int cardIndexToShow, int playerIndexToShowCard) {
        if (GraphicController.getPlayersDeckCard()[playerIndexToShowCard][cardIndexToShow] == null)
            return;
        String command = "empty";
        if (GraphicController.getPlayersDeckCard()[playerIndexToShowCard][cardIndexToShow].getClass().equals(EspecialCard.class))
            command = String.format("Card name: %s\nExplanation: %s", ((EspecialCard) GraphicController.getPlayersDeckCard()[playerIndexToShowCard][cardIndexToShow]).getCardName(), ((EspecialCard) GraphicController.getPlayersDeckCard()[playerIndexToShowCard][cardIndexToShow]).getCardExplanation());
        else if (GraphicController.getPlayersDeckCard()[playerIndexToShowCard][cardIndexToShow].getClass().equals(NormalCard.class))
            command = String.format("Card name: %s\nCard A/D: %d\nCard Damage: %d", ((NormalCard) GraphicController.getPlayersDeckCard()[playerIndexToShowCard][cardIndexToShow]).getCardName(), ((NormalCard) GraphicController.getPlayersDeckCard()[playerIndexToShowCard][cardIndexToShow]).getCardAttack_Deffence(), ((NormalCard) GraphicController.getPlayersDeckCard()[playerIndexToShowCard][cardIndexToShow]).getPlayerDamage());
        ShowCardInformation.setWrappingWidth(150);
        ShowCardInformation.setText(command);
    }

    public void choseCardToPlay10(MouseEvent mouseEvent) {
        if (giveAlertForNotTurn(0) == 1) return;
        playersdeck[0][0].setOpacity(0.5);
        chosedPlayerCardIndex = 0;
    }

    public void choseCardToPlay11(MouseEvent mouseEvent) {
        if (giveAlertForNotTurn(0) == 1) return;
        playersdeck[0][1].setOpacity(0.5);
        chosedPlayerCardIndex = 1;
    }

    public void choseCardToPlay12(MouseEvent mouseEvent) {
        if (giveAlertForNotTurn(0) == 1) return;
        playersdeck[0][2].setOpacity(0.5);
        chosedPlayerCardIndex = 2;
    }

    public void choseCardToPlay13(MouseEvent mouseEvent) {
        if (giveAlertForNotTurn(0) == 1) return;
        playersdeck[0][3].setOpacity(0.5);
        chosedPlayerCardIndex = 3;
    }

    public void choseCardToPlay14(MouseEvent mouseEvent) {
        if (giveAlertForNotTurn(0) == 1) return;
        playersdeck[0][4].setOpacity(0.5);
        chosedPlayerCardIndex = 4;
    }

    public void choseCardToPlay15(MouseEvent mouseEvent) {
        if (giveAlertForNotTurn(0) == 1) return;
        playersdeck[0][5].setOpacity(0.5);
        chosedPlayerCardIndex = 5;
    }

    public void choseCardToPlay20(MouseEvent mouseEvent) {
        if (giveAlertForNotTurn(1) == 1) return;
        playersdeck[1][0].setOpacity(0.5);
        chosedPlayerCardIndex = 0;
    }

    public void choseCardToPlay21(MouseEvent mouseEvent) {
        if (giveAlertForNotTurn(1) == 1) return;
        playersdeck[1][1].setOpacity(0.5);
        chosedPlayerCardIndex = 1;
    }

    public void choseCardToPlay22(MouseEvent mouseEvent) {
        if (giveAlertForNotTurn(1) == 1) return;
        playersdeck[1][2].setOpacity(0.5);
        chosedPlayerCardIndex = 2;
    }

    public void choseCardToPlay23(MouseEvent mouseEvent) {
        if (giveAlertForNotTurn(1) == 1) return;
        playersdeck[1][3].setOpacity(0.5);
        chosedPlayerCardIndex = 3;
    }

    public void choseCardToPlay24(MouseEvent mouseEvent) {
        if (giveAlertForNotTurn(1) == 1) return;
        playersdeck[1][4].setOpacity(0.5);
        chosedPlayerCardIndex = 4;
    }

    public void choseCardToPlay25(MouseEvent mouseEvent) {
        if (giveAlertForNotTurn(1) == 1) return;
        playersdeck[1][5].setOpacity(0.5);
        chosedPlayerCardIndex = 5;
    }

    public void placeCardin00(MouseEvent mouseEvent) {
    }

    public void placeCardin01(MouseEvent mouseEvent) {
        if (giveAlertForInvalidPlace(1) == 1) return;
        if (giveAlertForNotChoosedCard(chosedPlayerCardIndex) == 1) return;
        chosedTimeLineIndex = 0;
        placeCard();
    }

    public void placeCardin02(MouseEvent mouseEvent) {
        if (giveAlertForInvalidPlace(1) == 1) return;
        if (giveAlertForNotChoosedCard(chosedPlayerCardIndex) == 1) return;
        chosedTimeLineIndex = 1;
        placeCard();
    }

    public void placeCardin03(MouseEvent mouseEvent) {
        if (giveAlertForInvalidPlace(1) == 1) return;
        if (giveAlertForNotChoosedCard(chosedPlayerCardIndex) == 1) return;
        chosedTimeLineIndex = 2;
        placeCard();
    }

    public void placeCardin04(MouseEvent mouseEvent) {
        if (giveAlertForInvalidPlace(1) == 1) return;
        if (giveAlertForNotChoosedCard(chosedPlayerCardIndex) == 1) return;
        chosedTimeLineIndex = 3;
        placeCard();
    }

    public void placeCardin05(MouseEvent mouseEvent) {
        if (giveAlertForInvalidPlace(1) == 1) return;
        if (giveAlertForNotChoosedCard(chosedPlayerCardIndex) == 1) return;
        chosedTimeLineIndex = 4;
        placeCard();
    }

    public void placeCardin06(MouseEvent mouseEvent) {
        if (giveAlertForInvalidPlace(1) == 1) return;
        if (giveAlertForNotChoosedCard(chosedPlayerCardIndex) == 1) return;
        chosedTimeLineIndex = 5;
        placeCard();
    }

    public void placeCardin07(MouseEvent mouseEvent) {
        if (giveAlertForInvalidPlace(1) == 1) return;
        if (giveAlertForNotChoosedCard(chosedPlayerCardIndex) == 1) return;
        chosedTimeLineIndex = 6;
        placeCard();
    }

    public void placeCardin08(MouseEvent mouseEvent) {
        if (giveAlertForInvalidPlace(1) == 1) return;
        if (giveAlertForNotChoosedCard(chosedPlayerCardIndex) == 1) return;
        chosedTimeLineIndex = 7;
        placeCard();
    }

    public void placeCardin09(MouseEvent mouseEvent) {
        if (giveAlertForInvalidPlace(1) == 1) return;
        if (giveAlertForNotChoosedCard(chosedPlayerCardIndex) == 1) return;
        chosedTimeLineIndex = 8;
        placeCard();
    }

    public void placeCardin010(MouseEvent mouseEvent) {
        if (giveAlertForInvalidPlace(1) == 1) return;
        if (giveAlertForNotChoosedCard(chosedPlayerCardIndex) == 1) return;
        chosedTimeLineIndex = 9;
        placeCard();
    }

    public void placeCardin011(MouseEvent mouseEvent) {
        if (giveAlertForInvalidPlace(1) == 1) return;
        if (giveAlertForNotChoosedCard(chosedPlayerCardIndex) == 1) return;
        chosedTimeLineIndex = 10;
        placeCard();
    }

    public void placeCardin012(MouseEvent mouseEvent) {
        if (giveAlertForInvalidPlace(1) == 1) return;
        if (giveAlertForNotChoosedCard(chosedPlayerCardIndex) == 1) return;
        chosedTimeLineIndex = 11;
        placeCard();
    }

    public void placeCardin013(MouseEvent mouseEvent) {
        if (giveAlertForInvalidPlace(1) == 1) return;
        if (giveAlertForNotChoosedCard(chosedPlayerCardIndex) == 1) return;
        chosedTimeLineIndex = 12;
        placeCard();
    }

    public void placeCardin014(MouseEvent mouseEvent) {
        if (giveAlertForInvalidPlace(1) == 1) return;
        if (giveAlertForNotChoosedCard(chosedPlayerCardIndex) == 1) return;
        chosedTimeLineIndex = 13;
        placeCard();
    }

    public void placeCardin015(MouseEvent mouseEvent) {
        if (giveAlertForInvalidPlace(1) == 1) return;
        if (giveAlertForNotChoosedCard(chosedPlayerCardIndex) == 1) return;
        chosedTimeLineIndex = 14;
        placeCard();
    }

    public void placeCardin016(MouseEvent mouseEvent) {
        if (giveAlertForInvalidPlace(1) == 1) return;
        if (giveAlertForNotChoosedCard(chosedPlayerCardIndex) == 1) return;
        chosedTimeLineIndex = 15;
        placeCard();
    }

    public void placeCardin017(MouseEvent mouseEvent) {
        if (giveAlertForInvalidPlace(1) == 1) return;
        if (giveAlertForNotChoosedCard(chosedPlayerCardIndex) == 1) return;
        chosedTimeLineIndex = 16;
        placeCard();
    }

    public void placeCardin018(MouseEvent mouseEvent) {
        if (giveAlertForInvalidPlace(1) == 1) return;
        if (giveAlertForNotChoosedCard(chosedPlayerCardIndex) == 1) return;
        chosedTimeLineIndex = 17;
        placeCard();
    }

    public void placeCardin019(MouseEvent mouseEvent) {
        if (giveAlertForInvalidPlace(1) == 1) return;
        if (giveAlertForNotChoosedCard(chosedPlayerCardIndex) == 1) return;
        chosedTimeLineIndex = 18;
        placeCard();
    }

    public void placeCardin020(MouseEvent mouseEvent) {
        if (giveAlertForInvalidPlace(1) == 1) return;
        if (giveAlertForNotChoosedCard(chosedPlayerCardIndex) == 1) return;
        chosedTimeLineIndex = 19;
        placeCard();
    }

    public void placeCardin021(MouseEvent mouseEvent) {
        if (giveAlertForInvalidPlace(1) == 1) return;
        if (giveAlertForNotChoosedCard(chosedPlayerCardIndex) == 1) return;
        chosedTimeLineIndex = 20;
        placeCard();
    }

    public void placeCardin022(MouseEvent mouseEvent) {
    }

    public void placeCardin023(MouseEvent mouseEvent) {
    }

    public void placeCardin10(MouseEvent mouseEvent) {
    }

    public void placeCardin11(MouseEvent mouseEvent) {
        if (giveAlertForInvalidPlace(0) == 1) return;
        if (giveAlertForNotChoosedCard(chosedPlayerCardIndex) == 1) return;
        chosedTimeLineIndex = 0;
        placeCard();
    }

    public void placeCardin12(MouseEvent mouseEvent) {
        if (giveAlertForInvalidPlace(0) == 1) return;
        if (giveAlertForNotChoosedCard(chosedPlayerCardIndex) == 1) return;
        chosedTimeLineIndex = 1;
        placeCard();
    }

    public void placeCardin13(MouseEvent mouseEvent) {
        if (giveAlertForInvalidPlace(0) == 1) return;
        if (giveAlertForNotChoosedCard(chosedPlayerCardIndex) == 1) return;
        chosedTimeLineIndex = 2;
        placeCard();
    }

    public void placeCardin14(MouseEvent mouseEvent) {
        if (giveAlertForInvalidPlace(0) == 1) return;
        if (giveAlertForNotChoosedCard(chosedPlayerCardIndex) == 1) return;
        chosedTimeLineIndex = 3;
        placeCard();
    }

    public void placeCardin15(MouseEvent mouseEvent) {
        if (giveAlertForInvalidPlace(0) == 1) return;
        if (giveAlertForNotChoosedCard(chosedPlayerCardIndex) == 1) return;
        chosedTimeLineIndex = 4;
        placeCard();
    }

    public void placeCardin16(MouseEvent mouseEvent) {
        if (giveAlertForInvalidPlace(0) == 1) return;
        if (giveAlertForNotChoosedCard(chosedPlayerCardIndex) == 1) return;
        chosedTimeLineIndex = 5;
        placeCard();
    }

    public void placeCardin17(MouseEvent mouseEvent) {
        if (giveAlertForInvalidPlace(0) == 1) return;
        if (giveAlertForNotChoosedCard(chosedPlayerCardIndex) == 1) return;
        chosedTimeLineIndex = 6;
        placeCard();
    }

    public void placeCardin18(MouseEvent mouseEvent) {
        if (giveAlertForInvalidPlace(0) == 1) return;
        if (giveAlertForNotChoosedCard(chosedPlayerCardIndex) == 1) return;
        chosedTimeLineIndex = 7;
        placeCard();
    }

    public void placeCardin19(MouseEvent mouseEvent) {
        if (giveAlertForInvalidPlace(0) == 1) return;
        if (giveAlertForNotChoosedCard(chosedPlayerCardIndex) == 1) return;
        chosedTimeLineIndex = 8;
        placeCard();
    }

    public void placeCardin110(MouseEvent mouseEvent) {
        if (giveAlertForInvalidPlace(0) == 1) return;
        if (giveAlertForNotChoosedCard(chosedPlayerCardIndex) == 1) return;
        chosedTimeLineIndex = 9;
        placeCard();
    }

    public void placeCardin111(MouseEvent mouseEvent) {
        if (giveAlertForInvalidPlace(0) == 1) return;
        if (giveAlertForNotChoosedCard(chosedPlayerCardIndex) == 1) return;
        chosedTimeLineIndex = 10;
        placeCard();
    }

    public void placeCardin112(MouseEvent mouseEvent) {
        if (giveAlertForInvalidPlace(0) == 1) return;
        if (giveAlertForNotChoosedCard(chosedPlayerCardIndex) == 1) return;
        chosedTimeLineIndex = 11;
        placeCard();
    }

    public void placeCardin113(MouseEvent mouseEvent) {
        if (giveAlertForInvalidPlace(0) == 1) return;
        if (giveAlertForNotChoosedCard(chosedPlayerCardIndex) == 1) return;
        chosedTimeLineIndex = 12;
        placeCard();
    }

    public void placeCardin114(MouseEvent mouseEvent) {
        if (giveAlertForInvalidPlace(0) == 1) return;
        if (giveAlertForNotChoosedCard(chosedPlayerCardIndex) == 1) return;
        chosedTimeLineIndex = 13;
        placeCard();
    }

    public void placeCardin115(MouseEvent mouseEvent) {
        if (giveAlertForInvalidPlace(0) == 1) return;
        if (giveAlertForNotChoosedCard(chosedPlayerCardIndex) == 1) return;
        chosedTimeLineIndex = 14;
        placeCard();
    }

    public void placeCardin116(MouseEvent mouseEvent) {
        if (giveAlertForInvalidPlace(0) == 1) return;
        if (giveAlertForNotChoosedCard(chosedPlayerCardIndex) == 1) return;
        chosedTimeLineIndex = 15;
        placeCard();
    }

    public void placeCardin117(MouseEvent mouseEvent) {
        if (giveAlertForInvalidPlace(0) == 1) return;
        if (giveAlertForNotChoosedCard(chosedPlayerCardIndex) == 1) return;
        chosedTimeLineIndex = 16;
        placeCard();
    }

    public void placeCardin118(MouseEvent mouseEvent) {
        if (giveAlertForInvalidPlace(0) == 1) return;
        if (giveAlertForNotChoosedCard(chosedPlayerCardIndex) == 1) return;
        chosedTimeLineIndex = 17;
        placeCard();
    }

    public void placeCardin119(MouseEvent mouseEvent) {
        if (giveAlertForInvalidPlace(0) == 1) return;
        if (giveAlertForNotChoosedCard(chosedPlayerCardIndex) == 1) return;
        chosedTimeLineIndex = 18;
        placeCard();
    }

    public void placeCardin120(MouseEvent mouseEvent) {
        if (giveAlertForInvalidPlace(0) == 1) return;
        if (giveAlertForNotChoosedCard(chosedPlayerCardIndex) == 1) return;
        chosedTimeLineIndex = 19;
        placeCard();
    }

    public void placeCardin121(MouseEvent mouseEvent) {
        if (giveAlertForInvalidPlace(0) == 1) return;
        if (giveAlertForNotChoosedCard(chosedPlayerCardIndex) == 1) return;
        chosedTimeLineIndex = 20;
        placeCard();
    }

    public void placeCardin122(MouseEvent mouseEvent) {
    }

    public void placeCardin123(MouseEvent mouseEvent) {
    }

    public void showCard10Property(MouseEvent mouseEvent) {
        cardIndexToShow = 0;
        playerIndexToShowCard = 0;
        showCardProperty(cardIndexToShow, playerIndexToShowCard);
    }

    public void showCard11Property(MouseEvent mouseEvent) {
        cardIndexToShow = 1;
        playerIndexToShowCard = 0;
        showCardProperty(cardIndexToShow, playerIndexToShowCard);
    }

    public void showCard12Property(MouseEvent mouseEvent) {
        cardIndexToShow = 2;
        playerIndexToShowCard = 0;
        showCardProperty(cardIndexToShow, playerIndexToShowCard);
    }

    public void showCard13Property(MouseEvent mouseEvent) {
        cardIndexToShow = 3;
        playerIndexToShowCard = 0;
        showCardProperty(cardIndexToShow, playerIndexToShowCard);
    }

    public void showCard14Property(MouseEvent mouseEvent) {
        cardIndexToShow = 4;
        playerIndexToShowCard = 0;
        showCardProperty(cardIndexToShow, playerIndexToShowCard);
    }

    public void showCard15Property(MouseEvent mouseEvent) {
        cardIndexToShow = 5;
        playerIndexToShowCard = 0;
        showCardProperty(cardIndexToShow, playerIndexToShowCard);
    }

    public void showCard20Property(MouseEvent mouseEvent) {
        cardIndexToShow = 0;
        playerIndexToShowCard = 1;
        showCardProperty(cardIndexToShow, playerIndexToShowCard);
    }

    public void showCard21Property(MouseEvent mouseEvent) {
        cardIndexToShow = 1;
        playerIndexToShowCard = 1;
        showCardProperty(cardIndexToShow, playerIndexToShowCard);
    }

    public void showCard22Property(MouseEvent mouseEvent) {
        cardIndexToShow = 2;
        playerIndexToShowCard = 1;
        showCardProperty(cardIndexToShow, playerIndexToShowCard);
    }

    public void showCard23Property(MouseEvent mouseEvent) {
        cardIndexToShow = 3;
        playerIndexToShowCard = 1;
        showCardProperty(cardIndexToShow, playerIndexToShowCard);
    }

    public void showCard24Property(MouseEvent mouseEvent) {
        cardIndexToShow = 4;
        playerIndexToShowCard = 1;
        showCardProperty(cardIndexToShow, playerIndexToShowCard);
    }

    public void showCard25Property(MouseEvent mouseEvent) {
        cardIndexToShow = 5;
        playerIndexToShowCard = 1;
        showCardProperty(cardIndexToShow, playerIndexToShowCard);
    }
}
