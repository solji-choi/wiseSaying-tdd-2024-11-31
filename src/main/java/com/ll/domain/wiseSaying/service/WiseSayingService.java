package com.ll.domain.wiseSaying.service;

import com.ll.domain.wiseSaying.entity.WiseSaying;
import com.ll.domain.wiseSaying.repository.WiseSayingFileRepository;

import java.util.List;
import java.util.Optional;

public class WiseSayingService {
    private final WiseSayingFileRepository wiseSayingRepository;

    public WiseSayingService() {
        this.wiseSayingRepository = new WiseSayingFileRepository();
    }

    public void addWiseSayings(String content, String author) {
        wiseSayingRepository.add(content, author);
    }

    public int getLastId() {
        return wiseSayingRepository.getLastId();
    }

    public List<WiseSaying> getWiseSayings() {
        return wiseSayingRepository.getWiseSayings();
    }

    public int getParamId(String cmd) {
        try {
            return Integer.parseInt(cmd.substring(6));
        } catch (Exception e) {
            return 0;
        }
    }

    public boolean deleteWiseSaying(int id) {
        return wiseSayingRepository.delete(id);
    }

    public Optional<WiseSaying> findWiseSaying(int id) {
        return wiseSayingRepository.find(id);
    }

    public void modifyWiseSaying(WiseSaying wiseSaying, String content, String author) {
        wiseSayingRepository.modify(wiseSaying, content, author);
    }
}
