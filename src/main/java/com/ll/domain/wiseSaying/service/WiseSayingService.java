package com.ll.domain.wiseSaying.service;

import com.ll.domain.wiseSaying.entity.WiseSaying;
import com.ll.domain.wiseSaying.repository.WiseSayingRepository;

import java.util.List;

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

    public void deleteWiseSaying(int getParamId) {
        wiseSayingRepository.delete(getParamId);
    }
}
