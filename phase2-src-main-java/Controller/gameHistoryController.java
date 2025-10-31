package Controller;

import View.mainMenuGraphic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import Module.*;

import java.time.chrono.MinguoChronology;
import java.util.ArrayList;

public class gameHistoryController {
    @FXML
    public TableView<GameHistoryData> Table;
    int pageNum = 1;
    int rowNum = 10;


//    private void showTable() {
//        String []line, time;
//        System.out.println("Index\tTime\tWin-Lose\tName\tLevel\ttrophy");
//        for (int i = (page - 1) * pageCount; i < Math.min(page * pageCount, currentUser.getGameHistory().size()); i++) {
//            time = line[0].split("%");
//            System.out.println((i + 1) + "-\t" + time[0] + "." + time[1] + "." + time[2] + "." + time[3] + "." + time[4] + "." +
//                    time[5] + "\t" + line[1] + "\t" + line[2] + "\t" + line[3] + "\t" + line[4]);
//        }
//        System.out.println("page num: " + page);
//        System.out.println();
//    }

    public void update() {

        Table.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("index"));
        Table.getColumns().get(1).getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("date1"));
        Table.getColumns().get(1).getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("date2"));
        Table.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("win"));
        Table.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("opponentName"));
        Table.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("opponentLevel"));
        Table.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("trophy"));

        ObservableList<GameHistoryData> data = FXCollections.observableArrayList();

        ArrayList<GameHistoryData> history = new ArrayList<>();
        String []line, time;
        String win;
        System.out.println("####################################################################################");
        System.out.println(GraphicController.getUser().getGameHistory().size());
        System.out.println(GraphicController.getUser().getGameHistory().get(0));
        System.out.println(GraphicController.getUser().getGameHistory().get(1));
        System.out.println("####################################################################################");
        if (GraphicController.getUser().getGameHistory().size() != 0 && GraphicController.getUser().getGameHistory().size() != 1) {
            for (int i = 1; i < GraphicController.getUser().getGameHistory().size(); i++) {
                line = GraphicController.getUser().getGameHistory().get(i).split(":");
                System.out.println("####################################################################################");
                System.out.println(line[0]);
                System.out.println(line[1]);
                System.out.println("####################################################################################");
                time = line[0].split("%");
                //System.out.println(line.toString());
                if (line[1].equals("1")) win = "Won";
                else if (line[1].equals("0")) win = "Lost";
                else win = "Draw";
                history.add(new GameHistoryData(Integer.toString(i), time[0] + "/" + time[1] + "/" + time[2],
                        time[3] + ":" + time[4] + ":" + time[5], win, line[2], line[3], line[4]));
            }
        }

        for (int i = (pageNum - 1) * rowNum; i < Math.min(pageNum * rowNum, history.size()); i++) {
            data.add(history.get(i));
        }
        Table.setItems(data);
    }

    public void prev(MouseEvent mouseEvent) {
        if (pageNum != 1) pageNum--;
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

    public void next(MouseEvent mouseEvent) {
        if (pageNum * rowNum < GraphicController.getUser().getGameHistory().size()) pageNum++;
        update();
    }
}
