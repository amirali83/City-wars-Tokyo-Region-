package Controller;

import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import View.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class settingMenuController {
    public Rectangle songImage;
    public Slider soundVolume;

    int songNum = 1;
    public final int MAX_NUM = 3;

    public void updateee(MouseEvent mouseEvent) {
        GraphicController.getMediaPlayer().stop();
        String command = String.format("/Songs/%d.png", songNum);
        songImage.setFill(new ImagePattern(new Image(settingMenuController.class.getResource(command).toExternalForm())));
        command = String.format("/Songs/song%d.mp3", songNum);
        Media media = new Media(getClass().getResource(command).toExternalForm());
        GraphicController.setMedia(media);
        GraphicController.setMediaPlayer(new MediaPlayer(media));
        GraphicController.getMediaPlayer().setCycleCount(MediaPlayer.INDEFINITE);
        GraphicController.getMediaPlayer().setVolume(soundVolume.getValue() / 100);
        System.out.println(soundVolume.getValue());
        GraphicController.getMediaPlayer().play();
    }

    public void update(MouseEvent mouseEvent) {
        GraphicController.getMediaPlayer().setVolume(soundVolume.getValue() / 100);
    }

    public void prev(MouseEvent mouseEvent) {
        if (songNum != 1) {
            songNum--;
            GraphicController.getMediaPlayer().stop();
            updateee(null);
        }
    }

    public void next(MouseEvent mouseEvent) {
        if (songNum != MAX_NUM) {
            songNum++;
            GraphicController.getMediaPlayer().stop();
            updateee(null);
        }
    }

    public void back(MouseEvent mouseEvent) {
        mainMenuGraphic menu = new mainMenuGraphic();
        try {
            menu.start(GraphicController.getStage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
