package com.ll.domain.wiseSaying.controller;

import com.ll.domain.wiseSaying.entity.WiseSaying;
import com.ll.domain.wiseSaying.service.WiseSayingService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class WiseSayingController {
    private final Scanner scanner;
    private final WiseSayingService wiseSayingService;

    public WiseSayingController(Scanner scanner) {
        this.scanner = scanner;
        this.wiseSayingService = new WiseSayingService();
    }

    public void actionAdd() {
        System.out.print("명언 : ");
        String content = scanner.nextLine();

        System.out.print("작가 : ");
        String author = scanner.nextLine();

        wiseSayingService.addWiseSayings(content, author);

        System.out.println(wiseSayingService.getLastId() + "번 명언이 등록되었습니다.");
    }

    public void actionList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");

        List<WiseSaying> wiseSayings = wiseSayingService.getWiseSayings();

        for(WiseSaying wiseSaying : wiseSayings.reversed()) {
            System.out.println("%d / %s / %s".formatted(wiseSaying.getId(), wiseSaying.getAuthor(), wiseSaying.getContent()));
        }
    }

    public void actionDelete(String cmd) {
        int id = wiseSayingService.getParamId(cmd);

        if(id != 0) {
            if(wiseSayingService.deleteWiseSaying(id)){
                System.out.println(id + "번 명언이 삭제되었습니다.");
            } else {
                System.out.println(id + "번 명언은 존재하지 않습니다.");
            }
        } else {
            System.out.println("삭제할 id를 입력해주세요(숫자)");
        }
    }

    public void actionModify(String cmd) {
        int id = wiseSayingService.getParamId(cmd);

        if(id != 0) {
            List<WiseSaying> wiseSayings = wiseSayingService.getWiseSayings();

            Optional<WiseSaying> opWiseSaying = wiseSayings.stream()
                    .filter(wiseSaying -> wiseSaying.getId() == id)
                    .findFirst();

            WiseSaying wiseSaying = opWiseSaying.get();

            System.out.println("명언(기존) : " + wiseSaying.getContent());
            String content = scanner.nextLine();

            System.out.println("작가(기존) : " + wiseSaying.getAuthor());
            String author = scanner.nextLine();
        } else {
            System.out.println("수정할 id를 입력해주세요(숫자)");
        }
    }
}
