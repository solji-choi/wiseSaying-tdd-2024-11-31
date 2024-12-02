package com.ll.domain.wiseSaying.repository;

import com.ll.domain.wiseSaying.entity.WiseSaying;

import java.util.Optional;


public interface WiseSayingRepository {
    public void add(String content, String author);

    public boolean delete(int getParamId);

    public Optional<WiseSaying> find(int id);

    public void modify(WiseSaying wiseSaying, String content, String author);
}
