package com.ll.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Util {
    public static class Files {
        private static Path path = Paths.get("db/wiseSaying");
        private static File dir = path.toFile();

        public static void createFile(int id) {
            File file = new File(Util.Files.path + "/" + id + ".json");

            try {
                if(Util.Files.dir.isDirectory()){
                    file.createNewFile();
                } else {
                    Util.Files.dir.mkdirs();
                    file.createNewFile();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public static void dirDelete() {
            File[] files = Util.Files.dir.listFiles();

            for( int i=0; i<files.length; i++){
                files[i].delete();
            }

            Util.Files.dir.delete();
        }
    }
}
