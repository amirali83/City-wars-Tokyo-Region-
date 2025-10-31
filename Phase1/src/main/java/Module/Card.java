package Module;

public class Card {
    private String cardName;
    private int cardValue;

    public Card() {
        this.cardName = "empty";
    }

    public Card(String cardName, int cardValue) {
        this.cardName = cardName;
        this.cardValue = cardValue;
    }

    public String getCardName() {return cardName;}
    public int getCardValue() {return cardValue;}

    public void setCardName(String cardName) {this.cardName = cardName;}
    public void setCardValue(int cardValue) {this.cardValue = cardValue;}

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
