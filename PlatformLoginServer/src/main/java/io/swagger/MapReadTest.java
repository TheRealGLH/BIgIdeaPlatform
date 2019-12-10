package io.swagger;

import java.io.IOException;

public class MapReadTest {
    public static void main(String[] args) {
        MapReader mr = MapReader.getInstance();

        try {
            System.out.println(mr.readMap("battlefield"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(mr.getAllMapNames());
    }

}
