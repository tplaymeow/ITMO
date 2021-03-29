package utils;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class CSVConstructor {
    private static String separator = ",";
    private static String quotation = "'";

    public static void saveCSVFromData(ArrayList<ArrayList<String>> data, ArrayList<String> names, String fileName) {
        try {
            BufferedWriter fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName)));
            for (ArrayList<String> objData:
                 data) {
                fileWriter.write(quotation + String.join(quotation + separator + quotation, objData) + quotation + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<ArrayList<String>> loadFromData(String fileName) throws IOException {
        ArrayList<ArrayList<String>> objects = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        String readLine;
        while ((readLine = reader.readLine()) != null) {
            ArrayList<String> object = new ArrayList<String>(Arrays.asList(readLine.split(separator)));
            for (int i = 0; i < object.size(); i++) {
                object.set(i, object.get(i).substring(quotation.length(), object.get(i).length() - quotation.length()));
            }
            objects.add(object);
        }
        return objects;
    }
}
