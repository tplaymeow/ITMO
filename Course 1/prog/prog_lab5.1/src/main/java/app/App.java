package app;

import collectionManager.CollectionManager;
import commands.*;
import exceptions.AnnotationException;
import exceptions.ArgumentsCountException;
import exceptions.ConvertInstructionException;
import model.*;
import utils.CSVConstructor;
import exceptions.CantWriteException;
import utils.Converter;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Класс приложения
 */
public class App {
    private String inputFileName;
    private String outputFileName;
    private CollectionManager collectionManager;
    private final Stack<InputStream> streamsStack = new Stack<>();

    private boolean fileMod = false;

    /**
     * Констуктор с одним параметром.
     * Имя выходного файла по умолчанию output.csv
     * @param inputFileName имя файла с коллекцией для загрузки
     */
    public App(String inputFileName) {
        this.inputFileName = inputFileName;
        this.outputFileName = "output.csv";
        initializeCollectionMangerFromFile();
    }

    /**
     * Конструктор приложения
     * @param inputFileName имя входного файла
     * @param outputFileName имя файла для сохранения
     */
    public App(String inputFileName, String outputFileName) {
        this.inputFileName = inputFileName;
        this.outputFileName = outputFileName;
        initializeCollectionMangerFromFile();
    }

    private void initializeCollectionMangerFromFile() {
        collectionManager = new CollectionManager(this);
        Converter<StudyGroup> converter = new Converter<>(StudyGroup.class);
        Arrays.asList(new Class[]{Color.class, Country.class, FormOfEducation.class, Semester.class})
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
        collectionManager.setCollection(new LinkedList<>());
        try {
            collectionManager.getCollection().addAll(converter.toCollectionOfObjects(CSVConstructor.loadData(inputFileName), true));
            println("Коллекция загруженна из файла " + inputFileName + ". Количество элементов: "
                    + collectionManager.getCollection().size() + " (добавленны только элементы прошедшие валидацию)");
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
                new UpdateCommand(collectionManager),
                new ShowCommand(collectionManager)
        });
    }

    /**
     * Интерактивный режим приоложения
     * @param stream поток с которого будут читаться команды
     */
    public Boolean interactive(InputStream stream) {
        streamsStack.push(stream);
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

        String request = "";
        String[] wordsRequest;

        while (true) {
            try {
                request = reader.readLine();
                if (request == null) {
                    streamsStack.pop().close();
                    return false;
                }
                request = request.trim().replaceAll(" +", " ");
                if (request.equals("exit")) {
                    if (!reader.equals(System.in))
                        streamsStack.pop().close();
                    return false;
                }
            } catch (IOException ignored) { }

            try {
                wordsRequest = request.split(" ", 2);
                if (wordsRequest.length == 1) {
                    wordsRequest = new String[] {wordsRequest[0], ""};
                }
            } catch (NullPointerException e) {
                return true;
            }
            boolean didWork = false;
            for (Command command:
                    collectionManager.getCommands()) {
                if (command.getName().equals(wordsRequest[0])) {
                    try {
                        command.execute(wordsRequest[1]);
                        didWork = true;
                    } catch (ArgumentsCountException | IllegalArgumentException e) {
                        println(e.getMessage());
                        didWork = true;
                    }
                }
            }
            if (!didWork) println("Такой команды не существует");
        }
    }

    /**
     * Метод печатающий строку на экран, если приложение работает не с файлом
     * @param string выводимая строка
     */
    public void println(String string) {
        if (!fileMod) System.out.println(string);
    }

    /**
     * Сохранение коллекции в файл
     */
    public void save() {
        Converter<StudyGroup> converter = new Converter<>(StudyGroup.class);
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        data.add(converter.getNames());
        collectionManager.getCollection().forEach(collection -> data.add(converter.objectToData(collection)));
        try {
            CSVConstructor.saveCSVFromData(data, outputFileName);
        } catch (CantWriteException e) {
            println("Ошибка " + e.getMessage());
            println("Выберите другой файл (save filename)");
        }
    }

    /**
     * setter для поля {@link App#fileMod}
     */
    public void setFileMod(boolean fileMod) {
        this.fileMod = fileMod;
    }

    /**
     * setter для поля {@link App#outputFileName}
     */
    public void setOutputFileName(String outputFileName) {
        this.outputFileName = outputFileName;
    }

    /**
     * getter для поля {@link App#streamsStack}
     */
    public Stack<InputStream> getStreamsStack() {
        return streamsStack;
    }
}
