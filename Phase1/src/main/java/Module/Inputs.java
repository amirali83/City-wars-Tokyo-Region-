package Module;

import java.util.regex.*;

public enum Inputs {
    SIGN_UP_NOT_RANDOM("user create -u (?<username>.+) -p (?<password>\\S+) (?<passwordConfirmation>\\S+) -email (?<email>.+) -n (?<nickname>.+)",
            "user create -u <username> -p <password> <passwordConfirmation> -email <email> -n <nickname>"),
    SIGN_UP_RANDOM("user create -u (?<username>.+) -p random -email (?<email>.+) -n (?<nickname>.+)",
            "user create -u <username> -p random -email <email>. -n <nickname>"),
    SECURITY_QUESTION("question pick -q (?<questionNumber>.+) -a (?<answer>.+) -c (?<answerConfirm>.+)",
            "question pick -q <questionNumber> -a <answer> -c <answerConfirm>"),
    LOGIN("user login -u (?<username>.+) -p (?<password>.+)",
            "user login -u <username> -p <password>"),
    FORGOT_PASSWORD("Forgot my password -u (?<username>.+)",
            "Forgot my password -u <username>"),
    LOGOUT("log out", "log out"),
    PROFILE_MENU("profile menu", "profile menu"),
    START_GAME("start game", "start game"),
    SHOW_CARDS("show cards", "show cards"),
    GAME_HISTORY_MENU("game history", "game history"),
    SHOP_MENU("shop menu", "shop menu"),
    SHOW_INFORMATION("Show information", "Show information"),
    CHANGE_USERNAME("Profile change -u (?<username>.+)", "Profile change -u <username>"),
    CHANGE_NICKNAME("Profile change -n (?<nickname>.+)", "Profile change -n <nickname>"),
    CHANGE_PASSWORD("Profile change password -o (?<oldPassword>.+) -n (?<newPassword>.+)",
            "Profile change password -o <oldPassword> -n <newPassword>"),
    CHANGE_EMAIL("Profile change -e (?<email>.+)",  "Profile change -e <email>"),
    BUY_NEW_CARD("buy card", "buy card"),
    UPDATE_CARD("update card", "update card"),
    ADMIN_LOGIN("-login admin (?<pass>.+)", "-login admin <pass>"),
    ADMIN_ADD_CARD("add card", "add card"),
    ADMIN_EDIT_CARD("edit card", "edit card"),
    ADMIN_REMOVE_CARD("remove card", "remove card"),
    ADMIN_SEE_ALL_PLAYERS("see all players", "see all players"),
    HELP("help", "help"),
    SORT("sort -m (?<method>.+) -w (?<upOrDown>.+)", "sort -m <method> -w <upOrDown>"),
    NEXT_PAGE("next page", "next page"),
    PREVIOUS_PAGE("previous page", "previous page"),
    PAGE_NUM("page no. (?<page>.+)", "page no. <page>"),
    SELECT_CHARACTER("select character (?<character>.+)", "select character <character>"),
    SHOW_CARD_INFORMATION("-Select card number (?<number>.+) player (?<player>.+)",
            "-Select card number <number> player <player>"),
    PLACE_CARD_NUMBER("-Placing card number (?<number>.+) in block (?<index>.+)",
            "-Placing card number <number> in block <index>"),
    ;

    String regex;
    String string;

    Inputs(String regex, String string) {
        this.regex = regex;
        this.string = string;
    }

    public Matcher getMatcher(String input) {return Pattern.compile(this.regex).matcher(input);}
    public String getString() {return this.string;}

}
