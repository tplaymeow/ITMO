package app;

import commands.commandDescriptions.CommandDescription;
import exceptions.AnnotationException;
import exceptions.BadArgumentException;
import exceptions.EndOfFileException;
import exceptions.NoCommandException;
import managers.network.NetworkManagerInterface;
import response.Response;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class App {
    private final NetworkManagerInterface<CommandDescription, Response> network;

    private final Map<String, Integer> openScripts = new HashMap<>();
    private boolean fileMode = false;

    public App(NetworkManagerInterface<CommandDescription, Response> network) {
        this.network = network;
    }

    public void interactive(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        boolean commandDidSend;
        CommandDescription commandDescription;
        while (true) {
            // Read command
            commandDidSend = false;
            commandDescription = null;
            try {
                if (fileMode || inputStream.available() > 0) {
                    String line = reader.readLine();
                    if (line == null) return;

                    String commandName = line.split(" ", 2)[0];
                    String argument;
                    try { argument = line.split(" ", 2)[1]; }
                    catch (IndexOutOfBoundsException e) { argument = ""; }

                    // execute_script logic
                    boolean tmpFileMode = fileMode;
                    if (commandName.equals("execute_script")) {
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
                    } else if (commandName.equals("exit") && !fileMode) {
                        println("Завершение програмы");
                        System.exit(1);
                    }

                    commandDescription = CommandDescriptionFactory.getCommandDescription(commandName, argument, inputStream, this::println);
                    network.send(commandDescription);
                    commandDidSend = true;
                }
            } catch (IOException ignored) {
            } catch (NoCommandException | BadArgumentException | AnnotationException | InstantiationException | EndOfFileException | IllegalAccessException e) {
                println(e.getMessage());
            }

            Response response;
            while (commandDidSend) {
                if (Objects.nonNull(response = network.receive())) {
                    println(response.getMessage());
                    commandDidSend = false;
                    break;
                } else {
                    if (commandDidSend) {
                        if (askAboutResend()) {
                            network.send(commandDescription);
                        } else {
                            commandDidSend = false;
                            break;
                        }
                    }
                }
            }
        }
    }

    public void println(String string) {
        System.out.println(string);
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
}
