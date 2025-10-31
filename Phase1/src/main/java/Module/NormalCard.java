package Module;

public class NormalCard extends Card {
    private int cardAttack_Deffence;
    private int duration;
    private int playerDamage;
    private int upgradeLevel;
    private int upgradeCost;
    private int level;

    public NormalCard(String cardName, int cardValue, int cardAttack_Deffence, int duration, int playerDamage, int upgradeLevel, int upgradeCost) {
        super(cardName, cardValue);
        this.cardAttack_Deffence = cardAttack_Deffence;
        this.duration = duration;
        this.playerDamage = playerDamage;
        this.upgradeLevel = upgradeLevel;
        this.upgradeCost = upgradeCost;
        this.level = 1;
    }

    public int getCardAttack_Deffence() {return cardAttack_Deffence;}
    public int getDuration() {return duration;}
    public int getPlayerDamage() {return playerDamage;}
    public int getUpgradeLevel() {return upgradeLevel;}
    public int getUpgradeCost() {return upgradeCost;}
    public int getLevel() {return level;}

    public void setCardAttack_Deffence(int AD) {this.cardAttack_Deffence = AD;}
    public void setDuration(int D) {this.duration = D;}
    public void setPlayerDamage(int P) {this.playerDamage = P;}
    public void setUpgradeLevel(int U) {this.upgradeLevel = U;}
    public void setUpgradeCost(int U) {this.upgradeCost = U;}
    public void setLevel(int level) {this.level = level;}
}
