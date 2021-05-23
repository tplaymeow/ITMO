package app;

import commands.commandDescriptions.CommandDescription;
import commands.commands.*;
import exceptions.AnnotationException;
import exceptions.CantWriteException;
import exceptions.ConvertInstructionException;
import managers.CollectionManager;
import managers.network.NetworkManagerInterface;
import model.*;
import response.Response;
import utils.CSVConstructor;
import utils.Converter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.*;

public class App {
    private final String fileName;
    private final CollectionManager collectionManager;
    private final ArrayList<Command> commands = new ArrayList<>();
    private final NetworkManagerInterface<CommandDescription, Response> network;
    private final InputStream inputStream;

    public App(String fileName, NetworkManagerInterface<CommandDescription, Response> network, InputStream inputStream) {
        this.inputStream = inputStream;
        this.network = network;
        this.fileName = fileName;
        this.collectionManager = new CollectionManager(this);
        initializeCollectionMangerFromFile();
    }

    private void initializeCollectionMangerFromFile() {
        Converter<StudyGroup> converter = new Converter<>(StudyGroup.class);
        Arrays.stream(new Class[]{Color.class, Country.class, FormOfEducation.class, Semester.class})
                .forEach(enumClass -> {
                    converter.addInstructionForEnum(enumClass);
                    converter.addPossibleValuesForEnum(enumClass);
                });
        converter.addInstruction(LocalDateTime.class, string -> {
            try {
                return LocalDateTime.parse(string);
            } catch (DateTimeParseException e) {
                throw new ConvertInstructionException(string);
            }
        });

        // Добаление коллекции из файла
        try {
            collectionManager.addAll(converter.toCollectionOfObjects(CSVConstructor.loadData(fileName), true));
            println("Коллекция загруженна из файла " + fileName + ". Количество элементов: "
                    + collectionManager.size() + " (добавленны только элементы прошедшие валидацию)");
        } catch (IllegalAccessException | InstantiationException | AnnotationException e) {
            println(e.getMessage());
            println("Завершение программы...");
            System.exit(0);
        } catch (NoSuchFieldException e) {
            println("Неверно указанно имя поля");
            println("Созданна пустая коллекция");
        } catch (IOException e) {
            println("Ошибка: " + e.getMessage() + ". Созданна пустая коллекция");
        }

        commands.add(new HelpCommand(collectionManager));
        commands.add(new InfoCommand(collectionManager));
        commands.add(new ShowCommand(collectionManager));
        commands.add(new AddCommand(collectionManager));
        commands.add(new UpdateCommand(collectionManager));
        commands.add(new RemoveByIdCommand(collectionManager));
        commands.add(new ClearCommand(collectionManager));
        commands.add(new RemoveAtCommand(collectionManager));
        commands.add(new AddIfMinCommand(collectionManager));
        commands.add(new SortCommand(collectionManager));
        commands.add(new CountLessThanCommand(collectionManager));
        commands.add(new CountGreaterThanCommand(collectionManager));
        commands.add(new FilterCommand(collectionManager));
    }

    public void interactiveMode() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        while (true) {
            network.readObjectWithAction(commandDescription -> commands.stream()
                    .filter(command -> command.getName().equals(commandDescription.getName()))
                    .findFirst()
                    .map(command -> command.execute(commandDescription))
                    .orElse(null));

            // Чтение команд сервера
            try {
                if (inputStream.available() > 0) {
                    String line = reader.readLine();
                    if (line.equals("exit")) {
                        println("Завершение программы...");
                        System.exit(1);
                    } else if (line.equals("save")) {
                        save();
                    } else {
                        println("Неверная команда сервера.");
                    }
                }
            } catch (IOException e) {
                println(e.getLocalizedMessage());
            }
        }
    }

    public void save() {
        Converter<StudyGroup> converter = new Converter<>(StudyGroup.class);
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        data.add(converter.getNames());
        collectionManager.getCollection().forEach(collection -> data.add(converter.objectToData(collection)));
        try {
            CSVConstructor.saveCSVFromData(data, fileName);
        } catch (CantWriteException e) {
            println("Ошибка " + e.getMessage());
            println("Выберите другой файл (save filename)");
        }
    }

    public void println(String string) {
        System.out.println(string);
    }

    // Getter
    public List<Command> getCommands() {
        return Collections.unmodifiableList(commands);
    }
}
