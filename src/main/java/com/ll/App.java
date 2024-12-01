package com.ll;

import com.ll.domain.wiseSaying.controller.WiseSayingController;

import java.util.Scanner;

public class App {
    private final Scanner scanner;
    private final WiseSayingController wiseSayingController;

    public App(Scanner scanner) {
        this.scanner = scanner;
        this.wiseSayingController = new WiseSayingController(scanner);
    }

    public void run() {
        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine();

            if(cmd.equals("종료")) {
                System.out.println("프로그램을 종료합니다.");

                break;
            } else if(cmd.equals("등록")) {
                wiseSayingController.actionAdd();
            } else if(cmd.equals("목록")) {
                wiseSayingController.actionList();
            } else if(cmd.startsWith("삭제")) {
                wiseSayingController.actionDelete(cmd);
            }
        }
    }
}
