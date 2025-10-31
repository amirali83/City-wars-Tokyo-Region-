package View;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Matcher;

import Module.*;

public class GameHistoryMenu {
    private User currentUser;
    private Scanner sc;
    private int page = 1;
    private int pageCount = 10;

    public GameHistoryMenu(Scanner sc, User currentUser) {
        this.sc = sc;
        this.currentUser = currentUser;
    }

    public void start() {
        System.out.println("Game history menu");
        String command = "sort -m date -w up";
        sortPage(Inputs.SORT.getMatcher(command));
        showTable();
        command = sc.nextLine();
        do {
            if (Inputs.SORT.getMatcher(command).matches())
                sortPage(Inputs.SORT.getMatcher(command));
            else if (Inputs.NEXT_PAGE.getMatcher(command).matches())
                nextPage();
            else if (Inputs.PREVIOUS_PAGE.getMatcher(command).matches())
                previousPage();
            else if (Inputs.PAGE_NUM.getMatcher(command).matches())
                pageNum(Inputs.PAGE_NUM.getMatcher(command));
            else if (Inputs.HELP.getMatcher(command).matches())
                help();
            else
                System.out.println("Invalid command");
            if (!Inputs.HELP.getMatcher(command).matches())
                showTable();

            command = sc.nextLine();
        } while(!command.equals("back"));
        System.out.println("back");
    }

    private void help() {
        System.out.println(Inputs.SORT.getString());
        System.out.println("sort methods are date/winOrLose/name/level");
        System.out.println(Inputs.NEXT_PAGE.getString());
        System.out.println(Inputs.PREVIOUS_PAGE.getString());
        System.out.println(Inputs.PAGE_NUM.getString());
        System.out.println();
    }

    private void showTable() {
        String []line, time;
        System.out.println("Index\tTime\tWin-Lose\tName\tLevel\ttrophy");
        for (int i = (page - 1) * pageCount; i < Math.min(page * pageCount, currentUser.getGameHistory().size()); i++) {
            line = currentUser.getGameHistory().get(i).split(":");
            time = line[0].split("%");
            System.out.println((i + 1) + "-\t" + time[0] + "." + time[1] + "." + time[2] + "." + time[3] + "." + time[4] + "." +
                    time[5] + "\t" + line[1] + "\t" + line[2] + "\t" + line[3] + "\t" + line[4]);
        }
        System.out.println("page num: " + page);
        System.out.println();
    }

    private void pageNum(Matcher matcher) {
        matcher.find();
        page = Integer.parseInt(matcher.group("page"));
    }

    private void previousPage() {
        if (page != 1) page--;
    }

    private void sortPage(Matcher matcher) {
        matcher.find();
        boolean flag = true;
        if (matcher.group("upOrDown").equals("up")) flag = true;
        else if (matcher.group("upOrDown").equals("down")) flag = false;
        else {
            System.out.println("Invalid sort");
            return;
        }

        if (matcher.group("method").equals("date")) {
            sortMethod(0, flag);
        } else if (matcher.group("method").equals("winOrLose")) {
            sortMethod(1, flag);
        } else if (matcher.group("method").equals("name")) {
            sortMethod(2, flag);
        } else if (matcher.group("method").equals("level")) {
            sortMethod(3, flag);
        } else
            System.out.println("Invalid method");
    }

    private boolean compare(String a, String b) {
        int i = 0;
        while (true) {
            if (a.length() == i)
                return true;
            if (b.length() == i)
                return false;
            if (a.charAt(i) > b.charAt(i))
                return false;
            else if (a.charAt(i) < b.charAt(i))
                return true;
            i++;
        }
    }

    private void sortMethod(int method, boolean flag) {
        String []line1, line2;
        String []line11, line22;
        for (int i = currentUser.getGameHistory().size() - 1; i > -1; i--) {
            for (int j = 0; j < i; j++) {
                line1 = currentUser.getGameHistory().get(j).split(":");
                line2 = currentUser.getGameHistory().get(j + 1).split(":");
                if (method == 0) {
                    line11 = line1[method].split("%");
                    line22 = line2[method].split("%");
                    for (int k = 0; k < 6; k ++) {
                        boolean t = Integer.parseInt(line11[k]) > Integer.parseInt(line22[k]);
                        if (t == flag) {
                            Collections.swap(currentUser.getGameHistory(), j, j + 1);
                            break;
                        }
                    }
                } else if (method == 1) {
                    boolean t = Integer.parseInt(line1[method]) > Integer.parseInt(line2[method]);
                    if (t != flag) Collections.swap(currentUser.getGameHistory(), j, j + 1);
                } else if (method == 2) {
                    if (compare(line1[method], line2[method]) != flag)
                        Collections.swap(currentUser.getGameHistory(), j, j + 1);
                } else if (method == 3) {
                    boolean t = Integer.parseInt(line1[method]) > Integer.parseInt(line2[method]);
                    if (t != flag) Collections.swap(currentUser.getGameHistory(), j, j + 1);
                }
            }
        }
    }

    private void nextPage() {
        page++;
    }
}
