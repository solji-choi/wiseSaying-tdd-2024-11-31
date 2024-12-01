package com.ll.domain.wiseSaying.repository;

import com.ll.domain.wiseSaying.entity.WiseSaying;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class WiseSayingRepository {
    private final List<WiseSaying> wiseSayings;
    private int lastId;

    public WiseSayingRepository() {
        this.wiseSayings = new ArrayList<>();
        this.lastId = 0;
    }

    public void add(String content, String author) {
        ++lastId;
        wiseSayings.add(new WiseSaying(lastId, content, author));
    }
}
