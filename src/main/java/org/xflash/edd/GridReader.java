package org.xflash.edd;

import java.io.*;

public class GridReader {
    public static Grid from(File file) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {

            String line = nextReadableLine(br);
            String[] split = line.split(" ");
            if (split.length != 2)
                throw new IllegalArgumentException("First readable line should contains 2 arguments");
            Grid grid;
            try {
                grid = new Grid(Integer.valueOf(split[0]), Integer.valueOf(split[1]));

            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("First line should contain 2 integers W H");
            }

            return grid;
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Grid file not found: " + file, e);
        } catch (IOException e) {
            throw new RuntimeException("IOException during reading Grid file " + file, e);
        }
    }

    private static String nextReadableLine(BufferedReader br) throws IOException {
        String s = br.readLine();
        if (s == null) return null;
        s = s.trim();
        while (!s.trim().startsWith("#")) {
            s = br.readLine();
        }

        return s;
    }
}
