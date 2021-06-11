package app;

import commands.commandDescriptions.CommandDescription;
import commands.commands.*;
import exceptions.CantWriteException;
import managers.CollectionManager;
import managers.network.NetworkManagerInterface;
import model.*;
import org.apache.logging.log4j.Logger;
import orm.ORM;
import response.Response;
import utils.CSVConstructor;
import utils.Converter;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.*;

public class App {
    private final String fileName;
    private final CollectionManager collectionManager;
    private final ArrayList<Command> commands = new ArrayList<>();
    private final NetworkManagerInterface<CommandDescription, Response> network;
    private final InputStream inputStream;
    private Logger logger;
    private final DataSource dataSource;
    private ArrayList<User> users = new ArrayList<>();

    private ORM<User> userORM;

    public App(String fileName, NetworkManagerInterface<CommandDescription, Response> network, InputStream inputStream, Logger logger, DataSource dataSource) {
        this.logger = logger;
        this.inputStream = inputStream;
        this.network = network;
        this.fileName = fileName;
        this.collectionManager = new CollectionManager(this);
        this.dataSource = dataSource;
        initializeCollectionMangerFromFile();
    }

    private void initializeCollectionMangerFromFile() {
        // Добаление коллекции из файла
        userORM = new ORM<>(dataSource, User.class);
        userORM.prepare();
        try {
            userORM.createTables();
            users.addAll(userORM.getObjects());
            collectionManager.setGroupORM(userORM.getORMForClass(StudyGroup.class));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
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
        commands.add(new LogIn(collectionManager));
        commands.add(new Register(collectionManager));
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
        }
    }

    public void println(String string) {
        if (Objects.nonNull(logger)) logger.info(string);
        else System.out.println(string);
    }

    public void addUser(User user) {
        try {
            userORM.save(user);
            users.add(user);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public boolean isUser(User o) {
        return users.stream()
                .filter(user -> user.getLogin().equals(o.getLogin()))
                .findFirst()
                .map(user -> checkPassword(user, o)).orElse(false);
    }

    public boolean checkPassword(User user1, User user2) {
        return user1.getPassword().equals(user2.getPassword());
    }

    // Getter and Setters
    public List<Command> getCommands() {
        return Collections.unmodifiableList(commands);
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }
}
