package Module;

import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String passwordRecoveryA;
    private String passwordRecoveryQ;
    private ArrayList<Card> cards;
    private ArrayList<String> gameHistory;
    private int level;
    private int HP;
    private int XP;
    private int coins;
    private boolean recievedStarterPack;

    public User() {

    }

    public User(String username, String password, String nickname, String email, String passwordRecoveryA, String passwordRecoveryQ) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.passwordRecoveryA = passwordRecoveryA;
        this.passwordRecoveryQ = passwordRecoveryQ;
        this.level = 1;
        this.HP = 100;
        this.XP = 0;
        this.coins = 0;
        this.recievedStarterPack = false;
        this.cards = new ArrayList<>();
        this.gameHistory = new ArrayList<>();
    }

    public User(String username, String password, String nickname, String email, String passwordRecoveryA, String passwordRecoveryQ,
                int level, int HP, int XP, int coins, boolean recievedStarterPack, ArrayList<Card> cards, ArrayList<String> gameHistory) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.passwordRecoveryA = passwordRecoveryA;
        this.passwordRecoveryQ = passwordRecoveryQ;
        this.level = level;
        this.HP = HP;
        this.XP = XP;
        this.coins = coins;
        this.recievedStarterPack = recievedStarterPack;
        this.cards = new ArrayList<>();
        this.cards.addAll(cards);
        this.gameHistory = new ArrayList<>();
        this.gameHistory.addAll(gameHistory);
    }

    public ArrayList<Card> getCards() {return cards;}
    public ArrayList<String> getGameHistory() {return gameHistory;}
    public String getUsername() {return username;}
    public String getNickname() {return nickname;}
    public String getPassword() {return password;}
    public String getEmail() {return email;}
    public String getPasswordRecoveryQ() {return passwordRecoveryQ;}
    public String getPasswordRecoveryA() {return passwordRecoveryA;}
    public boolean getRecievedStarterPack() {return recievedStarterPack;}
    public int getLevel() {return level;}
    public int getHP() {return HP;}
    public int getXP() {return XP;}
    public int getCoins() {return coins;}

    public void setPassword(String password) {this.password = password;}
    public void setUsername(String username) {this.username = username;}
    public void setNickname(String nickname) {this.nickname = nickname;}
    public void setEmail(String email) {this.email = email;}
    public void setRecievedStarterPack(boolean recieved) {this.recievedStarterPack = recieved;}
    public void setCoins(int coins) {this.coins = coins;}

    public boolean hasCard(Card card) {
        if (cards.contains(card)) return true;
        return false;
    }
    public void addCard(Card card) {this.cards.add(card);}
}
