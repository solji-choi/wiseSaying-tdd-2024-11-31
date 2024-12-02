package com.ll.domain.wiseSaying.repository;

import com.ll.domain.wiseSaying.entity.WiseSaying;
import com.ll.util.Util;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
public class WiseSayingFileRepository implements WiseSayingRepository {
    private final List<WiseSaying> wiseSayings;
    private int lastId;

    public WiseSayingFileRepository() {
        this.wiseSayings = new ArrayList<>();
        this.lastId = 0;
    }

    public void add(String content, String author) {
        ++lastId;
        wiseSayings.add(new WiseSaying(lastId, content, author));
        Util.Files.createFile(lastId);
    }

    public boolean delete(int getParamId) {
        return wiseSayings.removeIf(wiseSaying -> wiseSaying.getId() == getParamId);
    }

    public Optional<WiseSaying> find(int id) {
        return  wiseSayings.stream()
                .filter(wiseSaying -> wiseSaying.getId() == id)
                .findFirst();
    }

    public void modify(WiseSaying wiseSaying, String content, String author) {
        wiseSaying.setContent(content);
        wiseSaying.setAuthor(author);
    }
}
