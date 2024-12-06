package com.ll.domain.wiseSaying.repository;

import com.ll.domain.wiseSaying.entity.WiseSaying;
import com.ll.util.JsonFileFilter;
import com.ll.util.Util;
import lombok.Getter;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Getter
public class WiseSayingFileRepository implements WiseSayingRepository {
    private final List<WiseSaying> wiseSayings;
    private int lastId;

    public WiseSayingFileRepository() {
        this.wiseSayings = new ArrayList<>();
        this.lastId = 0;
    }

    public List<WiseSaying> getWiseSayings() {
        Map<String, Object> map;
        FileFilter jsonFileFilter = new JsonFileFilter();
        wiseSayings.clear();

        if(Util.files.dir.exists()) {
            File[] files = Util.files.dir.listFiles(jsonFileFilter);
            String fileName;

            for(File file : files){
                fileName  = file.getName().replace(".json", "");

                map = Util.json.jsonToMap(Util.files.readFile(Integer.parseInt(fileName)));

                wiseSayings.add(new WiseSaying(Integer.parseInt(map.get("id").toString()), map.get("content").toString(), map.get("author").toString()));
            }
        }

        return wiseSayings;
    }

    public void add(String content, String author) {
        ++lastId;
        //wiseSayings.add(new WiseSaying(lastId, content, author));
        Util.files.createFile(lastId);

        WiseSaying wiseSaying = new WiseSaying(lastId, content, author);
        Util.files.writeFile(lastId, Util.json.mapToJson(wiseSaying.mapToWiseSaying()));
        Util.files.writelastId(lastId);
    }

    public boolean delete(int getParamId) {
        return Util.files.deleteFile(getParamId);
    }

    public Optional<WiseSaying> find(int id) {
        return  wiseSayings.stream()
                .filter(wiseSaying -> wiseSaying.getId() == id)
                .findFirst();
    }

    public void modify(WiseSaying wiseSaying, String content, String author) {
        wiseSaying.setContent(content);
        wiseSaying.setAuthor(author);

        Util.files.writeFile(lastId, Util.json.mapToJson(wiseSaying.mapToWiseSaying()));
    }
}
