package Module;

public class NormalCard extends Card {
    private int cardAttack_Deffence;
    private int playerDamage;
    private int upgradeLevel;
    private int upgradeCost;
    private int level;
    private GameCh ch = null;

    public NormalCard(String cardName, int cardValue, int cardAttack_Deffence, int duration, int playerDamage, int upgradeLevel, int upgradeCost, GameCh ch, String imageLink) {
        super(cardName, cardValue, duration, imageLink);
        this.cardAttack_Deffence = cardAttack_Deffence;
        this.playerDamage = playerDamage;
        this.upgradeLevel = upgradeLevel;
        this.upgradeCost = upgradeCost;
        this.level = 1;
        this.ch = ch;
    }

    public NormalCard(NormalCard card) {
        super(card.getCardName(), card.getCardValue(), card.getDuration(), card.getImageLink());
        this.cardAttack_Deffence = card.getCardAttack_Deffence();
        this.playerDamage = card.getPlayerDamage();
        this.upgradeLevel = card.getUpgradeLevel();
        this.upgradeCost = card.getUpgradeCost();
        this.level = card.getLevel();
        this.ch = card.getCh();
    }

    public int getCardAttack_Deffence() {return cardAttack_Deffence;}
    public int getPlayerDamage() {return playerDamage;}
    public int getUpgradeLevel() {return upgradeLevel;}
    public int getUpgradeCost() {return upgradeCost;}
    public int getLevel() {return level;}
    public GameCh getCh() {return ch;}

    public void setCardAttack_Deffence(int AD) {this.cardAttack_Deffence = AD;}
    public void setDuration(int D) {super.setDuration(D);}
    public void setPlayerDamage(int P) {this.playerDamage = P;}
    public void setUpgradeLevel(int U) {this.upgradeLevel = U;}
    public void setUpgradeCost(int U) {this.upgradeCost = U;}
    public void setLevel(int level) {this.level = level;}
    public void setCh(GameCh ch) {this.ch = ch;}

    @Override
    public Object clone() throws CloneNotSupportedException {
        NormalCard newCard = new NormalCard(this);
        return newCard;
    }
}
