package Module;

public class EspecialCard extends Card{
    private String cardExplanation;

    public EspecialCard(String cardName, String cardExplanation, int cardValue, int duration, String imageLink) {
        super(cardName, cardValue, duration, imageLink);
        this.cardExplanation = cardExplanation;
    }

    public EspecialCard(EspecialCard card) {
        super(card.getCardName(), card.getCardValue(), card.getDuration(), card.getImageLink());
        this.cardExplanation = card.getCardExplanation();
    }

    public String getCardExplanation() {return cardExplanation;}

    @Override
    public Object clone() throws CloneNotSupportedException {
        EspecialCard newCard = new EspecialCard(this);
        return newCard;
    }
}
