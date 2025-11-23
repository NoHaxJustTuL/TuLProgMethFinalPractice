package io;

import logic.Map;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MapParser {
    public static Map readFile(String fileName) {
        String name;
        int width;
        int height;
        int maxCommandCount;
        List<List<Integer>> tiles = new ArrayList<List<Integer>>();
        int spawnRow;
        int spawnCol;
        int difficulty;

        InputStream is = ClassLoader.getSystemResourceAsStream(fileName);
        BufferedReader s = new BufferedReader(new InputStreamReader(is));

        try {
            String line;

            // Read name
            name = s.readLine();

            // Read width, height, maxCommandCount, startRow, startCol, difficulty
            String[] parsedDetails = s.readLine().split(",");
            width = Integer.parseInt(parsedDetails[0]);
            height = Integer.parseInt(parsedDetails[1]);
            maxCommandCount = Integer.parseInt(parsedDetails[2]);
            spawnRow = Integer.parseInt(parsedDetails[3]);
            spawnCol = Integer.parseInt(parsedDetails[4]);
            difficulty = Integer.parseInt(parsedDetails[5]);

            // Read tiles
            for (int i = 0; i < height; i++) {
                line = s.readLine();
                if (line.isBlank()) {
                    throw new Exception("Parsing map failed");
                }

                String[] values = line.split(",");

                if (values.length != width) {
                    throw new Exception("Parsing map failed");
                }

                tiles.add(new ArrayList<Integer>());
                for (String value: values) {
                    tiles.get(i).add(Integer.parseInt(value));
                }
            }

            return new Map(name, width, height, maxCommandCount, tiles, spawnRow, spawnCol, difficulty);

        } catch (IOException e) {
            System.out.println("File is not found");
            return null;
        } catch (Exception e) {
            System.out.println("There is an error parsing file");
            return null;
        }

    }
}
