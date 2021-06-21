package app;

import commands.commands.*;
import managers.CollectionManager;
import managers.MultithreadingManager;
import managers.network.RequestWaiter;
import model.User;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class App {
    private final CollectionManager collectionManager;
    private final ArrayList<Command> commands = new ArrayList<>();
    private final RequestWaiter network;
    private final InputStream inputStream;
    private Logger logger;
    private final DataSource dataSource;
    private ArrayList<User> users = new ArrayList<>();


    public App(RequestWaiter network, InputStream inputStream, Logger logger, DataSource dataSource) {
        CollectionManager collectionManager1;
        this.logger = logger;
        this.inputStream = inputStream;
        this.network = network;
        collectionManager1 = null;
        try {
            collectionManager1 = new CollectionManager(this, dataSource);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        collectionManager = collectionManager1;
        this.dataSource = dataSource;
        initializeCollectionMangerFromFile();
    }

    private void initializeCollectionMangerFromFile() {
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

        network.setAction(commandDescription -> commands.stream()
                .filter(command -> command.getName().equals(commandDescription.getName()))
                .findFirst()
                .map(command -> command.execute(commandDescription))
                .orElse(null));

        MultithreadingManager.fixedThreadPoolSubmit(network);

        while (true) {
            // Чтение команд сервера
            try {
                if (inputStream.available() > 0) {
                    String line = reader.readLine();
                    if (line.equals("exit")) {
                        println("Завершение программы...");

                        System.exit(1);
                        break;
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
    }

    public void println(String string) {
        if (Objects.nonNull(logger)) logger.info(string);
        else System.out.println(string);
    }

    // Getter and Setters
    public List<Command> getCommands() {
        return Collections.unmodifiableList(commands);
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }
}
