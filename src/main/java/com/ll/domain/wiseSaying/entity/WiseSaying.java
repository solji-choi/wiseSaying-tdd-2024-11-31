package com.ll.domain.wiseSaying.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class WiseSaying {
    private int id;
    private String content;
    private String author;

    public WiseSaying(Map<String, Object> map) {
        this.id = Integer.parseInt(map.get("id").toString());
        this.content = map.get("content").toString();
        this.author = map.get("author").toString();
    }

    public Map<String, Object> mapToWiseSaying() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("content", content);
        map.put("author", author);

        return map;
    }
}
