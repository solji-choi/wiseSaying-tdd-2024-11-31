package com.ll.util;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Util {
    public static class files {
        private static Path path = Paths.get("db/wiseSaying");
        private static File dir = path.toFile();

        public static File pathRowFile (int id) {
            Path path = Path.of(Util.files.path + "/" + id + ".json");

            return path.toFile();
        }

        public static void createFile(int id) {
            File file = Util.files.pathRowFile(id);

            try {
                if(Util.files.dir.isDirectory()){
                    file.createNewFile();
                } else {
                    Util.files.dir.mkdirs();
                    file.createNewFile();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public static void dirDelete() {
            File[] files = Util.files.dir.listFiles();

            for( int i=0; i<files.length; i++){
                files[i].delete();
            }

            Util.files.dir.delete();
        }

        public static String readFile(int id) {
            int readData = -1;
            StringBuilder str = new StringBuilder();

            try {
                FileReader reader = new FileReader(Util.files.pathRowFile(id));

                while ((readData = reader.read()) != -1) {
                    str.append((char) readData);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return str.toString();
        }

        public static void writeFile(int id, String jsonStr) {
            try(
                    PrintWriter pw = new PrintWriter(Util.files.pathRowFile(id));
                    ) {
                pw.print(jsonStr);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static class json {
        public static String jsonTomap(Map<String, Object> map) {
            StringBuilder jsonStr = new StringBuilder();
            Set<String> keys = map.keySet();

            jsonStr.append("{\n");

            for(String key : keys){
                jsonStr.append("    ");
                jsonStr.append("\"" + key + "\": ");
                if(map.get(key) instanceof String) jsonStr.append("\"" + map.get(key)  + "\"");
                else jsonStr.append(map.get(key));
                jsonStr.append(",\n");
            }
            jsonStr.delete(jsonStr.length() - 2, jsonStr.length());
            jsonStr.append("\n}");

            return jsonStr.toString();
        }

        public static Map<String, Object> mapToJson(String jsonStr) {
            Map<String, Object> map = new HashMap<>();
            String[] jsonBits = jsonStr.substring(1, jsonStr.length() - 2).trim().split(", ");
            for(String maps : jsonBits) {
                String[] mapBits = maps.stripIndent().trim().split(": ");
                map.put(mapBits[0], mapBits[1]);
            }

            return map;
        }
    }
}
