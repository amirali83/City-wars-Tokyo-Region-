package Module;

public class Card {
    private String cardName;
    private int cardValue;
    private int duration;
    private String imageLink;

    public Card() {
        this.cardName = "empty";
        this.imageLink = "/CardImage/empty.png";
    }

    public Card(String cardName, int cardValue, int duration, String imageLink) {
        this.cardName = cardName;
        this.cardValue = cardValue;
        this.duration = duration;
        this.imageLink = imageLink;
    }

    public Card(Card card) {
        this.cardName = card.getCardName();
        this.cardValue = card.getCardValue();
        this.duration = card.getDuration();
        this.imageLink = card.getImageLink();
    }

    public String getCardName() {return cardName;}
    public int getCardValue() {return cardValue;}
    public int getDuration() {return duration;}
    public String getImageLink() {return imageLink;}

    public void setCardName(String cardName) {this.cardName = cardName;}
    public void setCardValue(int cardValue) {this.cardValue = cardValue;}
    public void setDuration(int duration) {this.duration = duration;}
    public void setImageLink(String imageLink) {this.imageLink = imageLink;}

    @Override
    public Object clone() throws CloneNotSupportedException {
        Card newCard = new Card(this);
        return newCard;
    }
}
