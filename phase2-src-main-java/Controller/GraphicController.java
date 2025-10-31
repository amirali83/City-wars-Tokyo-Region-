package Controller;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import Module.*;

import java.util.ArrayList;

public class GraphicController {
    private static Stage stage;
    private static Scene scene;
    private static Pane pane;
    private static ModuleLayer.Controller controller;
    private static String command;
    private static User user = null;
    private static User opponent = null;
    private static ArrayList<Card> allCards = new ArrayList<>();
    private static int []lives = new int [2];
    private static Rectangle [][]timlines = new Rectangle[2][24];
    private static Card [][]timelinesCard = new Card[2][21];
    private static Rectangle [][]playersDeck = new Rectangle[2][6];
    private static Card [][]playersDeckCard = new Card[2][6];
    private static int inCharge;
    private static int []playersDamage = new int[2];
    private static int []playersRound = new int[2];
    private static String winner;
    private static int gameType;
    private static int bet;
    private static Media media;
    private static MediaPlayer mediaPlayer;

    public static void setStage(Stage stage) {GraphicController.stage = stage;}
    public static void setScene(Scene scene) {GraphicController.scene = scene;}
    public static void setPane(Pane pane) {GraphicController.pane = pane;}
    public static void setController(ModuleLayer.Controller controller) {GraphicController.controller = controller;}
    public static void setCommand(String command) {GraphicController.command = command;}
    public static void setUser(User user) {GraphicController.user = user;}
    public static void setOpponent(User user) {GraphicController.opponent = user;}
    public static void setAllCards(ArrayList<Card> allCards) {GraphicController.allCards.addAll(allCards);}
    public static void setLives(int[] lives) {for (int i = 0; i < 2; i++) GraphicController.lives[i] = lives[i];}
    public static void setTimelines(Rectangle[][] timlines) {for (int i = 0; i < 24; i++) {GraphicController.timlines[0][i] = timlines[0][i]; GraphicController.timlines[1][i] = timlines[1][i];}}
    public static void setTimelines(Card[][] timlines) {for (int i = 0; i < 21; i++) {GraphicController.timelinesCard[0][i] = timlines[0][i]; GraphicController.timelinesCard[1][i] = timlines[1][i];}}
    public static void setPlayersDeck(Rectangle[][] playersDeck) {for (int i = 0; i < 6; i++) {GraphicController.playersDeck[0][i] = playersDeck[0][i]; GraphicController.playersDeck[1][i] = playersDeck[1][i];}}
    public static void setPlayersDeck(Card[][] playersDeck) {for (int i = 0; i < 6; i++) {GraphicController.playersDeckCard[0][i] = playersDeck[0][i]; GraphicController.playersDeckCard[1][i] = playersDeck[1][i];}}
    public static void setInCharge(int inCharge) {GraphicController.inCharge = inCharge;}
    public static void setPlayersDamage(int[] playersDamage) {GraphicController.playersDamage[0] = playersDamage[0]; GraphicController.playersDamage[1] = playersDamage[1];}
    public static void setPlayersRound(int[] playersRound) {GraphicController.playersRound[0] = playersRound[0]; GraphicController.playersRound[1] = playersRound[1];}
    public static void setWinner(String winner) {GraphicController.winner = winner;}
    public static void setGameType(int gameType) {GraphicController.gameType = gameType;}
    public static void setBet(int bet) {GraphicController.bet = bet;}
    public static void setMedia(Media media) {GraphicController.media = media;}
    public static void setMediaPlayer(MediaPlayer mediaPlayer) {GraphicController.mediaPlayer = mediaPlayer;}

    public static Stage getStage() {return stage;}
    public static Scene getScene() {return scene;}
    public static Pane getPane() {return pane;}
    public static ModuleLayer.Controller getController() {return controller;}
    public static String getCommand() {return command;}
    public static User getUser() {return user;}
    public static User getOpponent() {return opponent;}
    public static ArrayList<Card> getAllCards() {return allCards;}
    public static int[] getLives() {return lives;}
    public static Rectangle[][] getTimlines() {return timlines;}
    public static Card[][] getTimelinesCard() {return timelinesCard;}
    public static Rectangle[][] getPlayersDeck() {return playersDeck;}
    public static Card[][] getPlayersDeckCard() {return playersDeckCard;}
    public static int getInCharge() {return inCharge;}
    public static int[] getPlayersDamage() {return playersDamage;}
    public static int[] getPlayersRound() {return playersRound;}
    public static String getWinner() {return winner;}
    public static int getGameType() {return gameType;}
    public static int getBet() {return bet;}
    public static Media getMedia() {return media;}
    public static MediaPlayer getMediaPlayer() {return mediaPlayer;}
}
