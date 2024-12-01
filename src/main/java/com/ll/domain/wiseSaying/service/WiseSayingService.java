package com.ll.domain.wiseSaying.service;

import com.ll.domain.wiseSaying.entity.WiseSaying;
import com.ll.domain.wiseSaying.repository.WiseSayingRepository;

import java.util.List;
import java.util.Optional;

public class WiseSayingService {
    private final WiseSayingRepository wiseSayingRepository;

    public WiseSayingService() {
        this.wiseSayingRepository = new WiseSayingRepository();
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
}
