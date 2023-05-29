package app;

import commands.commandDescriptions.CommandDescription;
import commands.commandDescriptions.CommandDescriptionFactory;
import exceptions.BadArgumentException;
import managers.network.NetworkManagerInterface;
import model.User;
import response.Response;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class App {
    private final NetworkManagerInterface<CommandDescription, Response> network;

    private User user;

    private final Map<String, Integer> openScripts = new HashMap<>();
    private boolean fileMode = false;

    public App(NetworkManagerInterface<CommandDescription, Response> network) {
        this.network = network;
    }

    public void interactive(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String command;
        CommandDescription commandDescription;
        Response response;
        while (true) {
            // Read command string
            if (fileMode || inputStream.available() > 0) {
                command = reader.readLine();
                if (command == null) return;
                try {
                    commandDescription = CommandDescriptionFactory.get(command, user, inputStream, this::println);
                    switch (commandDescription.getName()) {
                        case "exit":
                            return;
                        case "execute_script":
                            boolean tmpFileMode = fileMode;
                            String argument = (String) commandDescription.getObject();
                            if (openScripts.containsKey(argument)) {
                                if (openScripts.get(argument).equals(10)) {
                                    if (askAboutRecursion()) {
                                        openScripts.clear();
                                        openScripts.put(argument, 1);
                                        fileMode = true;
                                        interactive(new FileInputStream(argument));
                                        fileMode = tmpFileMode;
                                    } else {
                                        openScripts.remove(argument);
                                        continue;
                                    }
                                } else {
                                    openScripts.put(argument, openScripts.get(argument) + 1);
                                    fileMode = true;
                                    interactive(new FileInputStream(argument));
                                    fileMode = tmpFileMode;
                                }
                            } else {
                                openScripts.put(argument, 1);
                                fileMode = true;
                                interactive(new FileInputStream(argument));
                                fileMode = tmpFileMode;
                            }
                            continue;
//                            break;
                        case "login":
                            login(reader, commandDescription);
                            break;
                        case "register":
                            register(reader, commandDescription);
                            break;
                        default:
                            if (user == null) {
                                println("Необходимо авторизироваться!!!");
                                continue;
                            }
                            network.send(commandDescription);
                            while (!Objects.nonNull(response = network.receive())) {
                                if (askAboutResend()) network.send(commandDescription);
                                else break;
                            }
                            if (Objects.nonNull(response)) println(response.getMessage());
                    }
                } catch (BadArgumentException e) {
                    println(e.getMessage());
                }
            }
        }
    }

    public void println(String string) {
        System.out.println(string);
    }

    private void register(BufferedReader reader, CommandDescription commandDescription) throws IOException {
        println("Введите логин:");
        String login = reader.readLine();
        println("Введите пароль:");
        String password = reader.readLine();

        User user = new User(login, password);
        commandDescription = new CommandDescription("register", user);

        Response response;
        network.send(commandDescription);
        while (!Objects.nonNull(response = network.receive())) {
            if (askAboutResend()) network.send(commandDescription);
            else break;
        }
        println(response.getMessage());
        if (response.isSuccess()) this.user = user;
        else if (askAboutRegister()) register(reader, commandDescription);
    }

    private void login(BufferedReader reader, CommandDescription commandDescription) throws IOException {
        println("Введите логин:");
        String login = reader.readLine();
        println("Введите пароль:");
        String password = reader.readLine();

        User user = new User(login, password);
        commandDescription.setUser(user);

        Response response;
        network.send(commandDescription);
        while (!Objects.nonNull(response = network.receive())) {
            if (askAboutResend()) network.send(commandDescription);
            else return;
        }
        println(response.getMessage());
        if (response.isSuccess()) this.user = user;
        else if (askAboutRegister()) register(reader, commandDescription);
    }

    private boolean askAboutRecursion() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String userAnswer;
        while (true) {
            println("В файле присутствует рекурсия. Хотите выполнить еще 10 шагов? [Y/N] ");
            userAnswer = bufferedReader.readLine();
            if (userAnswer.equals("Y")) {
                return true;
            } else if (userAnswer.equals("N")) return false;
            else println("Не верный ответ.");
        }
    }

    private boolean askAboutResend() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String userAnswer;
        while (true) {
            println("Команда не дошла до сервера. Переотправить? [Y/N] ");
            userAnswer = bufferedReader.readLine();
            if (userAnswer.equals("Y")) {
                return true;
            } else if (userAnswer.equals("N")) return false;
            else println("Не верный ответ.");
        }
    }

    private boolean askAboutRegister() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String userAnswer;
        while (true) {
            println("Хотите зарегистрироваться? [Y/N] ");
            userAnswer = bufferedReader.readLine();
            if (userAnswer.equals("Y")) {
                return true;
            } else if (userAnswer.equals("N")) return false;
            else println("Не верный ответ.");
        }
    }
}
