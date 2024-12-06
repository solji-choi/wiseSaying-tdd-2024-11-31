package com.ll.util;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Util {
    public static class files {
        private static Path path = Paths.get("db/wiseSaying");
        public static File dir = path.toFile();

        public static File pathRowFile (int id) {
            Path path = Path.of(Util.files.path + "/" + id + ".json");

            return path.toFile();
        }

        public static File pathlastId () {
            Path path = Path.of(Util.files.path + "/lastId.txt");

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
            if(Util.files.dir.exists()) {
                File[] files = Util.files.dir.listFiles();

                for( int i=0; i<files.length; i++){
                    files[i].delete();
                }

                Util.files.dir.delete();
            }
        }

        public static String readFile(Object object) {
            int readData = -1;
            StringBuilder str = new StringBuilder();

            try {
                FileReader reader = null;

                if(object instanceof Integer) {
                    reader = new FileReader(Util.files.pathRowFile(Integer.parseInt(object.toString())));
                } else if(object.toString().equals("lastId")) {
                    reader = new FileReader(pathlastId());
                }

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

        public static void writelastId (int id) {
            try(
                    PrintWriter pw = new PrintWriter(pathlastId());
            ) {
                pw.print(id);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public static boolean deleteFile (int id) {
            return Util.files.pathRowFile(id).delete();
        }
    }

    public static class json {
        public static String mapToJson(Map<String, Object> map) {
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

        public static Map<String, Object> jsonToMap(String jsonStr) {
            Map<String, Object> map = new LinkedHashMap<>();

            String[] jsonBits = jsonStr.substring(2, jsonStr.length() - 2).trim().split(",\n");
            for(String maps : jsonBits) {
                String[] mapBits = maps.stripIndent().trim().replace("\"", "").split(": ");
                map.put(mapBits[0], mapBits[1]);
            }

            return map;
        }
    }
}