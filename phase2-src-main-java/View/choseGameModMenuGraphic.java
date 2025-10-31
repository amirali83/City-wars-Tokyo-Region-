package View;

import Controller.GraphicController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class choseGameModMenuGraphic extends Application {
    public choseGameModMenuGraphic() {

    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane pane = fxmlLoader.load(getClass().getResource("/FXML/choseModMenu.fxml"));
        ModuleLayer.Controller controller = fxmlLoader.getController();
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Choose Mode");

        GraphicController.setStage(stage);
        GraphicController.setController(controller);
        GraphicController.setPane(pane);
        GraphicController.setScene(scene);

        stage.show();
    }
}
