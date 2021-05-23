package app;

import collectionManager.CollectionManager;
import commands.*;
import model.StudyGroup;
import utils.CSVConstructor;
import utils.CustomBufferReader;
import utils.Parser;
import utils.Validator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;

public class App {
    private String inputFileName;
    private String outputFileName;
    private CollectionManager collectionManager;


    public App(String inputFileName) {
        this.inputFileName = inputFileName;
        this.outputFileName = "output.csv";
        initializeCollectionMangerFromFile();
    }

    public App(String inputFileName, String outputFileName) {
        this.inputFileName = inputFileName;
        this.outputFileName = outputFileName;
        initializeCollectionMangerFromFile();
    }

    private void initializeCollectionMangerFromFile() {
        collectionManager = new CollectionManager();
        Parser<StudyGroup> parser = new Parser<>(StudyGroup.class);

        // Добаление коллекции из файла
        collectionManager.setCollection(new LinkedList<>());
        try {
            collectionManager.getCollection().addAll(parser.collectionFromData(CSVConstructor.loadFromData(inputFileName)));

            boolean isValid = true;
            Validator validator = new Validator(StudyGroup.class);

            for (StudyGroup group:
                 collectionManager.getCollection()) {
                isValid = validator.validate(group);
            }

            if (!isValid) {
                collectionManager.setCollection(new LinkedList<>());
                System.out.println("Данные не коректны. Созданна пустая коллекция");
            }

        } catch (InvocationTargetException | NoSuchMethodException | NoSuchFieldException | InstantiationException | IllegalAccessException e) {
            // TODO: что-то придумать, хотя это не должно высираться
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Файл не найден. Созданна пустая коллекция");
        }

        // Добавление команд в Collection Manager
        collectionManager.setCommands(new Command[] {
                new AddCommand(collectionManager),
                new AddIfMinCommand(collectionManager),
                new ClearCommand(collectionManager),
                new CountGreaterThanStudentsCountCommand(collectionManager),
                new CountLessThanShouldBeExpelledCommand(collectionManager),
                new ExecuteScriptCommand(collectionManager),
                new FilterBySemesterEnumCommand(collectionManager),
                new HelpCommand(collectionManager),
                new InfoCommand(collectionManager),
                new RemoveAtCommand(collectionManager),
                new RemoveByIdCommand(collectionManager),
                new SaveCommand(collectionManager),
                new SortCommand(collectionManager),
                new UpdateCommand(collectionManager)
        });
    }

    public Boolean interactive(InputStream stream) {
        CustomBufferReader reader = new CustomBufferReader(new InputStreamReader(stream));
        String request = "";
        String[] wordsRequest;

        while (true) {
            try {
                request = reader.readLine();
                request = request.trim().replaceAll(" +", " ");
            } catch (IOException ignored) { }

            try {
                wordsRequest = request.split(" ", 2);
                if (wordsRequest.length == 1) {
                    wordsRequest = new String[] {wordsRequest[0], ""};
                }
            } catch (NullPointerException e) {
                return true;
            }

            for (Command command:
                 collectionManager.getCommands()) {
                if (command.getName().equals(wordsRequest[0])) {
                    command.execute(wordsRequest[1]);
                }
            }
        }
    }

    public void print() {
        System.out.println();
    }
}