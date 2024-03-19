package org.example;

import java.util.List;
import java.util.Scanner;

public class BoardApp {
    BoardManager boardManager = new BoardManager();
    Scanner scan = new Scanner(System.in);
    public void run() {

        while (true) {
            boardManager.logcmd();
            String cmd = scan.nextLine();
            if (cmd.equals("exit")) {
                System.out.println("프로그램을 종료합니다.");
                break; //반복문 탈출
            }
            switch (cmd) {
                case "add" -> boardManager.add();
                case "list" -> boardManager.list();
                case "update" -> boardManager.update();
                case "delete" -> boardManager.delete();
                case "detail" -> boardManager.detail();
                case "search" -> boardManager.search();
                case "signup" -> boardManager.signup();
                case "login" -> boardManager.login();
//                case "sort" -> boardManager.sort();
            }
        }
    }

}
