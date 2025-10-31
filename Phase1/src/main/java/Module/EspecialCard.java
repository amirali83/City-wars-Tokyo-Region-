package Module;

public class EspecialCard extends Card{
    private String cardExplanation;
    private int duration;

    public EspecialCard(String cardName, String cardExplanation, int cardValue, int duration) {
        super(cardName, cardValue);
        this.cardExplanation = cardExplanation;
        this.duration = duration;
    }

    public String getCardExplanation() {return cardExplanation;}
    public int getDuration() {return duration;}
}
