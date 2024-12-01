package com.ll.domain.wiseSaying.controller;

import java.util.Scanner;

public class WiseSayingController {
    private final Scanner scanner;
    private int lastId;

    public WiseSayingController(Scanner scanner) {
        this.scanner = scanner;
        this.lastId = 0;
    }

    public void actionAdd() {
        System.out.print("명언 : ");
        String content = scanner.nextLine();

        System.out.print("작가 : ");
        String author = scanner.nextLine();

        ++lastId;
        System.out.println(lastId + "번 명언이 등록되었습니다.");
    }
}
