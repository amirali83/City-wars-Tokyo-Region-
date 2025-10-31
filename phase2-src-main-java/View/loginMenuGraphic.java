package View;

import Controller.GraphicController;
import example.AppController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class loginMenuGraphic extends Application{
    public static void main(String[] args) {
        AppController.startGame();
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setResizable(false);
        stage.centerOnScreen();
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane pane = fxmlLoader.load(getClass().getResource("/FXML/loginMenu.fxml"));
        ModuleLayer.Controller controller = fxmlLoader.getController();
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Login Menu");

        Media media = new Media(getClass().getResource("/Songs/song1.mp3").toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setVolume(0.5);
        mediaPlayer.play();

        GraphicController.setStage(stage);
        GraphicController.setController(controller);
        GraphicController.setPane(pane);
        GraphicController.setScene(scene);
        GraphicController.setMediaPlayer(mediaPlayer);
        GraphicController.setMedia(media);

        stage.show();
    }
}
