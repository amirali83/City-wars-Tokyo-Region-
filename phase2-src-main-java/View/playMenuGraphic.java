package View;

import Controller.GraphicController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class playMenuGraphic extends Application {
    public playMenuGraphic() {

    }

    @Override
    public void start(Stage stage) throws Exception {
        System.out.println("123454323454323432");
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane pane = fxmlLoader.load(getClass().getResource("/FXML/playMenu.fxml"));
        ModuleLayer.Controller controller = fxmlLoader.getController();
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setTitle("play Menu");
        stage.centerOnScreen();

        GraphicController.setStage(stage);
        GraphicController.setController(controller);
        GraphicController.setPane(pane);
        GraphicController.setScene(scene);

        stage.show();
    }
}
