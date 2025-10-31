package Module;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;

public class GameHistoryData {
    public String index;
    public String date1;
    public String date2;
    public String win;
    public String opponentName;
    public String opponentLevel;
    public String trophy;

    public GameHistoryData(String index, String date1, String date2, String win, String opponentName, String opponentLevel, String trophy) {
        this.index = index;
        this.date1 = date1;
        this.date2 = date2;
        this.win = win;
        this.opponentName = opponentName;
        this.opponentLevel = opponentLevel;
        this.trophy = trophy;
    }

    public void setIndex(String index) {this.index = index;}
    public void setDate1(String date1) {this.date1 = date1;}
    public void setDate2(String date2) {this.date2 = date2;}
    public void setWin(String win) {this.win = win;}
    public void setOpponentName(String opponentName) {this.opponentName = opponentName;}
    public void setOpponentLevel(String opponentLevel) {this.opponentLevel = opponentLevel;}
    public void setTrophy(String trophy) {this.trophy = trophy;}

    public String getIndex() {return index;}
    public String getDate1() {return date1;}
    public String getDate2() {return date2;}
    public String getWin() {return win;}
    public String getOpponentName() {return opponentName;}
    public String getOpponentLevel() {return opponentLevel;}
    public String getTrophy() {return trophy;}

}
