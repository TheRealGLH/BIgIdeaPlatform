package io.swagger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MapReader {

    private static String directory = "maps/";
    private static String extension = ".json";
    private static MapReader instance = null;

    private MapReader() {
    }

    public static MapReader getInstance() {
        if (instance == null) instance = new MapReader();
        return instance;
    }


    public String readMap(String mapName) throws IOException {
        File file = getFileFromResources(directory + mapName + extension);
        StringBuilder sb = new StringBuilder();
        try (FileReader reader = new FileReader(file);
             BufferedReader br = new BufferedReader(reader)) {

            String line;

            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        }
        return sb.toString();
    }

    public String getAllMapNames() {
        StringBuilder sb = new StringBuilder();
        ClassLoader loader = getClass().getClassLoader();
        URL url = loader.getResource(directory);
        String path = url.getPath();
        File[] files =  new File(path).listFiles();
        boolean notFirstFile = false;
        sb.append("{");
        for (File file : files) {
            if(notFirstFile) sb.append(", ");
            notFirstFile = true;
            String filename = file.getName().substring(0,file.getName().length()-extension.length());
            sb.append("\"").append(filename).append("\"");
        }
        sb.append("}");
        return sb.toString();
    }

    public List<String> getAllMapsList() {
        List<String> list = new ArrayList<>();
        ClassLoader loader = getClass().getClassLoader();
        URL url = loader.getResource(directory);
        String path = url.getPath();
        File[] files =  new File(path).listFiles();
        for (File file : files) {
            String filename = file.getName().substring(0,file.getName().length()-extension.length());
            list.add(filename);
        }
        return list;
    }

    private File getFileFromResources(String fileName) {

        ClassLoader classLoader = getClass().getClassLoader();

        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
            return new File(resource.getFile());
        }

    }

}
