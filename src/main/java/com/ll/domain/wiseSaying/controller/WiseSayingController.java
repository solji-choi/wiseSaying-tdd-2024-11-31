package com.ll.domain.wiseSaying.controller;

import java.util.Scanner;

public class WiseSayingController {
    private final Scanner scanner;

    public WiseSayingController(Scanner scanner) {
        this.scanner = scanner;
    }

    public void actionAdd() {
        System.out.print("명언 : ");
        String content = scanner.nextLine();

        System.out.print("작가 : ");
        String author = scanner.nextLine();
    }
}
