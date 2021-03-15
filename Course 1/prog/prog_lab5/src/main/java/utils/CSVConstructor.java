package utils;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class CSVConstructor {
    public static String separator = ",";

    public static void saveCSVFromData(ArrayList<ArrayList<String>> data, ArrayList<String> names, String fileName) {
        try {
            BufferedWriter fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName)));
            for (ArrayList<String> objData:
                 data) {
                fileWriter.write(String.join(separator, objData) + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<ArrayList<String>> loadFromData(String fileName) {
        ArrayList<ArrayList<String>> objects = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNext()) {
                objects.add(new ArrayList<String>(Arrays.asList(scanner.nextLine().split(separator))));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return objects;
    }
}
