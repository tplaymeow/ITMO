package utils;

import exceptions.CantWriteException;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Класс конструктора csv файло
 */
public class CSVConstructor {
    private static final String separator = ",";
    private static final String quotation = "'";

    /**
     * Сохраняет данные как csv файл
     * @param data данные
     * @param fileName имя файла
     * @throws CantWriteException
     */
    public static void saveCSVFromData(ArrayList<ArrayList<String>> data, String fileName) throws CantWriteException {
        try {
            File file = new File(fileName);
            file.createNewFile();
            if (!file.canWrite()) throw new CantWriteException();
            BufferedWriter fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            for (ArrayList<String> objData:
                 data) {
                fileWriter.write(quotation + String.join(quotation + separator + quotation, objData) + quotation + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Получает данные из csv
     * @param fileName имя файла
     * @return данные
     * @throws IOException
     */
    public static ArrayList<ArrayList<String>> loadData(String fileName) throws IOException {
        ArrayList<ArrayList<String>> objects = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        String readLine;
        while ((readLine = reader.readLine()) != null) {
            try {
                ArrayList<String> object = new ArrayList<String>(Arrays.asList(readLine.split(separator)));
                for (int i = 0; i < object.size(); i++) {
                    object.set(i, object.get(i).substring(quotation.length(), object.get(i).length() - quotation.length()));
                }
                objects.add(object);
            } catch (Exception ignored) { }
        }
        return objects;
    }
}
